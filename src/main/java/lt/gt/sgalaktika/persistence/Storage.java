package lt.gt.sgalaktika.persistence;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.Utils;
import lt.gt.sgalaktika.battle.BattleReport;
import lt.gt.sgalaktika.battle.BattleReportRound;
import lt.gt.sgalaktika.battle.BattleReportShot;

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
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( fleet );
		session.getTransaction().commit();
		session.close();
	}
	
	public void storeBattleReportShot ( BattleReportShot shot ) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( shot );
		session.getTransaction().commit();
		session.close();
	}
	
	public void storeBattleReportRound ( BattleReportRound round ) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( round );
		session.getTransaction().commit();
		session.close();
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
	
	public void storeBattleReport ( BattleReport report ) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( report );
		session.getTransaction().commit();
		session.close();
	}
	
	public BattleReport loadBattleReport ( long repId ) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		session.save( report );
//		session.getTransaction().commit();
		BattleReport report = (BattleReport) session.get( BattleReport.class, repId );
//		Utils.printReport( report );
		
		session.close();
		
		return report;
	}
}
