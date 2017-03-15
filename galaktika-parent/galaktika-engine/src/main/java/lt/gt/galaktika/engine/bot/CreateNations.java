package lt.gt.galaktika.engine.bot;

import java.util.List;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;

public class CreateNations {
	public final static void main(String args[]) {
		// TODO create nation for user1
		// TODO create nation for user2
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
//		INationDao nationDao = context.getBean( INationDao.class );
//		IDAO dao = context.getBean( IDAO.class );
		
		NationService nationService = context.getBean( NationService.class);
		GalaxyService galaxyService = context.getBean( GalaxyService.class);
		
		IUserDao userDao = context.getBean( IUserDao.class );
		User player1 = userDao.getByLogin( "player1" );
		User player2 = userDao.getByLogin( "player2" );
		
		List< Galaxy> galaxies = galaxyService.getGalaxies(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));
		Assert.assertNotNull( galaxies );
		Assert.assertNotEquals( 0, galaxies.size());
		
		Galaxy galaxy = galaxies.get(0);
		
		Assert.assertNotNull( player1 );
		Assert.assertNotNull( player2 );
		
		Nation nation1 = new Nation("nation1");
		Nation nation2 = new Nation("nation2");
		
		nation1 = nationService.createNation(nation1, player1, galaxy);
		nation2 = nationService.createNation(nation2, player2, galaxy);
		
		Assert.assertNotNull( nation1 );
		Assert.assertNotNull( nation2 );
		
		Assert.assertNotEquals(0, nation1.getNationId());
		Assert.assertNotEquals(0, nation2.getNationId());
		
	
		
		
		
		
		
//		nationDao.
	}
}
