package lt.gt.galaktika.core.test;

import org.junit.Test;

import lt.gt.galaktika.core.AggreementsObject;
import lt.gt.galaktika.core.Nation;

public class TestAggreementsObject
{
	@Test
	public void test() {
		System.out.println ( "test called" );
		
		Nation nation1 = new Nation(5, "anglai" );
		Nation nation2 = new Nation(15, "vokiečiai" );
		Nation nation3 = new Nation(11, "prancūzai" );
		
		AggreementsObject ao = new AggreementsObject();
		ao.setInWar( nation1, nation2);
		
		testIsInWar(nation1, nation2, ao);
		testIsInWar(nation2, nation1, ao);
		testIsInWar(nation2, nation3, ao);
		testIsInWar(nation1, nation3, ao);
		
		
	}
	
	private void testIsInWar ( Nation a, Nation b, AggreementsObject ao ) {
		if ( ao.isInWar( a, b))
			System.out.println ( a.getNationName() + " is in war with " + b.getNationName() ); 
		else  
			System.out.println ( a.getNationName() + " is NOT in war with " + b.getNationName() ); 
			
	}
}
