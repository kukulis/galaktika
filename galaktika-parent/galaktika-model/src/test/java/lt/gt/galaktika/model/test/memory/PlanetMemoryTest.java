package lt.gt.galaktika.model.test.memory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class PlanetMemoryTest {
	final static Logger LOG = LoggerFactory.getLogger(PlanetMemoryTest.class);
	
	
	@Test
	public void testPlanets() {
		LOG.trace( "testPlanets called" );
	}
}
