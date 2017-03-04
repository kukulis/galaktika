package lt.gt.galaktika.model.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DShipGroup;

@Repository
@Deprecated // DShipGroupDao instead 
public class ShipGroupDao implements IShipGroupDao
{
	@PersistenceContext
	private EntityManager em;	
	
	@Override
	@Transactional
	public void test ()
	{
		System.out.println ( "ShipGroupDao.test called" );
		
	}

	@Override
	@Transactional
	public DShipGroup getShipGroup ( long shipGroupId )
	{
//		em.
//		Session session = em.unwrap(Session.class);
//		session.
		// TODO Auto-generated method stub
		return em.find(DShipGroup.class, shipGroupId);
	}

	@Override
	@Transactional
	public long saveShipGroup ( DShipGroup shipGroup )
	{
		em.persist(shipGroup);
		long id = shipGroup.getShipGroupId();
//		em.detach(shipGroup);
//		em.fl
		return id;
	}
	
	@Override
	@Transactional
	public DShipGroup updateShipGroup ( DShipGroup shipGroup )
	{
		return em.merge( shipGroup );
	}
	
	@Override
	@Transactional
	public void deleteShipGroup ( DShipGroup shipGroup )
	{
		em.remove( em.merge(shipGroup) );
	}

	@Override
	@Transactional
	public DataSearchResult<DShipGroup> loadShipGroupPortion ( DataSearchLimits li )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public DShip getShip ( long shipId )
	{
		return em.find(DShip.class, shipId);
	}

	@Override
	@Transactional
	public long saveShip ( DShip ship )
	{
		em.persist(ship);
		long id = ship.getId();
		em.detach(ship);
		return id;
	}

	@Override
	@Transactional
	public DataSearchResult<DShip> loadShipsPortion ( DataSearchLimits li )
	{
		Criteria c = createDShipCriteria();
		DataSearchResult<DShip> result = new DataSearchResult<>();
		
		c.setFirstResult(li.getLimitFrom());
		c.setMaxResults(li.getLimitAmount());
		result.setRecords(c.list());
		
		Criteria countCriteria = createDShipCriteria();
		countCriteria.setProjection(Projections.rowCount());
		Long rowCount = (Long) countCriteria.uniqueResult();
		if ( rowCount != null )
			result.setTotalAmount(rowCount.longValue());

		return result;
	}
	
	private Criteria createDShipCriteria ( ) {
		Session session = em.unwrap(Session.class);
		Criteria c = session.createCriteria(DShip.class);
		return c;
	}

	@Override
	@Transactional
	public void flush ()
	{
		em.flush();
	}
	
}
