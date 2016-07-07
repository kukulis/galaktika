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
	
}
