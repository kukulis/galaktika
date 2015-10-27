package lt.gt.galaktika.test;

import org.junit.Test;

import lt.gt.galaktika.battle.BattleEngine;
import lt.gt.galaktika.battle.BattleReport;

//import com.sun.istack.internal.logging.Logger;
//import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import lt.gt.galaktika.data.impl.Fleet;
import lt.gt.galaktika.data.impl.FullFleet;
import lt.gt.galaktika.data.impl.Ship;
import lt.gt.galaktika.data.impl.ShipContainer;
import lt.gt.galaktika.data.impl.ShipGroup;
import lt.gt.galaktika.utils.Utils;

public class ImitateBattle {
//	private static Logger log = Logger.getLogger( ImitateBattle.class );

	
	@Test
	public void testBattle1 () {
		
		// TODO išdebuginti kad gerai veiktų
		FullFleet fleet1 = new FullFleet(new Fleet(), null, new ShipContainer() );
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
		
		
		FullFleet fleet2 = new FullFleet(new Fleet(), null, new ShipContainer() );
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
		
		System.out.println ( "before battle, fleet1 ships count="+ fleet1.calculateShips() + " fleet2 ships count="+ fleet2.calculateShips() );
		
		
		BattleEngine engine = new BattleEngine();
		int rounds = engine.doBattle( fleet1, fleet2, 10);
		
		int ships1 = fleet1.calculateShips();
		int ships2 = fleet2.calculateShips();
		
		
//		log.trace ( "ships1="+ships1 );
//		log.trace ( "ships2="+ships2 );
//		log.trace ( "  rounds="+rounds );
		System.out.println ( "rounds="+rounds );
		if ( ships1 == 0 )
			System.out.println ( "fleet2 won" );
		else if ( ships2 == 0 )
			System.out.println ( "fleet1 won" );
		
		BattleReport report = engine.getBattleReport();
		Utils.printReport ( report );
		
	}
}
