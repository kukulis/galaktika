package lt.gt.galaktika.core.test;

import org.junit.Test;

import lt.gt.math.Utils;

public class TestPow
{
	@Test
	public void testPow() {
		System.out.println( ((double) Math.round( Utils.pow(4,1.5) * 100 )) / 100);
	}
}
