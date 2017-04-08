package lt.gt.galaktika.engine.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.engines.PlanetEngine;
import lt.gt.galaktika.core.exception.GalaktikaException;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetData;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.SurfaceCommandIndustry;
import lt.gt.galaktika.core.planet.SurfaceCommandProduction;
import lt.gt.galaktika.core.planet.SurfaceCommandTechnologies;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetDataService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.TechnologiesService;
import lt.gt.math.Circle;
import lt.gt.math.IPlaneContainer;
import lt.gt.math.PlanePoint;
import lt.gt.math.SectoredPlaneContainer;
import lt.gt.math.SectoredPlaneContainer.Initializer;

@Service
public class GameService {
	public final static double MIN_DISTANCE = 5;
	public final static int MAX_FAILS = 100;
	public final static double MAX_SIZE_X = 100;
	public final static double MAX_SIZE_Y = 100;

	@Autowired
	GalaxyService galaxyService;

	@Autowired
	NationService nationService;

	@Autowired
	PlanetService planetService;

	@Autowired
	PlanetDataService planetDataService;

	@Autowired
	TechnologiesService technologiesService;
	
	public void makeTurn() {
		List<Galaxy> galaxies = galaxyService
				.getGalaxies(new GalaxiesFilter().setPurpose(EGalaxyPurposes.PLAY).setActive(true));
		if (galaxies.size() == 0) {
			System.err.println("There is no play active galaxy");
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
		PlanetEngine planetEngine = new PlanetEngine();

		// for each nation
		// for each nation's planet surface
		// do surface command

		List<Nation> nations = nationService.getNations(g);
		for (Nation n : nations) {
			List<Long> planets = planetDataService.findPlanetsIds(n, g.getTurn());
			Technologies t = technologiesService.getNationTechnologies(n, g.getTurn());

			for (Long planetId : planets) {
				System.out.println("Working with planet " + planetId);
				// PlanetSurface planetSurface = pds.loadPlanetSurface(planetId,
				// g.getTurn());
				PlanetData pd = planetDataService.loadPlanetData(planetId, g.getTurn());
				// -- TODO remove after debugging
				if ( pd.getSurface().getName()== "Technocrats") {
					System.out.println( "Found Technocrats");
				}
				// --------
				planetEngine.executePopulation(pd);
				Planet p = planetService.load(planetId);
				if (pd.getSurface().getSurfaceCommand() instanceof SurfaceCommandProduction) {
//					SurfaceCommandProduction scp = (SurfaceCommandProduction) pd.getSurface().getSurfaceCommand();
					planetEngine.executeProduction(pd, t);
				} else if (pd.getSurface().getSurfaceCommand() instanceof SurfaceCommandIndustry) {
//					SurfaceCommandIndustry sci = (SurfaceCommandIndustry) pd.getSurface().getSurfaceCommand();
					planetEngine.executeIndustry(pd);
				} else if (pd.getSurface().getSurfaceCommand() instanceof SurfaceCommandTechnologies) {
//					SurfaceCommandTechnologies sct = (SurfaceCommandTechnologies) pd.getSurface().getSurfaceCommand();
					planetEngine.executeTechnologies(pd, t);
				}

				// store planet data (surface only for now) to a new turn
				planetDataService.storePlanetSurface2(pd.getSurface(), p, g.getTurn() + 1);
//				planetDataService.
				// TODO store orbit
			}

			// store technologies to a new turn
			t.setTechnologiesId(0);
			technologiesService.createTechnologies(t, n, g.getTurn() + 1);
		}
	}

	private void orbitActions(Galaxy g) {

	}

	private void flightActions(Galaxy g) {

	}

	private void combatActions(Galaxy g) {

	}

	private void updateTurn(Galaxy g) {
		g.setTurn(g.getTurn() + 1);
		galaxyService.update(g);
	}

	public boolean createGalaxy() {
		List<Galaxy> galaxies = galaxyService
				.getGalaxies(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));
		if (galaxies.size() > 0)
			return false;
		Galaxy galaxy = new Galaxy(1000, 1000);
		galaxy.setTurn(1);
		galaxyService.createGalaxy(galaxy, EGalaxyPurposes.PLAY, true);
		return true;
	}

	public boolean createPlanets() {
		List<Galaxy> galaxies = galaxyService
				.getGalaxies(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));

		if (galaxies.size() <= 0) {
			System.err.println("There is no active galaxy with the play purpose");
			return false;
		}

		Galaxy g = galaxies.get(0);

		// check if the galaxy already has planets
		List<Planet> planets = planetService.findPlanets(g);
		if (planets.size() != 0) {
			System.out.println("There already is " + planets.size() + " planets in the galaxy");
			return false;
		}

		// let the size of the galaxy be 100 x 100 ( we will later correct these
		// values )
		IPlaneContainer planeContainer = null;
		try {
			planeContainer = new SectoredPlaneContainer(
					new Initializer().setMinLimits(0, 0).setCounts(10, 10).setSteps(10, 10));
		} catch (GalaktikaException ge) {
			System.out.println(ge.getMessage());
			return false;
		}

		while (true) {
			boolean inserted = false;
			for (int i = 0; i < MAX_FAILS; i++) {
				// 1) generate point
				double pX = Math.random() * MAX_SIZE_X;
				double pY = Math.random() * MAX_SIZE_Y;
				// 2) search neigbours near that point
				PlanePoint neighbour = planeContainer.getAnyCirclePoint(new Circle(pX, pY, MIN_DISTANCE));
				// 3) if none found, then insert (inserted=true)
				if (neighbour == null) {
					planeContainer.add(new Planet(pX, pY, 100, 1));
					inserted = true;
					break;
				}
				// 4) else continue
			}
			if (!inserted)
				break;
		}

		System.out.println("Points inserted=" + planeContainer.getAllPoints().size());

		System.out.println("Sector statistics:");
		((SectoredPlaneContainer) planeContainer).printSectorsStatistics(System.out);
		// create these planets to galaxy
		planeContainer.getAllPoints().stream().forEach(p -> planetService.createPlanet((Planet) p, g));
		
		return true;
	}
	
	public boolean assignSurfaces () {
		Galaxy g = galaxyService
			.getGalaxy(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY));
		// get all nations
		List<Nation> nations = nationService.getNations( g );
		List<Planet> planets = planetService.findPlanets(g);
		
		for ( int i = 0; i < nations.size(); i ++ ) {
			Planet p = planets.get(i);
			Nation n = nations.get(i);
			PlanetSurface ps = new PlanetSurface( n.getNationName()+ " home", n, 100, 100, 0, new SurfaceCommandIndustry());
			planetDataService.storePlanetSurface2(ps, p, 1);
		}
		return true;
	}
}
