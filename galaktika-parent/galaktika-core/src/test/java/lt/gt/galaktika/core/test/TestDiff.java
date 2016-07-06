package lt.gt.galaktika.core.test;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import lt.gt.galaktika.utils.Diff;

public class TestDiff
{
	@Test
	public void testDiff () {
		List<Integer> first = Arrays.asList( 1, 2, 5, 8 );
		List<Integer> second = Arrays.asList( 0, 2, 5, 9 );
		
		Diff <Integer> diff = new Diff<Integer>().diff(first, second);
		
		System.out.println( "First only:" );
		diff.getFirstOnly().forEach( x -> System.out.print( x + " " ) );
		System.out.println();
		System.out.println( "Second only:" );
		diff.getSecondOnly().forEach( x -> System.out.print( x + " " ) );
		System.out.println();
		System.out.println ( "Both:" );
		diff.getBothFirst().forEach( x -> System.out.print( x + " " ) );
		
		System.out.println();
//		diff.getFirstOnly().
	}
}
