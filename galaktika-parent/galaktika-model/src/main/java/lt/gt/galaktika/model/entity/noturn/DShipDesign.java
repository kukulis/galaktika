package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DShipDesign {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long designId;

	private String designName;
	private double attackMass;
	private int guns;
	private double defenceMass;
	private double cargoMass;
	private double engineMass;
	
	// TODO add owner to ship design

	public DShipDesign() {
	}

	public DShipDesign(String designName) {
		this.designName = designName;
	}

	public DShipDesign(String designName, double attackMass, int guns, double defenceMass, double cargoMass,
			double engineMass) {
		this.designName = designName;
		this.attackMass = attackMass;
		this.guns = guns;
		this.defenceMass = defenceMass;
		this.cargoMass = cargoMass;
		this.engineMass = engineMass;
	}
	
	

	public DShipDesign(long designId, String designName, double attackMass, int guns, double defenceMass,
			double cargoMass, double engineMass) {
		this.designId = designId;
		this.designName = designName;
		this.attackMass = attackMass;
		this.guns = guns;
		this.defenceMass = defenceMass;
		this.cargoMass = cargoMass;
		this.engineMass = engineMass;
	}

	public long getDesignId() {
		return designId;
	}

	public void setDesignId(long designId) {
		this.designId = designId;
	}

	public String getDesignName() {
		return designName;
	}

	public void setDesignName(String designName) {
		this.designName = designName;
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

	public double getDefenceMass() {
		return defenceMass;
	}

	public void setDefenceMass(double defenceMass) {
		this.defenceMass = defenceMass;
	}

	@Override
	public String toString() {
		return "DShipDesign [designId=" + designId + ", designName=" + designName + ", attackMass=" + attackMass
				+ ", guns=" + guns + ", defenceMass=" + defenceMass + ", cargoMass=" + cargoMass + ", engineMass="
				+ engineMass + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof DShipDesign ) {
			DShipDesign d = (DShipDesign) obj;
			return designId == d.designId;
		}
		else return false;
	}
}
