package lt.gt.galaktika.model.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.FlightCommand;
import lt.gt.galaktika.core.GalaxyLocation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DFleetData;
import lt.gt.galaktika.model.entity.turn.DShipGroup;
import lt.gt.galaktika.model.exception.FleetContractException;
import lt.gt.math.Utils;

@Service
public class FleetsService {

	final static Logger LOG = LoggerFactory.getLogger(FleetsService.class);

	@Autowired
	@Qualifier("dao")
	IDAO dao;

	@Autowired
	IFleetDataDao dFleetDataDao;

	@Autowired
	IFleetDao dFleetDao;

	@Autowired
	NationService nationService;

	@Autowired
	ShipService shipService;

	@Autowired
	PlanetService planetService;

	@Autowired
	SpaceLocationService spaceLocationService;

	
	/**
	 * If fleetId is 0, then create new one, else update existing.
	 * 
	 * By contract, ships and planets should be created in database before this
	 * method call.
	 * 
	 * @param fleet
	 * @return
	 */

	public Fleet saveFleet(Fleet fleet, int turnNumber) throws FleetContractException {

		DFleet dFleet = mapDFleet(fleet);
		if (fleet.getFleetId() == 0) {
			dFleet = dao.create(dFleet);
			fleet.setFleetId(dFleet.getFleetId());
		} else
			dFleet = dao.update(dFleet);

		FleetContractData fcd = loadFleetContractData(fleet);

		DFleetData dFleetData = dFleetDataDao.findWithGroupsAndLocations(dFleet.getFleetId(), turnNumber);

		boolean found = true;
		if (dFleetData == null) {
			found = false;
			dFleetData = new DFleetData(fleet.getFleetId(), turnNumber);
		}

		dFleetData = mapDFleetData(dFleetData, fleet, turnNumber, fcd);

		storeGroups(dFleetData);
		storeLocation(dFleetData);

		if (found)
			dao.update(dFleetData);
		else
			dao.create(dFleetData);

		return fleet;
	}

	public Fleet loadFleet(long fleetId, int turnNumber) {
		DFleet dFleet = dFleetDao.find(fleetId);
		DFleetData dFleetData = dFleetDataDao.findWithGroupsAndLocations(fleetId, turnNumber);

		LOG.trace("dFleetData=" + dFleetData);
		return mapFleet(dFleet, dFleetData);
	}

	// ================================================================================================================
	// ==================== inner code ================================================================================
	// ================================================================================================================
	
	public class FleetContractData {
		Map<Long, DShip> ships = new HashMap<>();
		Map<Long, DPlanet> planets = new HashMap<>();

		public void loadDPlanet(GalaxyLocation gl) throws FleetContractException {
			if (gl != null && gl instanceof Planet) {
				Planet p = (Planet) gl;
				DPlanet dp = dao.find(DPlanet.class, p.getPlanetId());
				if (dp == null)
					throw new FleetContractException("could not load planet " + p);
				planets.put(p.getPlanetId(), dp);
			}
		}

		public void loadDShip(Ship s) throws FleetContractException {
			if (s != null) {
				DShip ds = dao.find(DShip.class, s.getId());
				if (ds == null)
					throw new FleetContractException("could not load ship with id=" + s.getId());
				ships.put(s.getId(), ds);
			}
		}

		public DPlanet findPlanet(GalaxyLocation gl) {
			if (gl != null && gl instanceof Planet) {
				Planet p = (Planet) gl;
				return planets.get(p.getPlanetId());
			} else
				return null;
		}

		public DShip findShip(Ship s) {
			if (s != null)
				return ships.get(s.getId());
			else
				return null;
		}

	}

	
	private void storeGroups(DFleetData dFleetData) {
		dFleetData.getShipGroups().forEach(g -> {
			if (g.getShipGroupId() == 0)
				dao.create(g);
			else
				dao.update(g);
		});
	}

	private void storeLocation(DFleetData dFleetData) {
		if (dFleetData.getSpaceLocation() != null) {
			if (dFleetData.getSpaceLocation().getLocationId() == 0)
				dFleetData.setSpaceLocation(dao.create(dFleetData.getSpaceLocation()));
			else
				dao.update(dFleetData.getSpaceLocation());
		}
	}

	protected FleetContractData loadFleetContractData(Fleet fleet) throws FleetContractException {
		FleetContractData fcd = new FleetContractData();
		// load planets
		fcd.loadDPlanet(fleet.getGalaxyLocation());
		fcd.loadDPlanet(fleet.getFlightSource());
		fcd.loadDPlanet(fleet.getFlightDestination());

		// load ships
		for (ShipGroup g : fleet.getShipGroups())
			fcd.loadDShip(g.getShip());

		return fcd;
	}

