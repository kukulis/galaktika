package lt.gt.sgalaktika.construction;

import java.util.ArrayList;
import java.util.List;

public class FleetBuildSpecification {
	private List <ShipGroupBuildSpecification> shipGroupBSpecifications=new ArrayList<ShipGroupBuildSpecification>();
	
	private double _resourcesNeeded=0;
	private double _resourcesUsed=0;

	public List<ShipGroupBuildSpecification> getShipGroupBSpecifications() {
		return shipGroupBSpecifications;
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
