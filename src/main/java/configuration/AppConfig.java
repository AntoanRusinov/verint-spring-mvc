package configuration;

import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { "controller", "repository.impl", "service", "exception" })
@PropertySource(value = { "classpath:application.properties", "classpath:testProps.properties",
		"classpath:cron.properties" })
public class AppConfig {

	@Autowired
	private Environment env;

	@Value(value = "${ala_bala}")
	private Integer testToShowHowValueAnnotationWorks;

	@Scheduled(cron = "${cronExpression}")
	// @Scheduled(fixedRate = 3000l)
	// @Scheduled(cron = "0/3 * * * * *")
	public void doSomethingScheduled() {
		System.out.println("I'm a scheduler... and I'm activated now " + new Date(System.currentTimeMillis()));
	}

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

	@Bean(name = "testingDataSource")
	@Profile(value = "testing")
	// this will be initialized only when "testing" profile is active
	public DataSource testDataSource() {

		System.out.println("PROFILE: TESTING");

		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).setScriptEncoding("UTF-8").
				addScript("schema.sql").addScript("test-data.sql").build();
	}

	@Bean(name = "productionDataSource")
	@Profile(value = "production")
	// use HikariCP for performance purpose
	// this will be default dataSource for production
	public DataSource dataSource() {

		System.out.println("PROFILE: PRODUCTION");

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
	@Autowired
	// @Qualifier must be removed in order to be ok 
	public LocalSessionFactoryBean sessionFactory(@Qualifier(value = "productionDataSource") DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
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

	@Profile("production")
	@Bean(name = "mySqlJdbcTemplate")
	@Autowired
	public JdbcTemplate mySqlJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
