package lt.gt.galaktika.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.ShipGroup;

/**
 * Session Bean implementation class FleetsService
 */
@Singleton
@LocalBean
public class FleetsService implements FleetsServiceRemote {

	private long maxFleetId = 1;
	private long maxShipId = 1;
	List<Fleet> fleets;

	/**
	 * Default constructor.
	 */
	public FleetsService() {
		fleets = new ArrayList<>();
		long id = create("Pirmoji flotilė");
		addShipGroup(id, new ShipGroup(new Ship("cibukas", 1, 1, 1, 0.1, 0), 2));
		long id2 = create("Antroji flotilė");
		addShipGroup(id2, new ShipGroup(new Ship("dantukas", 1, 1, 1, 0.1, 0), 2));
	}

	@Override
	public List<Fleet> getFleets() {
		return fleets;
	}

	@Override
	public Fleet getById(long fleetId) {
		return fleets.stream().filter(f -> f.getFleetId() == fleetId).findFirst().orElse(null);
	}

	@Override
	public boolean update(Fleet fleet) {
		Optional<Fleet> oFleet = fleets.stream().filter(f -> f.getFleetId() == fleet.getFleetId()).findFirst();
		if (oFleet.isPresent())
			oFleet.get().setName(fleet.getName());
		return oFleet.isPresent();
	}

	@Override
	public long create(String fleetName) {
		Fleet fleet = new Fleet(maxFleetId++, fleetName);
		fleets.add(fleet);
		return fleet.getFleetId();
	}

	@Override
	public boolean delete(long fleetId) {
		int foundIndex = -1;
		for (int i = 0; i < fleets.size(); i++)
			if (fleets.get(i).getFleetId() == fleetId) {
				foundIndex = i;
				break;
			}
		if (foundIndex >= 0)
			fleets.remove(foundIndex);
		return false;
	}

	@Override
	public long addShipGroup(long fleetId, ShipGroup group) {
		// create ship group and ship
		// add to fleet
		Fleet fleet = getById(fleetId);
		if (fleet == null)
			return -1;

		if (group.getShip().getId() == 0) {
			group.setShip(createShip(group.getShip()));
		}
		// find group with the same ship
		// if not found then add new group
		// if found, then add ships amount to the existing group
		Optional<ShipGroup> oGroup = fleet.getShipGroups().stream()
				.filter(g -> g.getShip().getId() == group.getShip().getId()).findFirst();
		if (oGroup.isPresent()) {
			System.out.println("Updating ships count for ship " + group.getShip().getId());
			oGroup.get().setCount(oGroup.get().getCount() + group.getCount());
		} else {
			System.out.println("Adding ship to fleet " + fleet.getFleetId() + " ship id=" + group.getShip().getId());
			fleet.addShipGroup(group);
		}

		return group.getShip().getId();
	}

	private Ship createShip(Ship ship) {
		ship.setId(maxShipId++);
		return ship;
	}

	@Override
	public boolean updateShipGroup(long fleetId, ShipGroup group) {
		// find fleet
		Fleet fleet = fleets.stream().filter(f -> f.getFleetId() == fleetId).findFirst().orElse(null);
		if (fleet == null)
			return false;
		// find group in fleet with the same ship
		ShipGroup foundGroup = fleet.getShipGroups().stream()
				.filter(g -> g.getShip().getId() == group.getShip().getId()).findFirst().orElse(null);
		if (foundGroup == null)
			return false;

		foundGroup.setCount(group.getCount());
		return true;
	}

	@Override
	public boolean deleteShipGroup(long fleetId, long shipId) {
		Fleet fleet = fleets.stream().filter(f -> f.getFleetId() == fleetId).findFirst().orElse(null);
		if (fleet == null)
			return false;

		// find group in fleet with the same ship
		int foundIndex = -1;
		for (int i = 0; i < fleet.getShipGroups().size(); i++)
			if (fleet.getShipGroups().get(i).getShip().getId() == shipId) {
				foundIndex = i;
				break;
			}

		if (foundIndex > 0) {
			fleet.getShipGroups().remove(foundIndex);
			return true;
		}
		return false;
	}

	@Override
	public ShipGroup getShipGroup(long fleetId, long shipId) {
		Fleet fleet = fleets.stream().filter(f -> f.getFleetId() == fleetId).findFirst().orElse(null);
		if (fleet == null)
			return null;
		return fleet.getShipGroups().stream().filter(g -> g.getShip().getId() == shipId).findFirst().orElse(null);
	}

}
