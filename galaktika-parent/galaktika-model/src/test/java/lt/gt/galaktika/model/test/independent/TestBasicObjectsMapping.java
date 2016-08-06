package lt.gt.galaktika.model.test.independent;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.SpaceLocation;
import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.ShipDesign;
import lt.gt.galaktika.model.service.AbstractGalaktikaService;
import lt.gt.galaktika.model.service.NationService;
import lt.gt.galaktika.model.service.PlanetService;
import lt.gt.galaktika.model.service.ShipDesignService;
import lt.gt.galaktika.model.service.ShipService;
import lt.gt.galaktika.model.service.SpaceLocationService;
import lt.gt.galaktika.model.service.TechnologiesService;

public class TestBasicObjectsMapping {
	final static Logger LOG = LoggerFactory.getLogger(TestBasicObjectsMapping.class);

	@Test
	public void testBasicMappings() {
		AbstractGalaktikaService services[] = new AbstractGalaktikaService[] { new NationService(), new PlanetService(),
				new ShipDesignService(), new ShipService(), new SpaceLocationService(), new TechnologiesService() };

		// Object[] databaseObjects = new Object[] {
		// new DNation(1,"nacija",3),
		// new DPlanet(1,2,3,4,5),
		// new DShipDesign(1,"dizainas", 1,2,3,4,5),
		// new DShip(1,"katinas", 1,2,3,4,5,6),
		// new DSpaceLocation(1,2,3),
		// new DTechnologies(1,new DNation(2,"aaa", 4 ), 1,2,3,4,5)
		// };

		Object[] coreObjects = new Object[] { new Nation(1, "pieviai"), new Planet(1, 2, 3, 4, 5),
				new ShipDesign(1, "dizainiukas", 1, 2, 3, 4, 5), new Ship(1, "zuikis", 1, 2, 3, 4, 5, 6, 7),
				new SpaceLocation(1, 2, 3), new Technologies(1, 2, 3, 4, 5) };

		Assert.assertEquals("Testui reikalinga kad servisų ir objektų kiekiai sutaptų", services.length,
				coreObjects.length);

		for (int i = 0; i < services.length; i++) {
			Object databaseObject = services[i].mapToDbObject(coreObjects[i]);
			Object coreObject = services[i].mapToCoreObject(databaseObject);
			LOG.trace("Klasė "+coreObjects[i].getClass().getName()+ ": Prieš mapinant" + coreObjects[i] + ". Po mapinimo " + coreObject);

			Assert.assertEquals("Su klase " + coreObjects[i].getClass().getName() + "įvyko bėdos:",
					coreObjects[i].toString(), coreObject.toString());
		}
	}
}
