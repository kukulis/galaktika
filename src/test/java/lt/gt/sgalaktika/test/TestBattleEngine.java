package lt.gt.sgalaktika.test;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.ShipGroup;
import lt.gt.sgalaktika.battle.BattleEngine;
import lt.gt.sgalaktika.battle.Flipper;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class TestBattleEngine {
	private static Logger log = Logger.getLogger( TestBattleEngine.class );
	
	@Test
	public void testShoots () {
		BattleEngine battleEngine = new BattleEngine ();
		
		Assert.assertEquals( 0, battleEngine.shootProbability( 0.1 ), 0.00001 );
		Assert.assertEquals( 0, battleEngine.shootProbability( 0.25 ), 0.00001 );
		Assert.assertEquals( 0.25, battleEngine.shootProbability( 0.625 ), 0.00001 );
		Assert.assertEquals( 0.5, battleEngine.shootProbability( 1 ), 0.00001 );
		Assert.assertEquals( 0.75, battleEngine.shootProbability( 2.5 ), 0.00001 );
		Assert.assertEquals( 1, battleEngine.shootProbability( 4 ), 0.00001 );
		Assert.assertEquals( 1, battleEngine.shootProbability( 5 ), 0.00001 );
	}
	
	@Test
	public void testShoot () {
		BattleEngine battleEngine = new BattleEngine ();
		
		Ship attacker = new Ship("aaa");
		Ship defender = new Ship("bbb");
		int yesCount = 0;
		int noCount = 0;
		attacker.setAttack( 4 );
		defender.setDeffence( 15 );
		for ( int i = 0; i < 100; i ++ ) {
			if ( battleEngine.shoot(attacker, defender) ) {
				yesCount ++;
			}
			else {
				noCount ++;
			}
		}
		
		System.out.println ( "yes="+yesCount+"   no="+noCount );
	}
	
//	@Test
	public void testBattle0 () {
		Fleet fleet1 = new Fleet();
		Fleet fleet2 = new Fleet();
		
		BattleEngine engine = new BattleEngine();
		int rounds = engine.doBattle( fleet1, fleet2, 10);
		
		int ships1 = fleet1.calculateShips();
		int ships2 = fleet2.calculateShips();
		
		
		log.trace ( "ships1="+ships1 );
		log.trace ( "ships2="+ships2 );
		log.trace ( "rounds="+rounds );
	}
	
	
	
//	@Test
	public void testSelectGroup0 () {
		Fleet fleet1 = new Fleet();
		Fleet fleet2 = new Fleet();
		
		BattleEngine engine = new BattleEngine();
		
		BattleEngine.AttackerAndDefender aad = engine.selectAttackerAndDefender(fleet1, fleet2, 0 );
		System.out.println ( "attacker ship group="+aad.attackerShipGroup );
//		engine.randomSelectAttackerAndDefender(fleet1, fleet2);
	}
	
	@Test
	public void testSelectGroup1 () {
		Fleet fleet1 = new Fleet();
		Fleet fleet2 = new Fleet();
		Ship ship1 = new Ship ("ship1");
		ship1.setGuns( 3 );
		ShipGroup group1 = new ShipGroup (ship1);
		group1.setAmount(1);
		group1.setName( "group1" );
		fleet1.addShipGroup( group1 );
		
		BattleEngine engine = new BattleEngine();
		
		BattleEngine.AttackerAndDefender aad = engine.selectAttackerAndDefender(fleet1, fleet2, 0 );
		System.out.println ( "attacker ship group="+aad.attackerShipGroup.getName() );
		Assert.assertNotNull( aad.attackerShipGroup );
		Assert.assertEquals("group1", aad.attackerShipGroup.getName() );

		aad = engine.selectAttackerAndDefender(fleet1, fleet2, 1 );
		Assert.assertNull( aad.attackerShipGroup );

		// more groups and findings
		
		Ship ship2 = new Ship("ship2");
		ship2.setGuns( 2 );
		ShipGroup group2 = new ShipGroup (ship2);
		group2.setAmount(1);
		group2.setName("group2");
		fleet1.addShipGroup( group2 );
		
		// BattleEngine.AttackerAndDefender 
		aad = engine.selectAttackerAndDefender(fleet1, fleet2, 1 );
		Assert.assertNotNull( aad.attackerShipGroup );
		Assert.assertEquals( "group2", aad.attackerShipGroup.getName() );
		System.out.println ( "attacker group="+aad.attackerShipGroup.getName() );
	}
	
	@Test
	public void testSelectGroup2 () {
		Fleet fleet1 = new Fleet();
		Fleet fleet2 = new Fleet();
		Ship ship1 = new Ship("ship1");
		ship1.setGuns(1);
		ShipGroup group1 = new ShipGroup (ship1);
		group1.setAmount(1);
		group1.setName( "group1" );
		fleet2.addShipGroup( group1 );
		
		BattleEngine engine = new BattleEngine();
		BattleEngine.AttackerAndDefender aad = engine.selectAttackerAndDefender(fleet1, fleet2, 0 );
		
		Assert.assertNotNull( aad.attackerShipGroup );
		Assert.assertEquals("group1", aad.attackerShipGroup.getName() );
	}
	
	@Test
	public void testSelectGroupFromFleet () {
		Fleet fleet1 = new Fleet();
		Ship ship1 = new Ship ( "ship1");
		ship1.setGuns( 1 );
		ShipGroup group1 = new ShipGroup (ship1);
		group1.setAmount(1);
		group1.setName( "group1" );
		fleet1.addShipGroup( group1 );
		Ship ship2 = new Ship ( "ship2" );
		ship2.setGuns(1 );
		ShipGroup group2 = new ShipGroup ( ship2 );
		group2.setAmount(1);
		group2.setName("group2");
		fleet1.addShipGroup( group2 );
		
		ShipGroup group = fleet1.selectAttackerGroup(1 );
		Assert.assertNotNull( group );
		System.out.println ( "group from fleet="+group.getName() );
		Assert.assertEquals( "group2", group.getName() );
	}
	
	@Test
	public void testSelectGroupFromFleet2 () {
		Fleet fleet1 = new Fleet();
		ShipGroup shipGroup1_1 = new ShipGroup( new Ship("1ship1"));
		shipGroup1_1.getShip().setAttack( 1 );
		shipGroup1_1.getShip().setGuns( 1 );
		shipGroup1_1.getShip().setDeffence( 1.5 );
		fleet1.addShipGroup( shipGroup1_1 );
		
		ShipGroup shipGroup1_2 = new ShipGroup( new Ship("1ship2"));
		shipGroup1_2.getShip().setAttack(2);
		shipGroup1_2.getShip().setGuns( 1 );
		shipGroup1_2.getShip().setDeffence( 1.5 );
		fleet1.addShipGroup( shipGroup1_2);
		
		ShipGroup group = fleet1.selectAttackerGroup(0);
		Assert.assertNotNull( group );
		System.out.println ( "group from fleet="+group.getName() );
		Assert.assertEquals( "G1ship1", group.getName() );
		group.increaseShotedShipsAmount();
		group = fleet1.selectAttackerGroup(0);
		Assert.assertNotNull( group );
		System.out.println ( "group from fleet="+group.getName() );
		Assert.assertEquals( "G1ship2", group.getName() );
		group.increaseShotedShipsAmount();
		group = fleet1.selectAttackerGroup(0);
		Assert.assertNull( group );
	}
	
//	@Test
	public void testSelectGroupFromFleet3 () {
		Fleet fleet1 = new Fleet();
		ShipGroup shipGroup1_1 = new ShipGroup( new Ship("1ship1"));
		shipGroup1_1.getShip().setAttack(1 );
		shipGroup1_1.getShip().setGuns( 1 );
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
		
		shipGroup1_1.setShotedShips( 1 );
		shipGroup2_2.setShotedShips( 1 );

		BattleEngine engine = new BattleEngine();
		
		BattleEngine.AttackerAndDefender aad = engine.randomSelectAttackerAndDefender(fleet1, fleet2);
		Assert.assertNotNull( aad );
		Assert.assertNotNull(aad.attackerShipGroup ); 
//		System.out.println ( "aad="+aad );
//		System.out.println ( "aad.attackerShipGroup="+aad.attackerShipGroup.getName() );
	}
	
//	@Test
	public void testRandom () {
		int max = 2;
		for ( int i=0; i < 100; i++ ) {
			int val = Flipper.randomInt( max );
			if ( val == max )
				System.out.print( "-->");
			System.out.println ( "val="+val );
			
		}
	}
	
//	@Test
	public void testLog () {
		log.trace( "vienas");
		log.info( "du");
	}
}
