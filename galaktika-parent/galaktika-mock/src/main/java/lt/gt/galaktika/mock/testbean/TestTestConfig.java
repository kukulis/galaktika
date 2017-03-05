package lt.gt.galaktika.mock.testbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestTestConfig {
	public final static void main ( String [] args ) {
		ApplicationContext context = new AnnotationConfigApplicationContext( TestConfig.class );
		
		TestBean t = context.getBean( "testBean", TestBean.class ); //  
		System.out.println( "Received message from the testBean = " + t.getMessage() );
	}
}
