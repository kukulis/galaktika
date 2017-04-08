package lt.gt.math;

public class Utils {
	public static double sqrt3 ( double f ) {
		return Math.exp( Math.log( f ) / 3 );
	}
	
	public static double power3 ( double f ) {
		return f * f * f;
	}
	
	public static double pow ( double d, double p ) {
		double log =   Math.log( d );
		return Math.exp( log * p );
	}
	
	public static double avg(double x, double y) {
		return (x+y) / 2;
	}
	
	public static double limit ( double given, double limit ) {
		if ( given < limit )
			return given;
		else
			return limit;
	}
	
	public static boolean same ( double a, double b, double epsilon ) {
		return Math.abs( a-b ) < epsilon;
	}
	
	public static boolean same ( PlanePoint point, double x, double y, double epsilon ) {
		return same ( point.getX(), x, epsilon ) && same ( point.getY(), y, epsilon );
	}
	
	public static int calculateSector ( long minSectorValue, long step, int maxSectors, double value ) {
		if ( value <= minSectorValue )
			return 0;
		long longValue = (long) value;
		long sector = (longValue-minSectorValue) / step;
		if ( sector >= maxSectors )
			return maxSectors - 1;
		else
			return (int) sector;
	}
	
	public static boolean between0And1 ( double val, double epsilon ) {
		return val > epsilon && val < 1 - epsilon;
	}
}
