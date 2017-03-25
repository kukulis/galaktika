package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.entity.noturn.DGalaxy;
import lt.gt.galaktika.model.entity.noturn.DPlanet;

public interface IPlanetDao {
	public List<DPlanet> getGalaxyPlanets (DGalaxy g); 
}
