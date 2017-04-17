package lt.gt.galaktika.model.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetData;
import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.core.planet.ShipFactory;
import lt.gt.galaktika.core.planet.SurfaceActivities;
import lt.gt.galaktika.core.planet.SurfaceCommand;
import lt.gt.galaktika.core.planet.SurfaceCommandIndustry;
import lt.gt.galaktika.core.planet.SurfaceCommandProduction;
import lt.gt.galaktika.core.planet.SurfaceCommandTechnologies;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.dao.IPlanetSurfaceDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;
import lt.gt.galaktika.model.entity.turn.DFleetData;
import lt.gt.galaktika.model.entity.turn.DPlanetSurface;
import lt.gt.galaktika.model.entity.turn.DShipFactory;
import lt.gt.galaktika.model.entity.turn.DSurfaceCommand;
import lt.gt.galaktika.model.entity.turn.DTechnologies;
import lt.gt.galaktika.model.exception.FleetContractException;
import lt.gt.galaktika.model.exception.GalaktikaModelException;

@Service
public class PlanetDataService {
	final static Logger LOG = LoggerFactory.getLogger(PlanetDataService.class);

	@Autowired
	@Qualifier("dao")
	IDAO dao;

	@Autowired
	IFleetDataDao dFleetDataDao;

	@Autowired
	FleetsService fleetsService;

	@Autowired
	IPlanetSurfaceDao dPlanetSurfaceDao;

	@Autowired
	NationService nationService;

	@Autowired
	ShipService shipService;

	@Autowired
	ShipDesignService shipDesignService;

	@Autowired
	TechnologiesService technologiesService;
	
	@Autowired
	PlanetService planetService;
	
	
	public PlanetOrbit loadPlanetOrbit(long planetId, int turnNumber, boolean withGroups) {
		List<DFleetData> fleetDatas = dFleetDataDao.findInOrbit(planetId, turnNumber, withGroups);

		PlanetOrbit orbit = new PlanetOrbit();

		for (DFleetData dFleetData : fleetDatas)
			orbit.getFleets().add(fleetsService.mapFleet(dFleetData.getFleet(), withGroups ? dFleetData : null));

		return orbit;
	}

	public void storePlanetSurface(PlanetSurface planetSurface, Planet planet, int turnNumber) {
		DPlanetSurface dPlanetSurface = mapToDPlanetSurface(planetSurface, planet, turnNumber);
		fullSaveDPlanetSurface(dPlanetSurface);
	}

	/**
	 * Maps to DPlanetSurface *without* accessing outside resources.
	 * 
	 * @param surface
	 * @param planet
	 * @param turnNumber
	 * @return
	 */
	public DPlanetSurface mapToDPlanetSurface(PlanetSurface planetSurface, Planet planet, int turnNumber) {
		DPlanetSurface dPlanetSurface = new DPlanetSurface(planet.getPlanetId(), turnNumber);
		dPlanetSurface.setName(planetSurface.getName());
		dPlanetSurface.setPopulation(planetSurface.getPopulation());
		dPlanetSurface.setIndustry(planetSurface.getIndustry());
		dPlanetSurface.setCapital(planetSurface.getCapital());
		dPlanetSurface.setOwner(nationService.mapToDbObject(planetSurface.getNation()));

		if (planetSurface.getSurfaceCommand() != null) {
			DSurfaceCommand dCommand = mapDSurfaceCommand(planetSurface.getSurfaceCommand(), planet.getPlanetId(),
					turnNumber);
			if (dPlanetSurface.getCommands().size() == 0)
				dPlanetSurface.getCommands().add(dCommand);
			else {
				dPlanetSurface.getCommands().clear();
				dPlanetSurface.getCommands().add(dCommand);
			}
		}

		if (planetSurface.getShipFactory() != null) {
			DShipFactory dFactory = mapDFactory(planetSurface.getShipFactory(), planet.getPlanetId(), turnNumber);

			if (dPlanetSurface.getShipFactories().size() == 0)
				dPlanetSurface.getShipFactories().add(dFactory);
			else {
				dPlanetSurface.getShipFactories().clear();
				dPlanetSurface.getShipFactories().add(dFactory);
			}
		}
		// TODO remaining fields. Which ones?

		return dPlanetSurface;
	}

