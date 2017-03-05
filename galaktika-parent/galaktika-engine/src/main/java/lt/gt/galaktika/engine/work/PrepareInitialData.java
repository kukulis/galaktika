package lt.gt.galaktika.engine.work;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.User;

/**
 * This class will be used to create initial data in the database.
 *
 */
public class PrepareInitialData {
	IUserDao userDao;
	public static void main ( String args []) {
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		PrepareInitialData pid = new PrepareInitialData();
		pid.userDao = context.getBean( IUserDao.class );
		pid.createInitialUsers();
	}
	
	public void createInitialUsers() {
		System.out.println( "createInitialUsers called" );
		
		User user = new User( "test@test.lt", "test"  );
		user.setLogin( "test" );
		user.setPassword("test");
		User userAdmin = new User( "admin@admin.lt", "admin" );
		userAdmin.setLogin("admin");
		userAdmin.setPassword("admin");
		
		Long id = userDao.save(user);
		Long adminId = userDao.save(userAdmin);
		
		if ( id != null ) 
			System.out.println( "Successfully created test user id="+id );
		
		if ( adminId != null )
			System.out.println( "Successfully created admin user id="+adminId );
		
	}
}
