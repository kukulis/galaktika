package lt.gt.galaktika.core.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.TechnologyType;
import lt.gt.galaktika.core.engines.PlanetEngine;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetData;
import lt.gt.galaktika.core.planet.PlanetOrbit;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.SurfaceCommandTechnologies;

public class TestTechnologies
{
	private PlanetData planetData[];
	private Nation[] nations = new Nation[] { new Nation(1, "pirma tauta"), new Nation(2, "antra tauta"),

	};

	@Before
	public void before ()
	{
		planetData = new PlanetData[] { 
				new PlanetData(new Planet(50, 50, 100, 1),
				new PlanetSurface("planeta I", nations[0], 100, 100, 100, new SurfaceCommandTechnologies( TechnologyType.ATTACK )),
				new PlanetOrbit()), 
				new PlanetData(new Planet(50, 50, 100, 1),
				new PlanetSurface("planeta II", nations[1], 100, 50, 0, new SurfaceCommandTechnologies( TechnologyType.DEFENCE )),
				new PlanetOrbit()) 
		};
	}

	@Test
	public void testTechnologies ()
	{
		System.out.println("testTechnologies called");

		Map<Nation,Technologies> technologiesMap = new HashMap<>();
		// create technologies map
		Arrays.asList(nations).forEach(n->technologiesMap.put(n, new Technologies()));

		// calculate technologies
		PlanetEngine planetEngine = new PlanetEngine();
		Map<Nation,Technologies> calculatedTechnologies= planetEngine.cloneAndExecuteTechnologies(Arrays.asList(planetData), technologiesMap);
		
		// print technologies map results
		calculatedTechnologies.forEach((n,t) -> { 
			System.out.println ( "Nation's "+ n + " technologies are : " + t );
		});
	}

	@After
	public void after ()
	{

	}
}
