package lt.gt.sgalaktika.construction;

import lt.gt.math.Utils;
import lt.gt.sgalaktika.Fleet;
import lt.gt.sgalaktika.Ship;
import lt.gt.sgalaktika.ShipGroup;

public class ShipFactory {
	public Ship buildShip(ShipBuildSpecification buildSpecification,
			String serNo) {
		Ship ship = new Ship(serNo);

		ShipModel m = buildSpecification.getModel();
		Technologies t = buildSpecification.getTechnologies();

		ship.setGuns(m.getGuns());
		ship.setAttack(m.getAttackMass() / m.getGuns() * t.getAttackTech());
		ship.setCargo(m.getCargoMass() * t.getCargoTech() );
		ship.setEnginePower(m.getEngineMass() * t.getEngineTech());

		double mass = m.getAttackMass() + m.getCargoMass() + m.getEngineMass()
				+ m.getDefenceMass();
		
		double radius = Utils.sqrt3( mass );
		ship.setDeffence( m.getDefenceMass() / radius * t.getDefenceTech() );
		
		ship.setBrutoMass( mass );

		return ship;
	}
	
	public Fleet buildFleet ( FleetBuildSpecification fbS ) {
		Fleet fleet = new Fleet();
		
		int nr = 0;
		for (  ShipGroupBuildSpecification gspec : fbS.getShipGroupBSpecifications() ) {
			nr ++;
			Ship ship = buildShip(gspec.getShipBSpecification(), ""+nr );
			ShipGroup group = new ShipGroup( ship );
			group.setAmount( gspec.getShipAmount());
			
			fleet.addShipGroup( group );
		}
		
		return fleet;
	}
}
