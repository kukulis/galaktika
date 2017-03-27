package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.dao.IPlanetSurfaceDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.entity.turn.DPlanetSurface;

@Repository
@Transactional
public class DPlanetSurfaceDao implements IPlanetSurfaceDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public DPlanetSurface find(long planetId, int turnNumber) {

		String queryStr = "select s from DPlanetSurface s "
				+ " left join fetch s.shipFactories f" 
				+ " left join fetch s.commands c" 
				+ " left join fetch s.owner "
				+ " left join fetch c.design "
				+ " left join fetch c.technologies "
				+ " left join fetch f.ship "
				+ "where s.planetId=:planetId and s.turnNumber=:turnNumber order by s.turnNumber, s.planetId";
		Query query = em.createQuery(queryStr, DPlanetSurface.class);
		query.setParameter("planetId", planetId);
		query.setParameter("turnNumber", turnNumber);

		return (DPlanetSurface) query.getSingleResult();
	}
	
	public List<Long> findPlanets(Long nationId, int turnNumber) {
		String queryStr = "select s.planetId from DPlanetSurface s "
				+ "where s.owner.nationId=:nationId "
				+ " and s.turnNumber=:turnNumber";
		Query query = em.createQuery(queryStr, Long.class);
		query.setParameter( "nationId", nationId);
		query.setParameter("turnNumber", turnNumber);
		return query.getResultList();
	}

}
