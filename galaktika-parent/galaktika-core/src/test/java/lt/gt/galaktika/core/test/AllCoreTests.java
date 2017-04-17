package lt.gt.galaktika.core.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAggreementsObject.class, TestBattle.class, TestCompareGalaxyLocations.class, TestDiff.class,
		TestFindPlanetBattles.class, TestFindSpaceBattles.class, TestMakeShip.class, TestPow.class,
		TestShortestVector.class, TestSurfaceCommands.class, TestTechnologies.class })
public class AllCoreTests {

}
