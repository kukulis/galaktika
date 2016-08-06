package lt.gt.galaktika.model.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;
import lt.gt.galaktika.model.entity.noturn.DShip;

@Service
public class ShipService extends AbstractGalaktikaService<DShip, Ship> {
	final static Logger LOG = LoggerFactory.getLogger(ShipService.class);

	public Ship storeShip(Ship ship) {
		// 1 map DShip from ship

		return ship;
	}

	@Override
	public DShip createDbObject() {
		return new DShip();
	}

	@Override
	public Ship createCoreObject() {
		return new Ship();
	}
}
