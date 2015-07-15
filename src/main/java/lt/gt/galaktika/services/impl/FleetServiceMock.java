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

	@Override
	public IFleet getFleet ( long fleetId )
	{
		return dataHolder.getFleetsMap().get(fleetId);
	}

	@Override
	public IFullFleet getFullFleet ( long fleetId, byte mode )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShipContainerFleet getShipContainerFleet ( long fleetId )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISpaceTravelerFleet getSpaceTravelerFleet ( long fleetId )
	{
		// TODO Auto-generated method stub
		return null;
	}

}
