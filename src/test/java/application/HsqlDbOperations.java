package application;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@ActiveProfiles("testing")

// @WebAppConfiguration
public class HsqlDbOperations {
	//
	// @Before
	// public void setUp() {
	// EmbeddedDatabase db = new
	// EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql")
	// .build();
	// }

	@Test
	// @Timed(millis = 200)
	// @Commit
	public void commitWithoutRollback() {
		assertTrue(true);
	}

	// @Test
	// // @Rollback
	// // @Repeat(value = 10)
	// // @Timed(millis = 500)
	// public void rollback() {
	// assertTrue(true);
	// }

}