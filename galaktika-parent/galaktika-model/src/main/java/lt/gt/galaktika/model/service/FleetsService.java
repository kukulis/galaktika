package lt.gt.galaktika.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.FlightCommand;
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
	 * Also the contract is, that all the ships in fleet should be already
	 * stored to repository, previous of this method call.
	 * 
	 * @param fleet
	 * @return
	 */
	public Fleet saveFleet(Fleet fleet, int turnNumber) {

		// assume that fleet components are already stored to db.
		// need only to store the fleet himself
		// but we build DFleet object and we dont have the D* components of the
		// DFleet,
		// so we need to load them
		// then store DFleet
		// then return Fleet with the new stored fleet id

		// TODO

		DFleet dFleet = mapDFleet(fleet);
		if (fleet.getFleetId() == 0) {
			dFleet = dao.create(dFleet);
			fleet.setFleetId(dFleet.getFleetId());
		} else
			dFleet = dao.update(dFleet);

		// need to try to load fleet data with fleet id and turn number

		DFleetData dFleetData = mapDFleetData(fleet, turnNumber);
		dFleetData.getShipGroups().forEach(g -> dao.create(g));
		dao.create(dFleetData);
		LOG.trace("Fleet data stored with fleetId=" + dFleetData.getFleetId() + "  turnNumber="
				+ dFleetData.getTurnNumber());
		// TODO two ways
		// one - create absolutely new DFleetData object
		// second - update existing DFleetData object
		// TODO

		return fleet;
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

	
	protected DFleetData mapDFleetData(Fleet fleet, int turnNumber) {
		// TODO move access to dao somehow outside ( may be prepare needed data before and pass by parameters )
		DFleetData dFleetData = new DFleetData(fleet.getFleetId(), turnNumber);

		// shipGroups
		for (ShipGroup group : fleet.getShipGroups()) {
			DShip dship = dao.find(DShip.class, group.getShip().getId());
			DShipGroup dg = new DShipGroup(dship);
			dg.setShipsCount(group.getCount());
			dg.setTurnNumber(turnNumber);
			dFleetData.getShipGroups().add(dg);

		}
		if (fleet.getGalaxyLocation() instanceof Planet) {
			// planetLocation
			Planet planetLocation = (Planet) fleet.getGalaxyLocation();
			dFleetData.setPlanetLocation(loadOrMapPlanet(planetLocation));
		} else if (fleet.getGalaxyLocation() instanceof SpaceLocation) {
			// spaceLocation
			SpaceLocation location = (SpaceLocation) fleet.getGalaxyLocation();
			DSpaceLocation dSpaceLocation = dao.find(DSpaceLocation.class, location.getLocationId());
			if (dSpaceLocation == null)
				dSpaceLocation = spaceLocationService.mapToDbObject(location);

			dFleetData.setSpaceLocation(dSpaceLocation);
		}
		// flightCommand

		dFleetData.setFlightSource(loadOrMapPlanet((Planet) fleet.getFlightSource()));
		dFleetData.setFlightDestination(loadOrMapPlanet((Planet) fleet.getFlightDestination()));

		return dFleetData;
	}

	protected DPlanet loadOrMapPlanet(Planet p) {
		if (p == null)
			return null;

		DPlanet dPlanetLocation = dao.find(DPlanet.class, p.getPlanetId());
		if (dPlanetLocation == null)
			dPlanetLocation = planetService.mapToDbObject(p);

		return dPlanetLocation;
	}

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
