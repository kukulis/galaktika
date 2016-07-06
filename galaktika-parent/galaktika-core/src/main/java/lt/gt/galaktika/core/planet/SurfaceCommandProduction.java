package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.Technologies;

public class SurfaceCommandProduction implements SurfaceCommand
{
	/**
	 * The last ship beeing produced, finish state.
	 * For example
	 * 0 - undone
	 * 0.4 - 40% done
	 * 1 - 100% done. Should not happen, because, when it is 100% then it is removed from production queue.
	 */
	private double done=0; // when value is 1 then it is finished
	private ShipDesign shipDesign;
	private Technologies technologies;
	
	@Override
	public SurfaceActivities getActivityType ()
	{
		return SurfaceActivities.PRODUCTION;
	}

	public double getDone ()
	{
		return done;
	}

	public void setDone ( double done )
	{
		this.done = done;
	}

	public ShipDesign getShipDesign ()
	{
		return shipDesign;
	}

	public void setShipDesign ( ShipDesign shipDesign )
	{
		this.shipDesign = shipDesign;
	}

	public Technologies getTechnologies ()
	{
		return technologies;
	}

	public void setTechnologies ( Technologies technologies )
	{
		this.technologies = technologies;
	}
}
