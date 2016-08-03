//package lt.gt.galaktika.model.service;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import lt.gt.galaktika.core.Fleet;
//import lt.gt.galaktika.core.Ship;
//import lt.gt.galaktika.core.ShipGroup;
//import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;
//import lt.gt.galaktika.model.DataSearchLimits;
//import lt.gt.galaktika.model.DataSearchResult;
//import lt.gt.galaktika.model.FleetSortData;
//import lt.gt.galaktika.model.dao.DFleetFilter;
//import lt.gt.galaktika.model.dao.IFleetDao;
//import lt.gt.galaktika.model.dao.INationDao;
//import lt.gt.galaktika.model.entity.noturn.DFleet;
//import lt.gt.galaktika.model.entity.noturn.DShip;
//import lt.gt.galaktika.model.entity.turn.DShipGroup;
//
//@Service
//public class FleetService
//{
//	final static Logger LOG = LoggerFactory.getLogger(FleetService.class);
//
//	@Autowired
//	private IFleetDao fleetDao;
//
//	@Autowired
//	private INationDao nationDao;
//
//	public long save ( Fleet fleet, long nationId )
//	{
//		DFleet dFleet = new DFleet();
//		try
//		{
//			BeanUtils.copyProperties(dFleet, fleet);
//		} catch (InvocationTargetException | IllegalAccessException e)
//		{
//			throw new GalaktikaRuntimeException("Problems copying fleet bean", e);
//		}
//
//		dFleet.setNation(nationDao.getNation(nationId));
//		return fleetDao.save(dFleet);
//	}
//
//	/**
//	 * @deprecated wrong data call structure
//	 * @param pageNo
//	 * @return
//	 */
//	public List<Fleet> getPage ( int pageNo )
//	{
//		DataSearchResult<DFleet> results = fleetDao.loadPortion(new DataSearchLimits(), new DFleetFilter(),
//				new FleetSortData());
//		return results.getRecords().stream().map(f -> createFleet(f, false)).collect(Collectors.toList());
//	}
//
//	public DataSearchResult<Fleet> getFleets ( DataSearchLimits dsl, String filterName, long nationId,
//			boolean showDeleted, FleetSortData fsd )
//	{
//		// try
//		// {
//		LOG.trace("getFleets called");
//		DFleetFilter dFleetFilter = new DFleetFilter();
//		dFleetFilter.setHideDeletedFleets(!showDeleted);
//		dFleetFilter.setFilterNationId(nationId);
//		dFleetFilter.setFilterName(filterName);
//		DataSearchResult<DFleet> dResults = fleetDao.loadPortion(dsl, dFleetFilter, fsd);
//		DataSearchResult<Fleet> results = new DataSearchResult<>();
//		results.setRecords(dResults.getRecords().stream().map(f -> createFleet(f, false)).collect(Collectors.toList()));
//		results.setTotalAmount(dResults.getTotalAmount());
//		LOG.trace("before returning results");
//
//		return results;
//		// } catch (Exception e)
//		// {
//		// throw new GalaktikaRuntimeException(e);
//		// }
//	}
//
//	/**
//	 * Helper method, to convert database fleet to user fleet
//	 * 
//	 * @param f
//	 * @param copyGroups
//	 * @return
//	 */
//	public static Fleet createFleet ( DFleet f, boolean copyGroups )
//	{
//		Fleet fleet = new Fleet();
//		if (f != null)
//		{
//			fleet.setFleetId(f.getFleetId());
//			fleet.setName(f.getName());
//			try
//			{
//				if (copyGroups)
//					for (DShipGroup g : f.getShipGroups())
//					{
//						DShip dShip = g.getShip();
//						Ship ship = new Ship();
//						BeanUtils.copyProperties(ship, dShip);
//						ShipGroup group = new ShipGroup(ship);
//						group.setCount(g.getShipsCount());
//					}
//				;
//				if (f.getNation() != null)
//				{
//					BeanUtils.copyProperties(fleet.getOwner(), f.getNation());
//				}
//
//			} catch (InvocationTargetException | IllegalAccessException e)
//			{
//				throw new GalaktikaRuntimeException("Problems copying fleet bean", e);
//			}
//		}
//		return fleet;
//	}
//
//	public Fleet getFleet ( long id, long nationId )
//	{
//		DFleet dFleet = fleetDao.getFleet(id, nationId);
//		return createFleet(dFleet, true);
//	}
//
//	public boolean isFleet ( long id, long nationId )
//	{
//		return fleetDao.getFleet(id, nationId) != null;
//	}
//
//	public long storeFleet ( Fleet fleet )
//	{
//		try
//		{
//			DFleet dfleet = new DFleet();
//			BeanUtils.copyProperties(dfleet, fleet);
//			long rezId = fleetDao.save(dfleet);
//			return rezId;
//		} catch (InvocationTargetException | IllegalAccessException e)
//		{
//			throw new GalaktikaRuntimeException("Problems copying fleet bean", e);
//		}
//	}
//
//	public boolean deleteFleet ( long fleetId )
//	{
//		return fleetDao.updateDeletedFlag(fleetId, true);
//	}
//}
