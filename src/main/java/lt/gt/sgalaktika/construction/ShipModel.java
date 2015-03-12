package lt.gt.sgalaktika.construction;

public class ShipModel {
	private String name;
// TODO
	
	private double engineMass;
	private double cargoMass;
	private double defenceMass;
	private double attackMass;
	private int guns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getEngineMass() {
		return engineMass;
	}

	public void setEngineMass(double engineMass) {
		this.engineMass = engineMass;
	}

	public double getCargoMass() {
		return cargoMass;
	}

	public void setCargoMass(double cargoMass) {
		this.cargoMass = cargoMass;
	}

	public double getDefenceMass() {
		return defenceMass;
	}

	public void setDefenceMass(double defenceMass) {
		this.defenceMass = defenceMass;
	}

	public double getAttackMass() {
		return attackMass;
	}

	public void setAttackMass(double attackMass) {
		this.attackMass = attackMass;
	}

	public int getGuns() {
		return guns;
	}

	public void setGuns(int guns) {
		this.guns = guns;
	}
	
	
	
	
}
