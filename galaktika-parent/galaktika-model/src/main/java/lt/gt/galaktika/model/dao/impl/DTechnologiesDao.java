package lt.gt.galaktika.model.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.dao.ITechnologiesDao;
import lt.gt.galaktika.model.entity.turn.DTechnologies;

@Repository
@Transactional
public class DTechnologiesDao implements ITechnologiesDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public DTechnologies getNationTechnologies(long nationId, int turn ) {
		String queryStr = "select t from DTechnologies t "
				+ " where t.owner.nationId=:nationId "
				+ " and t.turnNumber=:turnNumber";
		Query query = em.createQuery(queryStr, DTechnologies.class);
		query.setParameter("nationId", nationId);
		query.setParameter("turnNumber", turn);
		return (DTechnologies) query.getSingleResult();
	}
}
