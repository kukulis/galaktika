package lt.gt.galaktika.model.test.memory.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.PlanetService;

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
	
	@Test
	public void testGalaxyPlanets() {
		Planet p1 = new Planet ( 10,10,100, 2);
		Planet p2 = new Planet ( 9,11,101, 1);
		
		Galaxy g = new Galaxy (100, 100);
		g = galaxyService.createGalaxy(g, EGalaxyPurposes.PLAY, true);
		
		planetService.createPlanet(p1, g);
		planetService.createPlanet(p2, g);
		
		List<Planet> planets = planetService.findPlanets(g);
		
		Assert.assertArrayEquals(new Object[]{p1,p2}, planets.toArray());
	}
}
