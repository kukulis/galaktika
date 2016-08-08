package lt.gt.galaktika.model.dao.impl;

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

}
