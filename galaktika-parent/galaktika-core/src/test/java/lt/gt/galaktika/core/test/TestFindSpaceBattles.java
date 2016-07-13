package lt.gt.galaktika.core.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import lt.gt.galaktika.core.AggreementsObject;
import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.FlightCommand;
import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.core.GalaxyEngine;
import lt.gt.galaktika.core.GalaxyLocation;
import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.battle.BattleParticipants;
import lt.gt.galaktika.core.planet.Planet;

public class TestFindSpaceBattles
{
	private ShipGroup[] groups = new ShipGroup[] { new ShipGroup(new Ship("grybas", 1, 1, 1, 1, 1)),
			new ShipGroup(new Ship("gaidys", 1, 1, 1, 2, 1)), new ShipGroup(new Ship("tarakonas", 1, 1, 1, 5, 0)),
			new ShipGroup(new Ship("voras", 1, 1, 1, 3, 0)), };

	private Fleet[] fleets = new Fleet[] { new Fleet(1, "pirmasis"), new Fleet(2, "antrasis"),
			new Fleet(3, "trečiasias"), new Fleet(4, "ketvirtasis") };

	private GalaxyLocation[] locations = new GalaxyLocation[] { new Planet(2, 2, 100, 1), new Planet(5, 4, 100, 1),
			new Planet(1, 3, 100, 1), new Planet(19, 4, 100, 1) };

	private Galaxy galaxy = new Galaxy(20, 20);

	private GalaxyEngine galaxyEngine = new GalaxyEngine();

	private Nation[] nations = new Nation[] { new Nation(1, "anglai"), new Nation(2, "prancūzai") };

	private AggreementsObject ao;

	@Before
	public void before ()
	{
		fleets[0].setOwner(nations[0]);
		fleets[1].setOwner(nations[1]);
		fleets[2].setOwner(nations[0]);
		fleets[3].setOwner(nations[1]);

		fleets[0].addShipGroup(groups[0]);
		fleets[1].addShipGroup(groups[1]);
		fleets[2].addShipGroup(groups[2]);
		fleets[3].addShipGroup(groups[3]);

		// fleet locations and flight commands
		fleets[0].setGalaxyLocation(locations[0]);
		fleets[1].setGalaxyLocation(locations[1]);
		fleets[2].setGalaxyLocation(locations[2]);
		fleets[3].setGalaxyLocation(locations[3]);

		fleets[0].setFlightCommand(new FlightCommand(locations[0], locations[1]));
		fleets[1].setFlightCommand(new FlightCommand(locations[1], locations[0]));
		fleets[2].setFlightCommand(new FlightCommand(locations[2], locations[3]));
		fleets[3].setFlightCommand(new FlightCommand(locations[3], locations[2]));

		ao = new AggreementsObject();
		ao.setInWar(nations[0], nations[1]);
	}

	@Test
	@Ignore
	public void test ()
	{
		System.out.println("test called");
		System.out.println("groups in  " + fleets[0].getName() + " " + fleets[0].getShipGroups().size());
		System.out.println("groups in  " + fleets[1].getName() + " " + fleets[1].getShipGroups().size());

		test12Fleets();

	}

	@Test
	@Ignore
	public void test2 ()
	{
		System.out.println("test 2 called");
		System.out.println("groups in  " + fleets[0].getName() + " " + fleets[0].getShipGroups().size());
		System.out.println("groups in  " + fleets[1].getName() + " " + fleets[1].getShipGroups().size());
		if (galaxyEngine.willMeet(fleets[0], fleets[1], galaxy))
			System.out.println("fleets will meet");
		else
			System.out.println("fleets will not meet");
		test12Fleets();

		// move step with each fleet
		moveOneTurn(fleets[0]);
		moveOneTurn(fleets[1]);

		if (galaxyEngine.willMeet(fleets[0], fleets[1], galaxy))
			System.out.println("fleets will meet");
		else
			System.out.println("fleets will not meet");
		test12Fleets();

	}

	private void test12Fleets ()
	{

		List<BattleParticipants> battleParticipants = galaxyEngine.findSpaceBattles(Arrays.asList(fleets[0], fleets[1]),
				ao, galaxy);

		System.out.println("received " + battleParticipants.size() + " battles");
		printBattleParticipants(battleParticipants);
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

	private void moveOneTurn ( Fleet fleet )
	{
		GalaxyLocation newLocation = galaxyEngine.calculateMovement(fleet, galaxy);
		System.out.println(
				"moveOneTurn called, oldLocation=" + fleet.getGalaxyLocation() + ", newLocation=" + newLocation);
		fleet.setGalaxyLocation(newLocation);
	}

	@Test
	public void testEdge1 ()
	{
		// test when path cuts edge
		System.out.println("testEdge1 called");
		List<BattleParticipants> battleParticipants = galaxyEngine.findSpaceBattles(Arrays.asList(fleets[2], fleets[3]),
				ao, galaxy);

		printBattleParticipants(battleParticipants);
	}
}
