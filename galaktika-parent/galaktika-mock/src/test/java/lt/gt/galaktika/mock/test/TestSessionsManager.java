package lt.gt.galaktika.mock.test;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.mock.SessionMock;
import lt.gt.galaktika.mock.SessionsManager;
import lt.gt.galaktika.mock.config.AdditionalBeansConfig;
import lt.gt.galaktika.mock.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.service.PlanetDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockDbConfig.class, ModelBeansConfig.class, AdditionalBeansConfig.class })
public class TestSessionsManager {
	
	@Autowired
	private PlanetDataService planetDataService;
	
	@Autowired
	private SessionsManager sessionsManager;
	
	@Test
	@Ignore
	public void testInitializeSessionManager () throws Exception {
		System.out.println( "Starting TestSessionsManager" );
		System.out.println( "Sleeping for 5 secconds" );
		Thread.sleep( 5000 );
		System.out.println( "End of TestSessionsManager" );
	}
	
	@Test
	public void testLogin () {
		SessionMock sess = sessionsManager.login( "aaa", "bbb" );
		System.out.println( "Received session with id="+sess.getId());
		if ( sess.isValid() )
			System.out.println( "Session is valid");
		else
			System.out.println( "Session is invalid");
		
		SessionMock sess2 = sessionsManager.login( "test", "test" );
		if ( sess2.isValid() )
			System.out.println( "Session is valid with id="+sess2.getId());
		else
			System.out.println( "Session is invalid with id="+sess2.getId());
		
	}
}
