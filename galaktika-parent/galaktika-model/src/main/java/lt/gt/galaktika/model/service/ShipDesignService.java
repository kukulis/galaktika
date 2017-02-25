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

	@Override
	public ShipDesign mapToCoreObject(DShipDesign dbObject) {
		if ( dbObject == null )
			return null;
		ShipDesign shipDesign = createCoreObject();
		shipDesign.setDesignId(dbObject.getDesignId());
		shipDesign.setDesignName( dbObject.getDesignName());
		shipDesign.setAttackMass(dbObject.getAttackMass());
		shipDesign.setGuns( dbObject.getGuns());
		shipDesign.setDefenceMass( dbObject.getDefenceMass());
		shipDesign.setCargoMass(dbObject.getCargoMass());
		shipDesign.setEngineMass(dbObject.getEngineMass());
		
		return shipDesign;
	}
	
	
}
