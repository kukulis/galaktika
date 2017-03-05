package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.gt.galaktika.model.dao.IGalaxyDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;

public class DGalaxyDao implements IGalaxyDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DGalaxy> find(EGalaxyPurposes purpose) {
		String queryStr = "select g from DGalaxy g where g.purpose=:purpose and g.active=true";
		Query query = em.createQuery(queryStr, DGalaxy.class);
		query.setParameter("purpose", purpose);
		return query.getResultList();
	}
}
