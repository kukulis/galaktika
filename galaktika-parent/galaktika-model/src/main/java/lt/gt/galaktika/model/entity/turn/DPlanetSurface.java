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

import lt.gt.galaktika.model.entity.noturn.DNation;

/**
 * Describes who is planet owner and all the industry and population data.
 * Does not have own id. Depends on planet and on turn.
 *
 */
@Entity @IdClass(DPlanetSurfaceId.class) 
@Table(name = "planetsurfaces")
public class DPlanetSurface {
	@Id
	private long planetId;
	@Id
	private int turnNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nationId", nullable = true)
	private DNation owner;
	private double population;
	private double industry;
	private double capital;
	
	/**
	 * It is supposed to have 1 or 0 elements
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "planetId"),@JoinColumn(name = "turnNumber")})
	private List<DSurfaceCommand> commands = new ArrayList<>();
	
	/**
	 * It is supposed to have 1 or 0 elements
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "planetId"),@JoinColumn(name = "turnNumber")})
	private List<DShipFactory> shipFactories = new ArrayList<>();
	
	public long getPlanetId() {
		return planetId;
	}
	public void setPlanetId(long planetId) {
		this.planetId = planetId;
	}
	public int getTurnNumber() {
		return turnNumber;
	}
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}
	public DNation getOwner() {
		return owner;
	}
	public void setOwner(DNation owner) {
		this.owner = owner;
	}
	public double getPopulation() {
		return population;
	}
	public void setPopulation(double population) {
		this.population = population;
	}
	public double getIndustry() {
		return industry;
	}
	public void setIndustry(double industry) {
		this.industry = industry;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public List<DShipFactory> getShipFactories() {
		return shipFactories;
	}
	public void setShipFactories(List<DShipFactory> shipFactories) {
		this.shipFactories = shipFactories;
	}
	public List<DSurfaceCommand> getCommands() {
		return commands;
	}
	public void setCommands(List<DSurfaceCommand> commands) {
		this.commands = commands;
	}
}
