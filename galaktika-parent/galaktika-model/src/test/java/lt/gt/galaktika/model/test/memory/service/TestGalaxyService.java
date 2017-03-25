package lt.gt.galaktika.model.test.memory.service;

import java.util.List;

import org.junit.Before;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.exception.GalaktikaModelException;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.test.memory.MemoryTestBase;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGalaxyService extends MemoryTestBase {
	
	@Autowired
	GalaxyService galaxyService;

	@Test(expected = GalaktikaModelException.class)
	@Ignore
	@Deprecated
	public void test1EmptyGalaxyService() {
		galaxyService.getGalaxies(null);
	}
	
	@Test
	public void test2GalaxyService () {
		
		Galaxy galaxy = new Galaxy(1000, 950);
		galaxy.setTurn( 1 );
		galaxyService.createGalaxy( galaxy, EGalaxyPurposes.PLAY, true );
		List<Galaxy> galaxies = galaxyService.getGalaxies(null);
		Assert.assertNotNull( galaxies );
		Assert.assertNotEquals(0,  galaxies.size());
		Galaxy gal = galaxyService.getGalaxies(null).get(0);
		Assert.assertEquals(galaxy.getSizeX(), gal.getSizeX(), 0.001);
		Assert.assertEquals(galaxy.getSizeY(), gal.getSizeY(), 0.001);
		Assert.assertEquals(galaxy.getTurn(), gal.getTurn());
	}
	
	@Test(expected = GalaktikaModelException.class)
	@Ignore
	@Deprecated
	public void test3TooManyGalaxyService() {
		galaxyService.createGalaxy( new Galaxy(1000, 950), EGalaxyPurposes.PLAY, true );
		galaxyService.createGalaxy( new Galaxy(500, 500), EGalaxyPurposes.PLAY, true );
		galaxyService.getGalaxies(null);
	}
	
	@Before
	public void clearBefore () {
		tearDown();
	}
}
