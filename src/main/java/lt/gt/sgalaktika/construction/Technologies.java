package lt.gt.sgalaktika.construction;

import javax.persistence.Embeddable;

@Embeddable
public class Technologies {
	private double attackTech=1;
	private double cargoTech=1;
	private double defenceTech=1;
	private double engineTech=1;
	
	public double getAttackTech() {
		return attackTech;
	}
	public void setAttackTech(double attack) {
		this.attackTech = attack;
	}
	public double getCargoTech() {
		return cargoTech;
	}
	public void setCargoTech(double cargo) {
		this.cargoTech = cargo;
	}
	public double getDefenceTech() {
		return defenceTech;
	}
	public void setDefenceTech(double defence) {
		this.defenceTech = defence;
	}
	public double getEngineTech() {
		return engineTech;
	}
	public void setEngineTech(double engine) {
		this.engineTech = engine;
	}
}
