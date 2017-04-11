package lt.gt.galaktika.model.test.memory.service;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.TechnologiesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTechnologiesService {

	@Autowired
	TechnologiesService technologiesService;

	@Autowired
	NationService nationService;

	@Autowired
	GalaxyService galaxyService;

	@Autowired
	IUserDao userDao;

	private static Nation nation;

	@Test
	public void test1Service() {
		User u = new User("baaa@aaa.lt", "Uzeris");
		u.setLogin("luzeris");
		Long userId = userDao.save(u);
		Assert.assertNotNull(userId);

		Galaxy g = new Galaxy(100, 100);
		g = galaxyService.createGalaxy(g, EGalaxyPurposes.PLAY, true);

		nation = nationService.createNation(new Nation(), u, g);
		Technologies t = technologiesService.createTechnologies(new Technologies(), nation, 1);
		Assert.assertNotNull(t);
		Assert.assertNotEquals(0, t.getTechnologiesId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void test2Service() {
		// should not be able to create second technologies object for the same nation in the same turn
		technologiesService.createTechnologies(new Technologies(), nation, 1);
	}
}
