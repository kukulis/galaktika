package lt.gt.galaktika.model.dao;

import lt.gt.galaktika.model.entity.turn.DPlanetSurface;

public interface IPlanetSurfaceDao {
	public DPlanetSurface find ( long planetId, int turnNumber );
}
