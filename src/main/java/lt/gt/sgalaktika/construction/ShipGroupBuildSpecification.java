package lt.gt.sgalaktika.construction;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ShipGroupBuildSpecification {
	
	@Id @GeneratedValue
	private long gb_id;
	
	@Embedded
	private ShipBuildSpecification shipBSpecification;
	private int shipAmount=1;
	
	private double resourcesUsed=0;
	
	@ManyToOne @JoinColumn(name="fb_id" )
	private FleetBuildSpecification fleetBuildSpecification;
	private int buildOrder;
	
	@Transient
	private double _resourcesNeeded=0;
	
	public ShipGroupBuildSpecification ( ShipBuildSpecification shipSpec ) {
		shipBSpecification = shipSpec;
	}
	
	public long getGb_id() {
		return gb_id;
	}

	public void setGb_id(long gb_id) {
		this.gb_id = gb_id;
	}
	
	public int getBuildOrder() {
		return buildOrder;
	}

	public void setBuildOrder(int buildOrder) {
		this.buildOrder = buildOrder;
	}

	public FleetBuildSpecification getFleetBuildSpecification() {
		return fleetBuildSpecification;
	}

	public void setFleetBuildSpecification(
			FleetBuildSpecification fleetBuildSpecification) {
		this.fleetBuildSpecification = fleetBuildSpecification;
	}

	public ShipBuildSpecification getShipBSpecification() {
		return shipBSpecification;
	}
	
	public void setShipBSpecification(ShipBuildSpecification shipBSpecification) {
		this.shipBSpecification = shipBSpecification;
	}

	public int getShipAmount() {
		return shipAmount;
	}

	public void setShipAmount(int shipAmount) {
		this.shipAmount = shipAmount;
	}
	
	public double getResourcesUsed() {
		return resourcesUsed;
	}

	public void setResourcesUsed(double resourcesUsed) {
		this.resourcesUsed = resourcesUsed;
	}

	public double get_resourcesNeeded() {
		return _resourcesNeeded;
	}
	
	public void calculateResourcesNeeded() {
		ShipModel model = shipBSpecification.getModel();
		_resourcesNeeded = ( model.getAttackMass() + model.getCargoMass() 
				+ model.getDefenceMass() + model.getEngineMass() ) * shipAmount;
	}
}
