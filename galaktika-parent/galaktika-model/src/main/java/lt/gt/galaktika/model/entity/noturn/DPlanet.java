package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="planets")
public class DPlanet
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long planetId;
	
	private double x, y;

	public long getPlanetId() {
		return planetId;
	}

	public void setPlanetId(long planetId) {
		this.planetId = planetId;
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
}
