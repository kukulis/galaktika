package lt.gt.galaktika.model.service;

import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.model.entity.turn.DTechnologies;

public class TechnologiesService extends AbstractGalaktikaService<DTechnologies, Technologies>{

	@Override
	public DTechnologies createDbObject() {
		return new DTechnologies();
	}

	@Override
	public Technologies createCoreObject() {
		return new Technologies();
	}
}
