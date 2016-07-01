package lt.gt.galaktika.model.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// čia pas mane
@Configuration
//@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class JpaTestConfig
{
	
	/*
	 * 
	 * db.driver: com.mysql.jdbc.Driver
db.url: jdbc:mysql://localhost/galaktika
db.username: galuser
db.password: galpass

# Hibernate
hibernate.dialect: org.hibernate.dialect.MySQL5Dialect
hibernate.show_sql: true
hibernate.hbm2ddl.auto: update
entitymanager.packagesToScan: lt.gt.galaktika.model.entity
	 * 
	 */

	@Value("com.mysql.jdbc.Driver")
	private String DB_DRIVER;

	@Value("jdbc:mysql://localhost/galaktika")
	private String DB_URL;

	@Value("galuser")
	private String DB_USERNAME;

	@Value("galpass")
	private String DB_PASSWORD;


	@Value("org.hibernate.dialect.MySQL5Dialect")
	private String HIBERNATE_DIALECT;

	@Value("true")
	private String HIBERNATE_SHOW_SQL;

	@Value("update")
	private String HIBERNATE_HBM2DDL_AUTO;

	@Value("lt.gt.galaktika.model.entity")
	private String[] ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Bean
	public DataSource dataSource ()
	{

//		DriverManagerDataSource ds = new DriverManagerDataSource();
//
//		ds.setDriverClassName("org.hsqldb.jdbcDriver");
//		ds.setUrl("jdbc:hsqldb:mem:testdb");
//		ds.setUsername("sa");
//		ds.setPassword("");
//
//		return ds;
		
		System.out.println("--- DatabaseConfig.dataSource DB_DRIVER=" + DB_DRIVER);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean ()
	{

		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();

		lcemfb.setDataSource(this.dataSource());
//		lcemfb.setPackagesToScan(new String[] { "com.jverstry" });
		lcemfb.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		lcemfb.setPersistenceUnitName("MyTestPU");

		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		lcemfb.setJpaVendorAdapter(va);

		Properties ps = new Properties();
//		ps.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
//		ps.put("hibernate.hbm2ddl.auto", "create");
		ps.put("hibernate.dialect", HIBERNATE_DIALECT);
		ps.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		ps.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		lcemfb.setJpaProperties(ps);

		lcemfb.afterPropertiesSet();

		return lcemfb;

	}

	@Bean
	public PlatformTransactionManager transactionManager ()
	{
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(this.entityManagerFactoryBean().getObject());
		return tm;

	}
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation ()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
