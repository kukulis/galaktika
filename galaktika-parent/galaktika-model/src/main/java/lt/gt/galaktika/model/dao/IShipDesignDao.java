package lt.gt.galaktika.model.dao;

import lt.gt.galaktika.model.entity.noturn.DShipDesign;

public interface IShipDesignDao {
	// TODO owrner in parameters
	DShipDesign findShipDesign ( String name, double attackMass, int guns, double defenseMass, double cargoMass, double engineMass );
}
