package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.exception.GalaktikaModelException;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGalaxyService {
	
	@Autowired
	GalaxyService galaxyService;

	@Test(expected = GalaktikaModelException.class)
//	@Ignore
	public void test1EmptyGalaxyService() {
		galaxyService.getGalaxy();
	}
	
	@Test
	public void test2GalaxyService () {
		
		Galaxy galaxy = new Galaxy(1000, 950);
		galaxy.setTurn( 1 );
		galaxyService.createPlayGalaxy( galaxy );
		Galaxy gal = galaxyService.getGalaxy();
		Assert.assertNotNull( gal );
		Assert.assertEquals(galaxy.getSizeX(), gal.getSizeX(), 0.001);
		Assert.assertEquals(galaxy.getSizeY(), gal.getSizeY(), 0.001);
		Assert.assertEquals(galaxy.getTurn(), gal.getTurn());
	}
	
	@Test(expected = GalaktikaModelException.class)
//	@Ignore
	public void test3TooManyGalaxyService() {
		galaxyService.createPlayGalaxy( new Galaxy(1000, 950) );
		galaxyService.createPlayGalaxy( new Galaxy(500, 500) );
		galaxyService.getGalaxy();
	}
}
