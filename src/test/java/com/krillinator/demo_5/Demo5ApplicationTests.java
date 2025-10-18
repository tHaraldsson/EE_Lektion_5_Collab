package com.krillinator.demo_5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class) // Postgres container will automatically spin up
@SpringBootTest
class Demo5ApplicationTests {

	@Test
	void contextLoads() {
	}

}
