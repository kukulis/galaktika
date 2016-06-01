package lt.gt.galaktika.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan({ "lt.gt.galaktika.web", "lt.gt.galaktika.model" })
public class GalaktikaApplication
{

	// @Autowired
	// private UserDao _userDao;

	@RequestMapping("/resource") // TODO move to controller
	public Map<String, Object> home ()
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World grybai");
		return model;
	}

	@RequestMapping("/vartotojas") // TODO move to controller ?
	public Principal vartotojas ( Principal user )
	{
		return user;
	}

	// @RequestMapping("/get-by-email")
	// public User userByEmail ( @RequestParam(value = "email", defaultValue =
	// "" ) String email)
	// {
	// try
	// {
	// User user = _userDao.getByEmail(email);
	// return user;
	// } catch (Exception ex)
	// {
	// return new User();
	// }
	// }

	public static void main ( String[] args )
	{
		SpringApplication.run(GalaktikaApplication.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter
	{
		@Autowired
		@Qualifier("customUserDetailsService")
		UserDetailsService userDetailsService;

		@Override
		protected void configure ( HttpSecurity http ) throws Exception
		{
			http.httpBasic().and().authorizeRequests().antMatchers("/index.html", "/home.html", "/login.html", "/")
					.permitAll().anyRequest().authenticated().and()
					.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf()
					.csrfTokenRepository(csrfTokenRepository());
		}

		private CsrfTokenRepository csrfTokenRepository ()
		{
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}

		@Autowired
		public void configureGlobal ( AuthenticationManagerBuilder auth ) throws Exception
		{

			auth.userDetailsService(userDetailsService);
			// auth
			// .inMemoryAuthentication()
			// .withUser("user").password("password").roles("USER");

			// auth.jdbcAuthentication().
		}
	}

	@Configuration
	@EnableTransactionManagement
	public static class DatabaseConfig
	{

		@Value("${db.driver}")
		private String DB_DRIVER;

		@Value("${db.password}")
		private String DB_PASSWORD;

		@Value("${db.url}")
		private String DB_URL;

		@Value("${db.username}")
		private String DB_USERNAME;

		@Value("${hibernate.dialect}")
		private String HIBERNATE_DIALECT;

		@Value("${hibernate.show_sql}")
		private String HIBERNATE_SHOW_SQL;

		@Value("${hibernate.hbm2ddl.auto}")
		private String HIBERNATE_HBM2DDL_AUTO;

		@Value("${entitymanager.packagesToScan}")
		private String[] ENTITYMANAGER_PACKAGES_TO_SCAN;

		@Bean
		public DataSource dataSource ()
		{
			System.out.println("--- DatabaseConfig.dataSource DB_DRIVER=" + DB_DRIVER);
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(DB_DRIVER);
			dataSource.setUrl(DB_URL);
			dataSource.setUsername(DB_USERNAME);
			dataSource.setPassword(DB_PASSWORD);
			return dataSource;
		}

		@Bean
		public LocalSessionFactoryBean sessionFactory ()
		{
			LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
			sessionFactoryBean.setDataSource(dataSource());

			for (String pack : ENTITYMANAGER_PACKAGES_TO_SCAN)
			{
				System.out.println("!!!! pack=" + pack);
			}

			sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);

			Properties hibernateProperties = new Properties();
			hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
			hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
			hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
			sessionFactoryBean.setHibernateProperties(hibernateProperties);

			return sessionFactoryBean;
		}

		@Bean
		public HibernateTransactionManager transactionManager ()
		{
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory().getObject());
			return transactionManager;
		}

	} // class DatabaseConfig
}
