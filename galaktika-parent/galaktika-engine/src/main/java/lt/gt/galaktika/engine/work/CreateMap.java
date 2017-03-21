package lt.gt.galaktika.engine.work;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lt.gt.galaktika.engine.config.MockDbConfig;
import lt.gt.galaktika.model.config.ModelBeansConfig;

public class CreateMap {
	public final static void main(String args[] ) {
		ApplicationContext context = new AnnotationConfigApplicationContext(  MockDbConfig.class, ModelBeansConfig.class );
		
		// we will create equal size planets in to random position,
		// but we will check if the position is valid ( not too near other planets )
		// and only in that case it will be created
		// so we may create planets array before saving to database
		
	}
}
