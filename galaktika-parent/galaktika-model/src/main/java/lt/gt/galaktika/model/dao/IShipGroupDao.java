package lt.gt.galaktika.model.dao;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DShipGroup;

public interface IShipGroupDao
{
	public void test();
	public DShipGroup getShipGroup ( long shipGroupId );
	public long saveShipGroup (DShipGroup shipGroup);
	public DShipGroup updateShipGroup (DShipGroup shipGroup );
	public void deleteShipGroup (DShipGroup shipGroup );
	public DataSearchResult <DShipGroup> loadShipGroupPortion(DataSearchLimits li);
	
	public DShip getShip ( long shipId );
	public long saveShip ( DShip ship );
	public DataSearchResult <DShip> loadShipsPortion(DataSearchLimits li);
	public void flush();
	
	DShip findShip (String name, double attack, int guns, double defence, double cargo, double speed, double totalMass);

}
