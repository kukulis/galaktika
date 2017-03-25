package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.gt.galaktika.model.dao.IPlanetDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.DPlanet;

public class DPlanetDao implements IPlanetDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<DPlanet> getGalaxyPlanets(DGalaxy g) {
		String queryStr = "select p from DPlanet p where p.galaxy.galaxyId=:galaxyId";
		Query query = em.createQuery(queryStr, DPlanet.class);
		query.setParameter("galaxyId", g.getGalaxyId());
		return query.getResultList();
	}
}
