package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetData;
import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestMemoryStoreOrbit {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryStoreOrbit.class);
	
	@Autowired
	PlanetDataService planetDataService;
	
	@Autowired
	PlanetService planetService;
	
	@Autowired
	GalaxyService galaxyService;

	@Test
	public void testStoreOrbit () throws Exception {
		LOG.trace( "testStoreOrbit called");
		
		Galaxy galaxy = new Galaxy(1000, 1000);
		galaxy.setTurn(1);
		Galaxy g = galaxyService.createGalaxy(galaxy, EGalaxyPurposes.PLAY, true);
		
		Planet p = planetService.createPlanet(new Planet(), g);
		PlanetData pd = new PlanetData();
		pd.setPlanet(p);
		
		pd.setOrbit(new PlanetOrbit());
		
		Fleet f = new Fleet();
		
		pd.getOrbit().getFleets().add( f );
		planetDataService.storeOrbit( pd, 1);

		PlanetData loadedPlanetData = planetDataService.loadPlanetData(p.getPlanetId(), 1);
		
		Assert.assertNotNull( loadedPlanetData.getOrbit() );
		Assert.assertNotNull(loadedPlanetData.getOrbit().getFleets());
		Assert.assertNotEquals(0, loadedPlanetData.getOrbit().getFleets().size());
	}
}
