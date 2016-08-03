package lt.gt.galaktika.test.integration;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.model.DataSearchLimits;
import lt.gt.galaktika.model.DataSearchResult;
import lt.gt.galaktika.model.ESortMethods;
import lt.gt.galaktika.model.FleetSortData;
import lt.gt.galaktika.model.dao.DFleetFilter;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.entity.noturn.DFleet;
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
		DataSearchResult<DFleet> fleetsPage = fleetDao.loadPortion(pi, new DFleetFilter(), new FleetSortData() );
		fleetsPage.getRecords().stream().forEach(f -> System.out.println(f.getFleetId() + " " + f.getName()));
		
		ObjectMapper mapper = new ObjectMapper();


        String s = null;
        try {
            s = mapper.writeValueAsString(fleetsPage);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	@Ignore
	public void loadFleetsList() {
		List<Fleet> fleets = fleetService.getPage(1);
		fleets.stream().forEach( f -> System.out.println (f) );
	}
	
	@Test
//	@Ignore
	public void testLoadFleetList () {
		System.out.println ( "testLoadFleetList called" );
		DataSearchLimits pi = new DataSearchLimits();
		pi.setLimitAmount(10);
		
		FleetSortData fsd=new FleetSortData();
		fsd.setNameSort( ESortMethods.ASC);
		DataSearchResult<Fleet> fleetsR = fleetService.getFleets(pi, null, 0, false, fsd );
		System.out.println ( "Total amount : " + fleetsR.getTotalAmount() );
		fleetsR.getRecords().stream().forEach(f -> System.out.println(f.getFleetId() + " " + f.getName()+" owner="+f.getOwner().getNationName() ));
		
		ObjectMapper mapper = new ObjectMapper();


        String s = null;
        try {
            s = mapper.writeValueAsString(fleetsR);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        System.out.println ( "json fleets="+s);

	}
	
//	@Test
	public void testStoreFleet () {
		System.out.println ( "testStoreFleet called" );
		long id = fleetService.save( new Fleet ("Naujas fleetas"), 0 );
	}

}
