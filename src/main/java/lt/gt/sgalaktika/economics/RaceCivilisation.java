package lt.gt.sgalaktika.economics;

import java.util.ArrayList;
import java.util.List;

import lt.gt.sgalaktika.construction.Technologies;
import lt.gt.sgalaktika.space.Race;

public class RaceCivilisation {
	private Race ownerRace;
	private Technologies technologies;
	private List <PlanetCivilisation> planetCivilisations = new ArrayList<PlanetCivilisation>();
	
	public Race getOwnerRace() {
		return ownerRace;
	}
	public void setOwnerRace(Race ownerRace) {
		this.ownerRace = ownerRace;
	}
	public Technologies getTechnologies() {
		return technologies;
	}
	public void setTechnologies(Technologies technologies) {
		this.technologies = technologies;
	}
	public List<PlanetCivilisation> getPlanetCivilisations() {
		return planetCivilisations;
	}
	public void setPlanetCivilisations(List<PlanetCivilisation> planetCivilisations) {
		this.planetCivilisations = planetCivilisations;
	}
	
	
}
