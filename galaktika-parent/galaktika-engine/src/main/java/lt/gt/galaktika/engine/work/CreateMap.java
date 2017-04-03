package lt.gt.galaktika.engine.work;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.exception.GalaktikaException;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.math.Circle;
import lt.gt.math.IPlaneContainer;
import lt.gt.math.PlanePoint;
import lt.gt.math.SectoredPlaneContainer;
import lt.gt.math.SectoredPlaneContainer.Initializer;
import lt.gt.math.fuzzy.SimplePlanePoint;

/** 
 * 
 * @author 
 * 
 * @deprecated use GameService or transform this class to use it
 *
 */
public class CreateMap {
	public final static double MIN_DISTANCE = 5;
	public final static int MAX_FAILS = 100;
	public final static double MAX_SIZE_X = 100;
	public final static double MAX_SIZE_Y = 100;
	
	public final static void main(String args[] ) {
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		
		// we will create equal size planets in to random position,
		// but we will check if the position is valid ( not too near other planets )
		// and only in that case it will be created
		// so we may create planets array before saving to database
		GalaxyService gs = context.getBean( GalaxyService.class );
		List<Galaxy> galaxies = gs.getGalaxies(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));
		
		if ( galaxies.size() <= 0 ) {
			System.err.println( "There is no active galaxy with the play purpose" );
			return;
		}
		
		Galaxy g = galaxies.get(0);
		PlanetService ps = context.getBean( PlanetService.class );
		
		// check if the galaxy already has planets
		List<Planet> planets =  ps.findPlanets( g );
		if ( planets.size() != 0  ) {
			System.out.println( "There already is "+planets.size()+ " planets in the galaxy" );
			return;
		}
		
		// let the size of the galaxy be 100 x 100 ( we will later correct these values )
		IPlaneContainer planeContainer = null;
		try {
			planeContainer = new SectoredPlaneContainer(new Initializer().setMinLimits(0, 0).setCounts(10, 10).setSteps(10, 10));
		} catch ( GalaktikaException ge ) {
			System.out.println( ge.getMessage());
			return;
		}
		
		while (true) {
			boolean inserted = false;
			for ( int i=0; i < MAX_FAILS; i ++) {
				// 1) generate point
				double pX = Math.random() * MAX_SIZE_X;
				double pY = Math.random() * MAX_SIZE_Y;
				// 2) search neigbours near that point
				PlanePoint neighbour = planeContainer.getAnyCirclePoint(new Circle(pX, pY, MIN_DISTANCE ));
				// 3) if none found, then insert (inserted=true)
				if ( neighbour == null ) {
					planeContainer.add( new Planet(pX, pY, 100, 1));
					inserted = true;
					break;
				}
				// 4) else continue
			}
			if ( !inserted )
				break;
		}
		
		System.out.println( "Points inserted="+ planeContainer.getAllPoints().size() );
		
		System.out.println( "Sector statistics:");
		((SectoredPlaneContainer) planeContainer).printSectorsStatistics(System.out);
		// create these planets to galaxy
		planeContainer.getAllPoints().stream().forEach(p -> ps.createPlanet((Planet)p, g));
		
	}
}
