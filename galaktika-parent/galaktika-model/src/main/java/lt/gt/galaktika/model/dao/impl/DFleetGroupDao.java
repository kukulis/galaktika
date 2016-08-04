package lt.gt.galaktika.model.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.dao.IFleetGroupDao;
import lt.gt.galaktika.model.entity.turn.DFleetData;

@Repository
@Transactional
public class DFleetGroupDao implements IFleetGroupDao {

	@PersistenceContext
	private EntityManager em;

	public DFleetData find(long fleetId, int turnNumber) {
		return innerFind(fleetId, turnNumber, false);
	}

	@Override
	public DFleetData findWithGroups(long fleetId, int turnNumber) {
		return innerFind(fleetId, turnNumber, true);
	}

	private DFleetData innerFind(long fleetId, int turnNumber, boolean withGroups) {
		Query query = em.createQuery(buildFindFleetDataQuery(withGroups), DFleetData.class);
		query.setParameter("fleetId", fleetId);
		query.setParameter("turnNumber", turnNumber);
		return (DFleetData) query.getSingleResult();
	}

	private String buildFindFleetDataQuery(boolean withGroups) {
		return "select d from DFleetData d left join fetch d.planetLocation left join fetch d.spaceLocation "
				+ (withGroups ? " left join fetch d.shipGroups " : "")
				+ "where d.fleetId=:fleetId and d.turnNumber=:turnNumber";
	}

}
