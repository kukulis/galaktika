package lt.gt.galaktika.model.entity.turn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;

@Entity
public class DShipFactory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long shipFactoryId;
	
	@Column(nullable=true)
	private long planetId;
	
	@Column(nullable=true)
	private int turnNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipId")
	private DShip ship;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "designId")
	private DShipDesign design;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "technologiesId")
	private DTechnologies technologies;
	
	private double donePart;

	public DShip getShip() {
		return ship;
	}

	public void setShip(DShip ship) {
		this.ship = ship;
	}

	public DShipDesign getDesign() {
		return design;
	}

	public void setDesign(DShipDesign design) {
		this.design = design;
	}

	public DTechnologies getTechnologies() {
		return technologies;
	}

	public void setTechnologies(DTechnologies technologies) {
		this.technologies = technologies;
	}

	public double getDonePart() {
		return donePart;
	}

	public void setDonePart(double donePart) {
		this.donePart = donePart;
	}

	public long getShipFactoryId() {
		return shipFactoryId;
	}

	public void setShipFactoryId(long shipFactoryId) {
		this.shipFactoryId = shipFactoryId;
	}

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
	
}
