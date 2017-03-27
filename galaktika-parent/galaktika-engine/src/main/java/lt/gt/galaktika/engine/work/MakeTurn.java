package lt.gt.galaktika.engine.work;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;

/**
 * This is engine.
 *
 */
public class MakeTurn {
	public final static void main ( String args[]) {
		System.out.println( "MakeTurn main called" );
		new MakeTurn().makeTurn ();
	}
	
	private ApplicationContext context;
	
	private void makeTurn() {
		context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		GalaxyService gs = context.getBean(GalaxyService.class);
		List<Galaxy> galaxies = gs.getGalaxies(new GalaxiesFilter().setPurpose(EGalaxyPurposes.PLAY).setActive(true));
		if ( galaxies.size() == 0 ) {
			System.err.println( "There is no play active galaxy");
			return;
		}
			
		Galaxy g = galaxies.get(0);
		
		surfaceActions(g);
		orbitActions(g);
		flightActions(g);
		combatActions(g);

//		updateTurn(g); // TODO enable later
	}
	private void surfaceActions(Galaxy g) {
		// for each nation
		// for each nation's planet surface
		// do surface command
		NationService ns = context.getBean( NationService.class);
		PlanetService ps = context.getBean(PlanetService.class);
		PlanetDataService pds = context.getBean( PlanetDataService.class ); 
		List<Nation> nations = ns.getNations( g );
		for ( Nation n : nations ) {
			List<Long> planets = pds.findPlanetsIds( n, g.getTurn() );
			for ( Long planetId: planets ) {
				System.out.println( "Working with planet "+planetId );
				pds.loadPlanetSurface(planetId, g.getTurn());
				// TODO
			}
		}
	}
	
	private void orbitActions(Galaxy g) {
		
	}
	private void flightActions(Galaxy g) {
		
	}
	
	private void combatActions(Galaxy g) {
		
	}
	
	private void updateTurn ( Galaxy g) {
		g.setTurn(g.getTurn()+1);
		GalaxyService gs = context.getBean(GalaxyService.class);
		gs.update(g);
	}
}
