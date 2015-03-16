package lt.gt.sgalaktika.integrationtest;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.battle.BattleReportRound;
import lt.gt.sgalaktika.battle.BattleReportShot;
import lt.gt.sgalaktika.persistence.Storage;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestLoadAndStore {
	Logger log = Logger.getLogger( TestLoadAndStore.class );
	
//	@Test
	public void storeTest  () {
		log.trace ( "started" );
		
		Ship ship = new Ship("tarakonas2");
		ship.setId(1);
		ship.setAttack(1);
		ship.setGuns(2);
		ship.setDeffence(3);
		ship.setCargo(4);
		ship.setBrutoMass(15);
		ship.setEnginePower( 15 );
		ship.setLoadAmount(1);
		
		Storage storage = new Storage();
		storage.storeShip( ship );
	}
	
//	@Test
	public void storeFleet () {
		log.trace ( "started" );
		Fleet fleet=new Fleet();
		
		Storage storage = new Storage ();
		storage.storeFleet (fleet);
		log.trace ( "finished" );
	}
	
//	@Test
	public void storeShot () {
		log.trace ( "------- started ----------" );
		Storage storage = new Storage ();
		
		BattleReportShot shot = new BattleReportShot();
		shot.setAttackerShip( "ship1" );
		shot.setDefenderShip( "ship2" );
		shot.setDestroyed( false );
		
		storage.storeBattleReportShot( shot );
		log.trace ( "------- finished ----------" );
	}

	@Test
	public void storeRound () {
		log.trace ( "------- started ----------" );
		BattleReportShot shot = new BattleReportShot();
		shot.setAttackerShip( "ship1" );
		shot.setDefenderShip( "ship2" );
		shot.setDestroyed( false );

		BattleReportShot shot2 = new BattleReportShot();
		shot2.setAttackerShip( "ship2" );
		shot2.setDefenderShip( "ship1" );
		shot2.setDestroyed( true );

		BattleReportRound round = new BattleReportRound() ;
		round.setRoundNumber( 10 );
		round.getShots().add ( shot );
		round.getShots().add ( shot2 );
		
		Storage storage = new Storage ();
		storage.storeBattleReportRound( round );
		
		log.trace ( "------- finished ----------" );
	}
}
