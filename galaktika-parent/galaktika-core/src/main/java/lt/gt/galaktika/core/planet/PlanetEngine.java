package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.Technologies;

public class PlanetEngine
{
	public PlanetData excecute ( PlanetData planetData ) {
		// TODO big method
		return planetData;
	}
	
	public Ship makeShip (ShipDesign design, Technologies t) {
		double totalMass = design.getAttackMass() * design.getGuns()
				+ design.getDefenceMass() + design.getCargoMass() + design.getEngineMass();
		double radius = lt.gt.math.Utils.sqrt3( totalMass );
		
		Ship ship = new Ship (design.getDesignName());
		ship.setAttack( design.getAttackMass() * t.getAttack() );
		ship.setGuns( design.getGuns() );
		ship.setDefence( design.getDefenceMass() * t.getDefence() / radius );
		ship.setCargo( lt.gt.math.Utils.pow( design.getCargoMass(), 1.5 ) * t.getCargo() );
		ship.setTotalMass( totalMass );
		ship.setSpeed( design.getEngineMass() * t.getEngines() / totalMass );
		return ship;
	}
	
	public PlanetSurface executeIndustry (PlanetSurface ps ) {
		PlanetSurface resultPs = new PlanetSurface();
		// TODO
		return resultPs;
	}
	
	public PlanetData executeProduction ( PlanetData pd ) {
		PlanetData resultPd=new PlanetData();
		// TODO
		return resultPd;
	}
	
	public Technologies executeTechnologies( PlanetSurface ps, Technologies t ) {
		Technologies resultT = new Technologies();
		// TODO
		return resultT;
	}
}
