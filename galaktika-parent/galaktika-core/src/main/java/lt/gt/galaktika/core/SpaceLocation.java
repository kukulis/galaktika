package lt.gt.galaktika.core;

public class SpaceLocation implements GalaxyLocation
{
	private double x,y;
	
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
	
}
