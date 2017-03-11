package lt.gt.galaktika.model.test.memory.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.test.memory.MemoryTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestMemoryNationService {
	
	@Autowired
	NationService nationService;
	
	@Autowired
	GalaxyService galaxyService;
	
	@Autowired
	IUserDao userDao;
	
	@Test
	public void testNations () {
		// prepare data
		User u = new User();
		u.setName("aaa");
		u.setEmail( "aaa@aaa.lt");
		userDao.save( u );
		Assert.assertNotEquals(0, u.getId());
		
		Galaxy galaxy = new Galaxy();
		Galaxy galaxy2 = new Galaxy();
		
		galaxy = galaxyService.createGalaxy(galaxy, EGalaxyPurposes.PLAY, true);
		galaxy2 =  galaxyService.createGalaxy(galaxy2, EGalaxyPurposes.PLAY, true);
		
		Assert.assertNotEquals(0, galaxy.getGalaxyId());
		Assert.assertNotEquals(0, galaxy2.getGalaxyId());
		
		Nation n1 = nationService.createNation(new Nation("gudrieji"), u, galaxy);
		Nation n2 = nationService.createNation(new Nation("protingieji"), u, galaxy2);
		Nation n3 = nationService.createNation(new Nation("iðmingingieji"), null, galaxy);
		

		Assert.assertNotEquals(0, n1.getNationId());
		Assert.assertNotEquals(0, n2.getNationId());
		Assert.assertNotEquals(0, n3.getNationId());
		// test data
		
		Nation loaded1 = nationService.getNation(u, galaxy);
		Nation loaded2 = nationService.getNation(u, galaxy2);
		List<Nation> nations1 = nationService.getNations(galaxy2);
		List<Nation> nations2 = nationService.getNations(galaxy);
		List<Nation> nations3 = nationService.getNations( u );
		
		Assert.assertEquals(n1, loaded1);
		Assert.assertEquals(n2, loaded2);
		
		Assert.assertArrayEquals( new Nation[]{n2}, nations1.toArray());
		Assert.assertArrayEquals( new Nation[]{n1, n3}, nations2.toArray());
		Assert.assertArrayEquals( new Nation[]{n1, n2}, nations3.toArray());

	}
	
}
