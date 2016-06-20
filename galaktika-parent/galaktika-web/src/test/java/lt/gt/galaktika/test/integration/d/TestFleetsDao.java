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
//	@Ignore
	public  void testGetFleetWithNation () {
		DFleet fleet1 =  fleetDao.getFleet( 3, 0 );
		DFleet fleet2 =  fleetDao.getFleet( 2, 0 );
		
		System.out.println ( "fleet11="+fleet1 ); 
		System.out.println ( "fleet12="+fleet2 );
		
		System.out.println( "fleet1 owner="+fleet1.getNation().getNationId() );
		System.out.println( "fleet2 owner="+(fleet2.getNation() != null ? fleet2.getNation().getNationId(): "nulas") );
	}
	
	@Test
	@Ignore
	public void testGetFleetById() {
		DFleet fleet1=fleetDao.getFleet( 1 );
		System.out.println ( "fleet1="+fleet1.getName() ); 
		
	}
	
	@Test
	@Ignore
	public void testDeleteFleet() {
		// TODO
		System.out.println ( "testDeleteFleet called" );
		boolean result = fleetDao.updateDeletedFlag(21, true);
		System.out.println ( "result="+result );
	}
}
