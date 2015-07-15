package lt.gt.galaktika.services.impl;

import java.util.HashMap;
import java.util.Map;

import lt.gt.galaktika.data.IFleet;
import lt.gt.galaktika.data.impl.SimpleFleet;

public class DataHolder
{
	private Map <Long,IFleet> fleetsMap = new HashMap<Long, IFleet>();
	long fleetSequence = 1;
	
	public DataHolder () {
		initializeFleetsMap();
	}
	
	private void initializeFleetsMap () {
		addFleet (new SimpleFleet() ); // todo full fleet
		addFleet (new SimpleFleet() );
		addFleet (new SimpleFleet() );
		addFleet (new SimpleFleet() );
		addFleet (new SimpleFleet() );
	}
	

	public void addFleet ( long fleetId, IFleet fleet ) {
		if ( fleetId == 0 ) {
			fleetId = fleetSequence ++;
		}
		else if ( fleetId > fleetSequence ) {
			fleetSequence = fleetId;
		}
		
		fleet.setID( fleetId );
		
		fleetsMap.put ( fleetId, fleet );
	}
	
	public void addFleet( IFleet fleet ) {
		addFleet( 0, fleet );
	}

	public Map<Long, IFleet> getFleetsMap ()
	{
		return fleetsMap;
	}
}
