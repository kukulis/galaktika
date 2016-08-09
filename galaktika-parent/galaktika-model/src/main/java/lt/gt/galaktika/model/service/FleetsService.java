package lt.gt.galaktika.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DFleetData;
import lt.gt.galaktika.model.entity.turn.DShipGroup;

@Service
public class FleetsService {
	
	final static Logger LOG = LoggerFactory.getLogger(FleetsService.class);


	@Autowired
	@Qualifier("dao")
	IDAO dao;

	@Autowired
	IFleetDataDao dFleetDataDao;

	@Autowired
	IFleetDao dFleetDao;
	
	@Autowired 
	NationService nationService;
	
	@Autowired
	ShipService shipService;
	

	/**
	 * If fleetId is 0, then create new one, else update existing.
	 * 
	 * @param fleet
	 * @return
	 */
	public Fleet saveFleet(Fleet fleet, int turnNumber ) {

		// assume that fleet components are already stored to db.
		// need only to store the fleet himself
		// but we build DFleet object and we dont have the D* components of the
		// DFleet,
		// so we need to load them
		// then store DFleet
		// then return Fleet with the new stored fleet id

		// TODO

		DFleet dFleet = mapDFleet(fleet);
		if (fleet.getFleetId() == 0) {
			dFleet = dao.create(dFleet);
			fleet.setFleetId(dFleet.getFleetId());
		} else
			dFleet = dao.update(dFleet);

		DFleetData dFleetData = mapDFleetData(fleet, turnNumber );
		// TODO two ways
		// one - create absolutely new DFleetData object
		// second - update existing DFleetData object
		// TODO

		return fleet;
	}
	
	public Fleet loadFleet ( long fleetId, int turnNumber ) {
		DFleet dFleet = dFleetDao.find( fleetId );
		return mapFleet ( dFleet, null ); // TODO fleet data
	}
	
	

	// public Fleet updateFleet ( Fleet fleet ) {
	// // TODO similar to createFleet
	//
	// return fleet;
	// }

	protected DFleet mapDFleet(Fleet fleet) {
		DFleet dFleet = null;
		
		if ( fleet.getFleetId() == 0 )
			dFleet = new DFleet();
		else
			dFleet = dao.find( DFleet.class, fleet.getFleetId() );
		
		
		dFleet.setName(fleet.getName());
		if (fleet.getOwner() != null) {
			DNation dNation = dao.find(DNation.class, fleet.getOwner().getNationId());
			LOG.trace ( "received dNation=" + dNation );
			dFleet.setNation( dNation );
		}
		return dFleet;
	}

	protected DFleetData mapDFleetData(Fleet fleet, int turnNumber) {
		DFleetData dFleetData = new DFleetData();
		// shipGroups
		for ( ShipGroup group : fleet.getShipGroups() ) {
			// TODO
//			if ( group.get)
			// first try to load ship group by 
			
			DShip dship = shipService.mapToDbObject(group.getShip());
			DShipGroup dg = new DShipGroup(dship);
			
		}
		// planetLocation
		// spaceLocation
		// flightCommand 
		// TODO
		return dFleetData;
	}
	
	protected Fleet mapFleet ( DFleet dFleet, DFleetData dFleetData ) {
		Fleet fleet = new Fleet();
		fleet.setName( dFleet.getName() );
		fleet.setFleetId( dFleet.getFleetId() );
		fleet.setOwner( nationService.mapToCoreObject( dFleet.getNation() ) );
		
		// TODO dFleetData fields
		
		return fleet;
	}
}
