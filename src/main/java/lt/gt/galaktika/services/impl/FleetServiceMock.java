package lt.gt.galaktika.services.impl;

import lt.gt.galaktika.data.IFleet;
import lt.gt.galaktika.data.IFullFleet;
import lt.gt.galaktika.data.IShipContainerFleet;
import lt.gt.galaktika.data.ISpaceTravelerFleet;
import lt.gt.galaktika.services.IFleetService;

public class FleetServiceMock implements IFleetService
{
	private DataHolder dataHolder;
	
	public FleetServiceMock ( DataHolder dh ) {
		dataHolder = dh;
	}

	public IFleet getFleet ( long fleetId )
	{
		return dataHolder.getFleetsMap().get(fleetId);
	}

	public IFullFleet getFullFleet ( long fleetId, byte mode )
	{
		// TODO Auto-generated method stub
		return null;
	}

	public IShipContainerFleet getShipContainerFleet ( long fleetId )
	{
		// TODO Auto-generated method stub
		return null;
	}

	public ISpaceTravelerFleet getSpaceTravelerFleet ( long fleetId )
	{
		// TODO Auto-generated method stub
		return null;
	}

}
