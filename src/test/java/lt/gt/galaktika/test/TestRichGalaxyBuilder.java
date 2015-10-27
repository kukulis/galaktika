package lt.gt.galaktika.test;

import java.util.ArrayList;

import org.junit.Test;

import lt.gt.galaktika.data.ILeader;
import lt.gt.galaktika.data.IPlanet;
import lt.gt.galaktika.data.impl.RichGalaxy;

public class TestRichGalaxyBuilder
{
	@Test
	public void testBuildGalaxy ()
	{

		RichGalaxy richGalaxy = RichGalaxy.RichGalaxyBuilder.getInstance().setLeaders(new ArrayList<ILeader>())
				.setPlanets(new ArrayList<IPlanet>()).build();
		RichGalaxy richGalaxy2 = RichGalaxy.RichGalaxyBuilder.getInstance().setLeaders(new ArrayList<ILeader>())
				.setPlanets(new ArrayList<IPlanet>()).build();

		System.out.println("leaders count=" + richGalaxy.getLeaders().size() + "   planets count="
				+ richGalaxy.getPlanets().size());
		
		System.out.println ( "hash codes: "+richGalaxy.hashCode() + "  " + richGalaxy2.hashCode() );
	}

}
