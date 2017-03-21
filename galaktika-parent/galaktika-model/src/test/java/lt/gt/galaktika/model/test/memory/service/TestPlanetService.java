package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestPlanetService {
	@Autowired
	PlanetService planetService;
	
	@Autowired
	GalaxyService galaxyService;
	
	@Test
	public void testSavePlanet () {
		Planet p = new Planet(100,100,100,1);
		Galaxy g = new Galaxy(1000,1000);
		
		g = galaxyService.createGalaxy(g, EGalaxyPurposes.PLAY, true);
		
		Planet rezP = planetService.createPlanet( p,g );
		
		Assert.assertEquals( p, rezP );
	}
}
