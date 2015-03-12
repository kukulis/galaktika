package lt.gt.sgalaktika.construction;

import lt.gt.sgalaktika.Ship;

public class ShipFactory {
	public Ship buildShip ( ShipModel model, Technologies technologies, String serNo ) {
		Ship ship = new Ship ( serNo );
		
		ship.setGuns( model.getGuns());
		ship.setAttack( model.getAttackMass() / model.getGuns());
		ship.setCargo( model.getCargoMass() );
		ship.setEnginePower( model.getEngineMass() * technologies.getEngine());
		
		double massNoDefence = model.getAttackMass() + model.getCargoMass()+model.getEngineMass();
		// TODO
		
		
		return ship;
	}
}
