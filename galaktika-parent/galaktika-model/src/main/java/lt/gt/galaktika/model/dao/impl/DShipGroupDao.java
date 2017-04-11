package lt.gt.galaktika.model.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DShipGroup;
import lt.gt.galaktika.utils.Utils;

@Repository
public class DShipGroupDao implements IShipGroupDao
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
		//  Auto-generated method stub
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
		// Auto-generated method stub
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

	@Override
	@Transactional
	public DShip findShip(String name, double attack, int guns, double defence, double cargo, double speed,
			double totalMass) {
		String queryStr = "select s from DShip s "
				+ "where s.name=:name "
				+ "and s.attack>=:attackFrom and s.attack <= :attackTo "
				+ "and s.guns=:guns "
				+ "and s.defence>=:defenceFrom and s.defence <= :defenceTo "
				+ "and s.cargo>=:cargoFrom and s.cargo <= :cargoTo "
				+ "and s.speed>=:speedFrom and s.speed <= :speedTo "
				+ "and s.totalMass>=:totalMassFrom and s.totalMass <= :totalMassTo ";
		Query query = em.createQuery(queryStr, DShip.class);
		query.setParameter("name", name);
		query.setParameter("attackFrom", attack-Utils.EPSILON);
		query.setParameter("attackTo", attack+Utils.EPSILON);
		query.setParameter("guns", guns);
		query.setParameter("defenceFrom", defence-Utils.EPSILON);
		query.setParameter("defenceTo", defence+Utils.EPSILON);
		query.setParameter("cargoFrom", cargo-Utils.EPSILON);
		query.setParameter("cargoTo", cargo+Utils.EPSILON);
		query.setParameter("speedFrom", speed-Utils.EPSILON);
		query.setParameter("speedTo", speed+Utils.EPSILON);
		query.setParameter("totalMassFrom", totalMass-Utils.EPSILON);
		query.setParameter("totalMassTo", totalMass+Utils.EPSILON);
		return (DShip) query.getSingleResult();
	}
}
