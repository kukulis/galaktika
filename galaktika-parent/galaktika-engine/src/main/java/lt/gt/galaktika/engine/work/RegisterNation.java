package lt.gt.galaktika.engine.work;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.core.Nation;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;
import lt.gt.galaktika.model.service.NationService;

/**
 * 
 * This is more mock.
 *
 */
public class RegisterNation {

	public static void main(String args[] ) {
		System.out.println( "Register nation main called" );
		// TODO
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		
		NationService ns =  context.getBean (NationService.class );
		Nation n = new Nation ( "terans");
		
		

		// Nation to user relation:
		// Lets say, that everything, that is possible to do, you need to have User logged in
		// so the logged-in user who creates Nation, he relates this nation to himself.
		
		
//		ns.create(  );
	}
}
