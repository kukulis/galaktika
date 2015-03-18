package lt.gt.sgalaktika.space;

import java.util.ArrayList;
import java.util.List;

public class Galaxy {
	private int id;
	private int currentTurn;
	private String name;
	
	private List <Planet> planets=new ArrayList<Planet>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Planet> getPlanets() {
		return planets;
	}
}
