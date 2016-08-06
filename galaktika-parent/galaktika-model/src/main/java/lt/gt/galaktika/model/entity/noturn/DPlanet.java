package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
	@Access(AccessType.PROPERTY)
	private long planetId;
	
	private double x, y;
	private double planetSize, richness;
	
	public DPlanet() {
	}

	public DPlanet(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public DPlanet(long planetId, double x, double y, double planetSize, double richness) {
		this.planetId = planetId;
		this.x = x;
		this.y = y;
		this.planetSize = planetSize;
		this.richness = richness;
	}

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
	
//	public String toString () {
//		return "Planet("+planetId+")["+x+","+y+"]";
//	}
	
	@Override
	public boolean equals(Object obj) {
		if ( !(obj instanceof DPlanet))
			return false;
		DPlanet another = (DPlanet) obj;
		return planetId==another.planetId && x==another.x && y==another.y;
	}

	@Override
	public String toString() {
		return "DPlanet [planetId=" + planetId + ", x=" + x + ", y=" + y + ", planetSize=" + planetSize + ", richness="
				+ richness + "]";
	}

	public double getPlanetSize() {
		return planetSize;
	}

	public void setPlanetSize(double planetSize) {
		this.planetSize = planetSize;
	}

	public double getRichness() {
		return richness;
	}

	public void setRichness(double richness) {
		this.richness = richness;
	}
}
