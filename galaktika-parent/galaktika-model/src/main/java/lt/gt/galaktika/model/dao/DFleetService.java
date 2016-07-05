package lt.gt.galaktika.model.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.model.entity.DFleet;
import lt.gt.galaktika.model.entity.DShipGroup;
import lt.gt.galaktika.utils.Diff;
import lt.gt.galaktika.utils.Utils;

@Service
public class DFleetService
{
	final static Logger LOG = LoggerFactory.getLogger(DFleetService.class);

	@Autowired
	private IFleetDao fleetDao;

	@Autowired
	private IShipGroupDao shipGroupDao;

	// public

	public int storeFleetShips ( DFleet fleet )
	{
		LOG.trace("storeFleetShips called");
		int count = 0;
		for (DShipGroup g : fleet.getShipGroups())
		{
			g.setFleet(fleet);
			shipGroupDao.saveShipGroup(g);
			count++;
		}
		return count;

	}

	public int updateFleetShips ( DFleet fleet )
	{
		LOG.trace("updateFleetShips called");
		// load fleet from database
		DFleet dbFleet = fleetDao.getFleet(fleet.getFleetId());
		// make differences
		Diff<DShipGroup> gDiff = new Diff<DShipGroup>().diff(fleet.getShipGroups(), dbFleet.getShipGroups());

		// add, update and delete different objects
		
		gDiff.getFirstOnly().forEach( g -> shipGroupDao.saveShipGroup( g ) );
		gDiff.getSecondOnly().forEach( g -> shipGroupDao.deleteShipGroup( g ) );
		
		Map<DShipGroup,DShipGroup> secondMap = Utils.createMap( gDiff.getBothSecond() );
		
		gDiff.getBothFirst().forEach( g -> { 
			DShipGroup dbG = secondMap.get(g);
			dbG.updateValues( g );
			shipGroupDao.updateShipGroup( dbG );
		}); 
		
		
		return gDiff.getFirstOnly().size() + gDiff.getBothFirst().size() + gDiff.getSecondOnly().size() ;
	}
}
