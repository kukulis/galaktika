package lt.gt.sgalaktika.construction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table ( name="shipmodel"
// ,uniqueConstraints=@UniqueConstraint ( name="uniqueModelName", columnNames={"name" } ) 
)
public class ShipModel {

	@Id @GeneratedValue
	private long model_id;
	
	@Column( length = 32)
	private String name;
	private double engineMass;
	private double cargoMass;
	private double defenceMass;
	private double attackMass;
	private int guns;

	
	public long getModel_id() {
		return model_id;
	}

	public void setModel_id(long model_id) {
		this.model_id = model_id;
	}

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
