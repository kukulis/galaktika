package lt.gt.galaktika.data.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lt.gt.galaktika.data.IFleetsSpace;
import lt.gt.galaktika.data.ISpaceTravelerFleet;

public class FleetsSpace implements IFleetsSpace
{
	private Map <Long, ISpaceTravelerFleet> fleetsMap = new HashMap<>();

	@Override
	public Collection<ISpaceTravelerFleet> getFleets ()
	{
		return fleetsMap.values();
	}

	@Override
	public ISpaceTravelerFleet getFleet ( long id )
	{
		return fleetsMap.get( id );
	}
	
}
