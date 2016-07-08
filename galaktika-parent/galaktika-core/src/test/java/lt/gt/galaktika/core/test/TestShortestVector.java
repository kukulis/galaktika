package lt.gt.galaktika.core.test;

import org.junit.Assert;
import org.junit.Test;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.math.PlaneVector;

public class TestShortestVector
{
	@Test
	public void testFindShortest ()
	{
		System.out.println("testFindShortest called");
		// GalaxyEngine engine = new GalaxyEngine();
		// engine.
		Galaxy galaxy = new Galaxy();
		galaxy.setSizeX(10);
		galaxy.setSizeY(10);
		SpaceLocation locationA = new SpaceLocation(1, 1);
		SpaceLocation locationB = new SpaceLocation(9, 9);
		PlaneVector vector = galaxy.getShortestVector(locationA, locationB);
		// two borders
		Assert.assertEquals(new PlaneVector(-2, -2), vector);
		// direct
		Assert.assertEquals(new PlaneVector(1, 1),
				galaxy.getShortestVector(new SpaceLocation(1, 1), new SpaceLocation(2, 2)));
		// same point
		Assert.assertEquals(new PlaneVector(0, 0),
				galaxy.getShortestVector(new SpaceLocation(1, 1), new SpaceLocation(1, 1)));
		
		// horizontal border
		Assert.assertEquals(new PlaneVector(0, -2),
				galaxy.getShortestVector(new SpaceLocation(1, 1), new SpaceLocation(1, 9)));
		// vertical border
		Assert.assertEquals(new PlaneVector(-2, 0),
				galaxy.getShortestVector(new SpaceLocation(1, 1), new SpaceLocation(9, 1)));
		
		// reverse two borders
		Assert.assertEquals(new PlaneVector(2, 3),
				galaxy.getShortestVector(new SpaceLocation(9, 8), new SpaceLocation(1, 1)));
		System.out.println("testFindShortest finished");
	}
}
