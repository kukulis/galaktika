package lt.gt.sgalaktika.integrationtest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.ShipGroup;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-config/database-context.xml" })
@Transactional  //Nors ir transactional visi, bet testuose duombazes pakeitimai rollbackinami
public class TestEntityManagerAndSpringConfig {
	
	  @PersistenceContext
	  private EntityManager em;
	  
	  @Autowired
	  private DataSource dataSource;
	  
	  @Test
	  public void emShouldNotBeNull(){
		  Assert.assertNotNull(em);
	  }
	  
	  @Test
	  public void dataSourceShouldNotBeNull(){
		  Assert.assertNotNull(dataSource);
	  }

	  @Test
	  public void shouldStoreShip(){
			Ship ship = new Ship("tarakonas2");
			ship.setAttack(1);
			ship.setGuns(2);
			ship.setDeffence(3);
			ship.setCargo(4);
			ship.setBrutoMass(15);
			ship.setEnginePower( 15 );
			ship.setLoadAmount(1);
			
			Assert.assertEquals(ship.getId(), 0); //pries irasant default long reiksme == 0
			
			em.persist(ship);
			
			Assert.assertTrue(ship.getId() > 0); //po irasymo sugeneruotas id
			
			Assert.assertTrue(em.contains(ship));

	  }
	  
	  @Test 
	  @Rollback(false)// istrinti su entityManager
	  public void shuoldInsertFindAndDelete() throws SQLException{
		  String shipserie = "-99";
		  
		  PreparedStatement st1 = dataSource.getConnection().prepareStatement("insert into ships (attack, brutoMass, cargo, deffence, enginePower, guns, loadAmount, shipSerie) "
				  										+ "values (1, 2, 3, 4, 5, 6, 7, "+shipserie+")"); //irasymas ne per entityManager
		  st1.executeUpdate();
		  
		  TypedQuery<Ship> q = em.createQuery("from Ship where shipSerie=?", Ship.class);
		  q.setParameter(1, shipserie);
		  Ship ship = q.getResultList().get(0);
		 
		  Assert.assertEquals(ship.getShipSerie(), shipserie);
		 
		  em.remove(ship);
		  
		  Assert.assertFalse(em.contains(ship));
	  }
	  
	  @Test
	//  @Rollback(false)
	  public void shouldStoreOneToMany(){
		  ShipGroup shipGroup1 = new ShipGroup();
		  ShipGroup shipGroup2 = new ShipGroup();
		  ShipGroup shipGroup3 = new ShipGroup();
		  
		  Fleet fleet = new Fleet();
		  
		  fleet.addShipGroup(shipGroup1);
		  fleet.addShipGroup(shipGroup2);
		  fleet.addShipGroup(shipGroup3);
		  
		  Assert.assertTrue(fleet.getId() == 0);
		  Assert.assertTrue(shipGroup1.getFleet().getId() == 0);
		  Assert.assertTrue(shipGroup2.getFleet().getId() == 0);
		  Assert.assertTrue(shipGroup3.getFleet().getId() == 0);
		  
		  em.persist(fleet);
		  
		  Assert.assertFalse(fleet.getId() == 0);
		  Assert.assertFalse(shipGroup1.getFleet().getId() == 0);
		  Assert.assertFalse(shipGroup2.getFleet().getId() == 0);
		  Assert.assertFalse(shipGroup3.getFleet().getId() == 0);
		  Assert.assertEquals(fleet.getId(), shipGroup1.getFleet().getId());
		  Assert.assertEquals(fleet.getId(), shipGroup2.getFleet().getId());
		  Assert.assertEquals(fleet.getId(), shipGroup3.getFleet().getId());

	  }
	  
	  @Test
	  public void testNamedQueries(){
		  em.persist(new Ship("1"));

		  em.persist(new Ship("2"));

		  em.persist(new Ship("3"));
		  
		  Query s=em.createNamedQuery("ships.findAll");
		  @SuppressWarnings("unchecked")
		  List<Ship> list = s.getResultList();
		  
		  Assert.assertEquals(list.size(), 3);
		  
	  }
	  
	  @Test
	  public void testFindById(){
		  Ship ship = new Ship("1");
		 
		  em.persist(ship);
		  
		  Assert.assertNotNull(em.find(Ship.class, ship.getId()));
		  
	  }
	  
	  @Test
	  public void testFindByIdNamedQuery(){
		  Ship ship = new Ship("1");
		  
		  em.persist(ship);
		  
		  Assert.assertNotNull(em.
				  				createNamedQuery("ships.findById").
				  				setParameter("id", ship.getId()).
				  				getResultList().get(0)
				  				);
		  
	  }
}
