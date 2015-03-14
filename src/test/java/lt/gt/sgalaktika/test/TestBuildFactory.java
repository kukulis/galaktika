package lt.gt.sgalaktika.test;

import lt.gt.sgalaktika.construction.ShipBuildSpecification;
import lt.gt.sgalaktika.construction.ShipFactory;
import lt.gt.sgalaktika.construction.ShipGroupBuildSpecification;
import lt.gt.sgalaktika.construction.ShipModel;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class TestBuildFactory {
	private static Logger log = Logger.getLogger( TestBuildFactory.class );
	
	@Test
	public void buildFleet () {
		log.trace( "begin");
		
		ShipFactory factory = new ShipFactory();
	}
	
	@Test
	public void checkResources () {
		log.trace( "begin");
		
		ShipGroupBuildSpecification gspec = new ShipGroupBuildSpecification();
		
		ShipBuildSpecification spec = new ShipBuildSpecification();
		
		ShipModel model = new ShipModel();
		
		model.setAttackMass(1);
		model.setDefenceMass( 1 );
		model.setCargoMass(1);
		model.setEngineMass( 1 );
		
		spec.setModel( model );
		gspec.setShipBSpecification( spec );
		gspec.setShipAmount( 1 );
		
		
		gspec.calculateResourcesNeeded();
		Assert.assertEquals(4, gspec.get_resourcesNeeded(), 0.0001 );
		
		
		ShipGroupBuildSpecification gspec2 = new ShipGroupBuildSpecification();
		
		ShipBuildSpecification spec2 = new ShipBuildSpecification();
		
		ShipModel model2 = new ShipModel();
		
		model2.setAttackMass(1);
		model2.setDefenceMass( 1 );
		model2.setCargoMass(1);
		model2.setEngineMass( 4 );
		
		spec2.setModel( model2 );
		gspec2.setShipBSpecification( spec2 );
		gspec2.setShipAmount( 3 );
		
		
		gspec2.calculateResourcesNeeded();
		
		Assert.assertEquals(21, gspec2.get_resourcesNeeded(), 0.0001 );

		log.trace ( "finished" );
		
		// TODO check fleet builder spec resources
	}

}
