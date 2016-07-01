package lt.gt.galaktika.model.dao;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.entity.DShip;
import lt.gt.galaktika.model.entity.DShipGroup;

public interface IShipGroupDao
{

	public void test();
	
	public DShipGroup getShipGroup ( long shipGroupId );
	public long saveShipGroup (DShipGroup shipGroup);
	public DataSearchResult <DShipGroup> loadShipGroupPortion(DataSearchLimits li);
	
	public DShip getShip ( long shipId );
	public long saveShip ( DShip ship );
	public DataSearchResult <DShip> loadShipsPortion(DataSearchLimits li);
	
//	public 
}
