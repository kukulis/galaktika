package lt.gt.galaktika.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.dao.IGalaxyDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.exception.GalaktikaModelException;

/**
 * Assume that there is only one active galaxy of one purpose.
 * 
 * TODO replan with multiple Galaxies later.
 *
 */
@Service
public class GalaxyService extends AbstractGalaktikaService<DGalaxy, Galaxy> {
	
	@Autowired
	IGalaxyDao galaxyDao;
	
	public Galaxy createGalaxy(Galaxy g, EGalaxyPurposes purpose, boolean active ) {
		DGalaxy dGalaxy = mapToDbObject( g );
		dGalaxy.setPurpose( purpose );
		dGalaxy.setActive( active );
		dGalaxy = dao.create( dGalaxy );
		return mapToCoreObject( dGalaxy );
	}
	
	public List<Galaxy> getGalaxies (GalaxiesFilter gFilter) {
		if ( gFilter == null )
			gFilter = new GalaxiesFilter();
		List<DGalaxy> dGalaxies = galaxyDao.find( gFilter );
		
		return dGalaxies.stream().map( dg -> mapToCoreObject(dg) ).collect(Collectors.toList());
	}

	@Override
	public DGalaxy createDbObject() {
		return new DGalaxy();
	}

	@Override
	public Galaxy createCoreObject() {
		return new Galaxy();
	}

	@Override
	@Deprecated
	public Galaxy create(Galaxy c) {
		throw new GalaktikaModelException("GalaxyService.create is deprecated, use createGalaxy instead");
	}
}
