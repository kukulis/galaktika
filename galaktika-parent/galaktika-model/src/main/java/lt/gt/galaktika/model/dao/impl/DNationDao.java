package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.NationsFilter;
import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.DNation;

@Repository
@Transactional
public class DNationDao implements INationDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public DNation getNation(long nationId) {
		return em.find(DNation.class, nationId);
	}

	@Override
	public List<DNation> find(NationsFilter filter) {
		Session session = em.unwrap(Session.class);
		Criteria c = session.createCriteria(DNation.class);
		
		if (filter.isUseGalaxyId())
			c.add(Restrictions.eq("galaxy.galaxyId", filter.getGalaxyId()));
		
		if ( filter.isUseUserId())
			c.add(Restrictions.eq("user.id", filter.getUserId()));
		
		if ( filter.isUseNationId())  // actually this is the key 
			c.add( Restrictions.eq("nationId", filter.getNationId()));
		
		c.addOrder(Order.asc( "nationId" ));
		
		return c.list();		
	}

}
