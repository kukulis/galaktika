package lt.gt.galaktika.model.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DShipGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaTestConfig.class, TestConfig.class })
public class DShipServiceTest
{
	final static Logger LOG = LoggerFactory.getLogger(DShipServiceTest.class);

	@Autowired
	private IShipGroupDao shipGroupDao;
	
	@Autowired
	private IFleetDao fleetDao;

	@Test
	@Ignore
	public void testStoreAndLoadShip ()
	{
		LOG.trace("testStoreAndLoadShip called");
		DShip shipToStore = new DShip();
		shipToStore.setAttack( 1 );
		shipToStore.setDefence(2);
		shipToStore.setGuns(3);
		shipToStore.setName( "kauptukas");
		shipToStore.setSpeed(4);
		shipToStore.setTotalMass(20);
		long shipId = shipGroupDao.saveShip(shipToStore);
		DShip ship = shipGroupDao.getShip( shipId );
	
		LOG.trace( "with id "+shipId+ "  loaded ship name="+ship.getName() );
		
	}
	
	@Test
	@Ignore
	public void testLoadDShipList() {
		DataSearchResult<DShip> shipsResult = shipGroupDao.loadShipsPortion( new DataSearchLimits() );
		
		shipsResult.getRecords().forEach( s -> LOG.trace( s.getId() + " "+ s.getName()));
	}
	
	@Test
	@Ignore
	public void testStoreShipGroup () {
		LOG.trace( "testStoreShipGroup called" );
		DShip storedShip=shipGroupDao.getShip( 2 );
		DShipGroup shipGroup = new DShipGroup(storedShip);
		DFleet fleet = fleetDao.getFleet( 6 ) ;
		shipGroup.setFleet( fleet );
		shipGroupDao.saveShipGroup(shipGroup);
	}

}
