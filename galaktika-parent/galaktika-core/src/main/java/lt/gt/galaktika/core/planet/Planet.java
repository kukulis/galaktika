package lt.gt.galaktika.core.planet;

public class Planet
{
	private double x,y;
	private double planetSize;
	private double richness=1;
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
	public double getPlanetSize ()
	{
		return planetSize;
	}
	public void setPlanetSize ( double planetSize )
	{
		this.planetSize = planetSize;
	}
	public double getRichness ()
	{
		return richness;
	}
	public void setRichness ( double richness )
	{
		this.richness = richness;
	}
}
