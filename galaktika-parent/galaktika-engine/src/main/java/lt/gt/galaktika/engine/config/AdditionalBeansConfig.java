package lt.gt.galaktika.engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lt.gt.galaktika.engine.SessionsManager;

@Configuration
@ComponentScan(basePackages = { "lt.gt.galaktika.mock" })
public class AdditionalBeansConfig {
	@Bean 
	public SessionsManager getSessionsManager () {
		return new SessionsManager();
	}
}
