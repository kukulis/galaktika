package lt.gt.sgalaktika.construction;

public class ShipGroupBuildSpecification {
	private ShipBuildSpecification shipBSpecification;
	private int shipAmount;
	
	private double resourcesUsed=0;
	
	private double _resourcesNeeded=0;
	
	public ShipGroupBuildSpecification ( ShipBuildSpecification shipSpec ) {
		shipBSpecification = shipSpec;
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
