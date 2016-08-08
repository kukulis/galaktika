package lt.gt.galaktika.model.dao;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.FleetSortData;
import lt.gt.galaktika.model.dao.impl.DFleetFilter;
import lt.gt.galaktika.model.entity.noturn.DFleet;

@Deprecated
public interface IFleetDaoOld
{
	public long save (DFleet fleet);
	public DFleet update (DFleet fleet);
	public DataSearchResult <DFleet> loadPortion(DataSearchLimits li, DFleetFilter filter, FleetSortData fsd );
	
	public DFleet getFleet ( long id );
	public DFleet getFleet ( long id, long nationId );
	public boolean updateDeletedFlag ( long fleetId, boolean value );
	
	public DFleet getFleetWithShips ( long id );
	
	public void flush();
	
	
}
