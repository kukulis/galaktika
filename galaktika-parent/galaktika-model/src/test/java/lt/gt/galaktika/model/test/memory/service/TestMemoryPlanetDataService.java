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

import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.entity.turn.DFleetData;
import lt.gt.galaktika.model.entity.turn.DTurn;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.test.memory.MemoryBeansConfig;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class TestMemoryPlanetDataService {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryPlanetDataService.class);
	
	@Autowired
	@Qualifier("dao")
	IDAO dao;
	
	@Autowired
	private PlanetDataService planetDataService;
	
	@Test
	public void testOrbit () {
		LOG.trace( "testOrbit called" );
//		planetDataService.loadPlanetOrbit( , turnNumber)
		
		DTurn turn = dao.create(new DTurn(4));
		DPlanet planet = dao.create( new DPlanet());
		
		DFleet fleet1 = dao.create( new DFleet( "pirmas" ) );
		DFleet fleet2 = dao.create( new DFleet( "antras" ) );
		
		DFleetData fd1 = new DFleetData(fleet1.getFleetId(), turn.getTurnNumber() );
		fd1.setPlanetLocation( planet );
		DFleetData fd2 = new DFleetData(fleet2.getFleetId(), turn.getTurnNumber() );
		fd2.setPlanetLocation( planet );
		
		dao.create( fd1 );
		dao.create( fd2 );
		
		PlanetOrbit planetOrbit = planetDataService.loadPlanetOrbit( planet.getPlanetId(), turn.getTurnNumber(), false );
		Assert.assertEquals( 2,  planetOrbit.getFleets().size() );
		planetOrbit.getFleets().forEach( f -> LOG.trace( f.toString() ) );

		planetOrbit = planetDataService.loadPlanetOrbit( planet.getPlanetId(), turn.getTurnNumber(), true );
		Assert.assertEquals( 2,  planetOrbit.getFleets().size() );
		planetOrbit.getFleets().forEach( f -> LOG.trace( f.toString() ) );

	}
}
