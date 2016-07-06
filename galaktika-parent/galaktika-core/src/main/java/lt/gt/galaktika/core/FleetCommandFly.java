package lt.gt.galaktika.core;

public class FleetCommandFly implements FleetCommand
{

	@Override
	public FleetActivities getActivityType ()
	{
		return FleetActivities.FLY;
	}
	
}
