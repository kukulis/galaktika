package lt.gt.galaktika.engine.bot;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.SurfaceCommandIndustry;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;


/**
 * 
 * @author
 * 
 * @deprecated use GameService instead or call it from here
 *
 */
public class AssignNationsPlanets {
	public final static void main(String args[]) {
		System.out.println ( "TODO assign nations planets" );
		
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		
		NationService ns = context.getBean(NationService.class);
		GalaxyService gs = context.getBean(GalaxyService.class);
		IUserDao userDao = context.getBean( "userDao", IUserDao.class );
		PlanetService ps = context.getBean( PlanetService.class);
		
		User player1 = userDao.getByLogin("player1");
		User player2 = userDao.getByLogin("player2");
		
		if ( player1 == null ) {
			System.err.println( "Cant load player1" );
			return;
		}
		
		if ( player2 == null ) {
			System.err.println( "Cant load player2" );
			return;
		}
		
		List<Galaxy> galaxies = gs.getGalaxies(new GalaxiesFilter().setPurpose(EGalaxyPurposes.PLAY).setActive(true) );
		if ( galaxies.size() == 0 )
		{
			System.err.println( "No active play galaxies" );
			return;
		}
		
		Galaxy g = galaxies.get(0);
		if( g.getTurn() > 1 ) {
			System.out.println ( "The galaxy is beyond 1 turn: "+g.getTurn() );
			return;
		}
		
		Nation nation1 = ns.getNation(player1, g);
		Nation nation2 = ns.getNation(player2, g);
		
		List<Planet> planets = ps.findPlanets(g);
		if ( planets.size() < 2 ) {
			System.err.println( "Not enough planets in the galaxy" );
			return;
		}
		Planet planet1 = planets.get(0);
		Planet planet2 = planets.get(1);
		
		PlanetDataService pds = context.getBean( PlanetDataService.class );
		
		// check if there is already planet surface for turn 1
		PlanetSurface psurf1 = pds.loadPlanetSurface(planet1.getPlanetId(), 1);
		PlanetSurface psurf2 = pds.loadPlanetSurface(planet2.getPlanetId(), 1);
		
		

		if ( psurf1 == null ) {
			PlanetSurface planetSurface1 = new PlanetSurface("Nation 1 home", nation1, 100, 100, 0, new SurfaceCommandIndustry());
			pds.storePlanetSurface2(planetSurface1, planet1, 1);
			System.out.println( "Created surface for planet1" );
		}
		else {
			System.err.println( "There is already surface for planet1" );
		}

		if( psurf2 == null ) {
			PlanetSurface planetSurface2 = new PlanetSurface("Nation 2 home", nation2, 100, 100, 0, new SurfaceCommandIndustry());
			pds.storePlanetSurface2(planetSurface2, planet2, 1);
			System.out.println( "Created surface for planet2" );
		}
		else {
			System.err.println( "There is already surface for planet2" );
		}
	}
}
