package lt.gt.math.test;

import lt.gt.math.fuzzy.FuzzyFunction;
import lt.gt.math.fuzzy.FuzzyPoint;

import org.junit.Assert;
import org.junit.Test;


public class TestFuzzy {
//	@Test
	public void callTest () {
		System.out.println ( "callTest called" );
	}
	
	@Test
	public void testZeroPoint () {
		FuzzyFunction ff = new FuzzyFunction();
		Assert.assertEquals( 0, ff.calculate(10), 0.0001 );
		Assert.assertEquals( 0, ff.calculate(-10), 0.0001 );
	}
	
	@Test
	public void testOnePoint () {
		FuzzyFunction ff = new FuzzyFunction ();
		ff.addPoint( new FuzzyPoint ( 10,10 ));
		
		Assert.assertEquals(10, ff.calculate( -10 ), 0.0001 );
		Assert.assertEquals(10, ff.calculate( 0 ), 0.0001 );
		Assert.assertEquals(10, ff.calculate( 10 ), 0.0001 );
		Assert.assertEquals(10, ff.calculate( 12 ), 0.0001 );
	}
	
	@Test
	public void testManyPoints () {
		FuzzyFunction ff = new FuzzyFunction ();
		ff.addPoint( new FuzzyPoint ( 0,0 ));
		ff.addPoint( new FuzzyPoint ( 10,10 ));
		
		Assert.assertEquals(0, ff.calculate ( -5 ), 0.0001 );
		Assert.assertEquals(0, ff.calculate ( 0 ), 0.0001 );
		Assert.assertEquals(5, ff.calculate ( 5 ), 0.0001 );
		Assert.assertEquals(10, ff.calculate ( 10 ), 0.0001 );
		Assert.assertEquals(10, ff.calculate ( 15 ), 0.0001 );
	}
	
	@Test
	public void testWrongOrder () {
		FuzzyFunction ff = new FuzzyFunction ();
		ff.addPoint( new FuzzyPoint ( 0,0 ));
		ff.addPoint( new FuzzyPoint ( 10,10 ));
		ff.addPoint( new FuzzyPoint ( 11,5 ));
		
//		ff.getPoints().add( new FuzzyPoint ( 11,5 ) );
		
		System.out.println ( "Calculated point="+ff.calculate( 10.5 ) );
		System.out.println ( "Calculated point="+ff.calculate( 13 ) );
		System.out.println ( "Calculated point="+ff.calculate( 14 ) );
		
	}
	
	
}
