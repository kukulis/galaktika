package lt.gt.galaktika.persistence;

import lt.gt.galaktika.persistence.impl.MemoryGalaxyDao;

public class GalaxyDaoFactory
{
	
	private DaoType defaultType = DaoType.MEMORY;
	
	private GalaxyDaoFactory() {
		
	}
	
	private static class SingletonHelper {
		private static final GalaxyDaoFactory INSTANCE = new GalaxyDaoFactory();
	}
	
	public static GalaxyDaoFactory getInstance () {
		return SingletonHelper.INSTANCE;
	}
	
	public IGalaxyDAO createGalaxyDao( DaoType daoType ) {
		if ( daoType == DaoType.MEMORY ) {
			return new MemoryGalaxyDao();
		}
		else throw new RuntimeException( "Unimplemented daoType"+ daoType );
	}
	
	
	public IGalaxyDAO createGalaxyDao() {
		return createGalaxyDao(defaultType);
	}
}
