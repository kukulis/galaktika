package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.ShipDesignService;
import lt.gt.galaktika.model.service.ShipService;
import lt.gt.galaktika.model.service.SpaceLocationService;
import lt.gt.galaktika.model.service.TechnologiesService;
import lt.gt.galaktika.model.test.memory.MemoryBeansConfig;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;
import lt.gt.galaktika.model.test.memory.ShipMemoryTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class MemoryTestBaseServices {
	final static Logger LOG = LoggerFactory.getLogger(ShipMemoryTest.class);

	@Autowired
	NationService nationService;
	@Autowired
	PlanetService planetService;
	@Autowired
	ShipDesignService shipDesignService;
	@Autowired
	ShipService shipService;
	@Autowired
	SpaceLocationService spaceLocationService;
	@Autowired
	TechnologiesService technologiesService;
	
	@Test
	public void testStoreBaseObjects () {
		Nation naciai = nationService.create( new Nation ( "naciai"));
		Planet marsas = planetService.create( new Planet(1, 2, 100, 1.5));
		ShipDesign dizainas = shipDesignService.create( new ShipDesign( "dizaineris"));
		Ship katinas = shipService.create( new Ship ( "katinas") );
		SpaceLocation taskas = spaceLocationService.create( new SpaceLocation(1, 0.5 ));
		Technologies t1 = technologiesService.create( new Technologies(0, 1, 2, 3, 4));
		
		Assert.assertNotEquals( 0, naciai.getNationId());
		Assert.assertNotEquals( 0, marsas.getPlanetId());
		Assert.assertNotEquals( 0, dizainas.getDesignId());
		Assert.assertNotEquals( 0, katinas.getId());
		Assert.assertNotEquals( 0, taskas.getLocationId());
		Assert.assertNotEquals( 0, t1.getTechnologiesId());
		
	}
}