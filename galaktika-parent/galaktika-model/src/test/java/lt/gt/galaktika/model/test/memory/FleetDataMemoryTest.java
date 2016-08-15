package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DFleetData;
import lt.gt.galaktika.model.entity.turn.DShipGroup;
import lt.gt.galaktika.model.entity.turn.DTurn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class FleetDataMemoryTest {
	final static Logger LOG = LoggerFactory.getLogger(FleetDataMemoryTest.class);

	@Autowired
	@Qualifier("dao")
	IDAO dao;

	@Autowired
	IFleetDataDao dFleetDataDao;

	@Test
//	 @Ignore
	public void testFleetData() {

		DFleet pirmieji = new DFleet("pirmieji");
		DFleet antrieji = new DFleet("pirmieji");
		pirmieji = dao.create(pirmieji);
		antrieji = dao.create(antrieji);

		DTurn pirmas = new DTurn(1);
		DTurn antras = new DTurn(2);
		pirmas = dao.create(pirmas);
		antras = dao.create(antras);

		DFleetData fleetData1_1 = new DFleetData(pirmieji.getFleetId(), pirmas.getTurnNumber());
		DFleetData fleetData1_2 = new DFleetData(pirmieji.getFleetId(), antras.getTurnNumber());
		DFleetData fleetData2_1 = new DFleetData(antrieji.getFleetId(), pirmas.getTurnNumber());
		DFleetData fleetData2_2 = new DFleetData(antrieji.getFleetId(), antras.getTurnNumber());

		// planet or space location
		DPlanet planet = dao.create(new DPlanet(1, 2));

		fleetData1_1.setPlanetLocation(planet);
		fleetData2_1.setPlanetLocation(planet);

		dao.create(fleetData1_1);
		dao.create(fleetData1_2);
		dao.create(fleetData2_1);
		dao.create(fleetData2_2);

		DFleetData loadedFleetData1_1 = dFleetDataDao.find(pirmieji.getFleetId(), pirmas.getTurnNumber());
		DPlanet loadedPlanet = loadedFleetData1_1.getPlanetLocation();
		Assert.assertEquals(planet, loadedPlanet);

		List<DFleetData> fleetDatas = dFleetDataDao.findInOrbit(planet.getPlanetId(), pirmas.getTurnNumber(), false );

		Assert.assertArrayEquals(new DFleetData[] { fleetData1_1, fleetData2_1 }, fleetDatas.toArray());
	}

	@Test
//	@Ignore
	public void testShipGroups() {
		DShip katinas = dao.create(new DShip("katinas"));
		DShip suva = dao.create(new DShip("Šuva"));
		DTurn turn = dao.create(new DTurn(3));
		DFleet zverys = dao.create(new DFleet("žvėrys"));
		DShipGroup katinai = dao.create(new DShipGroup(katinas)); // ,
																	// zverys.getFleetId(),
																	// turn.getTurnNumber()
		DShipGroup sunys = dao.create(new DShipGroup(suva)); // ,
																// zverys.getFleetId(),
																// turn.getTurnNumber()

		DFleetData apieZveris = new DFleetData(zverys.getFleetId(), turn.getTurnNumber());
		apieZveris.getShipGroups().add(katinai);
		apieZveris.getShipGroups().add(sunys);
		
		Assert.assertNull( katinai.getFleetId());
		Assert.assertNull( katinai.getTurnNumber());
		Assert.assertNull( sunys.getFleetId());
		Assert.assertNull( sunys.getTurnNumber());

		dao.create(apieZveris);

		DFleetData loadedFleetData = dFleetDataDao.findWithGroupsAndLocations(zverys.getFleetId(), turn.getTurnNumber());

		Assert.assertEquals(2, loadedFleetData.getShipGroups().size());
		loadedFleetData.getShipGroups().forEach(g -> {
			LOG.trace( g.toString() );
			Assert.assertNotNull(g.getFleetId());
			Assert.assertNotNull(g.getTurnNumber());
		});
	}
	
	@Test
//	@Ignore
	public void testOrbit () {
		LOG.trace( "testOrbit called" );
//		DShip katinas = dao.create(new DShip("katinas"));
//		DShip suva = dao.create(new DShip("Šuva"));
		DTurn turn = dao.create(new DTurn(4));
		DPlanet planet = dao.create( new DPlanet());
		
		DFleet fleet1 = dao.create( new DFleet( "pirmas" ) );
		DFleet fleet2 = dao.create( new DFleet( "antras" ) );
		
		DFleetData fd1 = new DFleetData(fleet1.getFleetId(), turn.getTurnNumber() );
		fd1.setPlanetLocation( planet );
		DFleetData fd2 = new DFleetData(fleet2.getFleetId(), turn.getTurnNumber() );
		fd2.setPlanetLocation( planet );
		
		dao.create( fd1 );
		dao.create( fd2 );
		
		
		List<DFleetData> fdatas = dFleetDataDao.findInOrbit(planet.getPlanetId(), turn.getTurnNumber(), false );
		
		Assert.assertArrayEquals(new Object[] {fd1,  fd2}, fdatas.toArray() );
		fdatas.forEach( d -> { 
			Assert.assertNotNull( d.getFleet() );
			LOG.trace( d.getFleet().toString() );
		});
		
	}

}
