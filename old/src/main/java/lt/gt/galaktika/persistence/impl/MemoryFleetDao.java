package lt.gt.galaktika.persistence.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lt.gt.galaktika.data.IFleet;
import lt.gt.galaktika.data.IFullFleet;
import lt.gt.galaktika.data.IShipContainer;
import lt.gt.galaktika.data.IShipContainerFleet;
import lt.gt.galaktika.data.ISpaceTraveler;
import lt.gt.galaktika.data.impl.Fleet;
import lt.gt.galaktika.data.impl.FullFleet;
import lt.gt.galaktika.persistence.IFleetDAO;

/**
 * Mintis tokia, kad floto dalys saugomos atskirai ..
 * 
 * @author giedrius
 *
 */
public class MemoryFleetDao implements IFleetDAO, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4869681154300381547L;
	
	private Map<Long, IFleet> fleetsMap = new HashMap<>();
	private Map<Long, ISpaceTraveler> spaceTravelersMap = new HashMap<>();
	private Map<Long, IShipContainer> shipContainersMap = new HashMap<>();

	// private Map <Long, IFullFleet> fullFleetsMap = new HashMap<>();

	@Override
	public Collection<IFleet> findAllFleets ()
	{
		return fleetsMap.values();
	}

	@Override
	public void create ( IFleet fleet )
	{
		if (fleet instanceof FullFleet)
		{
			FullFleet fullFleet = (FullFleet) fleet;
			fleetsMap.put(fleet.getID(), fullFleet.getFleet() );
			
			// TODO store other parts
		}
		else if (fleet instanceof Fleet)
		{
			fleetsMap.put(fleet.getID(), fleet);
		}
		else
			throw new RuntimeException("wrong class for creating fleet " + fleet.getClass().getName());

	}

	@Override
	public void delete ( IFleet fleet )
	{
		fleetsMap.remove(fleet.getID());
		spaceTravelersMap.remove( fleet.getID() );
		shipContainersMap.remove(fleet.getID() );
	}

	@Override
	public void update ( IFleet fleet )
	{
//		if (fleet instanceof FullFleet)
//		{
//			fullFleetsMap.put(fleet.getID(), (FullFleet) fleet);
//		}
//		else if (fleet instanceof Fleet)
//		{
//			fleetsMap.put(fleet.getID(), fleet);
//		}
//		else
//			throw new RuntimeException("wrong class for updating fleet " + fleet.getClass().getName());
		
		// TODO
	}

	@Override
	public <T extends IFleet> T findById ( IFleet pFleet, Class<T> c )
	{
		T returnedFleet = null;
		if (c == Fleet.class)
		{
			returnedFleet = (T) fleetsMap.get(pFleet.getID());
			// returnedFleet = (T) new Fleet(); // TODO set fields
		}
		else if (c == IShipContainerFleet.class || c == ISpaceTraveler.class || c == IFullFleet.class)
		{
			// TODO load parts depending on the c, including the base fleet
			Fleet fleet = new Fleet();
			returnedFleet = (T) new FullFleet(fleet, null, null);
		}
		else
			throw new RuntimeException("Wrong class given c=" + c.getName());

		return returnedFleet;
	}

}
