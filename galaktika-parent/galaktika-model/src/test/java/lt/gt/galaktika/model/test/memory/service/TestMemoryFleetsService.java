package lt.gt.galaktika.model.test.memory.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.FlightCommand;
import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.core.exception.GalaktikaException;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.exception.FleetContractException;
import lt.gt.galaktika.model.service.FleetsService;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.ShipService;
import lt.gt.galaktika.model.service.SpaceLocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestMemoryFleetsService {

	final static Logger LOG = LoggerFactory.getLogger(TestMemoryFleetsService.class);

	@Autowired
	FleetsService fleetsService;

	@Autowired
	NationService nationService;

	@Autowired
	ShipService shipService;

	@Autowired
	SpaceLocationService spaceLocationService;

	@Autowired
	PlanetService planetService;
	
	@Autowired
	GalaxyService galaxyService;
	
	@Autowired
	IUserDao userDao;

	@Test
	 @Ignore
	public void testFleetsService() throws GalaktikaException {
		LOG.trace("testFleetsService called");
		
		Galaxy g = galaxyService.createGalaxy(new Galaxy(), EGalaxyPurposes.PLAY, true);
		User u = new User("aaa@aaa.lt", "katinas");
		userDao.save(u);
		
		Nation nation = nationService.createNation(new Nation("vokieciai "), u, g );

		int turnNumber = 1;

		Fleet fleet = new Fleet("grybuva");
		fleet.setOwner(nation);

		// ======= contracted data
		Ship katinas = shipService.create(new Ship("katinas"));
		Ship meska = shipService.create(new Ship("meska"));
		Ship kelmas = shipService.create(new Ship("kelmas"));

		Planet planet1 = planetService.createPlanet(new Planet(10, 10, 101, 0.9), g);
		Planet planet2 = planetService.createPlanet(new Planet(5, 15, 101, 0.9), g);
		// ========= end of contracted data

		// ship groups
		fleet.addShipGroup(new ShipGroup(katinas, 3));
		fleet.addShipGroup(new ShipGroup(meska, 2));
		fleet.addShipGroup(new ShipGroup(kelmas, 1));

		// location
		SpaceLocation location = new SpaceLocation(3, 4);
		fleet.setGalaxyLocation(location);
		// flight command
		FlightCommand fCommand = new FlightCommand();
		fCommand.setSource(planet1);
		fCommand.setDestination(planet2);
		fleet.setFlightCommand(fCommand);

		fleet = fleetsService.saveFleet(fleet, turnNumber);

		Assert.assertNotEquals(0, fleet.getFleetId());
		LOG.trace("fleetId=" + fleet.getFleetId());

		Fleet loadedFleet = fleetsService.loadFleet(fleet.getFleetId(), turnNumber);
		Assert.assertNotNull(loadedFleet);
		Assert.assertNotNull(loadedFleet.getOwner());
		// ship groups
		Assert.assertArrayEquals(fleet.getShipGroups().toArray(), loadedFleet.getShipGroups().toArray());

		// location
		LOG.trace("loadedFleet.galaxyLocation=" + loadedFleet.getGalaxyLocation());
		Assert.assertNotNull(loadedFleet.getGalaxyLocation());
		Assert.assertNotEquals(0, ((SpaceLocation) loadedFleet.getGalaxyLocation()).getLocationId());
		Assert.assertEquals(fleet.getGalaxyLocation(), loadedFleet.getGalaxyLocation());
		// flight command
		Assert.assertNotNull(loadedFleet.getFlightCommand().getSource());
		Assert.assertNotNull(loadedFleet.getFlightCommand().getDestination());
		Assert.assertEquals(fCommand.getSource(), loadedFleet.getFlightCommand().getSource());
		Assert.assertEquals(fCommand.getDestination(), loadedFleet.getFlightCommand().getDestination());
	}

	@Test
	 @Ignore
	public void testUpdateFleet() throws GalaktikaException {
		// fleet update
		LOG.trace("testUpdateFleet called");

		// contract data
		Ship katinas = shipService.create(new Ship("katinas"));
		Ship suva = shipService.create(new Ship("suva"));
		
		Galaxy g = galaxyService.createGalaxy(new Galaxy(), EGalaxyPurposes.PLAY, true);
		User u = new User("aaa@aaba.lt", "katinaas");
		userDao.save(u);

		Nation nation = nationService.createNation(new Nation("vokieciai "), u, g);

		int turnNumber = 1;

		Fleet fleet = new Fleet("pievute");
		fleet.setOwner(nation);

		// first create fleet
		fleet = fleetsService.saveFleet(fleet, turnNumber);

		Assert.assertNotEquals(0, fleet.getFleetId());
		LOG.trace("fleetId=" + fleet.getFleetId());

		// then load
		fleet = fleetsService.loadFleet(fleet.getFleetId(), turnNumber);

		// change it
		fleet.addShipGroup(new ShipGroup(katinas));

		// update
		fleetsService.saveFleet(fleet, turnNumber);

		// load again
		Fleet loadedFleet = fleetsService.loadFleet(fleet.getFleetId(), turnNumber);

		// test asserts
		Assert.assertFalse(fleet == loadedFleet);
		Assert.assertEquals(fleet, loadedFleet);
		Assert.assertArrayEquals(fleet.getShipGroups().toArray(), loadedFleet.getShipGroups().toArray());

		// TODO update ships count
		// load
		// and asserts again
		
		// multiple turns
		int turn2 = 2;
		fleet.addShipGroup( new ShipGroup(suva) );
		fleetsService.saveFleet( fleet, turn2 );
		
		Fleet fleet2 = fleetsService.loadFleet( fleet.getFleetId(), turn2 );
		Assert.assertEquals(2, fleet2.getShipGroups().size());
		Assert.assertArrayEquals(fleet.getShipGroups().toArray(), fleet2.getShipGroups().toArray());
		
		Fleet fleet3 = fleetsService.loadFleet( fleet.getFleetId(), turnNumber );
		Assert.assertEquals( 1, fleet3.getShipGroups().size());

	}

	public void testWrongContractData() {
		// TODO assert exceptions that must be thrown
	}
	
	@Test
	public void testLoadFleets() {
		// create fleets
		Galaxy g = new Galaxy (100, 100);
		g = galaxyService.createGalaxy(g, EGalaxyPurposes.PLAY, true);
		
		User u1 = new User("aaa@aaa.lt", "aaaa" );
		User u2 = new User("bbb@bbb.lt", "bbbb" );
		User u3 = new User("ccc@ccc.lt", "ccbc" );
		
		userDao.save( u1 );
		userDao.save( u2 );
		userDao.save( u3 );
		
		Nation n1 = new Nation();
		Nation n2 = new Nation();
		Nation n3 = new Nation();
		
		n1 = nationService.createNation(n1, u1, g);
		n2 = nationService.createNation(n2, u2, g);
		n3 = nationService.createNation(n3, u3, g);

		Fleet f1 = new Fleet();
		Fleet f2 = new Fleet();
		Fleet f3 = new Fleet();
		
		f1.setOwner(n1);
		f2.setOwner(n2);
		f3.setOwner(n3);
		try {
			f1 =fleetsService.saveFleet(f1, 1);
			f2 =fleetsService.saveFleet(f2, 1);
			f3 =fleetsService.saveFleet(f3, 1);
		
		} catch ( FleetContractException fce ) {
			LOG.error(fce.getMessage() );
		}
		
		// load fleets
		List<Fleet> fleets = fleetsService.loadFleets(g, 1);
		
		// assert
		Assert.assertArrayEquals(new Fleet[]{f1,f2,f3},fleets.toArray());
	}

}
