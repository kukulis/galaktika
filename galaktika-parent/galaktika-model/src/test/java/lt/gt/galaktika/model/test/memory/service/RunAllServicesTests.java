package lt.gt.galaktika.model.test.memory.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MemoryTestBaseServices.class,
	TestGalaxyService.class,
	TestMemoryFleetsService.class,
	TestMemoryNationService.class,
	TestMemoryPlanetDataService2.class,
	TestShipDesignService.class
        })
public class RunAllServicesTests {

}
