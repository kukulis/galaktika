package lt.gt.galaktika.ejb;

import java.util.List;

import javax.ejb.Remote;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.ShipGroup;

@Remote
public interface FleetsServiceRemote {
	List<Fleet> getFleets();
	Fleet getById(long fleetId);
	boolean update(Fleet fleet);
	long create( String fleetName );
	boolean delete(long fleetId);
	/**
	 * Creates both ship and ship group and adds them to the fleet.
	 * @param fleet
	 * @param group
	 * @return
	 */
	long addShipGroup (Fleet fleet, ShipGroup group );
	boolean updateShipGroup(long fleetId, ShipGroup group );
	boolean deleteShipGroup (long fleetId, long shipId );
}
