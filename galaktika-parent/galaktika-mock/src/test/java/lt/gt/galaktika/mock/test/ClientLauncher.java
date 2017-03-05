package lt.gt.galaktika.mock.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.mock.config.AdditionalBeansConfig;
import lt.gt.galaktika.mock.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IDAO;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.User;

public class ClientLauncher {
	public final static void main ( String args[] ) {
		System.out.println ( "ClientLauncher.main called" );
		
		 ApplicationContext context = new AnnotationConfigApplicationContext( MockDbConfig.class, ModelBeansConfig.class, AdditionalBeansConfig.class);
		 
//		 SessionsManager sm = context.getBean( "sessionsManager", SessionsManager.class );
//		 SessionMock smock = sm.login("test", "test");
//		 if ( smock != null ) {
//			 if ( smock.isValid() )
//				 System.out.println( "valid session id="+ smock.getId() );
//			 else
//				 System.out.println( "invalid session id="+ smock.getId() );
//		 }
		 
		 IDAO dao = context.getBean("dao", IDAO.class );
		 
		 IUserDao userDao = context.getBean( "userDao", IUserDao.class );
		 User user = userDao.getByLogin( "test" );
		 if ( user != null ) 
			 System.out.println( "received user with email="+user.getEmail() );
		 
//		 context.close();
	}
}
