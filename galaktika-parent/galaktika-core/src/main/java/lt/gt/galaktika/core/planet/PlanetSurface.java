package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.Nation;

public class PlanetSurface
{
	private String name;
	private Nation nation;
	private double population;
	private double industry;
	private double capital;
	
	private SurfaceCommand surfaceCommand;
	
	public Nation getNation ()
	{
		return nation;
	}
	public void setNation ( Nation nation )
	{
		this.nation = nation;
	}
	public double getPopulation ()
	{
		return population;
	}
	public void setPopulation ( double population )
	{
		this.population = population;
	}
	public double getIndustry ()
	{
		return industry;
	}
	public void setIndustry ( double industry )
	{
		this.industry = industry;
	}
	public double getCapital ()
	{
		return capital;
	}
	public void setCapital ( double capital )
	{
		this.capital = capital;
	}
	public SurfaceCommand getSurfaceCommand ()
	{
		return surfaceCommand;
	}
	public void setSurfaceCommand ( SurfaceCommand surfaceCommand )
	{
		this.surfaceCommand = surfaceCommand;
	}
	public String getName ()
	{
		return name;
	}
	public void setName ( String name )
	{
		this.name = name;
	}
}
