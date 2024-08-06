package com.mmik.challenge.capita;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BorrowersApplicationTests {

	@Autowired
	private JdbcTemplate template;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testInitMigrationSettings() throws Exception {
		int count = this.template.queryForObject("select count(*) from borrower",
				Integer.class);
		assertEquals(count, 3);
	}

}
