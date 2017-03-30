package lt.gt.galaktika.engine.bot;

import java.util.List;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.TechnologiesService;

public class CreateNations {
	public final static void main(String args[]) {
	
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
//		INationDao nationDao = context.getBean( INationDao.class );
//		IDAO dao = context.getBean( IDAO.class );
		
		NationService nationService = context.getBean( NationService.class);
		GalaxyService galaxyService = context.getBean( GalaxyService.class);
		TechnologiesService technologiesService = context.getBean( TechnologiesService.class );
		
		IUserDao userDao = context.getBean( IUserDao.class );
		User player1 = userDao.getByLogin( "player1" );
		User player2 = userDao.getByLogin( "player2" );
		
		List< Galaxy> galaxies = galaxyService.getGalaxies(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));
		Assert.assertNotNull( galaxies );
		Assert.assertNotEquals( 0, galaxies.size());
		
		Galaxy galaxy = galaxies.get(0);
		
		Assert.assertNotNull( player1 );
		Assert.assertNotNull( player2 );
		
		Nation nation1 = nationService.getNation(player1, galaxy);
		if ( nation1 == null ) {
			nation1 = new Nation("nation1");
			nation1 = nationService.createNation(nation1, player1, galaxy);
		}
		
		Nation nation2 = nationService.getNation(player2, galaxy);
		if ( nation2 == null ) {
			nation1 = new Nation("nation2");
			nation1 = nationService.createNation(nation2, player2, galaxy);
		}
		
		Assert.assertNotNull( nation1 );
		Assert.assertNotNull( nation2 );
		
		Assert.assertNotEquals(0, nation1.getNationId());
		Assert.assertNotEquals(0, nation2.getNationId());
		
	
		// create technologies for nations
		try {
			technologiesService.createTechnologies ( new Technologies(), nation1, 1);
		} catch ( DataIntegrityViolationException e ) {
			System.err.println(e.getMessage() );
		}
		
		try {
			technologiesService.createTechnologies ( new Technologies(), nation2, 1);
		} catch ( DataIntegrityViolationException e ) {
			System.err.println(e.getMessage() );
		}
	}
}
