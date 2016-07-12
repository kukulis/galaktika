package lt.gt.galaktika.core;

import lt.gt.math.Utils;

public class SpaceLocation implements GalaxyLocation
{
	public final static double EPSILON = 0.000001;
	private double x, y;

	public SpaceLocation()
	{
		super();
	}

	public SpaceLocation(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX ()
	{
		return x;
	}

	@Override
	public double getY ()
	{
		return y;
	}

	public void setX ( double x )
	{
		this.x = x;
	}

	public void setY ( double y )
	{
		this.y = y;
	}

	public boolean equals ( Object obj )
	{
		GalaxyLocation gl = null;
		if (obj instanceof GalaxyLocation)
			gl = (GalaxyLocation) obj;
		else
			return false;

		return Utils.same(gl.getX(), x, lt.gt.galaktika.utils.Utils.EPSILON)
				&& Utils.same(gl.getY(), y, lt.gt.galaktika.utils.Utils.EPSILON);
	}
	
	public int hashCode() {
		return Double.hashCode(x*1000+y);
	}
	
	public String toString () {
		return "Space ["+x+";"+y+"]";
	}
}
