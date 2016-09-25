package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.core.TechnologyType;
import lt.gt.galaktika.core.planet.SurfaceActivities;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;
import lt.gt.galaktika.model.entity.turn.DSurfaceCommand;
import lt.gt.galaktika.model.entity.turn.DTechnologies;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, MemoryBeansConfig.class })
public class TestMemoryDSurfaceCommand  extends MemoryTestBase {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryDShipDesign.class);

//	@Autowired
//	@Qualifier("dao")
//	IDAO dao;

	@Test
	public void testDSurfaceCommand() {
		LOG.trace("testDSurfaceCommand called");

		// DShip katinas = dao.create( new DShip("katinas") );
		DShipDesign katino = dao.create(new DShipDesign("katino"));
		DTechnologies t1 = dao.create(new DTechnologies());

		DSurfaceCommand industryCommand = new DSurfaceCommand();
		industryCommand.setActivity(SurfaceActivities.INDUSTRY);

		DSurfaceCommand technologiesCommand = new DSurfaceCommand();
		technologiesCommand.setActivity(SurfaceActivities.TECHNOLOGIES);
		technologiesCommand.setTechnologyToUpgrade(TechnologyType.ATTACK);

		DSurfaceCommand productionCommand = new DSurfaceCommand();
		productionCommand.setDesign(katino);
		productionCommand.setTechnologies(t1);

		dao.create(industryCommand);
		dao.create(technologiesCommand);
		dao.create(productionCommand);

		List<DSurfaceCommand> commands = dao.find(DSurfaceCommand.class, "select c from DSurfaceCommand c left join fetch c.design left join fetch c.technologies", 0, 3);

		Assert.assertEquals(3, commands.size());
		// check each array element
		Assert.assertEquals(industryCommand.getActivity(), commands.get(0).getActivity());

		Assert.assertEquals(technologiesCommand.getActivity(), commands.get(1).getActivity());
		Assert.assertEquals(technologiesCommand.getTechnologyToUpgrade(), commands.get(1).getTechnologyToUpgrade());

		Assert.assertEquals(productionCommand.getActivity(), commands.get(2).getActivity());
		Assert.assertEquals(productionCommand.getDesign(), commands.get(2).getDesign());
		Assert.assertEquals(productionCommand.getTechnologies(), commands.get(2).getTechnologies());

	}

}
