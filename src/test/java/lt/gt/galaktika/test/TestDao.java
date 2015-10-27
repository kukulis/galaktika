package lt.gt.galaktika.test;

import org.junit.Test;

import lt.gt.galaktika.data.IFleet;
import lt.gt.galaktika.data.IFullFleet;
import lt.gt.galaktika.data.IShipContainerFleet;
import lt.gt.galaktika.data.impl.Fleet;
import lt.gt.galaktika.data.impl.FullFleet;
import lt.gt.galaktika.persistence.FleetDaoFactory;
import lt.gt.galaktika.persistence.FleetDaoType;
import lt.gt.galaktika.persistence.IFleetDAO;

public class TestDao
{
	@Test
	public void testCreateFleetDao () {
		IFleetDAO fleetDao = FleetDaoFactory.getInstance().createFleetDao( FleetDaoType.MEMORY );
		fleetDao.create( new Fleet () );
		
		IFleet fleet = fleetDao.findById( new Fleet(), Fleet.class );
		System.out.println ( "the first fleet class is " + fleet.getClass().getName() );
		
		IShipContainerFleet scFleet = fleetDao.findById(fleet, IShipContainerFleet.class );
		System.out.println ( "the seccond fleet class is " + scFleet.getClass().getName() + " fill info="+scFleet.getFillInfo() );
		
		IFullFleet fFleet = fleetDao.findById(scFleet, IFullFleet.class );
		
		System.out.println ( "test finished, the fleet class is " + fFleet.getClass().getName() + " fill info="+fFleet.getFillInfo() );
		
		FullFleet ff = fleetDao.findById(fFleet, FullFleet.class );
		System.out.println ( "end of the end ");
	}
}
