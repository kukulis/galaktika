package lt.gt.galaktika.engine.bot;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.TechnologyType;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.core.planet.ShipFactory;
import lt.gt.galaktika.core.planet.SurfaceCommandProduction;
import lt.gt.galaktika.core.planet.SurfaceCommandTechnologies;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.ShipDesignService;
import lt.gt.galaktika.model.service.TechnologiesService;

@Service
public class UsersBot {
	
	public final static String PLAYER1 = "player1";
	public final static String PLAYER2 = "player2";
	public final static String PLAYER3 = "player3";

	public final static String ADMIN = "admin";
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	NationService nationService;
	
	@Autowired
	GalaxyService galaxyService;

	@Autowired
	TechnologiesService technologiesService;
	
	@Autowired
	PlanetDataService planetDataService;
	
	@Autowired
	PlanetService planetService;
	
	@Autowired
	ShipDesignService shipDesignService;

	
	public boolean createUsers() {
		boolean ret = true;
		
		User adminUser = new User("admin@email.lt", "admin"); 
		adminUser.setLogin(ADMIN);
		adminUser.setPassword( "admin" );
		try { 
			userDao.save(adminUser);
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
			ret = false;
		}
		
		User player1User = new User("player1@email.lt", "player1");
		player1User.setLogin(PLAYER1);
		player1User.setPassword( "player1");
		try {
			userDao.save( player1User);
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
			ret = false;
		}
		
		User player2User = new User("player2@email.lt", "player2");
		player2User.setLogin(PLAYER2);
		player2User.setPassword( "player2" );
		try {
			userDao.save( player2User );
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
			ret = false;
		}
		
		User player3User = new User("player3@email.lt", "player3");
		player3User.setLogin(PLAYER3);
		player3User.setPassword( "player3" );
		try {
			userDao.save( player3User );
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
			ret = false;
		}
		
		return ret;
	}
	
	public boolean createNations () {
		
		boolean ok = true;
		
		User player1 = userDao.getByLogin( PLAYER1 );
		User player2 = userDao.getByLogin( PLAYER2 );
		User player3 = userDao.getByLogin( PLAYER3 );
		
		List< Galaxy> galaxies = galaxyService.getGalaxies(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));
		Assert.assertNotNull( galaxies );
		Assert.assertNotEquals( 0, galaxies.size());
		
		Galaxy galaxy = galaxies.get(0);
		
		Assert.assertNotNull( player1 );
		Assert.assertNotNull( player2 );
		
		Nation nation1 = nationService.getNation(player1, galaxy);
		if ( nation1 == null ) {
			nation1 = new Nation("nation1");
			nation1 = nationService.createNation(nation1, player1, galaxy);
		}
		
		Nation nation2 = nationService.getNation(player2, galaxy);
		if ( nation2 == null ) {
			nation2 = new Nation("nation2");
			nation2 = nationService.createNation(nation2, player2, galaxy);
		}
		// nation 3
		Nation nation3 = nationService.getNation(player3, galaxy);
		if ( nation3 == null ) {
			nation3 = new Nation("nation2");
			nation3 = nationService.createNation(nation3, player3, galaxy);
		}
	
		// create technologies for nations
		try {
			technologiesService.createTechnologies ( new Technologies(), nation1, 1);
		} catch ( DataIntegrityViolationException e ) {
			System.err.println(e.getMessage() );
			ok = false;
		}
		
		try {
			technologiesService.createTechnologies ( new Technologies(), nation2, 1);
		} catch ( DataIntegrityViolationException e ) {
			System.err.println(e.getMessage() );
			ok = false;
		}
		
		try {
			technologiesService.createTechnologies ( new Technologies(), nation3, 1);
		} catch ( DataIntegrityViolationException e ) {
			System.err.println(e.getMessage() );
			ok = false;
		}

		return ok;
	}
	
	public void makeTurn1Commands () {
		User player1 = userDao.getByLogin( PLAYER1 );
		User player2 = userDao.getByLogin( PLAYER2 );
		User player3 = userDao.getByLogin( PLAYER3 );
		Galaxy galaxy = galaxyService.getGalaxy(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));
		
		Nation nation1 = nationService.getNation(player1, galaxy);
		Nation nation2 = nationService.getNation(player2, galaxy);
		Nation nation3 = nationService.getNation(player3, galaxy);
		
