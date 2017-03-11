package lt.gt.galaktika.model;

public class NationsFilter {
	boolean useNationId = false;
	boolean useGalaxyId = false;
	boolean useUserId = false;
	
	long nationId;
	long galaxyId;
	long userId;
	
	public long getNationId() {
		return nationId;
	}
	public NationsFilter setNationId(long nationId) {
		this.nationId = nationId;
		this.useNationId = true;
		return this;
	}
	public long getGalaxyId() {
		return galaxyId;
	}
	public NationsFilter setGalaxyId(long galaxyId) {
		this.galaxyId = galaxyId;
		this.useGalaxyId = true;
		return this;
	}
	public long getUserId() {
		return userId;
	}
	public NationsFilter setUserId(long userId) {
		this.userId = userId;
		this.useUserId = true;
		return this;
	}
	public boolean isUseNationId() {
		return useNationId;
	}
	public boolean isUseGalaxyId() {
		return useGalaxyId;
	}
	public boolean isUseUserId() {
		return useUserId;
	}
}
