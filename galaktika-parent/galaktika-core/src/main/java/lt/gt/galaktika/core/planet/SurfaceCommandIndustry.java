package lt.gt.galaktika.core.planet;

public class SurfaceCommandIndustry implements SurfaceCommand
{

	@Override
	public SurfaceActivities getActivityType ()
	{
		return SurfaceActivities.INDUSTRY;
	}

	@Override
	public boolean equals(Object obj) {
		return  obj instanceof SurfaceCommandIndustry;
	}
}
