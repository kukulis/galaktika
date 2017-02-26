package lt.gt.galaktika.core.test;

import org.junit.Ignore;
import org.junit.Test;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.battle.BattleReport;
import lt.gt.galaktika.core.engines.BattleEngine;
import lt.gt.galaktika.core.engines.BattleEngine.AttackerAndDefender;
import lt.gt.galaktika.utils.Utils;

public class TestBattle {
	@Test
	@Ignore
	public void testBattle() {
		System.out.println("test battle called");

		Fleet fleet1 = new Fleet();

		// FullFleet fleet1 = new FullFleet(new Fleet(), null, new
		// ShipContainer() );
		ShipGroup shipGroup1_1 = new ShipGroup(new Ship("1ship1"));
		// ShipGroup shipGroup1_1 = new ShipGroup( new Ship("1ship1"));
		shipGroup1_1.getShip().setAttack(1);
		shipGroup1_1.getShip().setGuns(10);
		shipGroup1_1.getShip().setDefence(1.5);
		fleet1.addShipGroup(shipGroup1_1);

		ShipGroup shipGroup1_2 = new ShipGroup(new Ship("1ship2"));
		shipGroup1_2.getShip().setAttack(2);
		shipGroup1_2.getShip().setGuns(1);
		shipGroup1_2.getShip().setDefence(1.5);
		fleet1.addShipGroup(shipGroup1_2);

		Fleet fleet2 = new Fleet();
		ShipGroup shipGroup2_1 = new ShipGroup(new Ship("2ship1"));
		shipGroup2_1.getShip().setAttack(1);
		shipGroup2_1.getShip().setGuns(1);
		shipGroup2_1.getShip().setDefence(3.5);
		// shipGroup2_1.setAmount( 0 );
		fleet2.addShipGroup(shipGroup2_1);

		ShipGroup shipGroup2_2 = new ShipGroup(new Ship("2ship2"));
		shipGroup2_2.getShip().setAttack(2);
		shipGroup2_2.getShip().setGuns(1);
		shipGroup2_2.getShip().setDefence(3.5);
		fleet2.addShipGroup(shipGroup2_2);

		System.out.println("before battle, fleet1 ships count=" + fleet1.calculateShips() + " fleet2 ships count="
				+ fleet2.calculateShips());

		BattleEngine engine = new BattleEngine();
		BattleReport report = engine.doBattle(fleet1, fleet2, 10);

		int ships1 = fleet1.calculateShips();
		int ships2 = fleet2.calculateShips();

		// log.trace ( "ships1="+ships1 );
		// log.trace ( "ships2="+ships2 );
		// log.trace ( " rounds="+rounds );
		System.out.println("rounds=" + report.getRounds().size());
		if (ships1 == 0)
			System.out.println("fleet2 won");
		else if (ships2 == 0)
			System.out.println("fleet1 won");

		// BattleReport report = engine.getBattleReport();
		Utils.printReport(report);
	}

	@Test
//	@Ignore
	public void testBattle2() {
		Fleet fleet1 = new Fleet(1, "Pirmoji flotilė");

		Ship ship1 = new Ship("cibukas", 1, 1, 1, 0.1, 0);
		ship1.setId(10);
		fleet1.addShipGroup(new ShipGroup(ship1, 2));
		
//		Ship ship3 = new Ship("zaizalas", 1, 2, 3, 4, 6);
//		ship3.setId(3);
//		ship3.setTotalMass( 7 );
//		fleet1.addShipGroup(new ShipGroup(ship3, 10));
		Ship ship4 = new Ship("zaizalas", 1, 2, 3, 5, 6);
		ship4.setId(4);
		ship4.setTotalMass(7);
		fleet1.addShipGroup(new ShipGroup(ship4, 7));


		Fleet fleet2 = new Fleet(2, "Antroji flotilė");
		Ship ship2 = new Ship("dantukas", 1, 1, 1, 0.1, 0);
		ship2.setId(20);
		fleet2.addShipGroup(new ShipGroup(ship2, 2));
		
		Ship ship5= new Ship("meška", 1, 3, 3, 0.1, 0);
		ship5.setId(21);
		fleet2.addShipGroup(new ShipGroup(ship5, 7));

		BattleEngine engine = new BattleEngine();
		BattleReport report = engine.doBattle(fleet1, fleet2, 50);

		Utils.printReport(report);
	}

//	@Test
	public void testSelectAttackerAndDefender() {
		Fleet fleet1 = new Fleet(1, "Pirmoji flotilė");

		Ship ship1 = new Ship("cibukas", 1, 1, 1, 0.1, 0);
		ship1.setId(10);
		fleet1.addShipGroup(new ShipGroup(ship1, 2));

		Ship ship2 = new Ship("dantukas", 1, 1, 1, 0.1, 0);
		ship2.setId(20);
		Fleet fleet2 = new Fleet(2, "Antroji flotilė");
		fleet2.addShipGroup(new ShipGroup(ship2, 2));

		BattleEngine engine = new BattleEngine();

		for (int shipNumber = 0; shipNumber < 10; shipNumber++) {
			AttackerAndDefender attackerAndDefender = engine.selectAttackerAndDefender(fleet1, fleet2, shipNumber);
		}
	}
	
	// TODO heavily test Fleet ships groups selection
}
