package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.entity.noturn.DFleet;

public interface IFleetDao {
	public DFleet find ( long fleetId );
//	public List<DFleet> findFleets( long galaxyId, int fleetId);
}
