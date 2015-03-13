package lt.gt.sgalaktika.construction;

// TODO
public class ShipUpgradeSpecification {
	private ShipModel model;
	private Technologies technologies;
	
	private boolean changedAttack;
	private boolean changedGuns;
	private boolean changedDefence;
	private boolean changedCargo;
	private boolean changedEngines;
	public ShipModel getModel() {
		return model;
	}
	public void setModel(ShipModel model) {
		this.model = model;
	}
	public Technologies getTechnologies() {
		return technologies;
	}
	public void setTechnologies(Technologies technologies) {
		this.technologies = technologies;
	}
	public boolean isChangedAttack() {
		return changedAttack;
	}
	public void setChangedAttack(boolean changedAttack) {
		this.changedAttack = changedAttack;
	}
	public boolean isChangedGuns() {
		return changedGuns;
	}
	public void setChangedGuns(boolean changedGuns) {
		this.changedGuns = changedGuns;
	}
	public boolean isChangedDefence() {
		return changedDefence;
	}
	public void setChangedDefence(boolean changedDefence) {
		this.changedDefence = changedDefence;
	}
	public boolean isChangedCargo() {
		return changedCargo;
	}
	public void setChangedCargo(boolean changedCargo) {
		this.changedCargo = changedCargo;
	}
	public boolean isChangedEngines() {
		return changedEngines;
	}
	public void setChangedEngines(boolean changedEngines) {
		this.changedEngines = changedEngines;
	}
}
