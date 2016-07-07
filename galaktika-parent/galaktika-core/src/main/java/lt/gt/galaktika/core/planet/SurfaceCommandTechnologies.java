package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.TechnologyType;

public class SurfaceCommandTechnologies implements SurfaceCommand
{
	private TechnologyType technologyToUpgrade;
	
	public SurfaceCommandTechnologies()
	{
	}
	
	public SurfaceCommandTechnologies(TechnologyType technologyToUpgrade)
	{
		this.technologyToUpgrade = technologyToUpgrade;
	}

	public TechnologyType getTechnologyToUpgrade ()
	{
		return technologyToUpgrade;
	}

	public void setTechnologyToUpgrade ( TechnologyType technologyToUpgrade )
	{
		this.technologyToUpgrade = technologyToUpgrade;
	}

	@Override
	public SurfaceActivities getActivityType ()
	{
		return SurfaceActivities.TECHNOLOGIES;
	}
	
	
}
