package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "galaxies")
public class DGalaxy
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long galaxyId;

	double sizeX, sizeY;
	EGalaxyPurposes purpose;
	boolean active;
	int turn;
	
	
	public long getGalaxyId() {
		return galaxyId;
	}
	public void setGalaxyId(long galaxyId) {
		this.galaxyId = galaxyId;
	}
	public double getSizeX() {
		return sizeX;
	}
	public void setSizeX(double sizeX) {
		this.sizeX = sizeX;
	}
	public double getSizeY() {
		return sizeY;
	}
	public void setSizeY(double sizeY) {
		this.sizeY = sizeY;
	}
	public EGalaxyPurposes getPurpose() {
		return purpose;
	}
	public void setPurpose(EGalaxyPurposes purpose) {
		this.purpose = purpose;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
}
