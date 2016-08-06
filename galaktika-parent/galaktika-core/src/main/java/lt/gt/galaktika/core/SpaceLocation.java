package lt.gt.galaktika.core;

import lt.gt.math.Utils;

public class SpaceLocation implements GalaxyLocation
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7270372132044813689L;
	public final static double EPSILON = 0.000001;
	private long locationId;
	private double x, y;

	public SpaceLocation()
	{
	}

	public SpaceLocation(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public SpaceLocation(long locationId, double x, double y) {
		this.locationId = locationId;
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

	@Override
	public String toString() {
		return "SpaceLocation [locationId=" + locationId + ", x=" + x + ", y=" + y + "]";
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	
//	public String toString () {
//		return "Space ["+x+";"+y+"]";
//	}
	
}
