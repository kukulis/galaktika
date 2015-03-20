package lt.gt.sgalaktika.construction;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
//@Table ( name="shipbuildspecification" )
@Embeddable
public class ShipBuildSpecification {

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="model_id")
	private ShipModel model;
	
	@Embedded
	private Technologies technologies;
	
	public ShipBuildSpecification () {
		
	}
	
	public ShipBuildSpecification ( ShipModel m ) {
		model = m;
	}
	
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
