package lt.gt.galaktika.engine.work;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.engine.config.AdditionalBeansConfig;
import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;

/**
 * This is engine.
 *
 */
public class MakeTurn {
	public final static void main ( String args[]) {
		System.out.println( "MakeTurn main called" );
		
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class, AdditionalBeansConfig.class );
		GameService turnMaker = context.getBean(GameService.class);
		turnMaker.makeTurn();
	}
}
