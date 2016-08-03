package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fleets")
public class DFleet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fleetId;

	@Size(max = 32)
	private String name; // optional ?

	// relations
	// private long nationId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nationId", nullable = true)
	private DNation nation;

	private boolean deleted;

	public DFleet() {
	}

	public DFleet(String name) {
		this.name = name;
	}

	public long getFleetId() {
		return fleetId;
	}

	public void setFleetId(long fleetId) {
		this.fleetId = fleetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public DNation getNation() {
		return nation;
	}

	public void setNation(DNation nation) {
		this.nation = nation;
	}
	// public List<DShipGroup> getShipGroups ()
	// {
	// return shipGroups;
	// }
	// public void setShipGroups ( List<DShipGroup> shipGroups )
	// {
	// this.shipGroups = shipGroups;
	// }
}
