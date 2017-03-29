package lt.gt.galaktika.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.model.NationsFilter;
import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.exception.GalaktikaModelException;

@Service
public class NationService extends AbstractGalaktikaService<DNation, Nation> {

	@Autowired
	GalaxyService galaxyService;
	
	@Autowired
	INationDao nationDao;
	
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
		List<DNation> dNations = nationDao.find(new NationsFilter().setUserId(u.getId()).setGalaxyId(g.getGalaxyId()));
		if ( dNations.size() > 0 ) 
			return mapToCoreObject( dNations.get(0) );
		else
			return null;
	}
	
	public List<Nation> getNations ( User u ) {
		List<DNation> dNations = nationDao.find(new NationsFilter().setUserId(u.getId()));
		return dNations.stream().map( dn -> mapToCoreObject(dn)).collect(Collectors.toList());
	}
	
	public List<Nation> getNations ( Galaxy g ) {
		List<DNation> dNations = nationDao.find(new NationsFilter().setGalaxyId(g.getGalaxyId()));
		return dNations.stream().map( dn -> mapToCoreObject(dn)).collect(Collectors.toList());
	}

	@Override
	@Deprecated // do not use this for creating nation. Use createNation instead.
	public Nation create(Nation c) {
		throw new GalaktikaModelException( "The method 'create' in the NationService is deprecated" );
	}

	@Override
	public Class<DNation> getDClazz() {
		return DNation.class;
	}
}
