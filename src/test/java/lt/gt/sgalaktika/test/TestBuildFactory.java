package lt.gt.sgalaktika.test;

import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.ShipGroup;
import lt.gt.sgalaktika.construction.FleetBuildSpecification;
import lt.gt.sgalaktika.construction.ShipBuildSpecification;
import lt.gt.sgalaktika.construction.ShipFactory;
import lt.gt.sgalaktika.construction.ShipGroupBuildSpecification;
import lt.gt.sgalaktika.construction.ShipModel;
import lt.gt.sgalaktika.construction.Technologies;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class TestBuildFactory {
	private static Logger log = Logger.getLogger( TestBuildFactory.class );
	
	@Test
	public void buildFleet () {
		log.trace( "begin");
		
		FleetBuildSpecification fspec = new FleetBuildSpecification();
		ShipModel model_ = new ShipModel();
		ShipBuildSpecification spec_ = new ShipBuildSpecification(model_);
		spec_.setTechnologies( new Technologies() );
		ShipGroupBuildSpecification gspec_ = new ShipGroupBuildSpecification(spec_);
		
		model_.setGuns( 1 );
		model_.setAttackMass(2);
		model_.setDefenceMass(3);
		model_.setCargoMass(2);
		model_.setEngineMass(2);
		
		gspec_.setShipAmount( 3 );
		
		fspec.addShipGroupBS( gspec_ );
		
		ShipFactory factory = new ShipFactory();
		Fleet fleet = factory.buildFleet( fspec );
		
		Assert.assertEquals( 1, fleet.getShipGroups().size());
		
		for (  ShipGroup group : fleet.getShipGroups() ) {
			System.out.println ( "amount:"+ group.getAmount()+" ship:"+group.getShip() );
//			group.get
		}
	}
	
	@Test
	public void checkResources () {
		log.trace( "begin");
		
		ShipModel model = new ShipModel();
		ShipBuildSpecification spec = new ShipBuildSpecification(model);
		ShipGroupBuildSpecification gspec = new ShipGroupBuildSpecification(spec);
		
		
		
		model.setAttackMass(1);
		model.setDefenceMass( 1 );
		model.setCargoMass(1);
		model.setEngineMass( 1 );
		
//		spec.setModel( model );
//		gspec.setShipBSpecification( spec );
		gspec.setShipAmount( 1 );
		
		
		gspec.calculateResourcesNeeded();
		Assert.assertEquals(4, gspec.get_resourcesNeeded(), 0.0001 );
		
		
		ShipModel model2 = new ShipModel();
		ShipBuildSpecification spec2 = new ShipBuildSpecification(model2);
		ShipGroupBuildSpecification gspec2 = new ShipGroupBuildSpecification(spec2);
		
		
		
		model2.setAttackMass(1);
		model2.setDefenceMass( 1 );
		model2.setCargoMass(1);
		model2.setEngineMass( 4 );
		
//		spec2.setModel( model2 );
//		gspec2.setShipBSpecification( spec2 );
		gspec2.setShipAmount( 3 );
		
		
		gspec2.calculateResourcesNeeded();
		
		Assert.assertEquals(21, gspec2.get_resourcesNeeded(), 0.0001 );

		log.trace ( "finished" );
		
		// check fleet builder spec resources
		
		FleetBuildSpecification fspec = new FleetBuildSpecification();
		ShipModel model_ = new ShipModel();
		ShipBuildSpecification spec_ = new ShipBuildSpecification(model_);
		ShipGroupBuildSpecification gspec_ = new ShipGroupBuildSpecification(spec_);
		
		model_.setAttackMass(2);
		model_.setDefenceMass(2);
		model_.setCargoMass(2);
		model_.setEngineMass(2);
		
		gspec_.setShipAmount( 3 );
		
		fspec.addShipGroupBS( gspec_ );
		
		fspec.calculateResources();
		
		Assert.assertEquals( 24, fspec.get_resourcesNeeded(), 0.0001 );
		Assert.assertEquals( 0, fspec.get_resourcesUsed(), 0.0001 );
		
	}

}
