package lt.gt.galaktika.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.dao.ITechnologiesDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.turn.DTechnologies;
import lt.gt.galaktika.model.exception.GalaktikaModelException;

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
		try {
			return mapToCoreObject(technologiesDao.getNationTechnologies(n.getNationId(), turn) );
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		}
	}
	
	public Technologies createTechnologies ( Technologies t, Nation n, int turn ) {
		if ( turn == 0 )
			throw new GalaktikaModelException("Can't create technology in to turn 0"); // ?
		DTechnologies dt = mapToDbObject(t);
		DNation dn = dao.find(DNation.class, n.getNationId());
		dt.setOwner(dn);
		dt.setTurnNumber( turn );
		dt = dao.create( dt );
		return mapToCoreObject(dt);
	}
	
	@Override 
	@Deprecated
	public Technologies create(Technologies c) {
		throw new GalaktikaModelException("use createTechnologies instead");
	}

	@Override
	public Technologies mapToCoreObject(DTechnologies dbObject) {
		Technologies result = new Technologies();
		result.setTechnologiesId( dbObject.getTechnologiesId() );
		result.setAttack( dbObject.getAttack() );
		result.setDefence( dbObject.getDefence() );
		result.setCargo( dbObject.getCargo() );
		result.setEngines( dbObject.getEngines() );
		
		return result;
	}
	
	
}
