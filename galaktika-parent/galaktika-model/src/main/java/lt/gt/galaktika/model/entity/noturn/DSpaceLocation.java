package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spacelocation")
public class DSpaceLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long locationId;
	
	private double x, y;

	public DSpaceLocation() {
	}
	public DSpaceLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public DSpaceLocation(long locationId, double x, double y) {
		this.locationId = locationId;
		this.x = x;
		this.y = y;
	}
	
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "DSpaceLocation("+locationId+")["+x+","+y+"]";
	}
	
}