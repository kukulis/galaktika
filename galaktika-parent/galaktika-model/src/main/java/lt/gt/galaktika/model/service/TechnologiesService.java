package lt.gt.galaktika.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.model.dao.ITechnologiesDao;
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
}
