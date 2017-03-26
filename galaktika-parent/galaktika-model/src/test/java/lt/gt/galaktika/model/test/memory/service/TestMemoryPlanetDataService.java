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
import lt.gt.galaktika.core.exception.GalaktikaException;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.ShipFactory;
import lt.gt.galaktika.core.planet.SurfaceCommandIndustry;
import lt.gt.galaktika.core.planet.SurfaceCommandProduction;
import lt.gt.galaktika.core.planet.SurfaceCommandTechnologies;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.entity.turn.DFleetData;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;
import lt.gt.galaktika.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
@Deprecated  
/**
 * 
 * @deprecated Use TestMemoryPlanetDataService2 instead
 *
 */
public class TestMemoryPlanetDataService {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryPlanetDataService.class);
	
	@Autowired
	@Qualifier("dao")
	IDAO dao;
	
	@Autowired
	private PlanetDataService planetDataService;
	
	@Autowired
	private PlanetService planetService;
	
	@Autowired
	private NationService nationService;
	
	@Test
//	@Ignore
	public void testOrbit () {
		LOG.trace( "testOrbit called" );
//		planetDataService.loadPlanetOrbit( , turnNumber)
		
//		DTurn turn = dao.create(new DTurn(4));
		int turn = 4;
		DPlanet planet = dao.create( new DPlanet());
		
		DFleet fleet1 = dao.create( new DFleet( "pirmas" ) );
		DFleet fleet2 = dao.create( new DFleet( "antras" ) );
		
		DFleetData fd1 = new DFleetData(fleet1.getFleetId(), turn);
		fd1.setPlanetLocation( planet );
		DFleetData fd2 = new DFleetData(fleet2.getFleetId(), turn);
		fd2.setPlanetLocation( planet );
		
		dao.create( fd1 );
		dao.create( fd2 );
		
		PlanetOrbit planetOrbit = planetDataService.loadPlanetOrbit( planet.getPlanetId(), turn, false );
		Assert.assertEquals( 2,  planetOrbit.getFleets().size() );
		planetOrbit.getFleets().forEach( f -> LOG.trace( f.toString() ) );

		planetOrbit = planetDataService.loadPlanetOrbit( planet.getPlanetId(), turn, true );
		Assert.assertEquals( 2,  planetOrbit.getFleets().size() );
		planetOrbit.getFleets().forEach( f -> LOG.trace( f.toString() ) );

	}
	
	@Test
	public void testSurface () throws GalaktikaException {
		LOG.trace( "testSurface called" );
		
		Nation nation = nationService.create( new Nation ("pacukai"));
		
		Planet planet = new Planet();
		planet = planetService.create( planet );
		Assert.assertNotEquals( 0, planet.getPlanetId());

		PlanetSurface surface = new PlanetSurface();
		surface.setCapital( 100 );
		surface.setIndustry( 120 );
		surface.setPopulation( 99 );
		surface.setName( "grybuva" );
		surface.setNation( nation );
		surface.setSurfaceCommand(new SurfaceCommandIndustry()); // TODO fill command fields ( and other commands ) for testing
		surface.setShipFactory(new ShipFactory()); // TODO fill factory fields for testing
		planetDataService.storePlanetSurface( surface, planet, 1 );
		
		PlanetSurface lSurface = planetDataService.loadPlanetSurface ( planet.getPlanetId(), 1 );
		Assert.assertEquals( surface.getCapital(), lSurface.getCapital(), Utils.EPSILON );
		Assert.assertEquals(surface.getIndustry(), lSurface.getIndustry(), Utils.EPSILON );
		Assert.assertEquals( surface.getPopulation(), lSurface.getPopulation(), Utils.EPSILON );
		Assert.assertEquals( surface.getName(), lSurface.getName() );

		Assert.assertEquals( surface.getNation(), lSurface.getNation() );
		Assert.assertEquals( surface.getShipFactory(), lSurface.getShipFactory() );
		Assert.assertEquals( surface.getSurfaceCommand(), lSurface.getSurfaceCommand() );
		
	}
	@Test
	public void testSurface2 () throws GalaktikaException {
		LOG.trace( "testSurface called" );
		
		Nation nation = nationService.create( new Nation ("pacukai"));
		
		Planet planet = new Planet();
		planet = planetService.create( planet );
		Assert.assertNotEquals( 0, planet.getPlanetId());

		PlanetSurface surface = new PlanetSurface();
		surface.setCapital( 100 );
		surface.setIndustry( 120 );
		surface.setPopulation( 99 );
		surface.setName( "grybuva" );
		surface.setNation( nation );
		surface.setSurfaceCommand(new SurfaceCommandTechnologies()); // TODO fill command fields for testing
		surface.setShipFactory(new ShipFactory()); // TODO fill factory fields for testing
		planetDataService.storePlanetSurface( surface, planet, 1 );
		
		PlanetSurface lSurface = planetDataService.loadPlanetSurface ( planet.getPlanetId(), 1 );
		Assert.assertEquals( surface.getCapital(), lSurface.getCapital(), Utils.EPSILON );
		Assert.assertEquals(surface.getIndustry(), lSurface.getIndustry(), Utils.EPSILON );
		Assert.assertEquals( surface.getPopulation(), lSurface.getPopulation(), Utils.EPSILON );
		Assert.assertEquals( surface.getName(), lSurface.getName() );

		Assert.assertEquals( surface.getNation(), lSurface.getNation() );
		Assert.assertEquals( surface.getShipFactory(), lSurface.getShipFactory() );
		Assert.assertEquals( surface.getSurfaceCommand(), lSurface.getSurfaceCommand() );
		
	}
	@Test
	public void testSurface3 () throws GalaktikaException {
		LOG.trace( "testSurface called" );
		
		Nation nation = nationService.create( new Nation ("pacukai"));
		
		Planet planet = new Planet();
		planet = planetService.create( planet );
		Assert.assertNotEquals( 0, planet.getPlanetId());

		PlanetSurface surface = new PlanetSurface();
		surface.setCapital( 100 );
		surface.setIndustry( 120 );
		surface.setPopulation( 99 );
		surface.setName( "grybuva" );
		surface.setNation( nation );
		surface.setSurfaceCommand(new SurfaceCommandProduction()); // TODO fill command fields for testing
		surface.setShipFactory(new ShipFactory()); // TODO fill factory fields for testing
		planetDataService.storePlanetSurface( surface, planet, 1 );
		
		PlanetSurface lSurface = planetDataService.loadPlanetSurface ( planet.getPlanetId(), 1 );
		Assert.assertEquals( surface.getCapital(), lSurface.getCapital(), Utils.EPSILON );
		Assert.assertEquals(surface.getIndustry(), lSurface.getIndustry(), Utils.EPSILON );
		Assert.assertEquals( surface.getPopulation(), lSurface.getPopulation(), Utils.EPSILON );
		Assert.assertEquals( surface.getName(), lSurface.getName() );

		Assert.assertEquals( surface.getNation(), lSurface.getNation() );
		Assert.assertEquals( surface.getShipFactory(), lSurface.getShipFactory() );
		Assert.assertEquals( surface.getSurfaceCommand(), lSurface.getSurfaceCommand() );
		
	}
}
