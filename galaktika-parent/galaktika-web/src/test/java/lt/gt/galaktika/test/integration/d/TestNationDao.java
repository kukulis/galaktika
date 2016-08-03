package lt.gt.galaktika.test.integration.d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.web.GalaktikaApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GalaktikaApplication.class)
@WebAppConfiguration
public class TestNationDao
{
	@Autowired
	private INationDao nationDao;
	
	@Test
	public void testGetNation () {
		DNation n0 = nationDao.getNation(0 );
		DNation n1 = nationDao.getNation( 1 );
		DNation n2 = nationDao.getNation( 2 );
		
		if ( n0 != null ) {
			System.out.println ( "n0.name="+n0.getNationName() );
		}
		else 
			System.out.println ( "n0 is null ");
		
		if ( n1 != null ) {
			System.out.println ( "n1.name="+n1.getNationName() );
		}
		else 
			System.out.println ( "n1 is null ");

		if ( n2 != null ) {
			System.out.println ( "n2.name="+n2.getNationName() );
		}
		else 
			System.out.println ( "n2 is null ");

	}
}
