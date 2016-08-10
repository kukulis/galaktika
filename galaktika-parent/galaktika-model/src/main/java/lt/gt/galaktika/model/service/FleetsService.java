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
import lt.gt.galaktika.model.entity.noturn.DSpaceLocation;
import lt.gt.galaktika.model.entity.turn.DFleetData;
import lt.gt.galaktika.model.entity.turn.DShipGroup;
import lt.gt.galaktika.model.exception.FleetContractException;

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

	public class FleetContractData {

		// contract data
		Map<Long, DShip> ships = new HashMap<>();
		Map<Long, DPlanet> planets = new HashMap<>();

		// noncontract data (helps to separate dao calls)
		Map<SpaceLocation, DSpaceLocation> spaceLocations = new HashMap<>();

		public void loadDPlanet(GalaxyLocation gl) throws FleetContractException {
			if (gl != null && gl instanceof Planet) {
				Planet p = (Planet) gl;
				DPlanet dp = dao.find(DPlanet.class, p.getPlanetId());
				if (dp == null)
					throw new FleetContractException("could not load planet " + p );
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

		// ==== noncontract loads
		/**
		 * 
		 * @param l
		 */
		public void loadOrCreateDSpaceLocation(GalaxyLocation l) {
			if (l instanceof SpaceLocation) {
				SpaceLocation location = (SpaceLocation) l;
				// SpaceLocation location = (SpaceLocation)
				// fleet.getGalaxyLocation();
				DSpaceLocation dSpaceLocation = dao.find(DSpaceLocation.class, location.getLocationId());
				if (dSpaceLocation == null)
					dSpaceLocation = dao.create( spaceLocationService.mapToDbObject(location));

				spaceLocations.put(location, dSpaceLocation);
			}
		}

		public DSpaceLocation findSpaceLocation(GalaxyLocation l) {
			return spaceLocations.get(l);
		}

	}

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

		// need to try to load fleet data with fleet id and turn number

		DFleetData dFleetData = mapDFleetData(fleet, turnNumber, fcd);
		dFleetData.getShipGroups().forEach(g -> dao.create(g));

		dao.create(dFleetData);
		LOG.trace("Fleet data stored with fleetId=" + dFleetData.getFleetId() + "  turnNumber="
				+ dFleetData.getTurnNumber());
		
		// TODO update case

		return fleet;
	}

	public FleetContractData loadFleetContractData(Fleet fleet) throws FleetContractException {
		FleetContractData fcd = new FleetContractData();
		// load planets
		fcd.loadDPlanet(fleet.getGalaxyLocation());
		fcd.loadDPlanet(fleet.getFlightSource());
		fcd.loadDPlanet(fleet.getFlightDestination());

		// load ships
		for (ShipGroup g : fleet.getShipGroups())
			fcd.loadDShip(g.getShip());
		
		// non contract
		fcd.loadOrCreateDSpaceLocation( fleet.getGalaxyLocation() );

		return fcd;
	}

	public Fleet loadFleet(long fleetId, int turnNumber) {
		DFleet dFleet = dFleetDao.find(fleetId);
		DFleetData dFleetData = dFleetDataDao.findWithGroups(fleetId, turnNumber);

		LOG.trace("dFleetData=" + dFleetData);
		return mapFleet(dFleet, dFleetData);
	}

	// public Fleet updateFleet ( Fleet fleet ) {
	// // TODO similar to createFleet
	//
	// return fleet;
	// }

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

	protected DFleetData mapDFleetData(Fleet fleet, int turnNumber, FleetContractData fcd) {
		DFleetData dFleetData = new DFleetData(fleet.getFleetId(), turnNumber);

		// shipGroups
		for (ShipGroup group : fleet.getShipGroups()) {
			DShip dship = fcd.findShip(group.getShip());
			DShipGroup dg = new DShipGroup(dship);
			dg.setShipsCount(group.getCount());
			dg.setTurnNumber(turnNumber);
			dFleetData.getShipGroups().add(dg);
		}
		
		// location
		if (fleet.getGalaxyLocation() instanceof Planet)
			fcd.findPlanet(fleet.getGalaxyLocation());
		else if (fleet.getGalaxyLocation() instanceof SpaceLocation)
			dFleetData.setSpaceLocation(fcd.findSpaceLocation( fleet.getGalaxyLocation()));

		// flightCommand
		dFleetData.setFlightSource(fcd.findPlanet(fleet.getFlightSource()));
		dFleetData.setFlightDestination(fcd.findPlanet(fleet.getFlightDestination()));

		return dFleetData;
	}

//	/**
//	 * @deprecated the planet should be always in the db, due contract.
//	 * @param p
//	 * @return
//	 */
//	protected DPlanet loadOrMapPlanet(Planet p) {
//		if (p == null)
//			return null;
//
//		DPlanet dPlanetLocation = dao.find(DPlanet.class, p.getPlanetId());
//		if (dPlanetLocation == null)
//			dPlanetLocation = planetService.mapToDbObject(p);
//
//		return dPlanetLocation;
//	}

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
