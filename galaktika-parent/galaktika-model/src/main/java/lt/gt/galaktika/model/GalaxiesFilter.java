package lt.gt.galaktika.model;

import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;

public class GalaxiesFilter {
	boolean useActiveFilter = false;
	boolean usePurposeFilter = false;
	
	EGalaxyPurposes purpose;
	boolean active;

	public EGalaxyPurposes getPurpose() {
		return purpose;
	}
	public GalaxiesFilter setPurpose(EGalaxyPurposes purpose) {
		this.usePurposeFilter = true;
		this.purpose = purpose;
		return this;
	}
	public boolean isActive() {
		return active;
	}
	public GalaxiesFilter setActive(boolean active) {
		this.useActiveFilter = true;
		this.active = active;
		return this;
	}
	public boolean isUseActiveFilter() {
		return useActiveFilter;
	}
	public boolean isUsePurposeFilter() {
		return usePurposeFilter;
	}
}