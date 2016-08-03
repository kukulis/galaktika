package lt.gt.galaktika.model.test.memory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.dao.DFleetService;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DShipGroup;
import lt.gt.galaktika.utils.Diff;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class ShipGroupsMemoryTest
{
	final static Logger LOG = LoggerFactory.getLogger(ShipGroupsMemoryTest.class);

	@Autowired
	private IShipGroupDao shipGroupDao;

	@Autowired
	private IFleetDao fleetDao;

	@Autowired
	private DFleetService dFleetService;

	private DShip[] myShips = new DShip[] { new DShip("pirmas"), new DShip("antras"), new DShip("trečias"),
			new DShip("kevirtas"), new DShip("penktas"), new DShip("šeštas"), };

	private DFleet[] myFleets = new DFleet[] { new DFleet("aršieji"), new DFleet("skrajokliai"),
			new DFleet("paprastukai"), };

	@Before
	public void before ()
	{
		LOG.info("before called");
		// create lots of ships
		Arrays.asList(myShips).forEach(s -> shipGroupDao.saveShip(s));
		Arrays.asList(myShips).forEach(s -> LOG.trace("ship id=" + s.getId()));

		// create some fleats
		Arrays.asList(myFleets).forEach(f -> fleetDao.save(f));
		Arrays.asList(myFleets).forEach(f -> LOG.trace("fleet id=" + f.getFleetId()));

	}

	@Test
	// @Ignore
	public void testFirst ()
	{
		LOG.info("testFirst called");
		// store some groups for fleet
		myFleets[0].getShipGroups().add(new DShipGroup(myShips[0]));
		myFleets[0].getShipGroups().add(new DShipGroup(myShips[1]));

		dFleetService.storeFleetShips(myFleets[0]);

		// test what we got
		DFleet fleet = fleetDao.getFleetWithShips(myFleets[0].getFleetId());
		printShipGroups(fleet.getShipGroups());

		// update fleet groups
		fleet.getShipGroups().get(1).setShipsCount(10);
		fleet.getShipGroups().remove(0);
		fleet.getShipGroups().add(new DShipGroup(myShips[2]));
		fleet.getShipGroups().add(new DShipGroup(myShips[3]));

		dFleetService.updateFleetShips(fleet);
//		fleetDao.flush();
//		shipGroupDao.flush();
		fleetDao.update( fleet );

		DFleet fleet2 = fleetDao.getFleetWithShips(myFleets[0].getFleetId());
		//  validate id's
		fleet2.getShipGroups().forEach(g -> Assert.assertNotEquals(0, g.getShipGroupId()));
		
		LOG.info( "Ship groups after updating:" );
		printShipGroups( fleet2.getShipGroups() ) ;

		Set<DShipGroup> actualSet = new HashSet<>(fleet2.getShipGroups());
		Set<DShipGroup> expectedSet = new HashSet<>(Arrays.asList(new DShipGroup(myFleets[0], myShips[1]),
				new DShipGroup(myFleets[0], myShips[2]), new DShipGroup(myFleets[0], myShips[3])));
		
		Diff<DShipGroup> expectedDiff=new Diff<DShipGroup>().diff(actualSet, expectedSet);
		if ( expectedDiff.getFirstOnly().size() > 0 ) {
			LOG.error( "TOO many" );
			printShipGroups( expectedDiff.getFirstOnly() );
		}

		if ( expectedDiff.getSecondOnly().size() > 0 ) {
			LOG.error( "TOO few" );
			printShipGroups( expectedDiff.getSecondOnly() );
		}

		Assert.assertTrue( "Laivų grupių aibės nevienodos", expectedSet.equals( actualSet) );

	}

	@Test
	@Ignore
	public void testStoreOneGroup ()
	{
		DShipGroup group = new DShipGroup(myShips[0]);
		group.setFleet(myFleets[0]);
		long id = shipGroupDao.saveShipGroup(group);
		LOG.trace("stored ship group id=" + id);
	}

	@After
	public void after ()
	{
		LOG.info("after called");

	}

	private void printShipGroups ( Collection<DShipGroup> groups )
	{
		groups.forEach(
				g -> LOG.trace(g.getShipGroupId() + " ship: " + g.getShip().getId() + " " + g.getShip().getName()));
	}
}
