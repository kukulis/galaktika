package lt.gt.galaktika.model.test.memory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.dao.IFleetDataDao;
import lt.gt.galaktika.model.dao.IPlanetDao;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.dao.impl.DAO;
import lt.gt.galaktika.model.dao.impl.DFleetDataDao;
import lt.gt.galaktika.model.dao.impl.FleetDao;
import lt.gt.galaktika.model.dao.impl.PlanetDao;
import lt.gt.galaktika.model.dao.impl.ShipGroupDao;

@Configuration
@ComponentScan(basePackages = { "lt.galaktika.model.dao", "lt.galaktika.model.entity", "lt.galaktika.model.service", })

public class MemoryBeansConfig {

	@Bean
	public IShipGroupDao getShipGroupDao() {
		return new ShipGroupDao();
	}

	@Bean
	public IFleetDao getFleetDao() {
		return new FleetDao();
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
}