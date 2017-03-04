package lt.gt.galaktika.mock.work;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lt.gt.galaktika.mock.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.User;

/**
 * This class will be used to create initial data in the database.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockDbConfig.class, ModelBeansConfig.class })
public class PrepareInitialData {
	
	@Autowired
	IUserDao userDao;
	
	@Test
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
