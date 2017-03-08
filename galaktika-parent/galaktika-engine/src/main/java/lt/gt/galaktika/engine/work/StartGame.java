package lt.gt.galaktika.engine.work;

import java.util.List;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.GalaxiesFilter;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.entity.noturn.EGalaxyPurposes;
import lt.gt.galaktika.model.service.GalaxyService;

/**
 * This is work.
 *
 */
public class StartGame {
	public final static void main (String args[]) {
		System.out.println( "StartGame main called" );
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		
		GalaxyService gs = context.getBean( GalaxyService.class );
		List <Galaxy> galaxies = gs.getGalaxies(new GalaxiesFilter().setActive(true).setPurpose(EGalaxyPurposes.PLAY ) );
		
		Assert.assertTrue ( "There already is an active playing galaxy", galaxies.size() == 0 );
		
		Galaxy galaxy = new Galaxy(1000,1000);
		galaxy.setTurn(1);
		gs.createGalaxy( galaxy, EGalaxyPurposes.PLAY, true );
	}
}
