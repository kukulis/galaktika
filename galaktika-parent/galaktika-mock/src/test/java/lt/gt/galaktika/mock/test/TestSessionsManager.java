package lt.gt.galaktika.mock.test;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.mock.SessionMock;
import lt.gt.galaktika.mock.SessionsManager;
import lt.gt.galaktika.mock.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.service.PlanetDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockDbConfig.class, ModelBeansConfig.class })
public class TestSessionsManager {
	
	@Autowired
	private PlanetDataService planetDataService;
	
	@Test
	@Ignore
	public void testInitializeSessionManager () throws Exception {
		System.out.println( "Starting TestSessionsManager" );
		System.out.println( "Sleeping for 5 secconds" );
		Thread.sleep( 5000 );
		SessionsManager.getInstance();
		System.out.println( "End of TestSessionsManager" );
	}
	
	@Test
	public void testLogin () {
		SessionMock sess = SessionsManager.getInstance().login( "aaa", "bbb" );
		System.out.println( "Received session with id="+sess.getId());
	}
}
