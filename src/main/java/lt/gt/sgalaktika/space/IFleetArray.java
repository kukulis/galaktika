package lt.gt.sgalaktika.space;

import java.util.Collection;
import java.util.List;

import lt.gt.sgalaktika.Fleet;

public interface IFleetArray {
	public Fleet findFleet ( long fleetId );
	public Collection<Fleet> getAllFleets ();
	public Collection<Long> getAllFleetsIds();
	public long addFleet(Fleet fleet);
}
