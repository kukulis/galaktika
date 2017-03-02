package lt.gt.galaktika.mock.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestSessionsManager.class, 
    })
public class MockSuite {
	static {
		try {
			new TestSessionsManager();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
    }
//	public static void main (String args[]) {
//		
//	}
}
