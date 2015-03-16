package lt.gt.sgalaktika.test;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.ShipGroup;
import lt.gt.sgalaktika.Utils;
import lt.gt.sgalaktika.battle.BattleEngine;
import lt.gt.sgalaktika.battle.BattleReport;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ImitateBattle {
	private static Logger log = Logger.getLogger( ImitateBattle.class );

	
	@Test
	public void testBattle1 () {
		Fleet fleet1 = new Fleet();
		ShipGroup shipGroup1_1 = new ShipGroup( new Ship("1ship1"));
		shipGroup1_1.getShip().setAttack(1 );
		shipGroup1_1.getShip().setGuns( 10 );
		shipGroup1_1.getShip().setDeffence( 1.5 );
		fleet1.addShipGroup( shipGroup1_1 );
		
		ShipGroup shipGroup1_2 = new ShipGroup( new Ship("1ship2"));
		shipGroup1_2.getShip().setAttack(2);
		shipGroup1_2.getShip().setGuns( 1 );
		shipGroup1_2.getShip().setDeffence( 1.5 );
		fleet1.addShipGroup( shipGroup1_2);
		
		
		Fleet fleet2 = new Fleet();
		ShipGroup shipGroup2_1 = new ShipGroup( new Ship("2ship1"));
		shipGroup2_1.getShip().setAttack(1 );
		shipGroup2_1.getShip().setGuns(1);
		shipGroup2_1.getShip().setDeffence( 3.5 );
//		shipGroup2_1.setAmount( 0 );
		fleet2.addShipGroup( shipGroup2_1 );
		
		ShipGroup shipGroup2_2 = new ShipGroup( new Ship("2ship2"));
		shipGroup2_2.getShip().setAttack(2);
		shipGroup2_2.getShip().setGuns(1 );
		shipGroup2_2.getShip().setDeffence( 3.5 );
		fleet2.addShipGroup( shipGroup2_2);
		
		BattleEngine engine = new BattleEngine();
		int rounds = engine.doBattle( fleet1, fleet2, 10);
		
		int ships1 = fleet1.calculateShips();
		int ships2 = fleet2.calculateShips();
		
		
		log.trace ( "ships1="+ships1 );
		log.trace ( "ships2="+ships2 );
		log.trace ( "  rounds="+rounds );
		
		if ( ships1 == 0 )
			System.out.println ( "fleet2 won" );
		else if ( ships2 == 0 )
			System.out.println ( "fleet1 won" );
		
		BattleReport report = engine.getBattleReport();
		Utils.printReport ( report );
		
	}
	
	
	
	
//	@Test
	public void testBattleComplex () {
		log.trace("begin");
		
	}
}
