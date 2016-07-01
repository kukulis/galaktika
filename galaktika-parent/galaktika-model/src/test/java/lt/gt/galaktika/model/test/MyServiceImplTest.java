package lt.gt.galaktika.model.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.dao.IShipGroupDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JpaTestConfig.class, TestConfig.class })
public class MyServiceImplTest {

    @Autowired
    private IShipGroupDao shipGroupDao;

    @Test
    public void testCreateAndRetrieve() {

//        MilliTimeItem retr = myService.createAndRetrieve();

//        assertNotNull(retr);
    	// TODO use service, not dao
    	 shipGroupDao.test();

    }

}