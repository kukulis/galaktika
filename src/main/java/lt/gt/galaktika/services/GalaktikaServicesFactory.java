package lt.gt.galaktika.services;

import java.util.HashMap;
import java.util.Map;

import lt.gt.galaktika.services.impl.DataHolder;
import lt.gt.galaktika.services.impl.FleetServiceMock;

public class GalaktikaServicesFactory
{
	private static GalaktikaServicesFactory _instance = null;
	
	private Map <EGalaktikaServices, IGalaktikaService> servicesMap=new HashMap <EGalaktikaServices, IGalaktikaService> ();
	
	private DataHolder dataHolder;
	
	/**
	 * Hidden constructor
	 */
	private GalaktikaServicesFactory () {
		dataHolder = new DataHolder();
	}
	
	public static GalaktikaServicesFactory getInstance () {
		if ( _instance == null ) {
			_instance = new GalaktikaServicesFactory();
		}
		return _instance;
	}
	
	public IGalaktikaService getService ( EGalaktikaServices serviceID ) {
		IGalaktikaService service = servicesMap.get(serviceID );
		if ( service == null ) {
			service = createService ( serviceID );
			servicesMap.put( serviceID, service );
		}
		return service;
	}
	
	private IGalaktikaService createService (  EGalaktikaServices serviceID ) {
		if ( serviceID == EGalaktikaServices.FLEET_SERVICE ) { 
			return new FleetServiceMock(dataHolder);
		}
		// TODO other services
		throw new RuntimeException ( "Service "+serviceID + "is not implemented" );
	}
	
}
