package lt.gt.galaktika.core;

public class FleetCommandStay implements FleetCommand
{

	@Override
	public FleetActivities getActivityType ()
	{
		return FleetActivities.STAY;
	}

}
