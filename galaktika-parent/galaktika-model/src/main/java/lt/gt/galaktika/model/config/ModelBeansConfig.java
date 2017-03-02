package lt.gt.galaktika.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.dao.IFleetDaoOld;
import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.dao.IPlanetDao;
import lt.gt.galaktika.model.dao.IPlanetSurfaceDao;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.dao.impl.DAO;
import lt.gt.galaktika.model.dao.impl.DFleetDao;
import lt.gt.galaktika.model.dao.impl.DFleetDataDao;
import lt.gt.galaktika.model.dao.impl.DPlanetSurfaceDao;
import lt.gt.galaktika.model.dao.impl.FleetDaoOld;
import lt.gt.galaktika.model.dao.impl.PlanetDao;
import lt.gt.galaktika.model.dao.impl.ShipGroupDao;
import lt.gt.galaktika.model.service.FleetsService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.ShipDesignService;
import lt.gt.galaktika.model.service.ShipService;
import lt.gt.galaktika.model.service.SpaceLocationService;
import lt.gt.galaktika.model.service.TechnologiesService;

@Configuration
@ComponentScan(basePackages = { "lt.galaktika.model.dao", "lt.galaktika.model.entity", "lt.galaktika.model.service", })
public class ModelBeansConfig {

	@Bean
	public IShipGroupDao getShipGroupDao() {
		return new ShipGroupDao();
	}

	@Bean
	public IFleetDaoOld getFleetDao() {
		return new FleetDaoOld();
	}

	@Bean
	public IPlanetDao getPlanetDao() {
		return new PlanetDao();
	}

	@Bean(name = "dao")
	public IDAO getDao() {
		return new DAO();
	}

	@Bean
	public IFleetDataDao getDFleetDataDao() {
		return new DFleetDataDao();
	}
	
	@Bean
	public IFleetDao getDFleetDao () {
		return new DFleetDao();
	}
	
	@Bean
	public IPlanetSurfaceDao getDPlanetSurfaceDao () {
		return new DPlanetSurfaceDao();
	}
	
	@Bean
	public NationService getNationService () {
		return new NationService();
	}
	
	@Bean
	public FleetsService getFleetsService () {
		return new FleetsService();
	}
	
	@Bean
	public PlanetService getPlanetService () {
		return new PlanetService();
	}
	
	@Bean
	public ShipDesignService getShipDesignService () {
		return new ShipDesignService();
	}
	
	@Bean
	public ShipService getShipService () {
		return new ShipService ();
	}
	
	@Bean
	public SpaceLocationService getSpaceLocationService () {
		return new SpaceLocationService();
	}
	
	@Bean
	public TechnologiesService getTechnologiesService() {
		return new TechnologiesService();
	}
	
	@Bean
	public PlanetDataService getPlanetDataService() {
		return new PlanetDataService();
	}
	
}