package lt.gt.galaktika.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.dao.IPlanetSurfaceDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.entity.turn.DFleetData;
import lt.gt.galaktika.model.entity.turn.DPlanetSurface;
import lt.gt.galaktika.model.exception.PlanetSurfaceContractException;

@Service
public class PlanetDataService {
	final static Logger LOG = LoggerFactory.getLogger(PlanetDataService.class);

	@Autowired
	@Qualifier("dao")
	IDAO dao;

	@Autowired
	IFleetDataDao dFleetDataDao;

	@Autowired
	FleetsService fleetsService;

	@Autowired
	IPlanetSurfaceDao dPlanetSurfaceDao;

	@Autowired
	NationService nationService;

	public PlanetOrbit loadPlanetOrbit(long planetId, int turnNumber, boolean withGroups) {
		List<DFleetData> fleetDatas = dFleetDataDao.findInOrbit(planetId, turnNumber, withGroups);

		PlanetOrbit orbit = new PlanetOrbit();

		for (DFleetData dFleetData : fleetDatas)
			orbit.getFleets().add(fleetsService.mapFleet(dFleetData.getFleet(), withGroups ? dFleetData : null));

		return orbit;
	}

	// surface

	/**
	 * Contract: planet should be already stored to database; nation should be
	 * already stored to database;
	 * 
	 * 
	 * @param surface
	 * @param planet
	 * @param turnNumber
	 */
	public void storePlanetSurface(PlanetSurface surface, Planet planet, int turnNumber)
			throws PlanetSurfaceContractException {
		// TODO
		// lets first store without references
		DPlanetSurface dPlanetSurface = new DPlanetSurface(planet.getPlanetId(), turnNumber);
		loadContractData(dPlanetSurface, surface);
		dPlanetSurface.setName(surface.getName());
		dPlanetSurface.setPopulation( surface.getPopulation());
		dPlanetSurface.setIndustry(surface.getIndustry());
		dPlanetSurface.setCapital( surface.getCapital());

		// then store with references to SurfaceCommand TODO
		
		// then store with references to ShipFactory TODO
		
		dao.create( dPlanetSurface );
		
		// TODO update variant

	}

	public void loadContractData(DPlanetSurface dSurface, PlanetSurface surface) throws PlanetSurfaceContractException {

		DNation dNation = dao.find(DNation.class, surface.getNation().getNationId());
		if (dNation == null)
			throw new PlanetSurfaceContractException(
					"could not load dNation with id=" + surface.getNation().getNationId());
		dSurface.setOwner(dNation);

	}

}
