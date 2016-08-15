package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.entity.turn.DFleetData;

@Repository
@Transactional
public class DFleetDataDao implements IFleetDataDao {

	@PersistenceContext
	private EntityManager em;

	public DFleetData find(long fleetId, int turnNumber) {
		return innerFind(fleetId, turnNumber, false);
	}

	@Override
	public DFleetData findWithGroupsAndLocations(long fleetId, int turnNumber) {
		return innerFind(fleetId, turnNumber, true);
	}

	private DFleetData innerFind(long fleetId, int turnNumber, boolean withGroups) {
		Query query = em.createQuery(buildFindFleetDataQuery(withGroups), DFleetData.class);
		query.setParameter("fleetId", fleetId);
		query.setParameter("turnNumber", turnNumber);
		try {
			return (DFleetData) query.getSingleResult();
		} catch ( NoResultException nre ) {
			return null;
		}
	}

	private String buildFindFleetDataQuery(boolean withGroups) {
		return "select d from DFleetData d left join fetch d.planetLocation left join fetch d.spaceLocation "
				+ " left join fetch d.flightSource left join fetch d.flightDestination " 
				+ (withGroups ? " left join fetch d.shipGroups " : "")
				+ "where d.fleetId=:fleetId and d.turnNumber=:turnNumber";
	}

	@Override
	public List<DFleetData> findInOrbit(long planetId, int turnNumber) {
		String queryStr = "select d from DFleetData d left join fetch d.planetLocation as p left join fetch d.spaceLocation "
				+ "where p.planetId=:planetId and d.turnNumber=:turnNumber order by d.fleetId";  
		Query query = em.createQuery(queryStr, DFleetData.class);
		query.setParameter("planetId", planetId );
		query.setParameter("turnNumber", turnNumber );
		return query.getResultList();
	}

}
