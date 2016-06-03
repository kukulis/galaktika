package lt.gt.galaktika.model.dao;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
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

	public DataSearchResult<DFleet> loadPortion ( DataSearchLimits pi, DFleetFilter filter )
	{
		DataSearchResult<DFleet> result = new DataSearchResult<>();
		Criteria c = createDFleetCriteria(filter);
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
