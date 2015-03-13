package lt.gt.sgalaktika.integrationtest;

import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.persistence.Storage;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestLoadAndStore {
	Logger log = Logger.getLogger( TestLoadAndStore.class );
	
	@Test
	public void storeTest  () {
		log.trace ( "started" );
		
		Ship ship = new Ship("tarakonas2");
//		ship.setId(101);
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
}
