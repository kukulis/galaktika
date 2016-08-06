package lt.gt.galaktika.model.service;

import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.model.entity.noturn.DPlanet;

public class PlanetService extends AbstractGalaktikaService<DPlanet, Planet>{

	@Override
	public DPlanet createDbObject() {
		return new DPlanet();
	}

	@Override
	public Planet createCoreObject() {
		return new Planet();
	}
}
