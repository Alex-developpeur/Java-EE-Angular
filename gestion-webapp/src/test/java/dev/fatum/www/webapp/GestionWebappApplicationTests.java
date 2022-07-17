package dev.fatum.www.webapp;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
class GestionWebappApplicationTests {

	private static Instant startTime;

	@BeforeAll
	static void initAll() {
		System.out.println("Before All : " + Instant.now());
	}

	@BeforeEach
	void init() {
		System.out.println("Before Each");
		startTime = Instant.now();
	}

	@Test
	void contextLoads() {
		assumeTrue("abc".contains("abc"));
//		fail("test should have been aborted");
	}

	@Test
	void testTests() {
		assumeTrue("abc".contains("abc"));
//		fail("test should have been aborted");
	}

	@AfterEach
	void tearDown() {
		Instant endedAt = Instant.now();
		Duration duration = Duration.between(startTime, endedAt);
		System.out.println("After Each : " + duration.toString());
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("After All : " + Instant.now());
	}
}
