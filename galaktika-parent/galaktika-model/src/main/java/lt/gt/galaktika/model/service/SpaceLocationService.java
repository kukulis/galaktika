package lt.gt.galaktika.model.service;

import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.model.entity.noturn.DSpaceLocation;

public class SpaceLocationService extends AbstractGalaktikaService<DSpaceLocation, SpaceLocation> {

	@Override
	public DSpaceLocation createDbObject() {
		return new DSpaceLocation();
	}

	@Override
	public SpaceLocation createCoreObject() {
		return new SpaceLocation();
	}
}
