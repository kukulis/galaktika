package lt.gt.sgalaktika.persistence;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Storage {
	
	private SessionFactory sessionFactory;
	
	public Storage () {
		
		  sessionFactory = new Configuration()
          .configure() // configures settings from hibernate.cfg.xml
          .buildSessionFactory();
	}
	
	public void storeFleet ( Fleet fleet ) {
		
	}
	
	public Fleet loadFleet ( long id ) {
		return null;
	}
	
	public void storeShip ( Ship ship ) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		session.cr
		session.saveOrUpdate("ships", ship);
//		session.save( ship );
		session.getTransaction().commit();
		session.close();
	}
	
	public void loadShip ( long shipId ) {
		
	}
}
