package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.entity.noturn.DNation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class TestMemoryDNation  extends MemoryTestBase  {
final static Logger LOG = LoggerFactory.getLogger(TestMemoryDNation.class);
	
	// @Autowired
	// @Qualifier("dao")
	// IDAO dao;
	
	@Test
	public void testNation() {
		LOG.trace ( "testNation called");
		
		DNation protingieji= dao.create( new DNation("protingieji"));
		DNation stiprieji= dao.create( new DNation("stiprieji"));
		DNation greitieji= dao.create( new DNation("greitieji"));
		
		List<DNation> nations=dao.find(DNation.class, "from DNation", 0, 10);
		
		Assert.assertArrayEquals( new DNation[]{protingieji, stiprieji, greitieji}, nations.toArray());
		
		nations.forEach( n -> {
			LOG.trace( "loaded nation "+n );
		});
	}
}
