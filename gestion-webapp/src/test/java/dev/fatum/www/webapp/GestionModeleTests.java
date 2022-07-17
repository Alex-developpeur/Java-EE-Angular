package dev.fatum.www.webapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/*
@WebMvcTest - for testing the controller layer
@JsonTest - for testing the JSON marshalling and unmarshalling
@DataJpaTest - for testing the repository layer
@RestClientTests - for testing REST clients
 */

@SpringBootTest
class GestionModeleTests {

	private static Instant startTime;

	private static Instant eachTime;

	@BeforeAll
	static void initAll() {
		System.out.println("##### Before All : " + Instant.now());
		startTime = Instant.now();
	}

	@BeforeEach
	void init() {
		System.out.println("##### Before Each");
		eachTime = Instant.now();
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
		Duration duration = Duration.between(eachTime, endedAt);
		System.out.println("##### After Each : " + duration.toString());
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("##### After All : " + Instant.now());
		Instant endTime = Instant.now();
		long duration = Duration.between(startTime, endTime).toMillis();
		System.out.println(MessageFormat.format("##### Durée des tests : {0} ms", duration));
	}
}
