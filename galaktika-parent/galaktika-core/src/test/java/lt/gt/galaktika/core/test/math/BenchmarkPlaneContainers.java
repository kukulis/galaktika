package lt.gt.galaktika.core.test.math;

import org.junit.Test;

import lt.gt.galaktika.core.exception.GalaktikaException;
import lt.gt.math.IPlaneContainer;
import lt.gt.math.Rectangle;
import lt.gt.math.SectoredPlaneContainer;
import lt.gt.math.SimplePlaneContainer;
import lt.gt.math.fuzzy.SimplePlanePoint;

public class BenchmarkPlaneContainers {
	
	private void hugeTest(IPlaneContainer pc, double rectangleSize ) {
		// add lots of random points
		// create lots random rectangles and circles but with fixed dimensions
		// extract points by these rectangles and circles and calculate time
		
		int pointsAmount = 1000000;
		
		double minLimit = 0;
		double maxLimit = 100;
		
		double size= maxLimit - minLimit;
		
		int rectanglesAmount = 1000;
//		int circlesAmount = 1000;
		
		for ( int i = 0; i < pointsAmount; i++) {
			double x= Math.random() * size - minLimit;
			double y= Math.random() * size - minLimit;
			pc.add(new SimplePlanePoint(x, y));
		}
		
		long before = System.currentTimeMillis();
		for ( int i=0; i < rectanglesAmount; i++ ) {
			double minX= Math.random() * size - minLimit;
			double minY= Math.random() * size - minLimit;
			
			double maxX = minX + rectangleSize; 
			double maxY = minY + rectangleSize;
			
			Rectangle r = new Rectangle(minX, minY, maxX, maxY);
			pc.getRectanglePoints(r);
		}
		long after = System.currentTimeMillis();
		
		long duration = after - before;
//		int dur = (int) ( duration / 1000 );
		System.out.println( "Rectangles duration is "+duration);
	}
	
	@Test
	public void testSimpleHuge() throws GalaktikaException {
		System.out.println( "Testing sectored plane container" );
		IPlaneContainer pc = new SectoredPlaneContainer(new SectoredPlaneContainer.Initializer()
					.setMinLimits(0,  0) 
					.setCounts(20,20)
					.setSteps(5,5)) ;
		hugeTest( pc, 20 );
	}
	
	@Test
	public void testSectoredHuge() {
		System.out.println( "Testing simple plane container" );
		IPlaneContainer pc = new SimplePlaneContainer();
		hugeTest( pc, 10 );
	}
	
}
