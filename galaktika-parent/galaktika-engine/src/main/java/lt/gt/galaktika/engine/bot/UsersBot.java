package lt.gt.galaktika.engine.bot;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.TechnologiesService;

@Service
public class UsersBot {
	
	public final static String PLAYER1 = "player1";
	public final static String PLAYER2 = "player2";
	public final static String ADMIN = "admin";
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	NationService nationService;
	
	@Autowired
	GalaxyService galaxyService;

	@Autowired
	TechnologiesService technologiesService;
	
	

	public boolean createUsers() {
		boolean ret = true;
		
		User adminUser = new User("admin@email.lt", "admin"); 
		adminUser.setLogin(ADMIN);
		adminUser.setPassword( "admin" );
		try { 
			userDao.save(adminUser);
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
			ret = false;
		}
		
		User player1User = new User("player1@email.lt", "player1");
		player1User.setLogin(PLAYER1);
		player1User.setPassword( "player1");
		try {
			userDao.save( player1User);
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
			ret = false;
		}
		
		User player2User = new User("player2@email.lt", "player2");
		player2User.setLogin(PLAYER2);
		player2User.setPassword( "player2" );
		try {
			userDao.save( player2User );
		}
		catch ( DataIntegrityViolationException dve ) {
			System.out.println(dve.getMessage() );
			ret = false;
		}
		
		return ret;
	}
	
	public boolean createNations () {
		
		boolean ok = true;
		
		User player1 = userDao.getByLogin( PLAYER1 );
		User player2 = userDao.getByLogin( PLAYER2 );
		
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
			nation2 = new Nation("nation2");
			nation2 = nationService.createNation(nation2, player2, galaxy);
		}
		
	
		// create technologies for nations
		try {
			technologiesService.createTechnologies ( new Technologies(), nation1, 1);
		} catch ( DataIntegrityViolationException e ) {
			System.err.println(e.getMessage() );
			ok = false;
		}
		
		try {
			technologiesService.createTechnologies ( new Technologies(), nation2, 1);
		} catch ( DataIntegrityViolationException e ) {
			System.err.println(e.getMessage() );
			ok = false;
		}

		return ok;
	}
}
