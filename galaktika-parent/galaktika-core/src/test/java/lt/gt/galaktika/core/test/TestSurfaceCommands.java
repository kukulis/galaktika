package lt.gt.galaktika.core.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.engines.PlanetEngine;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetData;
import lt.gt.galaktika.core.planet.PlanetSurface;
import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.core.planet.SurfaceCommandProduction;
import lt.gt.galaktika.utils.Utils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSurfaceCommands {
	
	@Test
	public void test01MakeShip() {
		PlanetEngine pe = new PlanetEngine();
		ShipDesign sd = new ShipDesign("laivas");
		sd.setAttackMass(10);
		sd.setDefenceMass(10);
		sd.setEngineMass(10);
		sd.setCargoMass(10);
		sd.setGuns(1);
		Assert.assertTrue( pe.validateShipDesign( sd ) );
		
		Ship ship = pe.makeShip( sd, new Technologies());
		Assert.assertEquals(10, ship.getAttack(), Utils.EPSILON);
		Assert.assertTrue( "The actual defence "+ship.getDefence(), ship.getDefence() > 2 && ship.getDefence() < 3 );
		Assert.assertEquals(0.25, ship.getSpeed(), Utils.EPSILON);
		Assert.assertTrue("The actual cargo "+ship.getCargo(), ship.getCargo() > 31 && ship.getCargo() < 32);
	}
	
	@Test
	public void test10ProductionCommand() {
		System.out.println("testProductionCommand called");
		
		PlanetData pd = new PlanetData();
		pd.setPlanet( new Planet() );
		pd.setSurface(new PlanetSurface());
		
		pd.getSurface().setIndustry(100);
		pd.getSurface().setPopulation(100);
		pd.getSurface().setSurfaceCommand( new SurfaceCommandProduction() );
		
		ShipDesign shipDesign = new ShipDesign("laivas");
		shipDesign.setAttackMass(10);
		shipDesign.setGuns(1);
		shipDesign.setDefenceMass(10);
		shipDesign.setEngineMass(10);
		
		((SurfaceCommandProduction)pd.getSurface().getSurfaceCommand()).setShipDesign(shipDesign);
		
		PlanetEngine pe = new PlanetEngine();
		Technologies t = new Technologies();
		pe.executeProduction(pd, t);
		
		// -- assertions
		
		Assert.assertNotNull( pd.getOrbit() );
		Assert.assertNotNull( pd.getOrbit().getFleets() );
		Assert.assertNotEquals( 0, pd.getOrbit().getFleets().size() );
		Fleet f = pd.getOrbit().getFleets().get(0);
		Assert.assertNotEquals( 0, f.getShipGroups().size() ); 
		ShipGroup g = f.getShipGroups().get(0);
		Assert.assertEquals(1, g.getCount() );
		Assert.assertEquals(10, g.getShip().getAttack(), Utils.EPSILON );
	}
	
	// TODO test more ships, test when there are extra capital and so on ..
	
}
