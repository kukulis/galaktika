package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.entity.turn.DFleetData;

public interface IFleetDataDao {
	public DFleetData find ( long fleetId, int turnNumber );
	public DFleetData findWithGroupsAndLocations ( long fleetId, int turnNumber );
	public List<DFleetData> findInOrbit ( long planetId, int turnNumber, boolean withGroups );
}
