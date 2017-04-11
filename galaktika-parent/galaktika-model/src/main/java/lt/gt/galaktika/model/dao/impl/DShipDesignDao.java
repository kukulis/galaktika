package lt.gt.galaktika.model.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.dao.IShipDesignDao;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;
import lt.gt.galaktika.utils.Utils;


@Repository
public class DShipDesignDao implements IShipDesignDao {
	@PersistenceContext
	private EntityManager em;	

	@Override
	@Transactional
	// TODO owner in parameters
	public DShipDesign findShipDesign(String name, double attackMass, int guns, double defenseMass, double cargoMass,
			double engineMass) {
		String queryStr = "select d from DShipDesign d "
				+ "where d.designName=:designName "
				+ "and d.attackMass>=:attackMassFrom and d.attackMass <= :attackMassTo "
				+ "and d.guns=:guns "
				+ "and d.defenceMass>=:defenceMassFrom and d.defenceMass <= :defenceMassTo "
				+ "and d.cargoMass>=:cargoMassFrom and d.cargoMass <= :cargoMassTo "
				+ "and d.engineMass>=:engineMassFrom and d.engineMass <= :engineMassTo ";
		Query query = em.createQuery(queryStr, DShipDesign.class);
		query.setParameter("designName", name);
		query.setParameter("attackMassFrom", attackMass-Utils.EPSILON);
		query.setParameter("attackMassTo", attackMass+Utils.EPSILON);
		query.setParameter("guns", guns);
		query.setParameter("defenceMassFrom", defenseMass-Utils.EPSILON);
		query.setParameter("defenceMassTo", defenseMass+Utils.EPSILON);
		query.setParameter("cargoMassFrom", cargoMass-Utils.EPSILON);
		query.setParameter("cargoMassTo", cargoMass+Utils.EPSILON);
		query.setParameter("engineMassFrom", engineMass-Utils.EPSILON);
		query.setParameter("engineMassTo", engineMass+Utils.EPSILON);
		return (DShipDesign) query.getSingleResult();
	}
}
