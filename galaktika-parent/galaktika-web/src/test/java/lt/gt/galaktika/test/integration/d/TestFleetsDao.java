package lt.gt.galaktika.test.integration.d;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.entity.DFleet;
import lt.gt.galaktika.web.GalaktikaApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GalaktikaApplication.class)
@WebAppConfiguration
public class TestFleetsDao
{
	@Autowired
	private IFleetDao fleetDao;

	@Test
	@Ignore
	public void testStoreDFleet() {
		DFleet dfleet = new DFleet();
		dfleet.setName( "fleetas2" );
		long rezId = fleetDao.save( dfleet );
		System.out.println ( "stored with id="+rezId);
		rezId = fleetDao.save( dfleet );
		System.out.println ( "second time stored with id="+rezId);
	}
	
	@Test
	@Ignore
	public  void testGetFleetWithNation () {
		DFleet fleet11 =  fleetDao.getFleet( 11, 2 );
		DFleet fleet12 =  fleetDao.getFleet( 12, 2 );
		
		System.out.println ( "fleet11="+fleet11 ); 
		System.out.println ( "fleet12="+fleet12 ); 
	}
	
	@Test
	public void testDeleteFleet() {
		// TODO
		System.out.println ( "testDeleteFleet called" );
		boolean result = fleetDao.updateDeletedFlag(21, true);
		System.out.println ( "result="+result );
	}
}