//		planetService.
		List<Long> planetsIds1 = planetDataService.findPlanetsIds(nation1, 1);
		List<Long> planetsIds2 = planetDataService.findPlanetsIds(nation2, 1);
		List<Long> planetsIds3 = planetDataService.findPlanetsIds(nation3, 1);
		
		Planet planet1 = planetService.load( planetsIds1.get(0));
		Planet planet2 = planetService.load( planetsIds2.get(0));
		Planet planet3 = planetService.load( planetsIds3.get(0));
		
		PlanetSurface ps1 = planetDataService.loadPlanetSurface(planetsIds1.get(0), 1);
		PlanetSurface ps2 =planetDataService.loadPlanetSurface(planetsIds2.get(0), 1);
		ps2.setName("Technocrats");
		PlanetSurface ps3 =planetDataService.loadPlanetSurface(planetsIds3.get(0), 1);
		
		// set commands for surfaces
		ps2.setSurfaceCommand(new SurfaceCommandTechnologies(TechnologyType.ENGINE));
		planetDataService.storePlanetSurface(ps2, planet2, 1);
		
		SurfaceCommandProduction productionCommand = new SurfaceCommandProduction();
		productionCommand.setTechnologies( technologiesService.getNationTechnologies(nation3, 1));
		productionCommand.setMaxShips(1);
		ShipDesign shipDesign = new ShipDesign();
		shipDesign.setAttackMass(10);
		shipDesign.setDefenceMass(10);
		shipDesign.setEngineMass(10);
		shipDesign.setCargoMass(10);
		shipDesign.setDesignName("grybas");
		shipDesign.setGuns( 1 );
		
		shipDesign = shipDesignService.createShipDesign(shipDesign, ps3.getNation());
		productionCommand.setShipDesign(shipDesign);
		productionCommand.setMaxShips(3);
		ps3.setSurfaceCommand(productionCommand);
		
//		ShipFactory shipFactory = new ShipFactory();
//		ps3.setShipFactory(shipFactory);
		planetDataService.storePlanetSurface(ps3, planet3, 1);
	}
	
	public void makeTurn2Commands () {
		int turn = 2;
		
		User player1 = userDao.getByLogin( PLAYER1 );
		User player2 = userDao.getByLogin( PLAYER2 );
		User player3 = userDao.getByLogin( PLAYER3 );
		Galaxy galaxy = galaxyService.getGalaxy(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));
		
		Nation nation1 = nationService.getNation(player1, galaxy);
		Nation nation2 = nationService.getNation(player2, galaxy);
		Nation nation3 = nationService.getNation(player3, galaxy);
		
//		planetService.
		List<Long> planetsIds1 = planetDataService.findPlanetsIds(nation1, turn);
		List<Long> planetsIds2 = planetDataService.findPlanetsIds(nation2, turn);
		List<Long> planetsIds3 = planetDataService.findPlanetsIds(nation3, turn);
		
		Planet planet1 = planetService.load( planetsIds1.get(0));
		Planet planet2 = planetService.load( planetsIds2.get(0));
		Planet planet3 = planetService.load( planetsIds3.get(0));
		
		PlanetSurface ps1 = planetDataService.loadPlanetSurface(planetsIds1.get(0), turn);
		PlanetSurface ps2 =planetDataService.loadPlanetSurface(planetsIds2.get(0), turn);
		ps2.setName("Technocrats");
		PlanetSurface ps3 =planetDataService.loadPlanetSurface(planetsIds3.get(0), turn);
		
		// set commands for surfaces
		ps2.setSurfaceCommand(new SurfaceCommandTechnologies(TechnologyType.ATTACK));
		planetDataService.storePlanetSurface(ps2, planet2, turn);
		
		// TODO make other planets to build something
		
//		 ps3.getSurfaceCommand()
		
		{
			SurfaceCommandProduction productionCommand = new SurfaceCommandProduction();
			Technologies t = technologiesService.getNationTechnologies(nation1, turn);
			productionCommand.setTechnologies( t );
			productionCommand.setMaxShips(1);
			ShipDesign shipDesign = new ShipDesign();
			shipDesign.setAttackMass(10);
			shipDesign.setDefenceMass(10);
			shipDesign.setEngineMass(10);
			shipDesign.setCargoMass(0);
			shipDesign.setDesignName("uogiukas");
			shipDesign.setGuns( 1 );
			
			shipDesign = shipDesignService.createShipDesign(shipDesign, nation1);
			productionCommand.setShipDesign(shipDesign);
			productionCommand.setMaxShips(3);
			ps1.setSurfaceCommand(productionCommand);
		}
		
		
		planetDataService.storePlanetSurface(ps1, planet1, turn);

	}
	
	public void makeTurn3Commands () {
		System.out.println( "TODO make turn3 commands");
		// Flight to another planet
	}
}
