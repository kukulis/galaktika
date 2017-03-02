package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.service.ShipDesignService;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;

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
}
