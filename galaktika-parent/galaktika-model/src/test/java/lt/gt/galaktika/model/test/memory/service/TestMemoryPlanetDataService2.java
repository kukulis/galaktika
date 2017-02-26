package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.core.planet.ShipFactory;
import lt.gt.galaktika.core.planet.SurfaceCommandIndustry;
import lt.gt.galaktika.core.planet.SurfaceCommandProduction;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.entity.turn.DPlanetSurface;
import lt.gt.galaktika.model.entity.turn.DShipFactory;
import lt.gt.galaktika.model.entity.turn.DSurfaceCommand;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.ShipDesignService;
import lt.gt.galaktika.model.service.TechnologiesService;
import lt.gt.galaktika.model.test.memory.MemoryBeansConfig;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class TestMemoryPlanetDataService2 {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryPlanetDataService2.class);

	@Autowired
	@Qualifier("dao")
	IDAO dao;

	@Autowired
	private PlanetDataService planetDataService;

	@Autowired
	private PlanetService planetService;

	@Autowired
	private NationService nationService;
	
	@Autowired
	private ShipDesignService shipDesignService;
	
	@Autowired
	private TechnologiesService technologiesService;
	
	private class TestData {
		PlanetSurface surface;
		Planet planet;
		int turnNumber;
		public TestData(PlanetSurface surface, Planet planet, int turnNumber) {
			super();
			this.surface = surface;
			this.planet = planet;
			this.turnNumber = turnNumber;
		}
	}
	

	@Test
	public void testStorePlanetSurface2() {
		LOG.trace("testStorePlanetSurface2 called");
		TestData data = createTestData();
		planetDataService.storePlanetSurface2(data.surface, data.planet, data.turnNumber);
		PlanetSurface surface = planetDataService.loadPlanetSurface( data.planet.getPlanetId(), data.turnNumber );
		
		Assert.assertEquals( data.surface.getSurfaceCommand(), surface.getSurfaceCommand() );
		Assert.assertEquals( data.surface.getShipFactory(), surface.getShipFactory());
//		LOG.trace( "factories hash codes original:" + data.surface.getShipFactory().hashCode() + " new:" + surface.getShipFactory().hashCode() );
//		planetDataService.storePlanetSurface2(data.surface, data.planet, data.turnNumber );
		
		
	}
	
//	@Test
	public void testMap () {
		TestData data = createTestData();
		DPlanetSurface dPlanetSurface = planetDataService.mapToDPlanetSurface2(data.surface, data.planet, data.turnNumber);
		
		Assert.assertNotNull( dPlanetSurface );
		Assert.assertNotNull( dPlanetSurface.getShipFactories() );
		Assert.assertNotEquals( 0, dPlanetSurface.getShipFactories() );
		
		for ( DSurfaceCommand dsc : dPlanetSurface.getCommands() ) { 
			Assert.assertNotNull( dsc.getPlanetId() );
			Assert.assertEquals( data.planet.getPlanetId(), dsc.getPlanetId().longValue());
			Assert.assertNotNull( dsc.getTurnNumber() );
			Assert.assertEquals( data.turnNumber, dsc.getTurnNumber().intValue() );
		}
		
		for ( DShipFactory f : dPlanetSurface.getShipFactories() ) {
			Assert.assertNotNull( f.getPlanetId() );
			Assert.assertEquals( data.planet.getPlanetId(), f.getPlanetId().longValue() );
			Assert.assertNotNull( f.getTurnNumber() );
			Assert.assertEquals( data.turnNumber, f.getTurnNumber().intValue());
		}
		
	}

	private TestData createTestData() {
		Nation nation = nationService.create(new Nation("pacukai"));

		Planet planet = new Planet();
		planet = planetService.create(planet);
		Assert.assertNotEquals(0, planet.getPlanetId());

		PlanetSurface surface = new PlanetSurface();
		surface.setCapital(100);
		surface.setIndustry(120);
		surface.setPopulation(99);
		surface.setName("grybuva");
		surface.setNation(nation);
		
		Technologies technologies = technologiesService.create( new Technologies() );
		
		SurfaceCommandIndustry sci = new SurfaceCommandIndustry();
//		sci.se
		SurfaceCommandProduction scProd = new SurfaceCommandProduction();
		ShipDesign shipDesign = shipDesignService.create( new ShipDesign() );
		
		Ship ship = new Ship();
		
		scProd.setShipDesign(shipDesign);
		scProd.setMaxShips( 2 );
		scProd.setTechnologies( technologies );
		surface.setSurfaceCommand(scProd); 
		ShipFactory shipFactory = new ShipFactory();
		shipFactory.setShip( ship );
		shipFactory.setShipDesign( shipDesign );
		shipFactory.setTechnologies( technologies );
		surface.setShipFactory(shipFactory); 
		return new TestData(surface, planet, 1);
	}

}
