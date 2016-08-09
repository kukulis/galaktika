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
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.model.service.FleetsService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.ShipService;
import lt.gt.galaktika.model.service.SpaceLocationService;

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
	
	@Test
	public void testFleetsService () {
		LOG.trace ( "testFleetsService called" );
		Nation nation = nationService.create( new Nation ( "vokieciai ") );
		
		int turnNumber = 1;
		
		Fleet fleet = new Fleet( "grybuva" );
		fleet.setOwner( nation );
		
		Ship katinas = shipService.create( new Ship( "katinas" ) );
		Ship meska = shipService.create( new Ship( "meska" ) );
		Ship kelmas = shipService.create( new Ship( "kelmas" ) );
		
		// ship groups
		fleet.addShipGroup( new ShipGroup(katinas, 3));
		fleet.addShipGroup( new ShipGroup(meska, 2));
		fleet.addShipGroup( new ShipGroup(kelmas, 1));
		
		// location
 		SpaceLocation location = new SpaceLocation(3, 4 );
  		location = spaceLocationService.create( location );
		fleet.setGalaxyLocation( location );
		// TODO flight command
		fleet = fleetsService.saveFleet(fleet, turnNumber);
		
		Assert.assertNotEquals(0, fleet.getFleetId() );
		LOG.trace( "fleetId="+fleet.getFleetId() );
		
		Fleet loadedFleet = fleetsService.loadFleet( fleet.getFleetId(), turnNumber);
		Assert.assertNotNull( loadedFleet );
		Assert.assertNotNull( loadedFleet.getOwner() );
		// ship groups
		Assert.assertArrayEquals( fleet.getShipGroups().toArray(), loadedFleet.getShipGroups().toArray());
		// TODO check counts of ships in groups
		
		// location
		Assert.assertEquals( fleet.getGalaxyLocation(), loadedFleet.getGalaxyLocation() );
		
		
		// TODO flight command
	}
	
	// TODO test after fleet update
}
