package lt.gt.galaktika.core.test.math;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import lt.gt.galaktika.core.exception.GalaktikaException;
import lt.gt.math.Circle;
import lt.gt.math.IPlaneContainer;
import lt.gt.math.PlanePoint;
import lt.gt.math.PlanePointComparator;
import lt.gt.math.Rectangle;
import lt.gt.math.SectoredPlaneContainer;
import lt.gt.math.SimplePlaneContainer;
import lt.gt.math.fuzzy.SimplePlanePoint;

public class TestPlaneContainer {
	
	@Test
//	@Ignore
	public void testSimpleRectangles () {
		testRectangles( new SimplePlaneContainer() );
	}
 	
	@Test
//	@Ignore
	public void testSimpleCircles () {
		testCircles( new SimplePlaneContainer() );
	}
	
	private SectoredPlaneContainer createSectoredPlane() throws GalaktikaException {
		return  new SectoredPlaneContainer(new SectoredPlaneContainer.Initializer()
				.setMinLimits(-10,  -10) 
				.setCounts(5,5)
				.setSteps(4,4)) ;
	}
	
	@Test
//	@Ignore
	public void testSectorRectangles () throws GalaktikaException {
		testRectangles( createSectoredPlane() );
	}
 	
	@Test
//	@Ignore
	public void testSectorCircles ()  throws GalaktikaException {
		testCircles( createSectoredPlane() );
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
				
				Assert.assertArrayEquals(expected1.toArray(), getSorted(pc.getRectanglePoints(r1)).toArray());
				Assert.assertArrayEquals(expected2.toArray(), getSorted(pc.getRectanglePoints(r2)).toArray());
				Assert.assertArrayEquals(expected3.toArray(), getSorted(pc.getRectanglePoints(r3)).toArray());
				Assert.assertArrayEquals(expected4.toArray(), getSorted(pc.getRectanglePoints(r4)).toArray());
				Assert.assertArrayEquals(expected5.toArray(), getSorted(pc.getRectanglePoints(r5)).toArray());
				Assert.assertArrayEquals(expected6.toArray(), getSorted(pc.getRectanglePoints(r6)).toArray());
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
		
		Assert.assertArrayEquals( expected1.toArray(), getSorted(pc.getCirclePoints(c1)).toArray());
		Assert.assertArrayEquals( expected2.toArray(), getSorted(pc.getCirclePoints(c2)).toArray());
		Assert.assertArrayEquals( expected3.toArray(), getSorted(pc.getCirclePoints(c3)).toArray());
		Assert.assertArrayEquals( expected4.toArray(), getSorted(pc.getCirclePoints(c4)).toArray());
		Assert.assertArrayEquals( expected5.toArray(), getSorted(pc.getCirclePoints(c5)).toArray());
		Assert.assertArrayEquals( expected6.toArray(), getSorted(pc.getCirclePoints(c6)).toArray());
	}
	
	@Test
	public  void testSectors () throws GalaktikaException {
		SectoredPlaneContainer pc = new SectoredPlaneContainer(new SectoredPlaneContainer.Initializer()
				.setMinLimits(-10,  -10) 
				.setCounts(5,5)
				.setSteps(4,4));
		
		for ( int x=-10; x <=10; x ++ )
			for ( int y =-10; y<=10;y++)
				pc.add(new SimplePlanePoint(x,y));
		
		// test sectors
		for ( int xi=0; xi < 5; xi ++) 
			for ( int yi=0; yi < 5; yi ++) {
				List<PlanePoint> expected = new ArrayList<>();
				int minX = -10 + xi * 4;
				int minY = -10 + yi * 4;
				int maxX = -10 + (xi+1) * 4;
				int maxY = -10 + (yi+1) * 4;
				
				if ( maxX == 10 )
					maxX = 11;
				
				if ( maxY == 10 )
					maxY = 11;
				
				for (int x=minX; x < maxX; x++)
					for ( int y=minY; y < maxY; y ++ )
						expected.add( new SimplePlanePoint(x,y));
				
				Assert.assertArrayEquals( expected.toArray(), pc.getSectorPoints(xi, yi).toArray() );
				
//				System.out.println( "Asserted "+xi+" "+yi);
			}
	}
	
	@Test
	public void testGetIndexes() throws GalaktikaException {
		
		SectoredPlaneContainer pc = new SectoredPlaneContainer(new SectoredPlaneContainer.Initializer()
				.setMinLimits(-10,  -10) 
				.setCounts(5,5)
				.setSteps(4,4));
		
		// 0       1   2  3  4   
		// -10, -6, -2, 2, 6, 10

		// 2) Take multiple rectangles
		Rectangle r1 = new Rectangle( -1,-1, 1, 1 );
		Rectangle r2 = new Rectangle( -11,-11, 1, 1 );
		Rectangle r3 = new Rectangle( -1,-1, 11, 11 );
		Rectangle r4 = new Rectangle( -12,-12, -11, -11 ); // empty result
		Rectangle r5 = new Rectangle( -8, 1, 1, 8 );
		Rectangle r6 = new Rectangle( 1, -12, 8, -1);

		
		List<SectoredPlaneContainer.SectorKey> expected1 = new ArrayList<>();
		expected1.add(new SectoredPlaneContainer.SectorKey(2,2) );
		
		List<SectoredPlaneContainer.SectorKey> expected2 = new ArrayList<>();
		for ( int xi=0; xi <= 2; xi++)
			for ( int yi=0; yi <= 2; yi++)
				expected2.add( new SectoredPlaneContainer.SectorKey(xi,yi));
		
		List<SectoredPlaneContainer.SectorKey> expected3 = new ArrayList<>();
		for ( int xi=2; xi <= 4; xi++)
			for ( int yi=2; yi <= 4; yi++)
				expected3.add( new SectoredPlaneContainer.SectorKey(xi,yi));

		List<SectoredPlaneContainer.SectorKey> expected4 = new ArrayList<>();
		expected4.add( new SectoredPlaneContainer.SectorKey(0,0));
		
		
		List<SectoredPlaneContainer.SectorKey> expected5 = new ArrayList<>();
		for ( int xi=0; xi <= 2; xi++)
			for ( int yi=2; yi <= 4; yi++)
				expected5.add( new SectoredPlaneContainer.SectorKey(xi,yi));
		
		List<SectoredPlaneContainer.SectorKey> expected6 = new ArrayList<>();
		for ( int xi=2; xi <= 4; xi++)
			for ( int yi=0; yi <= 2; yi++)
				expected6.add( new SectoredPlaneContainer.SectorKey(xi,yi));
		
	    Assert.assertArrayEquals( expected1.toArray() , pc.getRectangleKeys( r1 ).toArray() );
	    Assert.assertArrayEquals( expected2.toArray() , pc.getRectangleKeys( r2 ).toArray() );
	    Assert.assertArrayEquals( expected3.toArray() , pc.getRectangleKeys( r3 ).toArray() );
	    Assert.assertArrayEquals( expected4.toArray() , pc.getRectangleKeys( r4 ).toArray() );
	    Assert.assertArrayEquals( expected5.toArray() , pc.getRectangleKeys( r5 ).toArray() );
	    Assert.assertArrayEquals( expected6.toArray() , pc.getRectangleKeys( r6 ).toArray() );
		
	}
	
	private PlanePointComparator ppc = new PlanePointComparator();
	
	public List<PlanePoint> getSorted( List<PlanePoint> l) {
		l.sort( ppc );
		return l;
	}
}
