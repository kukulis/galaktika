package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.entity.noturn.DNation;
import lt.gt.galaktika.model.entity.turn.DPlanetSurface;

public interface IPlanetSurfaceDao {
	DPlanetSurface find ( long planetId, int turnNumber );
	List<Long> findPlanets(Long nationId, int turnNumber);
}
