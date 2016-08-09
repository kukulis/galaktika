package lt.gt.galaktika.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.entity.noturn.DShip;

@Service
public class ShipService extends AbstractGalaktikaService<DShip, Ship> {
	final static Logger LOG = LoggerFactory.getLogger(ShipService.class);

	@Autowired
	IDAO dao;

	@Override
	public DShip createDbObject() {
		return new DShip();
	}

	@Override
	public Ship createCoreObject() {
		return new Ship();
	}
}
