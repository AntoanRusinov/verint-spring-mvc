package configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hsqldb.persist.HsqlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "controller" })
@EnableTransactionManagement
public class AppConfig {

	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("classpath:schema.sql")
				.addScript("classpath:test-data.sql").build();
	}

	@Bean
	public HsqlProperties hsqlProperties() {
		
		//get properties from application.properties via environment 
		
		HsqlProperties properties = new HsqlProperties();
		properties.setProperty("user", "");
		properties.setProperty("password", "");
		properties.setProperty("check_props", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("jpaVendorAdapter", "org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter");
		properties.setProperty("driverClassName", "org.hsqldb.jdbcDriver");
		properties.setProperty("hsql.driver", "org.hsqldb.jdbcDriver");
		properties.setProperty("jpa.database", "test");
		return properties;
	}

	@Bean
//	@Required
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			Properties hsqlProperties) {
		LocalContainerEntityManagerFactoryBean localEntityManager = new LocalContainerEntityManagerFactoryBean();
		localEntityManager.setDataSource(dataSource);
		localEntityManager.setPackagesToScan(new String[] { "model" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		localEntityManager.setJpaVendorAdapter(vendorAdapter);
		localEntityManager.setJpaProperties(hsqlProperties);
		return localEntityManager;
	}

	@Bean
//	@Required
	public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

		return jpaTransactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
