package lt.gt.galaktika.model.test.memory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.planet.SurfaceActivities;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IPlanetSurfaceDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;
import lt.gt.galaktika.model.entity.turn.DPlanetSurface;
import lt.gt.galaktika.model.entity.turn.DShipFactory;
import lt.gt.galaktika.model.entity.turn.DSurfaceCommand;
import lt.gt.galaktika.model.entity.turn.DTechnologies;
import lt.gt.galaktika.model.entity.turn.DTurn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class MemoryTestDPlanetSurface {
	final static Logger LOG = LoggerFactory.getLogger(MemoryTestDPlanetSurface.class);

	@Autowired
	@Qualifier("dao")
	IDAO dao;

	@Autowired
	IPlanetSurfaceDao dPlanetSurfaceDao;

	private DShip ship;
	private DShipDesign design;
	private DTechnologies technologies;

	@Before
	public void before() {
		ship = dao.create(new DShip("katinas"));
		design = dao.create(new DShipDesign("katino"));
		technologies = dao.create(new DTechnologies());
	}

	@Test
	public void testDPlanetSurface() {
		LOG.trace("testDPlanetSurface called");

		// create all the planet surface components
		// then store them to repository
		DTurn turn = dao.create(new DTurn(1));
		DPlanet planet = dao.create(new DPlanet(1, 1));
		DNation nation = dao.create(new DNation("kalniečiai"));
		DShipFactory factory = createShipFactory();
		DSurfaceCommand command = createSurfaceCommand();

		// assert planetId and turnId for factory and for command before adding
		// to surface
		Assert.assertNull(factory.getPlanetId());
		Assert.assertNull(factory.getTurnNumber());
		Assert.assertNull(command.getPlanetId());
		Assert.assertNull(command.getTurnNumber());
		Assert.assertNotEquals(0, factory.getShipFactoryId());
		Assert.assertNotEquals(0, command.getSufraceCommandId());

		// then create planet surface, add the components to it
		// then store planet surface

		DPlanetSurface surface = new DPlanetSurface(planet.getPlanetId(), turn.getTurnNumber());
		surface.setOwner(nation);

		surface.setCapital(100);
		surface.setIndustry(50);
		surface.setPopulation(100);

		surface.getCommands().add(command);
		surface.getShipFactories().add(factory);

		dao.create(surface);

		// then load it and check for elements values
		DPlanetSurface loadedSurface = dPlanetSurfaceDao.find(planet.getPlanetId(), turn.getTurnNumber());
		Assert.assertNotEquals(0, loadedSurface.getPlanetId());

		// assert planetId and turnId for factory and for command before adding
		// to surface
		Assert.assertEquals(new Long(planet.getPlanetId()), loadedSurface.getShipFactories().get(0).getPlanetId());
		Assert.assertEquals(new Integer(turn.getTurnNumber()), loadedSurface.getShipFactories().get(0).getTurnNumber());
		Assert.assertEquals(new Long(planet.getPlanetId()), loadedSurface.getCommands().get(0).getPlanetId());
		Assert.assertEquals(new Integer(turn.getTurnNumber()), loadedSurface.getCommands().get(0).getTurnNumber());

	}

	private DShipFactory createShipFactory() {
		return dao.create(new DShipFactory(ship, design, technologies));
	}

	private DSurfaceCommand createSurfaceCommand() {
		DSurfaceCommand command = new DSurfaceCommand();
		command.setActivity(SurfaceActivities.PRODUCTION);
		command.setDesign(design);
		return dao.create(command);
	}

}
