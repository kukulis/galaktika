package lt.gt.galaktika.services;

import lt.gt.galaktika.data.IFleet;
import lt.gt.galaktika.data.IFullFleet;
import lt.gt.galaktika.data.IShipContainerFleet;
import lt.gt.galaktika.data.ISpaceTravelerFleet;

public interface IFleetService extends IGalaktikaService
{
	public final static byte MODE_SIMPLE=0;
	public final static byte MODE_SHIP_CONTAINER=1;
	public final static byte MODE_SPACE_TRAVELER=2;
	public final static byte MODE_FULL=15;
	
	public IFleet getFleet ( long fleetId );
	public IShipContainerFleet getShipContainerFleet(long fleetId );
	public ISpaceTravelerFleet getSpaceTravelerFleet(long fleetId );
	public IFullFleet getFullFleet ( long fleetId, byte mode );
}
