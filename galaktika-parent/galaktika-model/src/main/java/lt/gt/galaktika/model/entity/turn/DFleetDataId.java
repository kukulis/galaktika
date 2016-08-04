package lt.gt.galaktika.model.entity.turn;

import java.io.Serializable;

public class DFleetDataId implements Serializable {
	private static final long serialVersionUID = -4814436820422020384L;
	
	long fleetId;
	int turnNumber;
	
	public long getFleetId() {
		return fleetId;
	}
	public void setFleetId(long fleetId) {
		this.fleetId = fleetId;
	}
	public int getTurnNumber() {
		return turnNumber;
	}
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}
	@Override
	public int hashCode() {
		return (Long.hashCode(fleetId) >>> 16) ^ turnNumber;
	}
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof DFleetDataId ) {
			DFleetDataId fdid = (DFleetDataId) obj;
			return fdid.getFleetId() == fleetId && fdid.getTurnNumber() == turnNumber;
		}
		else return false;
	}
	
	
}
