package lt.gt.galaktika.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.exception.GalaktikaModelException;

@Service
public class NationService extends AbstractGalaktikaService<DNation, Nation> {

	@Autowired
	GalaxyService galaxyService;
	
	@Override
	public DNation createDbObject() {
		return new DNation();
	}

	@Override
	public Nation createCoreObject() {
		return new Nation();
	}
	
	/**
	 * Nation is always related to some user and galaxy.
	 * @param u
	 * @param n
	 * @param g
	 */
	public Nation createNation (  Nation n, User u, Galaxy g ) {
		DNation dNation = mapToDbObject( n );
		dNation.setGalaxy( galaxyService.mapToDbObject( g ) );
		dNation.setUser( u );
		DNation rez =  dao.create( dNation );
		return mapToCoreObject( rez );
	}
	
	public Nation getNation ( User u, Galaxy g ) {
		// TODO
		return null;
	}
	
	public List<Nation> getNations ( User u ) {
		// TODO
		return null;
	}
	
	public List<Nation> getNations ( Galaxy g ) {
		// TODO
		return null;
	}

	@Override
	@Deprecated // do not use this for creating nation
	public Nation create(Nation c) {
		throw new GalaktikaModelException( "The method 'create' in the NationService is deprecated" );
	}
	
	
	
}
