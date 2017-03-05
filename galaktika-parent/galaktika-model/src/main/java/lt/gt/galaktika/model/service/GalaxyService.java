package lt.gt.galaktika.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.model.dao.IGalaxyDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.exception.GalaktikaModelException;

/**
 * Assume that there is only one active galaxy of one purpose.
 * 
 *
 *
 */
@Service
public class GalaxyService extends AbstractGalaktikaService<DGalaxy, Galaxy> {
	
	@Autowired
	IGalaxyDao galaxyDao;
	
	public void createPlayGalaxy(Galaxy g) {
		DGalaxy dGalaxy = mapToDbObject( g );
		dGalaxy.setPurpose( EGalaxyPurposes.PLAY );
		dGalaxy.setActive( true );
		dao.create( dGalaxy );
	}
	
	/**
	 * Should find one active galaxy with the given purpose.
	 * If there are more than one active galaxy with that purpose, then throw exception.
	 * @return
	 */
	public Galaxy getGalaxy () {
		List<DGalaxy> dGalaxies = galaxyDao.find( EGalaxyPurposes.PLAY );
		if ( dGalaxies.size() == 0  ) {
			throw new GalaktikaModelException("No active galaktika object with purpose "+EGalaxyPurposes.PLAY );
		}
		if ( dGalaxies.size() > 1  ) {
			throw new GalaktikaModelException("Too mutch active galaktika object with purpose "+EGalaxyPurposes.PLAY+"("+dGalaxies.size()+")" );
		}
		return mapToCoreObject( dGalaxies.get(0));
	}

	@Override
	public DGalaxy createDbObject() {
		return new DGalaxy();
	}

	@Override
	public Galaxy createCoreObject() {
		return new Galaxy();
	}
}
