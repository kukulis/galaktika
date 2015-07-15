package lt.gt.sgalaktika.space.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.space.IFleetArray;

public class SimpleFleetArray implements IFleetArray {

	private long maxFleetId=1;
	
	private Map<Long, Fleet> fleetsMap = new HashMap<Long, Fleet>();
	
	@Override
	public Fleet findFleet(long fleetId) {
		return fleetsMap.get(fleetId);
	}

	@Override
	public Collection<Fleet> getAllFleets() {
		return fleetsMap.values();
	}

	@Override
	public Collection<Long> getAllFleetsIds() {
		return fleetsMap.keySet();
	}

	@Override
	public long addFleet(Fleet fleet) {
		if ( fleet.getId() == 0 ) {
			fleet.setId(maxFleetId++);
		}
		
		fleetsMap.put(fleet.getId(), fleet);
		return fleet.getId();
	}

}
