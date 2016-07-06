package lt.gt.galaktika.core.planet;

public class PlanetData
{
	private PlanetSurface surface;
	private PlanetOrbit orbit;
	private Planet planet;
	
	public PlanetSurface getSurface ()
	{
		return surface;
	}
	public void setSurface ( PlanetSurface surface )
	{
		this.surface = surface;
	}
	public PlanetOrbit getOrbit ()
	{
		return orbit;
	}
	public void setOrbit ( PlanetOrbit orbit )
	{
		this.orbit = orbit;
	}
	public Planet getPlanet ()
	{
		return planet;
	}
	public void setPlanet ( Planet planet )
	{
		this.planet = planet;
	}
	
}
