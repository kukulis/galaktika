package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.service.ShipService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestShipService {
	final static Logger LOG = LoggerFactory.getLogger(TestShipService.class);

	@Autowired
	private ShipService shipService;
	
	@Test
	public void testFindShip () {
		Ship ship = new Ship();
		ship.setName("Grybas");
		ship.setAttack(1);
		ship.setGuns(3);
		ship.setDefence(2.1);
		ship.setCargo(3.2);
		ship.setSpeed(4.3);
		ship.setTotalMass(10.11);
		
		shipService.create(ship);
		
		Ship foundShip = shipService.findShip(ship.getName(), ship.getAttack(), ship.getGuns(), ship.getDefence(), ship.getCargo(), ship.getSpeed(), ship.getTotalMass());
		Ship unfoundShip = shipService.findShip(ship.getName(), ship.getAttack(), ship.getGuns(), ship.getDefence(), ship.getCargo(), ship.getSpeed(), ship.getTotalMass()+1);
		Ship unfoundShip2 = shipService.findShip(ship.getName(), ship.getAttack(), ship.getGuns(), ship.getDefence(), ship.getCargo(), ship.getSpeed()+1, ship.getTotalMass());
		Ship unfoundShip3 = shipService.findShip(ship.getName(), ship.getAttack(), ship.getGuns(), ship.getDefence(), ship.getCargo()+1, ship.getSpeed(), ship.getTotalMass());
		Ship unfoundShip4 = shipService.findShip(ship.getName(), ship.getAttack(), ship.getGuns(), ship.getDefence()+1, ship.getCargo(), ship.getSpeed(), ship.getTotalMass());
		Ship unfoundShip5 = shipService.findShip(ship.getName(), ship.getAttack(), ship.getGuns()+1, ship.getDefence(), ship.getCargo(), ship.getSpeed(), ship.getTotalMass());
		
		Assert.assertEquals( ship,  foundShip);
		Assert.assertNull( unfoundShip);
		Assert.assertNull( unfoundShip2);
		Assert.assertNull( unfoundShip3);
		Assert.assertNull( unfoundShip4);
		Assert.assertNull( unfoundShip5);
		
	}
}
