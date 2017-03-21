package lt.gt.math.fuzzy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lt.gt.math.Function;

public class FuzzyFunction implements Function {
	
	private List <SimplePlanePoint> points=new ArrayList<SimplePlanePoint>();

//	@Override
	public double calculate(double parameter) {
		if ( points.size() == 0 )
			return 0;
		
		int i=0;
		double upperX = points.get(i).getX();
		double lowerX = upperX;
		while ( upperX < parameter && i < points.size() - 1 ) {
			lowerX = upperX;
			i++;
			upperX = points.get(i).getX();
		}
		
		if ( lowerX >= parameter ) {
			return points.get(i).getY();
		}
		else if ( upperX <= parameter ) {
			return points.get(i).getY();
		}
		else {
			int lowerI = i - 1;
			SimplePlanePoint lowerPoint = points.get(lowerI);
			SimplePlanePoint upperPoint = points.get(i);
			
			double result = calculatePointY ( lowerPoint, upperPoint, parameter );
			
			return result;
		}
	}

	public double calculatePointY ( SimplePlanePoint p1, SimplePlanePoint p2, double x3 ) {
		return calculateY3 ( p1.getX(), p1.getY(), p2.getX(), p2.getY(), x3 ) ;
	}
	
	private double calculateY3 ( double x1, double y1, double x2, double y2, double x3 ) {
		double y3 = y1 + (y2-y1) * ( (x3-x1) / (x2-x1) );
		return y3;
	}
	
	/**
	 * @return Read only  list of points.
	 */
	public List<SimplePlanePoint> getPoints() {
		return Collections.unmodifiableList(points);
	}
	
	public void addPoint (SimplePlanePoint point ) {
		if ( points.size() > 0 ) {
			SimplePlanePoint lastPoint = points.get( points.size() - 1 );
			if (  point.getX() <= lastPoint.getX() ) {
				throw new RuntimeException ( "Bad x order. The new point should have greatest x, but now it is "+point.getX()+ " <= " + lastPoint.getX()  );
			}
		}
		this.points.add( point );
	}
}
