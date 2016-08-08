package lt.gt.galaktika.model.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DNation;

public class SuperHeroRepository1Test {

	private SessionFactory sessionFactory;

	private Session session = null;

	@Before

	public void before() {

		// setup the session factory

		Configuration configuration = new Configuration();

		// configuration.addAnnotatedClass(SuperHero.class)
		// .addAnnotatedClass(SuperPower.class)
		// .addAnnotatedClass(SuperPowerType.class);

		configuration.addAnnotatedClass(DFleet.class);
		configuration.addAnnotatedClass(DNation.class);

		// TODO change configuration properties

		// configuration.setProperty("hibernate.dialect",
		// "org.hibernate.dialect.H2Dialect");

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

		// configuration.setProperty("hibernate.connection.driver_class",
		// "org.h2.Driver");
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");

		// configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/galaktika");
		configuration.setProperty("hibernate.connection.username", "galuser");
		configuration.setProperty("hibernate.connection.password", "galpass");

		// configuration.setProperty("hibernate.hbm2ddl.auto", "create");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");

		sessionFactory = configuration.buildSessionFactory();

		session = sessionFactory.openSession();

	}

	@Test
	@Ignore
	public void returnsHerosWithMatchingType() {

		// TODO change test scenario

		// // create the objects needed for testing
		//
		// SuperPowerType powerType = new SuperPowerType();
		//
		// powerType.name = "TheType";
		//
		// powerType.description = "12345678901234567890aDescription";
		//
		// SuperPower superpower = new SuperPower();
		//
		// superpower.name = "SuperPower";
		//
		// superpower.description = "Description";
		//
		// superpower.type = powerType;
		//
		// SuperHero hero = new SuperHero();
		//
		// hero.name = "Name";
		//
		// hero.power = superpower;
		//
		// hero.weakness = "None";
		//
		// hero.secretIdentity = "Mr. Jones";
		//
		// // storing the objects for the test in the database
		//
		// session.save(powerType);
		//
		// session.save(superpower);
		//
		// session.save(hero);
		//
		// SuperHeroRepository heroRepository = new
		// SuperHeroRepository(session);
		//
		// List<SuperHero> heroes = heroRepository.loadBy(superpower);
		//
		// assertNotNull(heroes);
		//
		// assertEquals(1, heroes.size());

		DFleet dFleet = new DFleet();
		dFleet.setName("SuperHero fleet");
		session.save(dFleet);

	}

	@After
	public void after() {
		if (session != null) {
			session.flush();
			session.close();
		}
		sessionFactory.close();
	}

}