package lt.gt.galaktika.engine.test.integration;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.engine.config.AdditionalBeansConfig;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockDbConfig.class, ModelBeansConfig.class, AdditionalBeansConfig.class })
public class ValidateAfterTurn1 {
	
	@Autowired
	GalaxyService galaxyService;
	
	@Test
	public void validateTurn1 () {
		
		List<Galaxy> galaxies = galaxyService.getGalaxies(new GalaxiesFilter().setPurpose(EGalaxyPurposes.PLAY).setActive(true));
		if ( galaxies.size() == 0 ) {
			System.err.println( "There is no play active galaxy");
			return;
		}
			
		Galaxy g = galaxies.get(0);
		Assert.assertEquals(2, g.getTurn());
		
		// TODO other checks
	}
}
