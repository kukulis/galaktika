package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.dao.IGalaxyDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;

@Repository
@Transactional
public class DGalaxyDao implements IGalaxyDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DGalaxy> find(GalaxiesFilter filter) {
		Session session = em.unwrap(Session.class);
		Criteria c = session.createCriteria(DGalaxy.class);
		
		if (filter.isUseActiveFilter())
			c.add(Restrictions.eq("active", filter.isActive()));
		
		if ( filter.isUsePurposeFilter())
			c.add(Restrictions.eq("purpose", filter.getPurpose()));
		
		return c.list();
	}

}
