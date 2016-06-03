package lt.gt.galaktika.test.integration;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.dao.DFleetFilter;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.entity.DFleet;
import lt.gt.galaktika.model.service.FleetService;
import lt.gt.galaktika.web.GalaktikaApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GalaktikaApplication.class)
@WebAppConfiguration
public class TestFleets
{
	@Autowired
	private FleetService fleetService;

	@Autowired
	private IFleetDao fleetDao;

	@Test
	@Ignore
	public void loadFleetsPage ()
	{
		System.out.println("loadFleetsPage called");
		DataSearchLimits pi = new DataSearchLimits();
		pi.setLimitAmount(2);
		DataSearchResult<DFleet> fleetsPage = fleetDao.loadPortion(pi, new DFleetFilter() );
		fleetsPage.getRecords().stream().forEach(f -> System.out.println(f.getFleetId() + " " + f.getName()));
	}
	
	@Test
	@Ignore
	public void loadFleetsList() {
		List<Fleet> fleets = fleetService.getPage(1);
		fleets.stream().forEach( f -> System.out.println (f) );
	}
	
	// TODO test fleetService.getFleets
	
	@Test
//	@Ignore
	public void testLoadFleetList () {
		// TODO
		System.out.println ( "testLoadFleetList called" );
		DataSearchLimits pi = new DataSearchLimits();
		pi.setLimitAmount(2);
		
		DataSearchResult<Fleet> fleetsR = fleetService.getFleets(pi, 2);
		System.out.println ( "Total amount : " + fleetsR.getTotalAmount() );
		fleetsR.getRecords().stream().forEach(f -> System.out.println(f.getFleetId() + " " + f.getName()));
	}
	
//	@Test
	public void testStoreFleet () {
		System.out.println ( "testStoreFleet called" );
		long id = fleetService.save( new Fleet ("Naujas fleetas"), 0 );
	}

}