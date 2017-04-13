package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.entity.noturn.DShipDesign;

public interface IShipDesignDao {
	DShipDesign findShipDesign ( long nationId, String name, double attackMass, int guns, double defenseMass, double cargoMass, double engineMass );
	List<DShipDesign> findNationShipDesigns(long nationId);
}
