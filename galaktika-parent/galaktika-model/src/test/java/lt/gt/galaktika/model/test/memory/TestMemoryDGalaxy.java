package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IGalaxyDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestMemoryDGalaxy extends MemoryTestBase  {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryDGalaxy.class);
	
	@Autowired
	IGalaxyDao galaxyDao;

	@Test
	public void testGalaxy () {
		DGalaxy dGalaxy = new DGalaxy();
		dGalaxy.setSizeX(1000);
		dGalaxy.setSizeY(999);
		dGalaxy.setPurpose( EGalaxyPurposes.PLAY );
		dGalaxy.setActive( true );
		dao.create( dGalaxy );
		List<DGalaxy> foundGalaxies = galaxyDao.find( EGalaxyPurposes.PLAY );
		Assert.assertNotNull( foundGalaxies);
		Assert.assertEquals( 1, foundGalaxies.size());
		Assert.assertNotEquals(0, foundGalaxies.get(0).getGalaxyId() );
		Assert.assertEquals( dGalaxy.getSizeX(), foundGalaxies.get(0).getSizeX(), 0.001); 
		Assert.assertEquals( dGalaxy.getSizeY(), foundGalaxies.get(0).getSizeY(), 0.001);
		Assert.assertTrue( foundGalaxies.get(0).isActive() );
	}
}
