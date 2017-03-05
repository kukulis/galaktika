package lt.gt.galaktika.mock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lt.gt.galaktika.mock.SessionsManager;

@Configuration
@ComponentScan(basePackages = { "lt.gt.galaktika.mock" })
public class AdditionalBeansConfig {
	@Bean 
	public SessionsManager getSessionsManager () {
		return new SessionsManager();
	}
}
