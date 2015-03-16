package lt.gt.sgalaktika.integrationtest;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.Utils;
import lt.gt.sgalaktika.battle.BattleReport;
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

//	@Test
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
		round.addShot ( shot );
		round.addShot ( shot2 );
		
		Storage storage = new Storage ();
		storage.storeBattleReportRound( round );
		
//		storage.storeBattleReportShot( shot );
		
		log.trace ( "------- finished ----------" );
	}
	
	
//	@Test
	public void storeBattleReport (  ) {
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
		round.setRoundNumber( 0 );
		round.addShot ( shot );
		round.addShot ( shot2 );
		
		BattleReportRound round2 = new BattleReportRound ();
		round2.setRoundNumber( 1 );
		BattleReportShot shot3 = new BattleReportShot ();
		shot3.setAttackerShip( "aaa" );
		shot3.setDefenderShip( "bbb" );
		shot3.setDestroyed( true );
		
		round2.addShot( shot3 );
		
		BattleReport report = new BattleReport();
		report.addRound ( round );
		report.addRound ( round2 );
		
		Storage storage = new Storage ();
		storage.storeBattleReport( report );
		
		log.trace( "report id=" + report.getId() );
		
		
		log.trace ( "------- finished ----------" );
	}
	
	@Test
	public void loadBattleReport () {
		Storage storage = new Storage ();
		
		BattleReport report = storage.loadBattleReport ( 1 );
		
		Utils.printReport( report );
	}
}
