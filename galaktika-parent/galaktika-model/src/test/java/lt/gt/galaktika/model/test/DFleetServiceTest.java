package lt.gt.galaktika.model.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.entity.DFleet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaTestConfig.class, TestConfig.class })
public class DFleetServiceTest
{
	final static Logger LOG = LoggerFactory.getLogger(DFleetServiceTest.class);

	@Autowired
	private IFleetDao fleetDao;
	
	@Test
	public void testGetFleet() {
		LOG.trace( "testGetFleet called" );
		DFleet fleetNoShips = fleetDao.getFleet( 3 );
		Assert.assertNotNull( fleetNoShips );
		System.out.println( "fleet name="+ fleetNoShips.getName());
		
		DFleet fleet= fleetDao.getFleetWithShips( 3 );
		Assert.assertNotNull( fleet );
		LOG.trace( "name="+ fleet.getName() );
		fleet.getShipGroups().forEach( g -> LOG.trace( g.getShip().getName()+" count="+g.getCount() ));
	}

}
