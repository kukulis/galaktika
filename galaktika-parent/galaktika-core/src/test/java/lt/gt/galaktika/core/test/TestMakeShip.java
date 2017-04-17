package lt.gt.galaktika.core.test;

import org.junit.Test;

import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.engines.PlanetEngine;
import lt.gt.galaktika.core.planet.ShipDesign;

public class TestMakeShip
{
	@Test
	public void testMakeShip () {
		{
			ShipDesign shipDesign = new ShipDesign("pirmasis");
			shipDesign.setAttackMass(1);
			shipDesign.setDefenceMass(1);
			shipDesign.setCargoMass(10);
			shipDesign.setEngineMass(10);
			shipDesign.setGuns(1);
			
			PlanetEngine planetEngine = new PlanetEngine();
			Ship ship = planetEngine.makeShip(shipDesign, new Technologies());
			System.out.println ( "made ship "+ship );
		}
		
		{
			ShipDesign shipDesign = new ShipDesign("antrasis");
			shipDesign.setAttackMass(10);
			shipDesign.setDefenceMass(40);
			shipDesign.setCargoMass(0);
			shipDesign.setEngineMass(0);
			shipDesign.setGuns(1);
			
			PlanetEngine planetEngine = new PlanetEngine();
			Ship ship = planetEngine.makeShip(shipDesign, new Technologies());
			System.out.println ( "made ship "+ship );
		}
	}
	
}
