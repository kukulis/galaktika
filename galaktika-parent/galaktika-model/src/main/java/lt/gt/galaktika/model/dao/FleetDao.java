package lt.gt.galaktika.model.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.FleetSortData;
import lt.gt.galaktika.model.GalaktikaModelUtils;
import lt.gt.galaktika.model.entity.DFleet;
import lt.gt.galaktika.utils.Utils;

@Repository
@Transactional
public class FleetDao implements IFleetDao
{
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession ()
	{
		return _sessionFactory.getCurrentSession();
	}

	public long save ( DFleet fleet )
	{
		if (fleet.getFleetId() == 0)
		{
			Long id = (Long) getSession().save(fleet);
			return Utils.value(id, 0);
		}
		else
		{ // TODO ? move to different object, the id check condition to outside
			// from this method
			fleet = (DFleet) getSession().merge(fleet);
			return fleet.getFleetId();
		}
	}

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
		Optional.ofNullable(GalaktikaModelUtils.createOrder("fleetId", fsd.getIdSort())).ifPresent(o -> c.addOrder(o));
		Optional.ofNullable(GalaktikaModelUtils.createOrder("name", fsd.getNameSort())).ifPresent(o -> c.addOrder(o));

		c.setFirstResult(pi.getLimitFrom());
		c.setMaxResults(pi.getLimitAmount());
		result.setRecords(c.list());

		Criteria countCriteria = createDFleetCriteria(filter);
		countCriteria.setProjection(Projections.rowCount());
		Long rowCount = (Long) countCriteria.uniqueResult();
		result.setTotalAmount(rowCount.longValue());

		return result;
	}

	private Criteria createDFleetCriteria ( DFleetFilter filter )
	{
		Criteria c = getSession().createCriteria(DFleet.class);
		if (filter.getFilterNationId() != 0)
			c.add(Restrictions.eq("nationId", filter.getFilterNationId()));

		if (filter.isHideDeletedFleets())
			c.add(Restrictions.eqOrIsNull("deleted", false));

		if (!StringUtils.isEmpty(filter.getFilterName()))
			c.add(Restrictions.ilike("name", filter.getFilterName(), MatchMode.ANYWHERE));
		

		return c;
	}

	public DFleet getFleet ( long id, long nationId )
	{
		Query query = getSession().createQuery("from DFleet where id = :id and ( nationId=:nationId or :nationId=0)")
				.setParameter("id", id).setParameter("nationId", nationId);
		return (DFleet) query.uniqueResult();
	}

	@Override
	public boolean updateDeletedFlag ( long fleetId, boolean value )
	{
		Query query = getSession().createSQLQuery("update fleets set deleted=:deleted where fleetId=:fleetId")
				.setBoolean("deleted", value).setLong("fleetId", fleetId);
		return query.executeUpdate() > 0;
	}
}
