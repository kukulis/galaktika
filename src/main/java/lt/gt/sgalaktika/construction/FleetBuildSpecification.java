package lt.gt.sgalaktika.construction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class FleetBuildSpecification {
	
	@Id @GeneratedValue
	private long fb_id;
	
	@OneToMany(mappedBy="fleetBuildSpecification" , cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER )
	@OrderBy("buildOrder")
	private List <ShipGroupBuildSpecification> shipGroupBSpecifications=new ArrayList<ShipGroupBuildSpecification>();
	
	private double _resourcesNeeded=0;
	private double _resourcesUsed=0;
	
	public long getFb_id() {
		return fb_id;
	}

	public void setFb_id(long fb_id) {
		this.fb_id = fb_id;
	}

	public List<ShipGroupBuildSpecification> getShipGroupBSpecifications() {
		return shipGroupBSpecifications;
	}
	
	public void setShipGroupBSpecifications(
			List<ShipGroupBuildSpecification> shipGroupBSpecifications) {
		this.shipGroupBSpecifications = shipGroupBSpecifications;
	}

	public void addShipGroupBS( ShipGroupBuildSpecification gSpec ) {
		shipGroupBSpecifications.add( gSpec );
		gSpec.setFleetBuildSpecification ( this );
		gSpec.setBuildOrder( shipGroupBSpecifications.size());
	}
	
	public void calculateResources () {
		_resourcesNeeded=0;
		_resourcesUsed=0;
		for ( ShipGroupBuildSpecification gspec:  shipGroupBSpecifications) {
			gspec.calculateResourcesNeeded();
			_resourcesNeeded += gspec.get_resourcesNeeded();
			_resourcesUsed += gspec.getResourcesUsed();
		}
	}


	public double get_resourcesNeeded() {
		return _resourcesNeeded;
	}


	public double get_resourcesUsed() {
		return _resourcesUsed;
	}
	
	
}
