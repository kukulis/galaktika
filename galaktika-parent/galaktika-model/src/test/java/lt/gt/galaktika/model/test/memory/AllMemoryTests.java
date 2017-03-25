package lt.gt.galaktika.model.test.memory;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FleetDataMemoryTest.class, 
        FleetMemoryTest.class, 
        MemoryTestDPlanetSurface.class, 
        PlanetMemoryTest.class,
        ShipMemoryTest.class,
        SpaceLocationMemoryTest.class,
        TestMemoryDNation.class,
        TestMemoryDShipDesign.class,
        TestMemoryDShipFactory.class,
        TestMemoryDSurfaceCommand.class,
        TestMemoryTechnologies.class,
        TurnMemoryTest.class,
        TestMemoryPlanetDao.class
        })
public class AllMemoryTests {

}
