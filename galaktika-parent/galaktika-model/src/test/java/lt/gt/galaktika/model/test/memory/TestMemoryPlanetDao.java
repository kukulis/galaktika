package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IPlanetDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.DPlanet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestMemoryPlanetDao extends MemoryTestBase {
	
	@Autowired
	IDAO dao;
	
	@Autowired
	IPlanetDao planetDao;
	
//	@Autowired
//	IGalaxyDao galaxyDao;
	
	@Test
	public void testGalaxyPlanets() {
		// create galaxy
		// create planets
		// load planets
		// assert planets list
		DGalaxy g = dao.create(new DGalaxy());
		
		DPlanet p = new DPlanet(10, 10);
		DPlanet p2 = new DPlanet(11, 11);
		p.setGalaxy(g);
		p2.setGalaxy(g);
		
		dao.create(p);
		dao.create(p2);
		
		List<DPlanet> planets = planetDao.getGalaxyPlanets(g);
		Assert.assertArrayEquals(new Object[]{p, p2}, planets.toArray());
	}
}
