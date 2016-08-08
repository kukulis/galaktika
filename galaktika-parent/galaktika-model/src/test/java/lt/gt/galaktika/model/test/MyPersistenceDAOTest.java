package lt.gt.galaktika.model.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.entity.turn.DShipGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JpaTestConfig.class, TestConfig.class })
public class MyPersistenceDAOTest {
	final static Logger LOG = LoggerFactory.getLogger(MyPersistenceDAOTest.class);

    @Autowired
    private IShipGroupDao shipGroupDao;

    @Test
    @Ignore
    public void testSaveShipGroup() {
        // This operation should not throw an Exception
        shipGroupDao.test();
        DShipGroup dShipGroup = new DShipGroup();
        dShipGroup.setShipsCount( 10 );
        long id = shipGroupDao.saveShipGroup( dShipGroup );
        
        DShipGroup storedShipGroup =  shipGroupDao.getShipGroup( id );
        LOG.info( "count of the group id "+id+" is " + storedShipGroup.getShipsCount() );
    }


}
