package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.IShipContainerFleet;
import lt.gt.galaktika.data.ISpaceTravelerFleet;

public class FullFleet extends SimpleFleet
{
	private IShipContainerFleet shipContainerFleet;
	private ISpaceTravelerFleet spaceTravelerFleet;
	
	public IShipContainerFleet getShipContainerFleet ()
	{
		return shipContainerFleet;
	}
	public void setShipContainerFleet ( IShipContainerFleet shipContainerFleet )
	{
		this.shipContainerFleet = shipContainerFleet;
	}
	public ISpaceTravelerFleet getSpaceTravelerFleet ()
	{
		return spaceTravelerFleet;
	}
	public void setSpaceTravelerFleet ( ISpaceTravelerFleet spaceTravelerFleet )
	{
		this.spaceTravelerFleet = spaceTravelerFleet;
	}
	
	
	
}
