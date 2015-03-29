package lt.gt.web.services.api;

import java.util.List;

import lt.gt.sgalaktika.Ship;

public interface ShipsRepository {
	
	List<Ship> getShips();
	
	Ship getShip(long id);
	
	void storeShip(Ship ship);
	
	void updateShip(Ship ship);
	
	void deleteShip(Long id);
}
