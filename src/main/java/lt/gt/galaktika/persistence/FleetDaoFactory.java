package lt.gt.galaktika.persistence;

import lt.gt.galaktika.persistence.impl.MemoryFleetDao;

public class FleetDaoFactory
{
	private static FleetDaoFactory _instance;
	private FleetDaoFactory () {}
	
	private FleetDaoType defaultType = FleetDaoType.MEMORY;
	
	public static FleetDaoFactory getInstance () {
		if ( _instance == null ) {
			_instance = new FleetDaoFactory();
		}
		return _instance;
	}
	
	public IFleetDAO createFleetDao () {
		return createFleetDao( defaultType ); 
	}
	
	public IFleetDAO createFleetDao ( FleetDaoType daoType ) {
		if ( daoType == FleetDaoType.MEMORY ) {
			return new MemoryFleetDao();
		}
		assert false : "Unimplemented database dao type "+daoType;
		return null;
	}

	public FleetDaoType getDefaultType ()
	{
		return defaultType;
	}

	public void setDefaultType ( FleetDaoType defaultType )
	{
		this.defaultType = defaultType;
	}
}
