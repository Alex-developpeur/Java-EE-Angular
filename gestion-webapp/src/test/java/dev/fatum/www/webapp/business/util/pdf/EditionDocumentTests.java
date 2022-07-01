package dev.fatum.www.webapp.business.util.pdf;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.business.util.pdf.EditionDocument;
import dev.fatum.www.model.entities.documents.Devis;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EditionDocumentTests {

	private static Instant startTime;

	private static Instant eachTime;

	@Autowired
	private ManagerFactory managerFactory;

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
	void creationDunDevis() throws IOException {

		System.out.println("##### creationDunDevis ");

		// GIVEN
		int idDevis = 1;

		// WHEN
		Devis devis = managerFactory.getDevisManager().findDevisById(idDevis);

		// THEN
		EditionDocument editionDocument = new EditionDocument(devis);
		assertThat(editionDocument).isNotNull();
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
