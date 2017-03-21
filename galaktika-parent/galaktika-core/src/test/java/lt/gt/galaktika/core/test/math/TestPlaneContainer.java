package lt.gt.galaktika.core.test.math;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import lt.gt.math.Circle;
import lt.gt.math.IPlaneContainer;
import lt.gt.math.PlanePoint;
import lt.gt.math.Rectangle;
import lt.gt.math.SimplePlaneContainer;
import lt.gt.math.fuzzy.SimplePlanePoint;

public class TestPlaneContainer {
	
	@Test
	public void testSimpleRectangles () {
		testRectangles( new SimplePlaneContainer() );
	}
 	
	@Test
	public void testSimpleCircles () {
		testCircles( new SimplePlaneContainer() );
	}
	
	private void testRectangles ( IPlaneContainer pc ) {
		// 1) Generate points
				for ( int x=-10; x <=10; x ++ )
					for ( int y =-10; y<=10;y++)
						pc.add(new SimplePlanePoint(x,y));
				
				// 2) Take multiple rectangles
				Rectangle r1 = new Rectangle( -1,-1, 1, 1 );
				Rectangle r2 = new Rectangle( -11,-11, 1, 1 );
				Rectangle r3 = new Rectangle( -1,-1, 11, 11 );
				Rectangle r4 = new Rectangle( -12,-12, -11, -11 ); // empty result
				Rectangle r5 = new Rectangle( -8, 1, 1, 8 );
				Rectangle r6 = new Rectangle( 1, -12, 8, -1);
				
				// 3) Generate expected results and make assertions
				List<PlanePoint> expected1 = new ArrayList<>();
				List<PlanePoint> expected2 = new ArrayList<>();
				List<PlanePoint> expected3 = new ArrayList<>();
				List<PlanePoint> expected4 = new ArrayList<>();
				List<PlanePoint> expected5 = new ArrayList<>();
				List<PlanePoint> expected6 = new ArrayList<>();
				
				for ( int x=-1; x <=1; x++ )
					for ( int y=-1; y<=1; y++)
						expected1.add(new SimplePlanePoint(x, y));
				
				for ( int x=-10; x <=1; x++ )
					for ( int y=-10; y<=1; y++)
						expected2.add(new SimplePlanePoint(x, y));

				for ( int x=-1; x <=10; x++ )
					for ( int y=-1; y<=10; y++)
						expected3.add(new SimplePlanePoint(x, y));

				// expected4 remains empty
				
				for ( int x=-8; x <=1; x++ )
					for ( int y=1; y<=8; y++)
						expected5.add(new SimplePlanePoint(x, y));
				
				for ( int x=1; x <=8; x++ )
					for ( int y=-10; y<=-1; y++)
						expected6.add(new SimplePlanePoint(x, y));
				
				Assert.assertArrayEquals(expected1.toArray(), pc.getRectanglePoints(r1.getMinPoint(), r1.getMaxPoint()).toArray());
				Assert.assertArrayEquals(expected2.toArray(), pc.getRectanglePoints(r2.getMinPoint(), r2.getMaxPoint()).toArray());
				Assert.assertArrayEquals(expected3.toArray(), pc.getRectanglePoints(r3.getMinPoint(), r3.getMaxPoint()).toArray());
				Assert.assertArrayEquals(expected4.toArray(), pc.getRectanglePoints(r4.getMinPoint(), r4.getMaxPoint()).toArray());
				Assert.assertArrayEquals(expected5.toArray(), pc.getRectanglePoints(r5.getMinPoint(), r5.getMaxPoint()).toArray());
				Assert.assertArrayEquals(expected6.toArray(), pc.getRectanglePoints(r6.getMinPoint(), r6.getMaxPoint()).toArray());
	}
	
