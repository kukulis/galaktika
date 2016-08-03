package lt.gt.galaktika.model.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//import lt.gt.galaktika.model.dao.DFleetService;
import lt.gt.galaktika.model.dao.FleetDao;
import lt.gt.galaktika.model.dao.IFleetDao;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.dao.ShipGroupDao;

@Configuration
@ComponentScan(basePackages = {
    "lt.galaktika.model.dao",
    "lt.galaktika.model.entity",
    "lt.galaktika.model.service",
})

public class TestConfig {

    @Bean
    public IShipGroupDao getShipGroupDao() {
        return new ShipGroupDao();
    }
    @Bean
    public IFleetDao getFleetDao() {
        return new FleetDao();
    }
    
//    @Bean
//    public DFleetService getDFleetService() {
//    	return new DFleetService();
//    }
}