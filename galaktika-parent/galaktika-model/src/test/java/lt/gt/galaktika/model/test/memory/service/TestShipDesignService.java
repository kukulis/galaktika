package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.service.ShipDesignService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestShipDesignService {
	
	@Autowired
	private ShipDesignService shipDesignService;

	@Test
	public void testSaveAndLoad() {
		ShipDesign shipDesign = new ShipDesign();
		
		shipDesign = shipDesignService.create( shipDesign );
		
		Assert.assertNotEquals(0, shipDesign.getDesignId());
	}
	
	@Test
	public void testFindShipDesign () {
		ShipDesign shipDesign = new ShipDesign();
		shipDesign.setDesignName("Katinas");
		shipDesign.setAttackMass(1);
		shipDesign.setGuns(2);
		shipDesign.setDefenceMass(3);
		shipDesign.setCargoMass(4);
		shipDesign.setEngineMass(5);
		shipDesignService.create( shipDesign );
		ShipDesign foundShipDesign = shipDesignService.findShipDesign(shipDesign.getDesignName(), shipDesign.getAttackMass(), shipDesign.getGuns(), shipDesign.getDefenceMass(), shipDesign.getCargoMass(), shipDesign.getEngineMass());
		foundShipDesign.setDesignId(0);
		Assert.assertEquals( shipDesign, foundShipDesign);
	}
}
