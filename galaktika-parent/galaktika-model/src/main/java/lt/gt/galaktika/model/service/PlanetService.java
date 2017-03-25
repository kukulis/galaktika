package lt.gt.galaktika.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.model.dao.IGalaxyDao;
import lt.gt.galaktika.model.dao.IPlanetDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.exception.GalaktikaModelException;

public class PlanetService extends AbstractGalaktikaService<DPlanet, Planet>{
	
	@Autowired
	IPlanetDao planetDao;
	
//	@Autowired
//	IGalaxyDao galaxyDao;
	
	@Autowired
	GalaxyService galaxyService;
	
	@Override
	public DPlanet createDbObject() {
		return new DPlanet();
	}

	@Override
	public Planet createCoreObject() {
		return new Planet();
	}
	
	public Planet createPlanet ( Planet p, Galaxy g ) {
		DPlanet dp = mapToDbObject(p);
		DGalaxy dg = dao.find(DGalaxy.class, g.getGalaxyId());
		if ( dg == null )
			throw new GalaktikaModelException("Can't load galaxy by id="+g.getGalaxyId() );
		dp.setGalaxy(dg);
		
		DPlanet dp2 = dao.create( dp );
		return mapToCoreObject( dp2 );
	}

	@Override
	@Deprecated
	public Planet create(Planet c) {
		throw new GalaktikaModelException("PlanetService.create deprecated. Use createPlanet.");
	}
	
	public List<Planet> findPlanets(Galaxy g) {
		List<DPlanet> dPlanets = planetDao.getGalaxyPlanets(galaxyService.mapToDbObject(g));
		return dPlanets.stream().map(dp -> mapToCoreObject(dp)).collect(Collectors.toList());
	}
}
