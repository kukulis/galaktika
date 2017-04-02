package lt.gt.galaktika.engine.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.engine.config.AdditionalBeansConfig;
import lt.gt.galaktika.engine.work.TurnMaker;
import lt.gt.galaktika.model.config.MemoryTestConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class, AdditionalBeansConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTest {

	@Autowired
	private TurnMaker turnMaker;
	
	@Test
	public void test001() {
		System.out.println( "Test1 called" );
	}
	
	@Test
	public void test020MakeTurn1() {
		turnMaker.makeTurn();
	}
}
