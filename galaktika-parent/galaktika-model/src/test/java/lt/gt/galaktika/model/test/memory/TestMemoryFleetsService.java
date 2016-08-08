package lt.gt.galaktika.model.test.memory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.model.service.FleetsService;
import lt.gt.galaktika.model.service.NationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class TestMemoryFleetsService {

	final static Logger LOG = LoggerFactory.getLogger(TestMemoryFleetsService.class);

	@Autowired
	FleetsService fleetsService;
	
	@Autowired
	NationService nationService;
	
	@Test
	public void testFleetsService () {
		LOG.trace ( "testFleetsService called" );
		Nation nation = nationService.create( new Nation ( "vokieciai ") );
		
		int turnNumber = 1;
		
		Fleet fleet = new Fleet( "grybuva" );
		fleet.setOwner( nation );
		fleet = fleetsService.saveFleet(fleet, turnNumber);
		
		Assert.assertNotEquals(0, fleet.getFleetId() );
		LOG.trace( "fleetId="+fleet.getFleetId() );
		
		Fleet loadedFleet = fleetsService.loadFleet( fleet.getFleetId(), turnNumber);
		Assert.assertNotNull( loadedFleet );
		Assert.assertNotNull( loadedFleet.getOwner() );
	}
}
