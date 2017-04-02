package lt.gt.galaktika.engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lt.gt.galaktika.engine.SessionsManager;
import lt.gt.galaktika.engine.work.TurnMaker;

@Configuration
@ComponentScan(basePackages = { "lt.gt.galaktika.mock" })
public class AdditionalBeansConfig {
	@Bean 
	public SessionsManager getSessionsManager () {
		return new SessionsManager();
	}
	
	@Bean
	public TurnMaker getTurnMaker() {
		return new TurnMaker();
	}
}
