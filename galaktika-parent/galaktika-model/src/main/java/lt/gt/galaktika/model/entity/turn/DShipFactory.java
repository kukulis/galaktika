package lt.gt.galaktika.model.entity.turn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;

@Entity
@Table ( name="shipfactories")
public class DShipFactory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long shipFactoryId;
	
	@Column(nullable=true)
	private Long planetId;
	
	@Column(nullable=true)
	private Integer turnNumber;
	
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
	
	public DShipFactory() {
	}
	public DShipFactory(long planetId, int turnNumber) {
		this.planetId = planetId;
		this.turnNumber = turnNumber;
	}
	
	public DShipFactory(DShip ship, DShipDesign design, DTechnologies technologies) {
		this.ship = ship;
		this.design = design;
		this.technologies = technologies;
	}

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

	public Long getPlanetId() {
		return planetId;
	}

	public void setPlanetId(Long planetId) {
		this.planetId = planetId;
	}

	public Integer getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(Integer turnNumber) {
		this.turnNumber = turnNumber;
	}

	@Override
	public int hashCode() {
		// TODO not sure if this is correct
		return Long.hashCode(shipFactoryId );
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof DShipFactory ) {
			DShipFactory factory = (DShipFactory) obj;
			return factory.shipFactoryId == shipFactoryId;
		}
		else
			return false;
	}

	
	
}
