package lt.gt.web.services.api;

import java.util.List;

import lt.gt.sgalaktika.Ship;

public interface ShipsRepository {
	List<Ship> getShips();
	
	void storeShip(Ship ship);
	
	void deleteShip(Long id);
}
