package lt.gt.galaktika.model.service;

import org.springframework.stereotype.Service;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.model.entity.noturn.DNation;

@Service
public class NationService extends AbstractGalaktikaService<DNation, Nation> {

	@Override
	public DNation createDbObject() {
		return new DNation();
	}

	@Override
	public Nation createCoreObject() {
		return new Nation();
	}

}
