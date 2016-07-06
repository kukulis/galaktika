package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.TechnologyType;

public class SurfaceCommandUpgrade implements SurfaceCommand
{
	private TechnologyType technologyToUpgrade;

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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
