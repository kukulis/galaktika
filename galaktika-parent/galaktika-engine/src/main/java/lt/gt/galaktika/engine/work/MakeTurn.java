package lt.gt.galaktika.engine.work;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.engines.PlanetEngine;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetData;
import lt.gt.galaktika.core.planet.SurfaceCommandIndustry;
import lt.gt.galaktika.core.planet.SurfaceCommandProduction;
import lt.gt.galaktika.core.planet.SurfaceCommandTechnologies;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.TechnologiesService;

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
		updateTurn(g);
	}
	private void surfaceActions(Galaxy g) {
		// for each nation
		// for each nation's planet surface
		// do surface command
		NationService ns = context.getBean( NationService.class);
		PlanetService ps = context.getBean(PlanetService.class);
		PlanetDataService pds = context.getBean( PlanetDataService.class );
	    PlanetEngine planetEngine = new PlanetEngine();
	    TechnologiesService ts = context.getBean( TechnologiesService.class );

		List<Nation> nations = ns.getNations( g );
		for ( Nation n : nations ) {
			List<Long> planets = pds.findPlanetsIds( n, g.getTurn() );
			Technologies t = ts.getNationTechnologies(n, g.getTurn());
			
			for ( Long planetId: planets ) {
				 System.out.println( "Working with planet "+planetId );
//				 PlanetSurface planetSurface = pds.loadPlanetSurface(planetId, g.getTurn());
				 PlanetData pd = pds.loadPlanetData(planetId, g.getTurn() );
				 planetEngine.executePopulation( pd );
				 Planet p = ps.load( planetId );
				 if ( pd.getSurface().getSurfaceCommand() instanceof SurfaceCommandProduction ) {
					 SurfaceCommandProduction scp = (SurfaceCommandProduction) pd.getSurface().getSurfaceCommand();
					 planetEngine.executeProduction(pd, t);
				 }
				 else if ( pd.getSurface().getSurfaceCommand() instanceof SurfaceCommandIndustry ) {
					 SurfaceCommandIndustry sci = (SurfaceCommandIndustry) pd.getSurface().getSurfaceCommand();
					 planetEngine.executeIndustry(pd);
				 }
				 else if ( pd.getSurface().getSurfaceCommand() instanceof SurfaceCommandTechnologies ) {
					 SurfaceCommandTechnologies sct = (SurfaceCommandTechnologies) pd.getSurface().getSurfaceCommand();
					 planetEngine.executeTechnologies(pd, t);
				 }
				 
				 // store planet data (surface only for now) to a new turn
				 pds.storePlanetSurface2(pd.getSurface(), p, g.getTurn()+1);
			}
			
			// store technologies to a new turn
			t.setTechnologiesId(0);
			ts.createTechnologies(t, n, g.getTurn()+1);
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
