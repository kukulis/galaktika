package lt.gt.galaktika.persistence;

import lt.gt.galaktika.persistence.impl.MemoryFleetDao;

public class FleetDaoFactory
{
	private static FleetDaoFactory _instance;
	private FleetDaoFactory () {}
	
	private DaoType defaultType = DaoType.MEMORY;
	
	public static FleetDaoFactory getInstance () {
		if ( _instance == null ) {
			_instance = new FleetDaoFactory();
		}
		return _instance;
	}
	
	public IFleetDAO createFleetDao () {
		return createFleetDao( defaultType ); 
	}
	
	public IFleetDAO createFleetDao ( DaoType daoType ) {
		if ( daoType == DaoType.MEMORY ) {
			return new MemoryFleetDao();
		}
		assert false : "Unimplemented database dao type "+daoType;
		return null;
	}

	public DaoType getDefaultType ()
	{
		return defaultType;
	}

	public void setDefaultType ( DaoType defaultType )
	{
		this.defaultType = defaultType;
	}
}
