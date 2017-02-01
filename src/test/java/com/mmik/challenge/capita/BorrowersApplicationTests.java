package com.mmik.challenge.capita;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowersApplicationTests {

	@Autowired
	private JdbcTemplate template;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testInitMigrationSettings() throws Exception {
		assertThat(this.template.queryForObject("SELECT COUNT(*) from BORROWERS",
				Integer.class)).isEqualTo(3);
	}

}
