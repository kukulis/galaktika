package lt.gt.galaktika.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.model.dao.IShipDesignDao;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;

public class ShipDesignService extends AbstractGalaktikaService<DShipDesign, ShipDesign>{
	
	@Autowired
	IShipDesignDao shipDesignDao;
	
	@Override
	public DShipDesign createDbObject() {
		return new DShipDesign();
	}

	@Override
	public ShipDesign createCoreObject() {
		return new ShipDesign();
	}

	@Override
	public Class<DShipDesign> getDClazz() {
		return DShipDesign.class;
	}

//	@Override
//	public ShipDesign mapToCoreObject(DShipDesign dbObject) {
//		if ( dbObject == null )
//			return null;
//		ShipDesign shipDesign = createCoreObject();
//		shipDesign.setDesignId(dbObject.getDesignId());
//		shipDesign.setDesignName( dbObject.getDesignName());
//		shipDesign.setAttackMass(dbObject.getAttackMass());
//		shipDesign.setGuns( dbObject.getGuns());
//		shipDesign.setDefenceMass( dbObject.getDefenceMass());
//		shipDesign.setCargoMass(dbObject.getCargoMass());
//		shipDesign.setEngineMass(dbObject.getEngineMass());
//		
//		return shipDesign;
//	}
	
	// TODO owner in parameters
	public ShipDesign findShipDesign ( String name, double attackMass, int guns, double defenseMass, double cargoMass, double engineMass ) {
		try {
			DShipDesign dShipDesign = shipDesignDao.findShipDesign(name, attackMass, guns, defenseMass, cargoMass, engineMass);
			if ( dShipDesign == null )
				return null;
			return mapToCoreObject( dShipDesign);
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		}
	}
}
