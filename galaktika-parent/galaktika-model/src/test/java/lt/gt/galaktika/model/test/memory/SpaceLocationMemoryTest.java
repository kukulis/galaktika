package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.DSpaceLocation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class SpaceLocationMemoryTest  extends MemoryTestBase  {
final static Logger LOG = LoggerFactory.getLogger(SpaceLocationMemoryTest.class);
	
//	@Autowired
//	@Qualifier("dao")
//	IDAO dao;
	
	@Test
	public void testSpaceLocation() {
		dao.create( new DSpaceLocation( 1, 2 ));
		dao.create( new DSpaceLocation( 3, 4 ));
		dao.create( new DSpaceLocation( 5, 6 ));
		dao.create( new DSpaceLocation( 7, 8 ));
		
		List<DSpaceLocation> locations=dao.find(DSpaceLocation.class, "from DSpaceLocation", 0, 10);
		
		locations.forEach(l->LOG.trace(l.toString()));
		Assert.assertEquals(4, locations.size());
	}
}
