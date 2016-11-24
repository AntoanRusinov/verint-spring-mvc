package configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = { "controller", "repository.impl", "service", "exception" })
@PropertySource(value = { "classpath:application.properties", "classpath:testProps.properties" })
public class AppConfig {

	@Value(value = "${ala_bala}")
	private Integer testToShowHowValueAnnotationWorks;

	@Autowired
	private Environment env;

	// so that Spring @Value know how to interpret ${}
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	protected Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.globally_quoted_identifiers", "true");
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.generate-ddl", env.getProperty("hibernate.generate-ddl"));
		properties.setProperty("hibernate.naming_strategy", env.getProperty("hibernate.naming_strategy"));
		properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
		// properties.setProperty("hibernate.cache.use_second_level_cache",
		// env.getProperty("hibernate.cache.use_second_level_cache"));
		// properties.setProperty("hibernate.cache.region.factory_class",
		// env.getProperty("hibernate.cache.region.factory_class"));
		// properties.setProperty("hibernate.cache.use_query_cache",
		// env.getProperty("hibernate.cache.use_query_cache"));
		// properties.setProperty("hibernate.cache.use_minimal_puts",
		// env.getProperty("hibernate.cache.use_minimal_puts"));
		// properties.setProperty("hibernate.connection.autocommit",
		// env.getProperty("hibernate.connection.autocommit"));

		// just to show @Value working AF
		System.out.println("ALA BALAAA value: " + testToShowHowValueAnnotationWorks);

		return properties;
	}

	@Bean
	// use HikariCP for performance purpose
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setPoolName("springHikariCP");
		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setDriverClassName(env.getProperty("mysql.driver-class-name"));
		hikariConfig.setJdbcUrl(env.getProperty("mysql.url"));
		hikariConfig.setUsername(env.getProperty("mysql.username"));
		hikariConfig.setPassword(env.getProperty("mysql.password"));
		hikariConfig.addDataSourceProperty("cachePrepStmts", env.getProperty("hikari.cachePrepStmts"));
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", env.getProperty("hikari.prepStmtCacheSize"));
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", env.getProperty("hikari.prepStmtCacheSqlLimit"));
		hikariConfig.addDataSourceProperty("useServerPrepStmts", env.getProperty("hikari.useServerPrepStmts"));
		return new HikariDataSource(hikariConfig);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
