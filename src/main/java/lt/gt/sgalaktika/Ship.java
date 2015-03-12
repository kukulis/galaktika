package lt.gt.sgalaktika;

public class Ship {
	
	/**
	 * Race+Model+Date.
	 */
	private String shipSerie;
	
	private double attack;
	private int guns;
	private double deffence;
	private double cargo;
	private double enginePower;
	private double brutoMass;
	private double load;
	
	public Ship ( String serie ) {
		this.shipSerie = serie;
	}
	
	public int getGuns() {
		return guns;
	}
	public void setGuns(int guns) {
		this.guns = guns;
	}
	public double getDeffence() {
		return deffence;
	}
	public void setDeffence(double deffence) {
		this.deffence = deffence;
	}
	public String getShipSerie() {
		return shipSerie;
	}
	public void setShipSerie(String shipSerie) {
		this.shipSerie = shipSerie;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public double getCargo() {
		return cargo;
	}

	public void setCargo(double cargo) {
		this.cargo = cargo;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}

	public double getBrutoMass() {
		return brutoMass;
	}

	public void setBrutoMass(double brutoMass) {
		this.brutoMass = brutoMass;
	}

	public double getLoad() {
		return load;
	}

	public void setLoad(double load) {
		this.load = load;
	}
}
