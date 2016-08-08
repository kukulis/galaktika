package lt.gt.galaktika.model.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lt.gt.galaktika.model.dao.IFleetDaoOld;
import lt.gt.galaktika.model.dao.IShipGroupDao;
import lt.gt.galaktika.model.dao.impl.FleetDaoOld;
import lt.gt.galaktika.model.dao.impl.ShipGroupDao;

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
    public IFleetDaoOld getFleetDao() {
        return new FleetDaoOld();
    }
    
//    @Bean
//    public DFleetService getDFleetService() {
//    	return new DFleetService();
//    }
}