	private void testCircles (IPlaneContainer pc) {
		for ( int x=-10; x <=10; x ++ )
			for ( int y =-10; y<=10;y++)
				pc.add(new SimplePlanePoint(x,y));
		
		Circle c1 = new Circle(0,0, 0.9);
		Circle c2 = new Circle(-10,-10, 0.9);
		Circle c3 = new Circle(11,11, 1.5);
		Circle c4 = new Circle(0,0, 20);
		Circle c5 = new Circle (1,1, 5);
		Circle c6 = new Circle (20,20, 5);
		
		List<PlanePoint> expected1 = new ArrayList<>();
		List<PlanePoint> expected2 = new ArrayList<>();
		List<PlanePoint> expected3 = new ArrayList<>();
		List<PlanePoint> expected4 = new ArrayList<>();
		List<PlanePoint> expected5 = new ArrayList<>();
		List<PlanePoint> expected6 = new ArrayList<>();
		
		expected1.add( new SimplePlanePoint(0,0));
		expected2.add( new SimplePlanePoint(-10,-10));
		expected3.add( new SimplePlanePoint(10,10));
		expected4.addAll(pc.getAllPoints() );
		
		expected5.add( new SimplePlanePoint(-4,1));
		
		expected5.add( new SimplePlanePoint(-3,-2));
		expected5.add( new SimplePlanePoint(-3,-1));
		expected5.add( new SimplePlanePoint(-3,0));
		expected5.add( new SimplePlanePoint(-3,1));
		expected5.add( new SimplePlanePoint(-3,2));
		expected5.add( new SimplePlanePoint(-3,3));
		expected5.add( new SimplePlanePoint(-3,4));

		expected5.add( new SimplePlanePoint(-2,-3));
		expected5.add( new SimplePlanePoint(-2,-2));
		expected5.add( new SimplePlanePoint(-2,-1));
		expected5.add( new SimplePlanePoint(-2,0));
		expected5.add( new SimplePlanePoint(-2,1));
		expected5.add( new SimplePlanePoint(-2,2));
		expected5.add( new SimplePlanePoint(-2,3));
		expected5.add( new SimplePlanePoint(-2,4));
		expected5.add( new SimplePlanePoint(-2,5));
		
		expected5.add( new SimplePlanePoint(-1,-3));
		expected5.add( new SimplePlanePoint(-1,-2));
		expected5.add( new SimplePlanePoint(-1,-1));
		expected5.add( new SimplePlanePoint(-1,0));
		expected5.add( new SimplePlanePoint(-1,1));
		expected5.add( new SimplePlanePoint(-1,2));
		expected5.add( new SimplePlanePoint(-1,3));
		expected5.add( new SimplePlanePoint(-1,4));
		expected5.add( new SimplePlanePoint(-1,5));

		expected5.add( new SimplePlanePoint(0,-3));
		expected5.add( new SimplePlanePoint(0,-2));
		expected5.add( new SimplePlanePoint(0,-1));
		expected5.add( new SimplePlanePoint(0,0));
		expected5.add( new SimplePlanePoint(0,1));
		expected5.add( new SimplePlanePoint(0,2));
		expected5.add( new SimplePlanePoint(0,3));
		expected5.add( new SimplePlanePoint(0,4));
		expected5.add( new SimplePlanePoint(0,5));

		expected5.add( new SimplePlanePoint(1,-4));
		expected5.add( new SimplePlanePoint(1,-3));
		expected5.add( new SimplePlanePoint(1,-2));
		expected5.add( new SimplePlanePoint(1,-1));
		expected5.add( new SimplePlanePoint(1,0));
		expected5.add( new SimplePlanePoint(1,1));
		expected5.add( new SimplePlanePoint(1,2));
		expected5.add( new SimplePlanePoint(1,3));
		expected5.add( new SimplePlanePoint(1,4));
		expected5.add( new SimplePlanePoint(1,5));
		expected5.add( new SimplePlanePoint(1,6));

		expected5.add( new SimplePlanePoint(2,-3));
		expected5.add( new SimplePlanePoint(2,-2));
		expected5.add( new SimplePlanePoint(2,-1));
		expected5.add( new SimplePlanePoint(2,0));
		expected5.add( new SimplePlanePoint(2,1));
		expected5.add( new SimplePlanePoint(2,2));
		expected5.add( new SimplePlanePoint(2,3));
		expected5.add( new SimplePlanePoint(2,4));
		expected5.add( new SimplePlanePoint(2,5));
		
		expected5.add( new SimplePlanePoint(3,-3));
		expected5.add( new SimplePlanePoint(3,-2));
		expected5.add( new SimplePlanePoint(3,-1));
		expected5.add( new SimplePlanePoint(3,0));
		expected5.add( new SimplePlanePoint(3,1));
		expected5.add( new SimplePlanePoint(3,2));
		expected5.add( new SimplePlanePoint(3,3));
		expected5.add( new SimplePlanePoint(3,4));
		expected5.add( new SimplePlanePoint(3,5));

		expected5.add( new SimplePlanePoint(4,-3));
		expected5.add( new SimplePlanePoint(4,-2));
		expected5.add( new SimplePlanePoint(4,-1));
		expected5.add( new SimplePlanePoint(4,0));
		expected5.add( new SimplePlanePoint(4,1));
		expected5.add( new SimplePlanePoint(4,2));
		expected5.add( new SimplePlanePoint(4,3));
		expected5.add( new SimplePlanePoint(4,4));
		expected5.add( new SimplePlanePoint(4,5));

		expected5.add( new SimplePlanePoint(5,-2));
		expected5.add( new SimplePlanePoint(5,-1));
		expected5.add( new SimplePlanePoint(5,0));
		expected5.add( new SimplePlanePoint(5,1));
		expected5.add( new SimplePlanePoint(5,2));
		expected5.add( new SimplePlanePoint(5,3));
		expected5.add( new SimplePlanePoint(5,4));
		
		expected5.add( new SimplePlanePoint(6,1));
		
		// expected6 stays as it is
		
		Assert.assertArrayEquals( expected1.toArray(), pc.getCirclePoints(c1.getCenter(), c1.getRadius()).toArray());
		Assert.assertArrayEquals( expected2.toArray(), pc.getCirclePoints(c2.getCenter(), c2.getRadius()).toArray());
		Assert.assertArrayEquals( expected3.toArray(), pc.getCirclePoints(c3.getCenter(), c3.getRadius()).toArray());
		Assert.assertArrayEquals( expected4.toArray(), pc.getCirclePoints(c4.getCenter(), c4.getRadius()).toArray());
		Assert.assertArrayEquals( expected5.toArray(), pc.getCirclePoints(c5.getCenter(), c5.getRadius()).toArray());
		Assert.assertArrayEquals( expected6.toArray(), pc.getCirclePoints(c6.getCenter(), c6.getRadius()).toArray());

	}
}
