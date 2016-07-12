package lt.gt.math;

public class PlaneVector
{
	public final static double EPSILON=0.000001;
	private double x, y;
	
	public PlaneVector()
	{
	}
	
	public PlaneVector ( PlanePoint source, PlanePoint target ) {
		x = target.getX() - source.getX();
		y = target.getY() - source.getY();
	}

	public PlaneVector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX ()
	{
		return x;
	}

	public void setX ( double x )
	{
		this.x = x;
	}

	public double getY ()
	{
		return y;
	}

	public void setY ( double y )
	{
		this.y = y;
	}
	
	/**
	 * 
	 * @return vector length
	 */
	public double mod() {
		return Math.sqrt( x*x + y*y );
	}

	@Override
	public String toString ()
	{
		return "["+x+";"+y+"]";
	}

	@Override
	public boolean equals ( Object obj )
	{
		PlaneVector v = (PlaneVector) obj;
		return  Utils.same(v.getX(), getX(), EPSILON ) && Utils.same(v.getY(), getY(), EPSILON ); 
	}
}
