package lt.gt.galaktika.model.test.integration;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import lt.gt.galaktika.model.dao.DFleetService;
import lt.gt.galaktika.model.dao.IFleetDaoOld;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DShipGroup;
import lt.gt.galaktika.model.test.JpaTestConfig;
import lt.gt.galaktika.model.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaTestConfig.class, TestConfig.class })
public class DFleetServiceTest
{
	final static Logger LOG = LoggerFactory.getLogger(DFleetServiceTest.class);

	@Autowired
	private IFleetDaoOld fleetDao;
	
	@Autowired
	private IShipGroupDao shipGroupDao;
	
//	@Autowired
//	private DFleetService dFleetService;
//	
	@Test
	@Ignore
	public void testGetFleet() {
//		LOG.trace( "testGetFleet called" );
//		DFleet fleetNoShips = fleetDao.getFleet( 3 );
//		Assert.assertNotNull( fleetNoShips );
//		System.out.println( "fleet name="+ fleetNoShips.getName());
		
		DFleet fleet= fleetDao.getFleetWithShips( 6 );
		Assert.assertNotNull( fleet );
		LOG.trace( "name="+ fleet.getName() );
//		fleet.getShipGroups().forEach( g -> LOG.trace( g.getShip().getName()+" count="+g.getShipsCount() ));
	}
	
	@Test
	@Ignore
	public void testStoreFleet() {
		LOG.trace( "testStoreFleet called" );
//		DFleet fleet = new DFleet();
//		fleet.setName( "Testukai" );
//		long fleetId = fleetDao.save( fleet );
		long fleetId=6;
		DFleet fleet = fleetDao.getFleetWithShips( fleetId );
		LOG.trace( "loaded fleet name="+fleet.getName() );
		DShip ship1 = shipGroupDao.getShip( 1 );
		DShip ship2 = shipGroupDao.getShip( 2 );
		DShip ship3 = shipGroupDao.getShip( 3 );
//		fleet.getShipGroups().add( new DShipGroup(ship1) );
//		fleet.getShipGroups().add( new DShipGroup(ship2) );
//		fleet.getShipGroups().add( new DShipGroup(ship3) );
//		dFleetService.storeFleetShips( fleet );
//		dFleetService.updateFleetShips( new DFleet() );
	}
	
	@Test
	@Ignore
	public void testFleetNationId() {
		DFleet fleet = fleetDao.getFleet( 6 );
		System.out.println( "Nation id="+ fleet.getNation().getNationId() );
	}

	@Test
	public void testUpdateFleetShips () {
		LOG.trace( "testUpdateFleetShips called" );
	}
}
