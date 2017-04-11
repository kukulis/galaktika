package lt.gt.galaktika.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.entity.noturn.DShip;

@Service
public class ShipService extends AbstractGalaktikaService<DShip, Ship> {
	final static Logger LOG = LoggerFactory.getLogger(ShipService.class);

	@Autowired
	IShipGroupDao dShipGroupDao;

	@Override
	public DShip createDbObject() {
		return new DShip();
	}

	@Override
	public Ship createCoreObject() {
		return new Ship();
	}

	@Override
	public Class<DShip> getDClazz() {
		return DShip.class;
	}

//	@Override
//	public Ship mapToCoreObject(DShip dShip) {
//		Ship ship = createCoreObject();
//		ship.setAttack( dShip.getAttack());
//		ship.setBuildTurnId( dShip.getBuildTurnId() );
//		ship.setCargo( dShip.getCargo() );
//		ship.setDefence( dShip.getDefence());
//		ship.setGuns(dShip.getGuns() );
//		ship.setId( dShip.getId());
//		ship.setName(dShip.getName());
//		ship.setSpeed(dShip.getSpeed());
//		ship.setTotalMass(dShip.getTotalMass());
//		return ship;
//	}
	
	public Ship findShip ( String name, double attack, int guns, double defence, double cargo, double speed, double totalMass ) {
		try {
			DShip dShip = dShipGroupDao.findShip ( name, attack, guns, defence, cargo, speed, totalMass );
			if ( dShip == null )
				return null;
			return mapToCoreObject( dShip );
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		}
	}
}
