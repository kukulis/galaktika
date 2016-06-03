package lt.gt.galaktika.test;

import org.junit.Test;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.battle.BattleEngine;
import lt.gt.galaktika.core.battle.BattleReport;
import lt.gt.galaktika.utils.Utils;

// TODO move to core
public class TestBattle
{
	@Test
	public void  testBattle() {
		System.out.println ( "test battle called" );
		
		Fleet fleet1 = new Fleet();
		
//		FullFleet fleet1 = new FullFleet(new Fleet(), null, new ShipContainer() );
		ShipGroup shipGroup1_1 = new ShipGroup(new Ship("1ship1"));
//		ShipGroup shipGroup1_1 = new ShipGroup( new Ship("1ship1"));
		shipGroup1_1.getShip().setAttack(1 );
		shipGroup1_1.getShip().setGuns( 10 );
		shipGroup1_1.getShip().setDefence( 1.5 );
		fleet1.addShipGroup( shipGroup1_1 );
		
		ShipGroup shipGroup1_2 = new ShipGroup( new Ship("1ship2"));
		shipGroup1_2.getShip().setAttack(2);
		shipGroup1_2.getShip().setGuns( 1 );
		shipGroup1_2.getShip().setDefence( 1.5 );
		fleet1.addShipGroup( shipGroup1_2);
		
		
		Fleet fleet2 = new Fleet();
		ShipGroup shipGroup2_1 = new ShipGroup( new Ship("2ship1"));
		shipGroup2_1.getShip().setAttack(1 );
		shipGroup2_1.getShip().setGuns(1);
		shipGroup2_1.getShip().setDefence( 3.5 );
//		shipGroup2_1.setAmount( 0 );
		fleet2.addShipGroup( shipGroup2_1 );
		
		ShipGroup shipGroup2_2 = new ShipGroup( new Ship("2ship2"));
		shipGroup2_2.getShip().setAttack(2);
		shipGroup2_2.getShip().setGuns(1 );
		shipGroup2_2.getShip().setDefence( 3.5 );
		fleet2.addShipGroup( shipGroup2_2);
		
		System.out.println ( "before battle, fleet1 ships count="+ fleet1.calculateShips() + " fleet2 ships count="+ fleet2.calculateShips() );
		
		
		BattleEngine engine = new BattleEngine();
		BattleReport report = engine.doBattle( fleet1, fleet2, 10);
		
		int ships1 = fleet1.calculateShips();
		int ships2 = fleet2.calculateShips();
		
		
//		log.trace ( "ships1="+ships1 );
//		log.trace ( "ships2="+ships2 );
//		log.trace ( "  rounds="+rounds );
		System.out.println ( "rounds="+report.getRounds().size() );
		if ( ships1 == 0 )
			System.out.println ( "fleet2 won" );
		else if ( ships2 == 0 )
			System.out.println ( "fleet1 won" );
		
//		BattleReport report = engine.getBattleReport();
		Utils.printReport ( report );
	}
}