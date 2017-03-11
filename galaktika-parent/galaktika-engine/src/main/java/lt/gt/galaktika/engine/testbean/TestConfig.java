package lt.gt.galaktika.engine.testbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	@Bean(name="testBean")
	public TestBean getTestBean () {
		return new TestBean();
	}
}