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
import lt.gt.galaktika.model.entity.noturn.DShipDesign;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestMemoryDShipDesign extends MemoryTestBase {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryDShipDesign.class);

	// @Autowired
	// @Qualifier("dao")
	// IDAO dao;

	@Test
	public void testDShipDesign() {
		LOG.trace("testDShipDesign called");

		DShipDesign katino = dao.create(new DShipDesign("katino", 1, 1, 1, 1, 1));
		DShipDesign suns = dao.create(new DShipDesign("suns", 2, 1, 1, 1, 1));
		DShipDesign meskos = dao.create(new DShipDesign("meskos", 3, 1, 3, 1, 1));
		DShipDesign kelmo = dao.create(new DShipDesign("katino", 0, 0, 5, 0, 0));

		List<DShipDesign> designs = dao.find(DShipDesign.class, "from DShipDesign", 0, 10);
		designs.forEach(d -> {
			LOG.trace(d.toString());
		});

		Assert.assertArrayEquals(new DShipDesign[] { katino, suns, meskos, kelmo }, designs.toArray());
	}
}
