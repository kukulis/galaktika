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
import lt.gt.galaktika.model.entity.noturn.DShip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class ShipMemoryTest  extends MemoryTestBase  {
	
final static Logger LOG = LoggerFactory.getLogger(ShipMemoryTest.class);
	
//	@Autowired
//	@Qualifier("dao")
//	IDAO dao;
	
	@Test
	public void testShips() {
		dao.create( new DShip( "grybas") );
		dao.create( new DShip( "uoga") );
		dao.create( new DShip( "slyva") );
		dao.create( new DShip( "kelmas") );
		
		List<DShip> ships = dao.find(DShip.class, "from DShip", 0, 10);
		ships.forEach( s -> LOG.trace( s.toString() ));
		Assert.assertEquals( 4, ships.size());
	}
}
