package lt.gt.galaktika.model.dao;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.entity.DFleet;

public interface IFleetDao
{
	public long save (DFleet fleet);
	public DataSearchResult <DFleet> loadPortion(DataSearchLimits li, DFleetFilter filter );
	
	public DFleet getFleet ( long id, long nationId );
}
