package lt.gt.galaktika.core.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lt.gt.galaktika.core.AggreementsObject;
import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.GalaxyEngine;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.battle.BattleParticipants;
import lt.gt.galaktika.core.planet.Planet;

public class TestFindPlanetBattles
{
	private Galaxy galaxy = new Galaxy(20, 20);

	private GalaxyEngine galaxyEngine = new GalaxyEngine();

	private Planet [] planets = new Planet[] {
			new Planet(10, 10, 100, 1),
			new Planet(11, 12, 100, 1),
			new Planet(1, 12, 100, 1),
			new Planet(11, 1, 100, 1),
			new Planet(5, 15, 100, 1),
			new Planet(10, 19, 100, 1),
			new Planet(14, 1, 100, 1),
			new Planet(3, 5, 100, 1),
			new Planet(2, 12, 100, 1),
	};
	
//	private Nation[] nations= new Nation[] {
	Nation anglai=new Nation(1, "anglai");
	Nation vokieciai=new Nation(2, "vokiečiai");
	Nation prancuzai =new Nation(3, "prancūzai");
	Nation rusai=new Nation(4, "rusai");
//	};
	
	private Fleet[] fleets = new Fleet[] {
		new Fleet(0, "nulinis" ),
		new Fleet(1, "pirmas" ),
		new Fleet(2, "antras" ),
		new Fleet(3, "trečias" ),
		new Fleet(4, "ketvirtas" ),
		new Fleet(5, "penktas" ),
		new Fleet(6, "šeštas" ),
		new Fleet(7, "septintas" ),
		new Fleet(8, "aštuntas" ),
	};
	
	private AggreementsObject ao=new AggreementsObject();
	
	@Before
	public void before() {
		// owners
		fleets[0].setOwner( anglai );
		fleets[1].setOwner( anglai );
		fleets[2].setOwner( vokieciai );
		fleets[3].setOwner( vokieciai );
		fleets[4].setOwner( prancuzai );
		fleets[5].setOwner( prancuzai );
		fleets[6].setOwner( rusai );
		fleets[7].setOwner( rusai );
		fleets[8].setOwner( rusai );
		
		ao.setInWar(anglai, vokieciai);
		ao.setInWar(anglai, prancuzai);
		ao.setInWar(prancuzai, vokieciai);
		ao.setInWar(rusai, vokieciai);
		
		// make locations
		fleets[0].setGalaxyLocation( planets[0]);
		fleets[2].setGalaxyLocation( planets[0]);
		fleets[4].setGalaxyLocation( planets[0]);
		fleets[6].setGalaxyLocation( planets[0]);

		fleets[1].setGalaxyLocation( planets[1]);
		fleets[3].setGalaxyLocation( planets[1]);
		fleets[5].setGalaxyLocation( planets[1]);
		fleets[7].setGalaxyLocation( planets[1]);
		fleets[8].setGalaxyLocation( planets[1]);
	}
	
	@Test
	public void test() {
		System.out.println ( "test called" );
		List<BattleParticipants> bp = galaxyEngine.findPlanetBattles( Arrays.asList( fleets ), ao, galaxy );
		printBattleParticipants(bp);
	}
	
	private void printBattleParticipants ( List<BattleParticipants> battleParticipants )
	{
		battleParticipants.forEach(b -> {
			System.out.println("Battle:" + b.getBattleLocation().getX() + ";" + b.getBattleLocation().getY());
			System.out.print(b.getParticipantA().getName());
			System.out.println();
			System.out.print(b.getParticipantB().getName());
			System.out.println();
		});

	}


}
