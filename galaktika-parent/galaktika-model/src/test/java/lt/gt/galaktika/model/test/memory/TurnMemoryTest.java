package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.entity.turn.DTurn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class TurnMemoryTest extends MemoryTestBase {

	final static Logger LOG = LoggerFactory.getLogger(TurnMemoryTest.class);
	// @Autowired
	// @Qualifier("dao")
	// IDAO dao;

	@Test
	public void testTurns() {
		dao.create(new DTurn(1));
		dao.create(new DTurn(2));
		dao.create(new DTurn(3));
		dao.create(new DTurn(4));

		List<DTurn> turns = dao.find(DTurn.class, "from DTurn", 0, 10);

		turns.forEach(t -> LOG.trace(t.toString()));
		Assert.assertEquals(4, turns.size());

	}

}
