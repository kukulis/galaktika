package lt.gt.sgalaktika.economics;

import lt.gt.sgalaktika.space.Race;

public class PlanetCivilisation {
	private String planetName;
	private double populationSize;
	private int currentTurn;
	private Race ownerRace;
	
	public String getPlanetName() {
		return planetName;
	}
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	public double getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(double populationSize) {
		this.populationSize = populationSize;
	}
	public int getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public void executePopulationWork () {
		
	}
	public void executeGrow () {
		
	}
	
	public void executeConstructionCommands() {
		// TODO
	}
	
	public Race getOwnerRace() {
		return ownerRace;
	}
	public void setOwnerRace(Race ownerRace) {
		this.ownerRace = ownerRace;
	}
	

}
