package lt.gt.galaktika.engine.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.FlightCommand;
import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.GalaxyLocation;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.core.engines.GalaxyEngine;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.engine.config.AdditionalBeansConfig;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.FleetsService;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class, AdditionalBeansConfig.class })
public class FlightsTest {

	@Autowired
	FleetsService fleetService;

	@Autowired
	GalaxyService galaxyService;

	@Autowired
	IUserDao userDao;

	@Autowired
	NationService nationService;

	@Autowired
	PlanetService planetService;

	@Test
	public void testFlights() throws Exception {
		System.out.println("testFlight called");

		// 0) Prepare fleets and store them to database
		Galaxy g = new Galaxy(300, 300);
		g = galaxyService.createGalaxy(g, EGalaxyPurposes.IMITATE, true);

		Nation n1 = null;
		Nation n2 = null;

		{
			User user1 = new User("aaa@aaa.lt", "aaaa");
			User user2 = new User("bbb@bbb.lt", "bbbb");

			userDao.save(user1);
			userDao.save(user2);

			n1 = new Nation("aaa");
			n2 = new Nation("bbb");

			n1 = nationService.createNation(n1, user1, g);
			n2 = nationService.createNation(n2, user2, g);

			Planet p1 = planetService.createPlanet(new Planet(100, 100, 1, 1), g),
					p2 = planetService.createPlanet(new Planet(100, 200, 1, 1), g),
					p3 = planetService.createPlanet(new Planet(200, 100, 1, 1), g);

			Fleet f = new Fleet();
			f.setGalaxyLocation(p1);
			f.setFlightCommand(new FlightCommand(p1, p2));
			f.addShipGroup(new ShipGroup(new Ship("laivas", 1, 1, 1, 1, 1)));
			f.setOwner(n1);
			fleetService.completelySaveFleet(f, 1);

			Fleet f2 = new Fleet();
			f2.setGalaxyLocation(p1);
			f2.setFlightCommand(new FlightCommand(p1, p3));
			f2.addShipGroup(new ShipGroup(new Ship("laivas", 1, 1, 1, 1, 1)));
			f2.setOwner(n2);

			fleetService.completelySaveFleet(f2, 1);
		}
		{
			int turn = 1;
			// 1) Load all fleets from a given turn
			List<Fleet> fleets = fleetService.loadFleets(g, turn);

			Assert.assertEquals(2, fleets.size());

			// 2) Move fleets using core engines
			GalaxyEngine galaxyEngine = new GalaxyEngine();
			for (Fleet fleet : fleets) {
				fleet = fleetService.loadFleet(fleet.getFleetId(), turn);

				GalaxyLocation newLocation = galaxyEngine.calculateMovement(fleet, g);
				fleet.setGalaxyLocation(newLocation);

				// assert before saving
				if (fleet.getOwner().equals(n1)) {
					Assert.assertEquals(100, fleet.getGalaxyLocation().getX(), Utils.EPSILON);
					Assert.assertEquals(101, fleet.getGalaxyLocation().getY(), Utils.EPSILON);
				}
				if (fleet.getOwner().equals(n2)) {
					Assert.assertEquals(101, fleet.getGalaxyLocation().getX(), Utils.EPSILON);
					Assert.assertEquals(100, fleet.getGalaxyLocation().getY(), Utils.EPSILON);
				}

				// 3) Save fleets into new turn
				fleetService.saveFleet(fleet, 2);
			}

		}

		// load fleets again and assert locations
		List<Fleet> fleets = fleetService.loadFleets(g, 2);
		for (Fleet fleet : fleets) {
			if (fleet.getOwner().equals(n1)) {
				System.out.println("owner1 fleet found");
				Assert.assertEquals(100, fleet.getGalaxyLocation().getX(), Utils.EPSILON);
				Assert.assertEquals(101, fleet.getGalaxyLocation().getY(), Utils.EPSILON);
			}
			if (fleet.getOwner().equals(n2)) {
				System.out.println("owner2 fleet found");
				Assert.assertEquals(101, fleet.getGalaxyLocation().getX(), Utils.EPSILON);
				Assert.assertEquals(100, fleet.getGalaxyLocation().getY(), Utils.EPSILON);
			}
		}

	}

	@Test
	public void testOneFlight() {
		Galaxy g = new Galaxy(300, 300);
		Fleet f = new Fleet();
		f.setGalaxyLocation(new SpaceLocation(100, 100));
		f.setFlightCommand(new FlightCommand(new SpaceLocation(100, 100), new SpaceLocation(100, 200)));
		f.addShipGroup(new ShipGroup(new Ship("laivas", 1, 1, 1, 1, 1)));

		GalaxyEngine galaxyEngine = new GalaxyEngine();
		GalaxyLocation newLocation = galaxyEngine.calculateMovement(f, g);
		Assert.assertEquals(100, newLocation.getX(), Utils.EPSILON);
		Assert.assertEquals(101, newLocation.getY(), Utils.EPSILON);
	}
}