	protected DFleet mapDFleet(Fleet fleet) {
		DFleet dFleet = null;

		if (fleet.getFleetId() == 0)
			dFleet = new DFleet();
		else
			dFleet = dao.find(DFleet.class, fleet.getFleetId());

		dFleet.setName(fleet.getName());
		if (fleet.getOwner() != null) {
			DNation dNation = dao.find(DNation.class, fleet.getOwner().getNationId());
			LOG.trace("received dNation=" + dNation);
			dFleet.setNation(dNation);
		}
		return dFleet;
	}

	/**
	 * Maps DFleetData object for storing to db.
	 * 
	 * @param dFleetData
	 * @param fleet
	 * @param turnNumber
	 * @param fcd
	 * @return
	 * @throws FleetContractException
	 */
	protected DFleetData mapDFleetData(DFleetData dFleetData, Fleet fleet, int turnNumber, FleetContractData fcd)
			throws FleetContractException {
		// put existing ship groups to map by shipid
		Map<Long, DShipGroup> shipidToDShipGroup = new HashMap<>();
		for (DShipGroup g : dFleetData.getShipGroups())
			shipidToDShipGroup.put(g.getShip().getId(), g);

		dFleetData.getShipGroups().clear();

		// for each fleet ship group, find or create dFleetGroup
		for (ShipGroup g : fleet.getShipGroups()) {
			DShipGroup dg = shipidToDShipGroup.get(g.getShip().getId());
			if (dg == null) {
				LOG.trace("creating new DShipGroup");
				DShip dShip = fcd.findShip(g.getShip());
				if (dShip == null)
					throw new FleetContractException("no ship with id=" + g.getShip().getId() + " found");
				dg = new DShipGroup(dShip);
			}

			dg.setShipsCount(g.getCount());
			dFleetData.getShipGroups().add(dg);
		}

		// location
		if (fleet.getGalaxyLocation() instanceof Planet)
			dFleetData.setPlanetLocation(fcd.findPlanet(fleet.getGalaxyLocation()));
		else if (fleet.getGalaxyLocation() instanceof SpaceLocation) {
			if (dFleetData.getSpaceLocation() == null
					|| Utils.same(fleet.getGalaxyLocation(), dFleetData.getSpaceLocation().getX(),
							dFleetData.getSpaceLocation().getY(), lt.gt.galaktika.utils.Utils.EPSILON))
				dFleetData.setSpaceLocation(
						spaceLocationService.mapToDbObject((SpaceLocation) fleet.getGalaxyLocation()));
		}

		// flightCommand
		dFleetData.setFlightSource(fcd.findPlanet(fleet.getFlightSource()));
		dFleetData.setFlightDestination(fcd.findPlanet(fleet.getFlightDestination()));

		return dFleetData;
	}

	/**
	 * Maps fleet from DFleetData, after loading from db.
	 * 
	 * @param dFleet
	 * @param dFleetData
	 * @return
	 */
	protected Fleet mapFleet(DFleet dFleet, DFleetData dFleetData) {
		Fleet fleet = new Fleet();
		fleet.setName(dFleet.getName());
		fleet.setFleetId(dFleet.getFleetId());
		fleet.setOwner(nationService.mapToCoreObject(dFleet.getNation()));

		if (dFleetData != null) {
			// dFleetData fields
			for (DShipGroup dg : dFleetData.getShipGroups()) {
				Ship ship = shipService.mapToCoreObject(dg.getShip());
				ShipGroup group = new ShipGroup(ship, dg.getShipsCount());
				fleet.addShipGroup(group);
			}

			// location
			if (dFleetData.getPlanetLocation() != null) {
				Planet p = planetService.mapToCoreObject(dFleetData.getPlanetLocation());
				fleet.setGalaxyLocation(p);
			} else if (dFleetData.getSpaceLocation() != null) {
				SpaceLocation l = spaceLocationService.mapToCoreObject(dFleetData.getSpaceLocation());
				fleet.setGalaxyLocation(l);
			}
			if (dFleetData.getFlightSource() != null && dFleetData.getFlightDestination() != null) {
				FlightCommand fCommand = new FlightCommand();
				// flight source
				fCommand.setSource(planetService.mapToCoreObject(dFleetData.getFlightSource()));
				// flight destination
				fCommand.setDestination(planetService.mapToCoreObject(dFleetData.getFlightDestination()));

				fleet.setFlightCommand(fCommand);
			}
		}

		return fleet;
	}
}
