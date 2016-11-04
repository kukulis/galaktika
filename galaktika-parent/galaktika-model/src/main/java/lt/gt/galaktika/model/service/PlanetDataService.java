package lt.gt.galaktika.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.core.planet.PlanetSurface;
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
import lt.gt.galaktika.model.exception.PlanetSurfaceContractException;

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

	public PlanetOrbit loadPlanetOrbit(long planetId, int turnNumber, boolean withGroups) {
		List<DFleetData> fleetDatas = dFleetDataDao.findInOrbit(planetId, turnNumber, withGroups);

		PlanetOrbit orbit = new PlanetOrbit();

		for (DFleetData dFleetData : fleetDatas)
			orbit.getFleets().add(fleetsService.mapFleet(dFleetData.getFleet(), withGroups ? dFleetData : null));

		return orbit;
	}

	/**
	 * Contract: planet should be already stored to database; nation should be
	 * already stored to database; DShip for factory , DShipDesign for factory
	 * and command, DTechnologies for factory and for command should be already
	 * stored to database.
	 * 
	 * @param surface
	 * @param planet
	 * @param turnNumber
	 * @deprecated implementing in different way with storePlanetSurface2
	 */
	public void storePlanetSurface(PlanetSurface surface, Planet planet, int turnNumber)
			throws PlanetSurfaceContractException {
		DPlanetSurface dPlanetSurface = new DPlanetSurface(planet.getPlanetId(), turnNumber);
		PlanetContractData pContract = new PlanetContractData();
		pContract.loadContractData(surface);
		dPlanetSurface.setName(surface.getName());
		dPlanetSurface.setPopulation(surface.getPopulation());
		dPlanetSurface.setIndustry(surface.getIndustry());
		dPlanetSurface.setCapital(surface.getCapital());
		dPlanetSurface.setOwner(pContract.getNation());
		
		dPlanetSurface = dao.create(dPlanetSurface);

		if (surface.getSurfaceCommand() != null) {
			DSurfaceCommand dCommand = dao.create(mapDSurfaceCommand(surface.getSurfaceCommand(), planet.getPlanetId(), turnNumber,
					pContract));
			if (dPlanetSurface.getCommands().size() == 0)
				dPlanetSurface.getCommands().add(dCommand);
			else {
				dPlanetSurface.getCommands().clear();
				dPlanetSurface.getCommands().add( dCommand);
			}
		}

		if (surface.getShipFactory() != null) {
			DShipFactory dFactory = dao.create(mapDFactory(surface.getShipFactory(), pContract));
			
			if (dPlanetSurface.getShipFactories().size() == 0)
				dPlanetSurface.getShipFactories().add(dFactory);
			else {
				dPlanetSurface.getShipFactories().clear();
				dPlanetSurface.getShipFactories().add( dFactory);
			}
		}
		
		dao.update( dPlanetSurface );

		

		// TODO the update variant

	}
	
	public void storePlanetSurface2 (PlanetSurface planetSurface, Planet planet, int turnNumber ) {
		// TODO
		DPlanetSurface dPlanetSurface = mapToDPlanetSurface2( planetSurface, planet,turnNumber );
		upfillDPlanetSurface( dPlanetSurface );
		fullSaveDPlanetSurface ( dPlanetSurface );
	}
	
	
	/**
	 * Maps to DPlanetSurface *without* accessing outside resources.
	 * 
	 * @param surface
	 * @param planet
	 * @param turnNumber
	 * @return
	 */
	public DPlanetSurface mapToDPlanetSurface2 (PlanetSurface planetSurface, Planet planet, int turnNumber) {
//		DPlanetSurface dPlanetSurface = new DPlanetSurface();
		
		DPlanetSurface dPlanetSurface = new DPlanetSurface(planet.getPlanetId(), turnNumber);
//		PlanetContractData pContract = new PlanetContractData();
//		pContract.loadContractData(planetSurface);
		dPlanetSurface.setName(planetSurface.getName());
		dPlanetSurface.setPopulation(planetSurface.getPopulation());
		dPlanetSurface.setIndustry(planetSurface.getIndustry());
		dPlanetSurface.setCapital(planetSurface.getCapital());
		dPlanetSurface.setOwner(nationService.mapToDbObject( planetSurface.getNation()));
		
		if (planetSurface.getSurfaceCommand() != null) {
			DSurfaceCommand dCommand = mapDSurfaceCommand2(planetSurface.getSurfaceCommand(), planet.getPlanetId(), turnNumber );
			if (dPlanetSurface.getCommands().size() == 0)
				dPlanetSurface.getCommands().add(dCommand);
			else {
				dPlanetSurface.getCommands().clear();
				dPlanetSurface.getCommands().add( dCommand);
			}
		}

		if ( planetSurface.getShipFactory() != null) {
			DShipFactory dFactory = mapDFactory2(planetSurface.getShipFactory());
			
			if (dPlanetSurface.getShipFactories().size() == 0)
				dPlanetSurface.getShipFactories().add(dFactory);
			else {
				dPlanetSurface.getShipFactories().clear();
				dPlanetSurface.getShipFactories().add( dFactory);
			}
		}
//		TODO remaining fields
		
		return dPlanetSurface;
	}

	/**
	 * Fills the DPlanetSurface by loading data from outside resources, but does *no* writing to outside resources.
	 * @param dPlanetSurface
	 * @return
	 */
	public DPlanetSurface upfillDPlanetSurface (DPlanetSurface dPlanetSurface ) {
		// TODO
		return dPlanetSurface;
	}
	
	public boolean fullSaveDPlanetSurface ( DPlanetSurface dPlanetSurface ) {
		
		// TODO check if needed separate savings
		
		dao.create( dPlanetSurface );
		return true;
	}

	/**
	 * @deprecated will implement with different way mapDSurfaceCommand2
	 * @param surfaceCommand
	 * @param planetId
	 * @param turnNumber
	 * @param pContract
	 * @return
	 */
	public DSurfaceCommand mapDSurfaceCommand(SurfaceCommand surfaceCommand, long planetId, int turnNumber,
			PlanetContractData pContract) {
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
			dCommand.setDesign(pContract.getDesign());
			dCommand.setTechnologies(pContract.getTechnologies());
			dCommand.setMaxShips(pCommand.getMaxShips());
		}

		return dCommand;
	}
	
	public DSurfaceCommand mapDSurfaceCommand2(SurfaceCommand surfaceCommand, long planetId, int turnNumber) {
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
			dCommand.setDesign( shipDesignService.mapToDbObject( pCommand.getShipDesign() ));
			dCommand.setTechnologies( technologiesService.mapToDbObject( pCommand.getTechnologies() ) );
			dCommand.setMaxShips(pCommand.getMaxShips());
		}
		return dCommand;
	}
	
	
	public SurfaceCommand mapSurfaceCommand ( DSurfaceCommand dCommand ) {
		if( SurfaceActivities.INDUSTRY.equals( dCommand.getActivity() )) {
			SurfaceCommandIndustry command = new SurfaceCommandIndustry();
			return command;
		}
		else if (SurfaceActivities.PRODUCTION.equals( dCommand.getActivity() )) {
			SurfaceCommandProduction command = new SurfaceCommandProduction();
			command.setMaxShips( dCommand.getMaxShips());
			command.setShipDesign( shipDesignService.mapToCoreObject(dCommand.getDesign()) );
			command.setTechnologies( technologiesService.mapToCoreObject( dCommand.getTechnologies()));
			return command;
		}
		else if (SurfaceActivities.TECHNOLOGIES.equals( dCommand.getActivity() )) {
			SurfaceCommandTechnologies command = new SurfaceCommandTechnologies();
			command.setTechnologyToUpgrade( dCommand.getTechnologyToUpgrade() );
			return command;
		}
		else {
			return null;
		}
	}
	
	

	/**
	 * @deprecated use mapDFactory
	 * @param factory
	 * @param pcd
	 * @return
	 */
	public DShipFactory mapDFactory(ShipFactory factory, PlanetContractData pcd) {
		DShipFactory dFactory = new DShipFactory();
		dFactory.setDonePart(factory.getDonePart());
		dFactory.setDesign(pcd.getfDesign());
		dFactory.setTechnologies(pcd.getfTechnologies());
		dFactory.setShip(pcd.getfShip());
		return dFactory;
	}
	
	public DShipFactory mapDFactory2(ShipFactory factory ) {
		DShipFactory dFactory = new DShipFactory();
		dFactory.setDonePart(factory.getDonePart());
		dFactory.setDesign( shipDesignService.mapToDbObject( factory.getShipDesign() ) );
		dFactory.setTechnologies( technologiesService.mapToDbObject( factory.getTechnologies()));
		dFactory.setShip( shipService.mapToDbObject(factory.getShip()));
		return dFactory;
	}
	
	public ShipFactory mapFactory ( DShipFactory dFactory ) {
		ShipFactory factory = new ShipFactory();
		factory.setDonePart( dFactory.getDonePart());
		factory.setShip( shipService.mapToCoreObject( dFactory.getShip() ) );
		factory.setShipDesign( shipDesignService.mapToCoreObject( dFactory.getDesign() ) );
		factory.setTechnologies(  technologiesService.mapToCoreObject(dFactory.getTechnologies() ) );
		return factory;
	}

	public PlanetSurface loadPlanetSurface ( long planetId, int turnNumber ) {
		DPlanetSurface dPS = dPlanetSurfaceDao.find(planetId, turnNumber);
		
		PlanetSurface surface = new PlanetSurface();
		surface.setName( dPS.getName() );
		surface.setPopulation ( dPS.getPopulation() );
		surface.setIndustry( dPS.getIndustry() );
		surface.setCapital( dPS.getCapital() );
		surface.setNation( nationService.mapToCoreObject(dPS.getOwner() ));

		LOG.trace( "factories amount = "+ dPS.getShipFactories().size() );
		if ( dPS.getShipFactories().size() > 0 )
			surface.setShipFactory( mapFactory( (DShipFactory) dPS.getShipFactories().toArray()[0] ));
		
		LOG.trace( "commands amount = "+ dPS.getCommands().size() );
		if ( dPS.getCommands().size() > 0 )
			surface.setSurfaceCommand( mapSurfaceCommand( (DSurfaceCommand) dPS.getCommands().toArray()[0] ) );
		
		return surface;
	}
	
	/**
	 * 
	 * @author Giedrius Tumelis
	 * 
	 * @deprecated will implement without it.
	 *
	 */
	private class PlanetContractData {
		private DNation nation;
		private DShipDesign design;
		private DTechnologies technologies;

		private DShipDesign fDesign;
		private DTechnologies fTechnologies;
		private DShip fShip;

		public DNation getNation() {
			return nation;
		}

		public DShipDesign getDesign() {
			return design;
		}

		public DTechnologies getTechnologies() {
			return technologies;
		}

		public DShipDesign getfDesign() {
			return fDesign;
		}

		public DTechnologies getfTechnologies() {
			return fTechnologies;
		}

		public DShip getfShip() {
			return fShip;
		}

		public void loadContractData(PlanetSurface surface) throws PlanetSurfaceContractException {
			nation = dao.find(DNation.class, surface.getNation().getNationId());
			if (nation == null)
				throw new PlanetSurfaceContractException(
						"could not load dNation with id=" + surface.getNation().getNationId());

			if (surface.getSurfaceCommand() instanceof SurfaceCommandProduction) {
				SurfaceCommandProduction pCommand = (SurfaceCommandProduction) surface.getSurfaceCommand();
				technologies = dao.find(DTechnologies.class, pCommand.getTechnologies().getTechnologiesId());
				if (technologies == null)
					throw new PlanetSurfaceContractException(
							"could not load dTechnologies with id=" + pCommand.getTechnologies().getTechnologiesId());

				design = dao.find(DShipDesign.class, pCommand.getShipDesign().getDesignId());
				if (design == null)
					throw new PlanetSurfaceContractException(
							"could not load dShipDesign with id=" + pCommand.getShipDesign().getDesignId());
			}

			if (surface.getShipFactory() != null) {
				if (surface.getShipFactory().getShipDesign() != null) {
					fDesign = dao.find(DShipDesign.class, surface.getShipFactory().getShipDesign().getDesignId());
					if (fDesign == null)
						throw new PlanetSurfaceContractException("could not load factory dShipDesign with id="
								+ surface.getShipFactory().getShipDesign().getDesignId());
				}

				if (surface.getShipFactory().getTechnologies() != null) {
					fTechnologies = dao.find(DTechnologies.class,
							surface.getShipFactory().getTechnologies().getTechnologiesId());
					if (technologies == null)
						throw new PlanetSurfaceContractException("could not load dTechnologies with id="
								+ surface.getShipFactory().getTechnologies().getTechnologiesId());
				}

				if (surface.getShipFactory().getShip() != null) {
					fShip = dao.find(DShip.class, surface.getShipFactory().getShip().getId());
					if (fShip == null)
						throw new PlanetSurfaceContractException(
								"could not load dShip with id=" + surface.getShipFactory().getShip().getId());
				}
			}
		}

	}

}