	/**
	 * Fills the DPlanetSurface by loading data from outside resources, but does
	 * *no* writing to outside resources.
	 * 
	 * @param dPlanetSurface
	 * @return
	 */
	protected DPlanetSurface upfillDPlanetSurface(DPlanetSurface dPlanetSurface) {
		dPlanetSurface.setOwner(dao.find(DNation.class, dPlanetSurface.getOwner().getNationId()));
		LOG.trace("after upfilling owner");

		// other presaved data
		for (DSurfaceCommand dcommand : dPlanetSurface.getCommands()) {
			DShipDesign design = dcommand.getDesign();
			if (design != null && !dao.contains(design)) {
				DShipDesign foundDesign = dao.find(DShipDesign.class, design.getDesignId());
				if (foundDesign == null)
					throw new GalaktikaModelException(
							String.format("Ship design with id %d was not found. It must be created before saving the factory, by contract.", design.getDesignId()));
				dcommand.setDesign(foundDesign);
			}
			DTechnologies technologies = dcommand.getTechnologies();
			if (technologies != null && !dao.contains(technologies)) {
				DTechnologies foundTechnologies = dao.find(DTechnologies.class, technologies.getTechnologiesId());
				if (foundTechnologies == null)
					throw new GalaktikaModelException(
							String.format("Technologies with id %d was not found", technologies.getTechnologiesId()));
			}
		}

		for (DShipFactory f : dPlanetSurface.getShipFactories()) {
			if (f.getShip() != null) {
				DShip foundShip = dao.find(DShip.class, f.getShip().getId());
				if (foundShip != null)
					f.setShip(foundShip);
			}
		}

		return dPlanetSurface;
	}

	protected boolean fullSaveDPlanetSurface(DPlanetSurface dPlanetSurface) {
		upfillDPlanetSurface(dPlanetSurface);

		// clearing before saving, to prevent bulk save
		Set<DSurfaceCommand> commands = new HashSet<>();
		commands.addAll(dPlanetSurface.getCommands());
		dPlanetSurface.getCommands().clear();

		// clearing before saving, to prevent bulk save
		Set<DShipFactory> factories = new HashSet<>();
		factories.addAll(dPlanetSurface.getShipFactories());
		dPlanetSurface.getShipFactories().clear();

		// must create planet surface before commands
		dPlanetSurfaceDao.deleteDSurfaceCommands(dPlanetSurface.getPlanetId(), dPlanetSurface.getTurnNumber());
		dPlanetSurfaceDao.deleteDShipFactories(dPlanetSurface.getPlanetId(), dPlanetSurface.getTurnNumber());
		dPlanetSurfaceDao.delete(dPlanetSurface.getPlanetId(), dPlanetSurface.getTurnNumber());
		
		dao.create(dPlanetSurface);

		// command
		for (DSurfaceCommand sc : commands)
			dPlanetSurface.getCommands().add(dao.create(sc));
		LOG.trace("after storing commands");

		for (DShipFactory f : factories) {
//			if (!dao.contains(f.getShip())) // ship is created by contract, no need to create it here
//				dao.create(f.getShip());
			dPlanetSurface.getShipFactories().add(dao.create(f));
		}
		LOG.trace("after storing factories");

		return true;
	}


	public DSurfaceCommand mapDSurfaceCommand(SurfaceCommand surfaceCommand, long planetId, int turnNumber) {
		DSurfaceCommand dCommand = new DSurfaceCommand();
		dCommand.setPlanetId(planetId);
		dCommand.setTurnNumber(turnNumber);
		dCommand.setActivity(surfaceCommand.getActivityType());

		if (surfaceCommand instanceof SurfaceCommandIndustry) {
			// nothing
		} else if (surfaceCommand instanceof SurfaceCommandTechnologies) {
			SurfaceCommandTechnologies tCommand = (SurfaceCommandTechnologies) surfaceCommand;
			dCommand.setTechnologyToUpgrade(tCommand.getTechnologyToUpgrade());
		} else if (surfaceCommand instanceof SurfaceCommandProduction) {
			SurfaceCommandProduction pCommand = (SurfaceCommandProduction) surfaceCommand;
			dCommand.setDesign(shipDesignService.mapToDbObject(pCommand.getShipDesign()));
			dCommand.setTechnologies(technologiesService.mapToDbObject(pCommand.getTechnologies()));
			dCommand.setMaxShips(pCommand.getMaxShips());
		}
		return dCommand;
	}

	public SurfaceCommand mapSurfaceCommand(DSurfaceCommand dCommand) {
		if (SurfaceActivities.INDUSTRY.equals(dCommand.getActivity())) {
			SurfaceCommandIndustry command = new SurfaceCommandIndustry();
			return command;
		} else if (SurfaceActivities.PRODUCTION.equals(dCommand.getActivity())) {
			SurfaceCommandProduction command = new SurfaceCommandProduction();
			command.setMaxShips(dCommand.getMaxShips());
			command.setShipDesign(shipDesignService.mapToCoreObject(dCommand.getDesign()));
			command.setTechnologies(technologiesService.mapToCoreObject(dCommand.getTechnologies()));
			return command;
		} else if (SurfaceActivities.TECHNOLOGIES.equals(dCommand.getActivity())) {
			SurfaceCommandTechnologies command = new SurfaceCommandTechnologies();
			command.setTechnologyToUpgrade(dCommand.getTechnologyToUpgrade());
			return command;
		} else {
			return null;
		}
	}

