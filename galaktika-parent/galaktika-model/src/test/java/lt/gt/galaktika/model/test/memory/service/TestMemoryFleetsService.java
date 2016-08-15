package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.FlightCommand;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.core.exception.GalaktikaException;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.model.service.FleetsService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.ShipService;
import lt.gt.galaktika.model.service.SpaceLocationService;
import lt.gt.galaktika.model.test.memory.MemoryBeansConfig;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
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

	@Test
//	 @Ignore
	public void testFleetsService() throws GalaktikaException {
		LOG.trace("testFleetsService called");
		Nation nation = nationService.create(new Nation("vokieciai "));

		int turnNumber = 1;

		Fleet fleet = new Fleet("grybuva");
		fleet.setOwner(nation);

		// ======= contracted data
		Ship katinas = shipService.create(new Ship("katinas"));
		Ship meska = shipService.create(new Ship("meska"));
		Ship kelmas = shipService.create(new Ship("kelmas"));

		Planet planet1 = planetService.create(new Planet(10, 10, 101, 0.9));
		Planet planet2 = planetService.create(new Planet(5, 15, 101, 0.9));
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
	// @Ignore
	public void testUpdateFleet() throws GalaktikaException {
		// fleet update
		LOG.trace("testUpdateFleet called");

		// contract data
		Ship katinas = shipService.create(new Ship("katinas"));
		Ship suva = shipService.create(new Ship("suva"));

		Nation nation = nationService.create(new Nation("vokieciai "));

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

}
