package lt.gt.sgalaktika.construction;

import lt.gt.math.Utils;
import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;

public class ShipFactory {
	public Ship buildShip(ShipBuildSpecification buildSpecification,
			String serNo) {
		Ship ship = new Ship(serNo);

		ShipModel m = buildSpecification.getModel();
		Technologies t = buildSpecification.getTechnologies();

		ship.setGuns(m.getGuns());
		ship.setAttack(m.getAttackMass() / m.getGuns() * t.getAttack());
		ship.setCargo(m.getCargoMass() * t.getCargo() );
		ship.setEnginePower(m.getEngineMass() * t.getEngine());

		double mass = m.getAttackMass() + m.getCargoMass() + m.getEngineMass()
				+ m.getDefenceMass();
		
		double radius = Utils.sqrt3( mass );
		ship.setDeffence( m.getDefenceMass() / radius * t.getDefence() );
		
		ship.setBrutoMass( mass );

		return ship;
	}
	
	public Fleet buildFleet ( FleetBuildSpecification fbS ) {
		Fleet fleet = new Fleet();
		
		return fleet;
	}
}
