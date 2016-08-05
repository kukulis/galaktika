package lt.gt.galaktika.model.entity.turn;

import java.io.Serializable;

public class DPlanetSurfaceId implements Serializable {
	private static final long serialVersionUID = -1689954452253883094L;
	private long planetId;
	private int turnNumber;
	public long getPlanetId() {
		return planetId;
	}
	public void setPlanetId(long planetId) {
		this.planetId = planetId;
	}
	public int getTurnNumber() {
		return turnNumber;
	}
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}
	@Override
	public int hashCode() {
		return Long.hashCode( planetId ) >>> 16 ^ turnNumber;
	}
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof DPlanetSurfaceId ) {
			DPlanetSurfaceId psid = (DPlanetSurfaceId) obj;
			return planetId == psid.planetId && turnNumber == psid.turnNumber;
		}
		else return false;
	}
	
}
