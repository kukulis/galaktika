package lt.gt.galaktika.engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lt.gt.galaktika.engine.SessionsManager;
import lt.gt.galaktika.engine.bot.UsersBot;
import lt.gt.galaktika.engine.work.GameService;

@Configuration
@ComponentScan(basePackages = { "lt.gt.galaktika.mock" })
public class AdditionalBeansConfig {
	@Bean 
	public SessionsManager getSessionsManager () {
		return new SessionsManager();
	}
	
	@Bean
	public GameService getTurnMaker() {
		return new GameService();
	}
	
	@Bean
	public UsersBot getUsersBot() {
		return new UsersBot();
	}
}
