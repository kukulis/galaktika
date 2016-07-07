package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.GalaxyLocation;

public class Planet implements GalaxyLocation
{
	private double x,y;
	private double planetSize=100;
	private double richness=1;
	
	public Planet()
	{
	}
	
	public Planet(double x, double y, double planetSize, double richness)
	{
		this.x = x;
		this.y = y;
		this.planetSize = planetSize;
		this.richness = richness;
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
