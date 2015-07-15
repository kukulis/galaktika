package lt.gt.galaktika.test;

import lt.gt.galaktika.data.IFleet;
import lt.gt.galaktika.services.EGalaktikaServices;
import lt.gt.galaktika.services.GalaktikaServicesFactory;
import lt.gt.galaktika.services.IFleetService;

import org.junit.Test;

public class TestServices
{
	@Test
	public void testCreateService ( ) {
		System.out.println ( "testCreateService called" );
		// TODO
		IFleetService fleetService = (IFleetService) GalaktikaServicesFactory.getInstance().getService( EGalaktikaServices.FLEET_SERVICE );
		IFleet fleet = fleetService.getFleet( 2 );
		
		System.out.println ( "loaded fleet " + fleet );
	}
}