	public DShipFactory mapDFactory(ShipFactory factory, long planetId, int turnNumber) {
		DShipFactory dFactory = new DShipFactory(planetId, turnNumber);
		dFactory.setDonePart(factory.getDonePart());
		dFactory.setDesign(shipDesignService.mapToDbObject(factory.getShipDesign()));
		dFactory.setTechnologies(technologiesService.mapToDbObject(factory.getTechnologies()));
		dFactory.setShip(shipService.mapToDbObject(factory.getShip()));

		return dFactory;
	}

	public ShipFactory mapFactory(DShipFactory dFactory) {
		ShipFactory factory = new ShipFactory();
		factory.setDonePart(dFactory.getDonePart());
		dao.detach( dFactory.getShip() );
		factory.setShip(shipService.mapToCoreObject(dFactory.getShip()));
		factory.setShipDesign(shipDesignService.mapToCoreObject(dFactory.getDesign()));
		factory.setTechnologies(technologiesService.mapToCoreObject(dFactory.getTechnologies()));
		return factory;
	}

	public PlanetSurface loadPlanetSurface(long planetId, int turnNumber) {
		DPlanetSurface dPS = null;
		try {
			dPS = dPlanetSurfaceDao.find(planetId, turnNumber);
		} catch ( EmptyResultDataAccessException e ) {
			LOG.debug("No surface for planet "+planetId+" turn="+turnNumber );
			return null;
		}

		PlanetSurface surface = new PlanetSurface();
		surface.setName(dPS.getName());
		surface.setPopulation(dPS.getPopulation());
		surface.setIndustry(dPS.getIndustry());
		surface.setCapital(dPS.getCapital());
		surface.setNation(nationService.mapToCoreObject(dPS.getOwner()));

		LOG.trace("factories amount = " + dPS.getShipFactories().size());
		if (dPS.getShipFactories().size() > 0)
			surface.setShipFactory(mapFactory((DShipFactory) dPS.getShipFactories().toArray()[0]));

		LOG.trace("commands amount = " + dPS.getCommands().size());
		if (dPS.getCommands().size() > 0)
			surface.setSurfaceCommand(mapSurfaceCommand((DSurfaceCommand) dPS.getCommands().toArray()[0]));

		return surface;
	}
	
	public List<Long> findPlanetsIds ( Nation n, int turnNumber) {
		return dPlanetSurfaceDao.findPlanets(n.getNationId(), turnNumber);
	}

	public PlanetData loadPlanetData ( long planetId, int turn ) {
		return new PlanetData(
			planetService.load( planetId ),
			loadPlanetSurface(planetId, turn),
			loadPlanetOrbit(planetId, turn, false) );
	}
	
	public void storeOrbit ( PlanetData planetData, int turnNumber ) throws FleetContractException {
		for ( Fleet f : planetData.getOrbit().getFleets() ) {
			f.setGalaxyLocation( planetData.getPlanet());
			fleetsService.saveFleet(f, turnNumber);
		}
	}
	
	public void storeNoTurnPlanetSurfaceObjects( PlanetSurface planetSurface) {
		// store ships, ship designs, and write them back to planetData with ids
		Ship ship = planetSurface.getShipFactory().getShip();
		ShipDesign shipDesign = planetSurface.getShipFactory().getShipDesign();
		
		// 1) Find the ship
		// a) Find by id
		// b) Find by other fields:
		//     name, attack, guns, defense, cargo, speed, totalMass
		// if not found create it
		
		Ship loadedShip = null;
		if ( ship.getId() != 0)
			loadedShip = shipService.load(ship.getId());
		if ( loadedShip == null ) {
			loadedShip = shipService.findShip ( ship.getName(), ship.getAttack(), ship.getGuns(), ship.getDefence(), ship.getCargo(), ship.getSpeed(), ship.getTotalMass() );
			if ( loadedShip == null ) 
				loadedShip = shipService.create( ship ); 
			planetSurface.getShipFactory().setShip( loadedShip );
		}
		
		// 2) Find the ship design
		// a) Find by id
		// b) Find by other fields:
		//  name, attack, guns, defense, cargo, engines
		// if not found, create it

		ShipDesign loadedShipDesign = null;
		if ( shipDesign.getDesignId() != 0)
			loadedShipDesign = shipDesignService.load( shipDesign.getDesignId() );
		if ( loadedShipDesign == null ) {
			loadedShipDesign = shipDesignService.findShipDesign ( shipDesign.getDesignName(), shipDesign.getAttackMass(), shipDesign.getGuns(), shipDesign.getDefenceMass(), shipDesign.getCargoMass(), shipDesign.getEngineMass(), planetSurface.getNation() );
			if ( loadedShipDesign == null )
				loadedShipDesign = shipDesignService.createShipDesign(loadedShipDesign, planetSurface.getNation());
			planetSurface.getShipFactory().setShipDesign( loadedShipDesign );
		}
		
	}
	
}


