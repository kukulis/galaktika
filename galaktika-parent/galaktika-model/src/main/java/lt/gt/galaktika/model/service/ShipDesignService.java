package lt.gt.galaktika.model.service;

import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;

public class ShipDesignService extends AbstractGalaktikaService<DShipDesign, ShipDesign>{

	@Override
	public DShipDesign createDbObject() {
		return new DShipDesign();
	}

	@Override
	public ShipDesign createCoreObject() {
		return new ShipDesign();
	}
}
