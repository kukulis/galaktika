package lt.gt.galaktika.model.entity.turn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lt.gt.galaktika.model.entity.noturn.DFleet;
import lt.gt.galaktika.model.entity.noturn.DPlanet;
import lt.gt.galaktika.model.entity.noturn.DSpaceLocation;

@Entity @IdClass(DFleetDataId.class) 
@Table(name = "fleetdata")
public class DFleetData {
	@Id
	private long fleetId;
	@Id
	private int turnNumber;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "fleetId"),@JoinColumn(name = "turnNumber")})
	private List<DShipGroup> shipGroups = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planetId", nullable = true)
	private DPlanet planetLocation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spaceLocationId", nullable = true)
	private DSpaceLocation spaceLocation;
	
	// flightCommand
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flightSourceId", nullable = true )
	private DPlanet flightSource;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flightDestinationId", nullable = true)
	private DPlanet flightDestination;
	
	@ManyToOne
	@JoinColumn(name = "fleetId", referencedColumnName = "fleetId",
	insertable =  false, updatable = false)
	private DFleet fleet;
	
	public DFleetData() {
	}

	public DFleetData(long fleetId, int turnNumber) {
		this.fleetId = fleetId;
		this.turnNumber = turnNumber;
	}

	public long getFleetId() {
		return fleetId;
	}

	public void setFleetId(long fleetId) {
		this.fleetId = fleetId;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public List<DShipGroup> getShipGroups() {
		return shipGroups;
	}

	public void setShipGroups(List<DShipGroup> shipGroups) {
		this.shipGroups = shipGroups;
	}

	public DPlanet getPlanetLocation() {
		return planetLocation;
	}

	public void setPlanetLocation(DPlanet planetLocation) {
		this.planetLocation = planetLocation;
	}

	public DSpaceLocation getSpaceLocation() {
		return spaceLocation;
	}

	public void setSpaceLocation(DSpaceLocation spaceLocation) {
		this.spaceLocation = spaceLocation;
	}
	
	public DPlanet getFlightSource() {
		return flightSource;
	}

	public void setFlightSource(DPlanet flightSource) {
		this.flightSource = flightSource;
	}

	public DPlanet getFlightDestination() {
		return flightDestination;
	}

	public void setFlightDestination(DPlanet flightDestination) {
		this.flightDestination = flightDestination;
	}

	@Override
	public int hashCode() {
		return DFleetDataId.hashCode(fleetId, turnNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof DFleetData ) {
			DFleetData fdata = (DFleetData) obj;
			return fleetId == fdata.fleetId && turnNumber == fdata.turnNumber;
		}
		else return false;
	}

	public DFleet getFleet() {
		return fleet;
	}

	public void setFleet(DFleet fleet) {
		this.fleet = fleet;
	}
	
	
}
