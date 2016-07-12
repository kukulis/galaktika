package lt.gt.galaktika.core.test;

import org.junit.Assert;
import org.junit.Test;

import lt.gt.galaktika.core.GalaxyLocation;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.core.planet.Planet;

public class TestCompareGalaxyLocations
{
	@Test
	public void test ()
	{
		System.out.println("test called");

		GalaxyLocation space1 = new SpaceLocation(10.5, 4.6);
		GalaxyLocation planet1 = new Planet(10.5, 4.6, 100, 1);
		GalaxyLocation space2 = new SpaceLocation(10.6, 4.6);
		GalaxyLocation planet2 = new Planet(10.6, 4.6, 101, 1);
		GalaxyLocation planet3 = new Planet(10.6, 4.6, 102, .5);

		Assert.assertEquals(space1, planet1);
		Assert.assertEquals(space2, planet2);
		Assert.assertEquals(planet2, planet3);

		Assert.assertNotEquals(space1, space2);
		Assert.assertNotEquals(space1, planet2);
		Assert.assertNotEquals(planet1, planet2);
		Assert.assertNotEquals(planet1, space2);
	}
}
