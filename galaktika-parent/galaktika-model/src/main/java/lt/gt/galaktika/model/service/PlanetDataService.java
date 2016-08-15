package lt.gt.galaktika.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.entity.turn.DFleetData;

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
	
	public PlanetOrbit loadPlanetOrbit ( long planetId, int turnNumber, boolean withGroups ) {
		List<DFleetData> fleetDatas = dFleetDataDao.findInOrbit(planetId, turnNumber, withGroups );
		
		PlanetOrbit orbit = new PlanetOrbit();
		
		for (DFleetData dFleetData : fleetDatas ) 
			orbit.getFleets().add( fleetsService.mapFleet( dFleetData.getFleet(), withGroups?dFleetData:null ) );
		
		return orbit;
	}

}
