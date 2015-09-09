package lt.gt.galaktika.persistence.impl;

import java.util.Collection;

import lt.gt.galaktika.data.IFleet;
import lt.gt.galaktika.data.IFullFleet;
import lt.gt.galaktika.data.IShipContainerFleet;
import lt.gt.galaktika.data.ISpaceTraveler;
import lt.gt.galaktika.data.impl.Fleet;
import lt.gt.galaktika.data.impl.FullFleet;
import lt.gt.galaktika.persistence.IFleetDAO;

public class MemoryFleetDao implements IFleetDAO
{


	@Override
	public <T extends IFleet> Collection<T> findAllFleets ( Class<T> c )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create ( IFleet fleet )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete ( IFleet fleet )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update ( IFleet fleet )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends IFleet> T findById ( IFleet pFleet, Class<T> c )
	{
		T returnedFleet = null;
		if ( c == Fleet.class ) {
			
			returnedFleet = (T) new Fleet(); // TODO set fields
		}
		else if (c==IShipContainerFleet.class || c==ISpaceTraveler.class || c==IFullFleet.class ) {
			// TODO load parts depending on the c, including the base fleet
			Fleet fleet = new Fleet();
			returnedFleet = (T) new FullFleet(fleet, null, null ); 
		}
		else throw new RuntimeException ( "Wrong class given c="+c.getName() );
		
		return returnedFleet;
	}

	
}
