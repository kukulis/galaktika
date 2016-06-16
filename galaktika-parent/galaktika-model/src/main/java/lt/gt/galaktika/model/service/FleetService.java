package lt.gt.galaktika.model.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;
import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.FleetSortData;
import lt.gt.galaktika.model.dao.DFleetFilter;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.entity.DFleet;

@Service
public class FleetService
{

	@Autowired
	private IFleetDao fleetDao;

	public long save ( Fleet fleet, long nationId )
	{
		DFleet dFleet = new DFleet();
		try
		{
			BeanUtils.copyProperties(dFleet, fleet);
		} catch (InvocationTargetException | IllegalAccessException e)
		{
			throw new GalaktikaRuntimeException("Problems copying fleet bean", e);
		}
		
		dFleet.setNationId( nationId );
		return fleetDao.save(dFleet);
	}

	/**
	 * @deprecated wrong data call structure
	 * @param pageNo
	 * @return
	 */
	public List<Fleet> getPage ( int pageNo )
	{
		DataSearchResult<DFleet> results = fleetDao.loadPortion(new DataSearchLimits(), new DFleetFilter(), new FleetSortData());
		return results.getRecords().stream().map(f -> createFleet(f)).collect(Collectors.toList());
	}

	public DataSearchResult<Fleet> getFleets ( DataSearchLimits dsl, String filterName, long nationId, boolean showDeleted, FleetSortData fsd )
	{
		DFleetFilter dFleetFilter = new DFleetFilter();
		dFleetFilter.setHideDeletedFleets( !showDeleted );
		dFleetFilter.setFilterNationId(nationId);
		dFleetFilter.setFilterName( filterName);
		DataSearchResult<DFleet> dResults = fleetDao.loadPortion(dsl, dFleetFilter, fsd);
		DataSearchResult<Fleet> results = new DataSearchResult<>();
		results.setRecords(dResults.getRecords().stream().map(f -> createFleet(f)).collect(Collectors.toList()));
		results.setTotalAmount(dResults.getTotalAmount());

		return results;
	}

	/**
	 * Helper method, to convert database fleet to user fleet
	 * 
	 * @param f
	 * @return
	 */
	public static Fleet createFleet ( DFleet f )
	{
		Fleet fleet = new Fleet();
		if (f != null)
			try
			{
				BeanUtils.copyProperties(fleet, f);
			} catch (InvocationTargetException | IllegalAccessException e)
			{
				throw new GalaktikaRuntimeException("Problems copying fleet bean", e);
			}
		return fleet;
	}

	public Fleet getFleet ( long id, long nationId )
	{
		DFleet dFleet = fleetDao.getFleet(id, nationId);
		return createFleet(dFleet);
	}
	
	public boolean isFleet( long id, long nationId) {
		return fleetDao.getFleet(id, nationId) != null;
	}

	public long storeFleet ( Fleet fleet )
	{
		try
		{
			DFleet dfleet = new DFleet();
			BeanUtils.copyProperties(dfleet, fleet);
			long rezId = fleetDao.save(dfleet);
			return rezId;
		} catch (InvocationTargetException | IllegalAccessException e)
		{
			throw new GalaktikaRuntimeException("Problems copying fleet bean", e);
		}
	}
	
	
	public boolean deleteFleet( long fleetId ) {
		return fleetDao.updateDeletedFlag(fleetId, true );
	}
}
