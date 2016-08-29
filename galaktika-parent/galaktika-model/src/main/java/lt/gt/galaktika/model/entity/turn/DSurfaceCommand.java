package lt.gt.galaktika.model.entity.turn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.gt.galaktika.core.TechnologyType;
import lt.gt.galaktika.core.planet.SurfaceActivities;
import lt.gt.galaktika.model.entity.noturn.DShipDesign;

@Entity
@Table ( name="surfacecommands")
public class DSurfaceCommand {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sufraceCommandId;
	
	@Column(nullable=true)
	private Long planetId;
	
	@Column(nullable=true)
	private Integer turnNumber;
	
	@Enumerated(EnumType.STRING)
	private SurfaceActivities activity = SurfaceActivities.INDUSTRY;
	
	// in case it is industry
	// nothing
	
	//in case it is technologies
	@Enumerated(EnumType.STRING)
	private TechnologyType technologyToUpgrade;
	
	// in case it is production
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "designId")
	private DShipDesign design;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "technologiesId")
	private DTechnologies technologies;
	
//	private double donePart; // TODO move to shipFactory
	private int maxShips;
	
	public DSurfaceCommand() {
	}

	public long getSufraceCommandId() {
		return sufraceCommandId;
	}

	public void setSufraceCommandId(long sufraceCommandId) {
		this.sufraceCommandId = sufraceCommandId;
	}

	public SurfaceActivities getActivity() {
		return activity;
	}

	public void setActivity(SurfaceActivities activity) {
		this.activity = activity;
	}

	public TechnologyType getTechnologyToUpgrade() {
		return technologyToUpgrade;
	}

	public void setTechnologyToUpgrade(TechnologyType technologyToUpgrade) {
		this.technologyToUpgrade = technologyToUpgrade;
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

//	public double getDonePart() {
//		return donePart;
//	}
//
//	public void setDonePart(double donePart) {
//		this.donePart = donePart;
//	}

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
	
	public int getMaxShips() {
		return maxShips;
	}

	public void setMaxShips(int maxShips) {
		this.maxShips = maxShips;
	}

	@Override
	public String toString() {
		return "DSurfaceCommand [sufraceCommandId=" + sufraceCommandId + ", planetId=" + planetId + ", turnNumber="
				+ turnNumber + ", activity=" + activity + ", technologyToUpgrade=" + technologyToUpgrade + ", design="
				+ design + ", technologies=" + technologies + ", maxShips=" + maxShips + "]";
	}

}
