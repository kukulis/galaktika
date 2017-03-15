package lt.gt.galaktika.engine.bot;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.User;

public class CreateUsers {
	public static void main (String args[] ) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		IUserDao userDao = context.getBean( IUserDao.class );
		User adminUser = new User("admin@email.lt", "admin"); 
		adminUser.setLogin("admin");
		adminUser.setPassword( "admin" );
		Long adminId = null;
		try { 
			adminId = userDao.save(adminUser);
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
		}
		
		User player1User = new User("player1@email.lt", "player1");
		player1User.setLogin("player1");
		player1User.setPassword( "player1");
		Long player1Id = null;
		try {
			player1Id = userDao.save( player1User);
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
		}
		
		User player2User = new User("player2@email.lt", "player2");
		player2User.setLogin("player2");
		player2User.setPassword( "player2" );
		Long player2Id = null;
		try {
			player2Id = userDao.save( player2User );
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
		}
		
		Assert.assertNotNull(adminId);
		Assert.assertNotEquals(0, adminId.longValue());
		Assert.assertNotNull(player1Id);
		Assert.assertNotEquals(0, player1Id.longValue());
		Assert.assertNotNull(player2Id);
		Assert.assertNotEquals(0, player2Id.longValue());
		
	}
}
