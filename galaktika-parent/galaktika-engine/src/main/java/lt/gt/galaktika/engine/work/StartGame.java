package lt.gt.galaktika.engine.work;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.core.Galaxy;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.service.GalaxyService;

/**
 * This is work.
 * @author Lenovo
 *
 */
public class StartGame {
	public final static void main (String args[]) {
		System.out.println( "StartGame main called" );
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		
		Galaxy galaxy = new Galaxy(1000,1000);
		galaxy.setTurn(1);
		GalaxyService gs = context.getBean( GalaxyService.class );
		gs.createPlayGalaxy( galaxy );
	}
}
