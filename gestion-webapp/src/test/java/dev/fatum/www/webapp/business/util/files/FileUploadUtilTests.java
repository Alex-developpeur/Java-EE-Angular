package dev.fatum.www.webapp.business.util.files;

import dev.fatum.www.business.util.files.FileUploadUtil;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
class FileUploadUtilTests {

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
	void uploadImage() {

		System.out.println("##### trouverUnUtilisateurAvecSonIdentifiant ");

		// GIVEN
		String uploadDir = "documents/entreprise-2/img/";
		String fileName = "logo.png";
		MultipartFile multipartFile = new MockMultipartFile("documents/entreprise-1/img/logo.png", "Hello World".getBytes());

		// WHEN
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
