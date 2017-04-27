package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;

@Repository
@Transactional
public class DFleetDao implements IFleetDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public DFleet find(long fleetId) {
		String queryStr = "select f from DFleet f left join fetch f.nation where f.fleetId=:fleetId";
		Query query = em.createQuery(queryStr, DFleet.class);
		query.setParameter("fleetId", fleetId);
		return (DFleet) query.getSingleResult();
	}

//	@Override
	@Deprecated
	public List<DFleet> findFleets(long galaxyId, int turnNumber) {
		String queryStr = "select f from DFleetData fd "
				+ "left join fetch fd.fleet "
				+ "left join fetch f.nation n "
				+ " where n.galaxy.galaxyId=:galaxyId"
				+ " and fd.turnNumber=:turnNumber";
		Query query = em.createQuery(queryStr, DFleet.class);
		query.setParameter("galaxyId", galaxyId);
		query.setParameter("turnNumber", turnNumber);
		return query.getResultList();
	}
}
