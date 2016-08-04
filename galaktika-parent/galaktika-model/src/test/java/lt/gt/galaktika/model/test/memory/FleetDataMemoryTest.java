package lt.gt.galaktika.model.test.memory;

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
import lt.gt.galaktika.model.dao.IFleetGroupDao;
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
	IFleetGroupDao dFleetGroupDao;

	@Test
//	@Ignore
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

		dao.create(fleetData1_1);
		dao.create(fleetData1_2);
		dao.create(fleetData2_1);
		dao.create(fleetData2_2);

		DFleetData loadedFleetData1_1 = dFleetGroupDao.find(pirmieji.getFleetId(), pirmas.getTurnNumber());
		DPlanet loadedPlanet = loadedFleetData1_1.getPlanetLocation();
		Assert.assertEquals(planet, loadedPlanet);

	}

	@Test
	public void testShipGroups() {
		DShip katinas = dao.create(new DShip("katinas"));
		DShip suva = dao.create(new DShip("Šuva"));
		DTurn turn = dao.create(new DTurn(3));
		DFleet zverys = dao.create(new DFleet("žvėrys"));
		DShipGroup katinai = dao.create(new DShipGroup(katinas )); // , zverys.getFleetId(), turn.getTurnNumber()
		DShipGroup sunys = dao.create(new DShipGroup(suva )); // , zverys.getFleetId(), turn.getTurnNumber()
		
		DFleetData apieZveris = new DFleetData( zverys.getFleetId(), turn.getTurnNumber() );
		apieZveris.getShipGroups().add( katinai );
		apieZveris.getShipGroups().add( sunys );
		
		dao.create( apieZveris );
		
		DFleetData loadedFleetData = dFleetGroupDao.findWithGroups( zverys.getFleetId(), turn.getTurnNumber() );
		
		Assert.assertEquals(2, loadedFleetData.getShipGroups().size() );
		loadedFleetData.getShipGroups().forEach( g-> {
			Assert.assertNotNull( g.getFleetId() );
			Assert.assertNotNull( g.getTurnNumber() );
		});
	}

}
