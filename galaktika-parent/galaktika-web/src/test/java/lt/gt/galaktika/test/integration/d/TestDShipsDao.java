package lt.gt.galaktika.test.integration.d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.entity.DShip;
import lt.gt.galaktika.web.GalaktikaApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GalaktikaApplication.class)
@WebAppConfiguration
public class TestDShipsDao
{
	@Autowired
	private IShipGroupDao shipGroupDao;

	@Test
	public void testGetShipsList ()
	{
		DataSearchResult<DShip> shipsResult = shipGroupDao.loadShipsPortion(new DataSearchLimits());
		shipsResult.getRecords().forEach(s -> System.out.println(s.getId() + " " + s.getName()));
	}
}
