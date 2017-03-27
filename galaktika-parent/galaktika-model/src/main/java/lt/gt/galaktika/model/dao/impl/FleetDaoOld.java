package lt.gt.galaktika.model.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.FleetSortData;
import lt.gt.galaktika.model.GalaktikaModelUtils;
import lt.gt.galaktika.model.dao.IFleetDaoOld;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.utils.Utils;

@Deprecated // DFleetDao instead
@Repository
@Transactional
public class FleetDaoOld implements IFleetDaoOld
{
	@PersistenceContext
	private EntityManager em;	


	private Session getSession ()
	{
		return em.unwrap(Session.class);
	}

	@Transactional
	public long save ( DFleet fleet )
	{
		if (fleet.getFleetId() == 0)
		{
			Long id = (Long) getSession().save(fleet);
			return Utils.value(id, 0);
		}
		else
		{ //  move to different object, the id check condition to outside
			// from this method
			fleet = (DFleet) getSession().merge(fleet);
			return fleet.getFleetId();
		}
	}

	@Override
	@Transactional
	public DFleet update ( DFleet fleet )
	{
		return em.merge( fleet );
	}

	@Transactional
	public DataSearchResult<DFleet> loadPortion ( DataSearchLimits pi, DFleetFilter filter, FleetSortData fsd )
	{
		DataSearchResult<DFleet> result = new DataSearchResult<>();
		Criteria c = createDFleetCriteria(filter);

		// Stream.of(new SimpleEntry<>("fleetId", fsd.getIdSort()), new
		// SimpleEntry<>("name", fsd.getNameSort()))
		// .map(e -> Optional.of(GalaktikaModelUtils.createOrder(e.getKey(),
		// e.getValue())))
		// .forEach(opt -> opt.ifPresent(o -> c.addOrder(o)));
		//
		Order o = GalaktikaModelUtils.createOrder("fleetId", fsd.getIdSort()); 
		if ( o != null )  c.addOrder(o) ;
		
		o = GalaktikaModelUtils.createOrder("name", fsd.getNameSort());
		if ( o != null ) c.addOrder(o) ;

		c.setFirstResult(pi.getLimitFrom());
		c.setMaxResults(pi.getLimitAmount());
		result.setRecords(c.list());

		Criteria countCriteria = createDFleetCriteria(filter);
		countCriteria.setProjection(Projections.rowCount());
		Long rowCount = (Long) countCriteria.uniqueResult();
		result.setTotalAmount(rowCount.longValue());

		return result;
	}

	@Transactional
	private Criteria createDFleetCriteria ( DFleetFilter filter )
	{
		Criteria c = getSession().createCriteria(DFleet.class);
		c = c.createAlias("nation", "n", JoinType.LEFT_OUTER_JOIN );

		if (filter.getFilterNationId() != 0)
			c.add(Restrictions.eq("n.nationId", filter.getFilterNationId()));

		if (filter.isHideDeletedFleets())
			c.add(Restrictions.eqOrIsNull("deleted", false));

		if (!StringUtils.isEmpty(filter.getFilterName()))
			c.add(Restrictions.ilike("name", filter.getFilterName(), MatchMode.ANYWHERE));
		

		return c;
	}

	@Transactional
	public DFleet getFleet ( long id, long nationId )
	{
		Query query = getSession().createQuery("select f from DFleet f left join fetch f.nation as na where fleetId = :fleetId and ( na.nationId=:nationId or :nationId=0)");
				query.setParameter("fleetId", id);
				query.setParameter("nationId", nationId);
				Object result = query.uniqueResult();
//				System.out.println ( "result class="+result.getClass().getName() );
				
				return (DFleet) result;
	}

	@Override
	@Transactional
	public boolean updateDeletedFlag ( long fleetId, boolean value )
	{
		Query query = getSession().createSQLQuery("update fleets set deleted=:deleted where fleetId=:fleetId");
				query.setBoolean("deleted", value);
				query.setLong("fleetId", fleetId);
		return query.executeUpdate() > 0;
	}

	@Override
	@Transactional
	public DFleet getFleet ( long id )
	{
//		return (DFleet) getSession().get( DFleet.class, id );
		DFleet fleet = em.find(DFleet.class, id );
//		em.detach( fleet );
		return fleet;
	}

	@Override
	@Transactional
	public DFleet getFleetWithShips ( long id )
	{
		Query query = getSession().createQuery("select f from DFleet f "
				+ "left join fetch f.nation as na "
				+ "left join fetch f.shipGroups as gr "
				+ "left join fetch gr.ship as sh " 
				+ "where f.fleetId = :fleetId"
				);
		query.setParameter("fleetId", id);
//		query.setParameter("nationId", nationId);
		Object result = query.uniqueResult();
//		System.out.println ( "result class="+result.getClass().getName() );
//		em.detach( result );
		
		return (DFleet) result;
	}

	@Override
	@Transactional
	public void flush ()
	{
		em.flush();
	}
}
