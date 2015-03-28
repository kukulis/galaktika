package lt.gt.web.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lt.gt.sgalaktika.Ship;
import lt.gt.web.services.api.ShipsRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class ShipsRepositoryImpl implements ShipsRepository {
	
	 @PersistenceContext
	 private EntityManager em;

	@Override
	public List<Ship> getShips() {
		return em.createNamedQuery("ships.findAll", Ship.class).getResultList();
	}

	@Transactional
	@Override
	public void storeShip(Ship ship) {
		em.persist(ship);
	}
	
	@Transactional
	@Override
	public void deleteShip(Long id) {
		em.createQuery("delete from Ship s where s.id = :id").setParameter("id", id).executeUpdate();	
	}

}
