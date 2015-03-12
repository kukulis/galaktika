package lt.gt.sgalaktika;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
	private List <ShipGroup> groups = new ArrayList<ShipGroup> ();
	
	
//	public void removeOneShip ( Ship ship ) {
//		// find the group
//		for ( ShipGroup group : groups ) {
//			if ( group.getShip().getShipSerie().equals( ship.getShipSerie() ) ) {
//				group.setAmount( group.getAmount() - 1 );
//			}
//		}
//	}
	
	/**
	 * TODO use counter, which will be updated in "add" and "remove" methods.
	 * @return
	 */
	public int calculateShips () {
		int count = 0;
		for ( ShipGroup group : groups ) {
			count += group.getAmount();
		}
		return count;
	}
	
	public void addShipGroup ( ShipGroup group ) {
		groups.add(group);
	}
	
	public void resetShotsAmount () {
		for ( ShipGroup group : groups ) {
			group.setShotedShips( 0 );
		}
	}
	
	/**
	 * TODO use counters for performance
	 * @return
	 */
	public int calculateShots() {
		int shots = 0;
		for ( ShipGroup group : groups ) {
			shots += group.getShotedShips();
		}
		
		return shots;
	}
	
	public int calculateShipsWithGuns () {
		int count = 0;
		for ( ShipGroup group : groups ) {
			if ( group.getShip().getGuns() > 0 ) {
				count += group.getAmount();
			}
		}
		return count;
	}
	
	public int calculateAbleShotShipCount () {
		int count = 0;
		for ( ShipGroup group : groups ) {
			count += group.getAbleShotAmount();
		}
		return count;
	}
	
	public ShipGroup selectAttackerGroup (int shipNumber) {
		int i =0;
		
		while (  groups.size() > i && (shipNumber > 0 || groups.get(i).getAbleShotAmount() == 0 )  ) {
			ShipGroup group = groups.get( i );
			if ( group.getShip().getGuns() > 0 ) {
				shipNumber = shipNumber - group.getAbleShotAmount();
			}
			i++;
		}
		if ( i < groups.size() )
			return groups.get(i);
		else
			return null;
		
	}
	
	public ShipGroup selectAnyGroup (int shipNumber) {
		int i =0;
		
		while (  groups.size() > i && (shipNumber > 0 || groups.get(i).getAmount() == 0 )  ) {
			ShipGroup group = groups.get( i );
			shipNumber = shipNumber - group.getAmount();
			i++;
		}
		if ( i < groups.size() )
			return groups.get(i);
		else
			return null;
		
	}
	
//	/**
//	 * @deprecated
//	 * 
//	 * @param shipNumber
//	 * @param attacker
//	 * @return
//	 */
//	private ShipGroup innerSelectGroup ( int shipNumber, boolean attacker ) {
//	}
//	public ShipGroup selectGroup ( int shipNumber ) {
//		int i = 0;
//		while ( shipNumber > 0 && groups.size() > i  ) {
//			ShipGroup group = groups.get( i );
//			shipNumber = shipNumber - group
//		}
//	}
	
}
