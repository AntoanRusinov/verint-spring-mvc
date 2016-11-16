package application;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import configuration.AppConfig;

@WebAppConfiguration
// @ActiveProfiles(profiles = {"dev"})
// @RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class HsqlDbOperations {

	@Before
	public void setUp() {
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql")
				.build();
	}

	@Test
	// @Timed(millis = 200)
	// @Commit
	public void commitWithoutRollback() {
		assertTrue(true);
	}

	@org.junit.Test
	// @Rollback
	// @Repeat(value = 10)
	// @Timed(millis = 500)
	public void rollback() {
		assertTrue(true);
	}

}