package lt.gt.galaktika.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.dao.IShipDesignDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;
import lt.gt.galaktika.model.exception.GalaktikaModelException;

public class ShipDesignService extends AbstractGalaktikaService<DShipDesign, ShipDesign>{
	
	@Autowired
	IShipDesignDao shipDesignDao;
	
	@Autowired
	INationDao nationDao;
	
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
	
	@Override
	@Deprecated
	public ShipDesign create(ShipDesign c) {
		throw new GalaktikaModelException("Deprecated. Use createShipDesign instead.");
	}
	
	public ShipDesign createShipDesign(ShipDesign sd, Nation owner) {
		DShipDesign dShipDesign = mapToDbObject(sd);
		DNation dNation = nationDao.getNation( owner.getNationId() );
		dShipDesign.setOwner(dNation);
		return mapToCoreObject(dao.create( dShipDesign ) );
	}

	public ShipDesign findShipDesign ( String name, double attackMass, int guns, double defenseMass, double cargoMass, double engineMass, Nation n ) {
		try {
			DShipDesign dShipDesign = shipDesignDao.findShipDesign( n.getNationId(), name, attackMass, guns, defenseMass, cargoMass, engineMass);
			if ( dShipDesign == null )
				return null;
			return mapToCoreObject( dShipDesign);
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		}
	}
	
	public List<ShipDesign> getNationShipDesigns ( Nation n ) {
		return shipDesignDao.findNationShipDesigns(n.getNationId()).stream().map(this::mapToCoreObject).collect(Collectors.toList());
	}
}
