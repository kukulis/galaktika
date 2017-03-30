package lt.gt.galaktika.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.dao.ITechnologiesDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.turn.DTechnologies;

public class TechnologiesService extends AbstractGalaktikaService<DTechnologies, Technologies>{

	@Autowired
	ITechnologiesDao technologiesDao;
	
	@Override
	public DTechnologies createDbObject() {
		return new DTechnologies();
	}

	@Override
	public Technologies createCoreObject() {
		return new Technologies();
	}

	@Override
	public Class<DTechnologies> getDClazz() {
		return DTechnologies.class;
	}
	
	public Technologies getNationTechnologies ( Nation n, int turn ) {
		return mapToCoreObject(technologiesDao.getNationTechnologies(n.getNationId(), turn) );
	}
	
	public Technologies createTechnologies ( Technologies t, Nation n, int turn ) {
		DTechnologies dt = mapToDbObject(t);
		DNation dn = dao.find(DNation.class, n.getNationId());
		dt.setOwner(dn);
		dt.setTurnNumber( turn );
		dt = dao.create( dt );
		return mapToCoreObject(dt);
	}
}
