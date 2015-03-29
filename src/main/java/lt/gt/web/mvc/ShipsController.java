package lt.gt.web.mvc;

import java.util.List;

import lt.gt.sgalaktika.Ship;
import lt.gt.web.services.api.ShipsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ships")
public class ShipsController {
	
	@Autowired
	private ShipsRepository shipsRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Ship> listShips(){
		return shipsRepository.getShips();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Ship getShip(@PathVariable("id") long id){
		return shipsRepository.getShip(id);
	}
	
	@RequestMapping( method = RequestMethod.POST)
	public void save(@RequestBody Ship ship){
		shipsRepository.storeShip(ship);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody Ship ship){
		shipsRepository.updateShip(ship);
	}
	
	@RequestMapping(value="/{id}",  method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") long id){
		shipsRepository.deleteShip(id);
	}  
}
