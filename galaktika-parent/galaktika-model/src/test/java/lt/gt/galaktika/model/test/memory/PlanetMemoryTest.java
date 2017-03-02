package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.DPlanet;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class PlanetMemoryTest extends MemoryTestBase {
	final static Logger LOG = LoggerFactory.getLogger(PlanetMemoryTest.class);
	
//	@Autowired
//	@Qualifier("dao")
//	IDAO dao;
	
	@Test
	public void testPlanets() {
		LOG.trace( "testPlanets called" );
		
		dao.create( new DPlanet(10,11) );
		dao.create( new DPlanet(1,3) );
		dao.create( new DPlanet(1,5) );
		dao.create( new DPlanet(2,3) );
		
		List <DPlanet> planets = dao.find(DPlanet.class, "from DPlanet", 0, 10);
		planets.forEach( p -> LOG.trace( p.toString() ));
		Assert.assertEquals( 4, planets.size());
	}
}
