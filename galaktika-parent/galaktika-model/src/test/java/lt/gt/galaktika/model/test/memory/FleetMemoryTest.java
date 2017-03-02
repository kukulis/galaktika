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
import lt.gt.galaktika.model.entity.noturn.DFleet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class FleetMemoryTest extends MemoryTestBase {
final static Logger LOG = LoggerFactory.getLogger(FleetMemoryTest.class);
	
//	@Autowired
//	@Qualifier("dao")
//	IDAO dao;
	
	@Test
	public void testFleets() {
		dao.create( new DFleet( "pirmieji"));
		dao.create( new DFleet( "antrieji"));
		dao.create( new DFleet( "tretieji"));
		dao.create( new DFleet( "ketvirtieji"));
		
		List<DFleet> fleets = dao.find( DFleet.class, "from DFleet", 0, 10);
		fleets.forEach( f -> LOG.trace( f.toString() ));
		
		Assert.assertEquals ( 4, fleets.size() );
	}

}
