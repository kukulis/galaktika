package lt.gt.galaktika.model.dao;

import lt.gt.galaktika.model.entity.turn.DFleetData;

public interface IFleetGroupDao {
	public DFleetData find ( long fleetId, int turnNumber );
	public DFleetData findWithGroups ( long fleetId, int turnNumber );
}
