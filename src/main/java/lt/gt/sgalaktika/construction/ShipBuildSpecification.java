package lt.gt.sgalaktika.construction;


public class ShipBuildSpecification {
	private ShipModel model;
	private Technologies technologies;
	
	public Technologies getTechnologies() {
		return technologies;
	}

	public void setTechnologies(Technologies technologies) {
		this.technologies = technologies;
	}

	public ShipModel getModel() {
		return model;
	}

	public void setModel(ShipModel model) {
		this.model = model;
	}
}
