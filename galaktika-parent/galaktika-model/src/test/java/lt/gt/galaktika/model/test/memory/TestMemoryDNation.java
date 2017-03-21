package lt.gt.galaktika.model.test.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.NationsFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.noturn.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MemoryTestConfig.class, ModelBeansConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMemoryDNation  extends MemoryTestBase  {
	final static Logger LOG = LoggerFactory.getLogger(TestMemoryDNation.class);
	
	@Autowired
	INationDao nationDao;
	
	@Test
//	@Ignore
	public void test2Nation() {
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
	
	@Test
	public void test1NationFind () {
		
		// -- prepare part
		DGalaxy gal1 = dao.create( new DGalaxy());
		DGalaxy gal2 = dao.create( new DGalaxy());
		
		User tmpU = new User();
		tmpU.setName( "Kiskis" );
		tmpU.setLogin("grybas");
		tmpU.setEmail( "jamailas@aaa.lt" );
		
		User u = dao.create( tmpU );
		
		DNation na1 = new DNation("kopustvalgiai");
		DNation na2 = new DNation("morkavalgiai");
		DNation na3 = new DNation("vilkai");
		
		na1.setUser(u);
		na1.setGalaxy(gal1);
		na1 = dao.create( na1 );
		
		na2.setUser(u);
		na2.setGalaxy(gal2);
		na2 = dao.create( na2 );
		
		na3.setGalaxy(gal2);
		na3 = dao.create(na3);
		
		// --- test part
		
		DNation foundNation1 = nationDao.getNation(na1.getNationId());
		Assert.assertEquals( na1, foundNation1);
		Assert.assertEquals( u.getId(), foundNation1.getUser().getId() );
		
		List<DNation> nations1 =nationDao.find (new NationsFilter().setGalaxyId(gal2.getGalaxyId()));
		Assert.assertNotNull( nations1 );
		Assert.assertArrayEquals(new DNation[] {na2,na3}, nations1.toArray());

		List<DNation> nations2 =nationDao.find (new NationsFilter().setUserId(u.getId()));
		Assert.assertNotNull( nations2 );
		Assert.assertArrayEquals(new DNation[] {na1,na2}, nations2.toArray());

		List<DNation> nations3 =nationDao.find( new NationsFilter().setGalaxyId(gal1.getGalaxyId()).setUserId(u.getId()));
		Assert.assertNotNull( nations3 );
		Assert.assertArrayEquals(new DNation[] {na1}, nations3.toArray());

		List<DNation> nations4 = nationDao.find( new NationsFilter().setNationId(na3.getNationId()));
		Assert.assertNotNull( nations4 );
		Assert.assertArrayEquals(new DNation[] {na3}, nations4.toArray());
	}
}
