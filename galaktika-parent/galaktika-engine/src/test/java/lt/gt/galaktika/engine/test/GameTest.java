package lt.gt.galaktika.engine.test;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.engine.bot.UsersBot;
import lt.gt.galaktika.engine.config.AdditionalBeansConfig;
import lt.gt.galaktika.engine.work.GameService;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.TechnologiesService;
import lt.gt.galaktika.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class, AdditionalBeansConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTest {

	@Autowired
	private GameService gameService;

	@Autowired
	private UsersBot usersBot;

	// --- for testing
	@Autowired
	IUserDao usersDao;

	@Autowired
	GalaxyService galaxyService;

	@Autowired
	NationService nationService;

	@Autowired
	TechnologiesService technologiesService;

	@Autowired
	PlanetService planetService;
	
	@Autowired
	PlanetDataService planetDataService;

	@Test
	public void test001Users() {
		System.out.println("Creating users...");
		Assert.assertTrue(usersBot.createUsers());

		User player1 = usersDao.getByLogin(UsersBot.PLAYER1);
		User player2 = usersDao.getByLogin(UsersBot.PLAYER2);

		Assert.assertNotNull(player1);
		Assert.assertNotNull(player2);
	}

	@Test
	public void test002Galaxy() {
		System.out.println("Creating galaxy...");
		Assert.assertTrue(gameService.createGalaxy());
		Galaxy galaxy = galaxyService.getGalaxy(new GalaxiesFilter().setPurpose(EGalaxyPurposes.PLAY).setActive(true));
		Assert.assertNotNull(galaxy);
	}

	@Test
	public void test003Nations() {
		System.out.println("Creating nations...");
		usersBot.createNations();

		Galaxy g = galaxyService.getGalaxy(new GalaxiesFilter().setPurpose(EGalaxyPurposes.PLAY).setActive(true));
		User u1 = usersDao.getByLogin(UsersBot.PLAYER1);
		User u2 = usersDao.getByLogin(UsersBot.PLAYER2);

		Nation nation1 = nationService.getNation(u1, g);
		Nation nation2 = nationService.getNation(u2, g);

		Assert.assertNotNull(nation1);
		Assert.assertNotNull(nation2);

		Assert.assertNotEquals(0, nation1.getNationId());
		Assert.assertNotEquals(0, nation2.getNationId());
	}

	@Test
	public void test004Planets() {
		System.out.println("Creating planets...");
		gameService.createPlanets();
		Galaxy g = galaxyService.getGalaxy(new GalaxiesFilter().setPurpose(EGalaxyPurposes.PLAY).setActive(true));
		List<Nation> nations = nationService.getNations( g );
		List<Planet> planets = planetService.findPlanets( g );
		Assert.assertTrue( "There are not at least 2 nations", nations.size() >= 2 );
		Assert.assertTrue( "There are more nations than planets", nations.size() <= planets.size() );
	}

	@Test
	public void test005PlanetSurfaces() {
		System.out.println("Creating planet surfaces with initial commands...");
		gameService.assignSurfaces();
		// TODO bot for different commands 
	}

	@Test
	public void test020MakeTurn1() {
		gameService.makeTurn();
		// validate turn1 results
		
		Galaxy g = galaxyService.getGalaxy(new GalaxiesFilter().setPurpose(EGalaxyPurposes.PLAY).setActive(true));
		List<Planet> planets = planetService.findPlanets(g);
		
		Planet planet1 = planets.get(0);
		Planet planet2 = planets.get(1);
		
		PlanetSurface surface1 = planetDataService.loadPlanetSurface(planet1.getPlanetId(), 2);
		PlanetSurface surface2 = planetDataService.loadPlanetSurface(planet2.getPlanetId(), 2);
		
		Assert.assertEquals(100, surface1.getCapital(), Utils.EPSILON );
		Assert.assertEquals(100, surface2.getCapital(), Utils.EPSILON );
	}
}
