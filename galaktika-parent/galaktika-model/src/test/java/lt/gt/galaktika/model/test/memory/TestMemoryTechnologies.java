package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.ITechnologiesDao;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.turn.DTechnologies;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
public class TestMemoryTechnologies  extends MemoryTestBase {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryTechnologies.class);

//	@Autowired
//	@Qualifier("dao")
//	IDAO dao;

	
	@Autowired
	ITechnologiesDao technologiesDao;
	
	@Test
	public void testTechnologies() {
		LOG.trace("testTechnologies called");

		DTechnologies t1 = dao.create(new DTechnologies());
		DTechnologies t2 = dao.create(new DTechnologies(2, 2, 2, 2));
		DTechnologies t3 = dao.create(new DTechnologies(3, 3, 3, 3));
		DTechnologies t4 = dao.create(new DTechnologies(4, 4, 4, 4));

		List<DTechnologies> technologies = dao.find(DTechnologies.class, "from DTechnologies", 0, 10);
		
		technologies.forEach( t->{
			LOG.trace( t.toString() );
		});

		Assert.assertArrayEquals(new DTechnologies[] { t1, t2, t3, t4 }, technologies.toArray());
	}
	
	@Test
	public void testNationTechnologies() {
		DTechnologies dt = new DTechnologies(2, 2, 2, 2);
		DNation nat = dao.create( new DNation("grybai") );
		dt.setOwner(nat);
		dt.setTurnNumber( 1);
		dt = dao.create(dt);
		DTechnologies loadedT = technologiesDao.getNationTechnologies(nat.getNationId(), 1);
		dt.setTechnologiesId(0);
		Assert.assertNotEquals( 0 , loadedT.getTechnologiesId() );
		Assert.assertTrue( dt.equals( loadedT ));
	}

}
