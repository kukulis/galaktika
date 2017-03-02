package lt.gt.galaktika.model.test.memory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;
import lt.gt.galaktika.model.entity.turn.DShipFactory;
import lt.gt.galaktika.model.entity.turn.DTechnologies;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestMemoryDShipFactory  extends MemoryTestBase {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryDShipFactory.class);

//	@Autowired
//	@Qualifier("dao")
//	IDAO dao;

	@Test
	public void testDShipFactory() {
		LOG.trace("testDShipFactory called");

		// OK, lets construct base objects for DShipFactory
		// then lets construct DShipFactory
		// then load and make some checkings.
		
		DShip katinas = dao.create(new DShip("katinas"));
		DShipDesign katino = dao.create(new DShipDesign("katino"));
		DTechnologies t1 = dao.create(new DTechnologies());

		DShipFactory shipFactory = dao.create(new DShipFactory(katinas, katino, t1));

		Assert.assertFalse(shipFactory.getShipFactoryId() == 0);
	}
}
