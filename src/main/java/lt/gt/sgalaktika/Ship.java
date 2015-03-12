package lt.gt.sgalaktika;

public class Ship {
	
	/**
	 * Race+Model+Date.
	 */
	private String shipSerie;
	
	private int attack;
	private int guns;
	private double deffence;
	private int cargo;
	private int speed;
	
	public Ship ( String serie ) {
		this.shipSerie = serie;
	}
	
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
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
	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getShipSerie() {
		return shipSerie;
	}
	public void setShipSerie(String shipSerie) {
		this.shipSerie = shipSerie;
	}

}
