package dev.fatum.www.webapp.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import dev.fatum.www.consumer.dao.*;
import dev.fatum.www.consumer.dao.documents.DevisDao;
import dev.fatum.www.consumer.dao.documents.FactureDao;
import dev.fatum.www.consumer.dao.documents.NumerosDocumentsDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ImplementDaoTests {

	private static Instant startTime;

	private static Instant eachTime;

	@Autowired
	private DevisDao devisDao;

	@Autowired
	private FactureDao factureDao;

	@Autowired
	private NumerosDocumentsDao numerosDocumentsDao;

	@Autowired
	private PersonneDao personneDao;

	@Autowired
	private EntrepriseDao entrepriseDao;

	@Autowired
	private GroupesDao groupesDao;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private AdministrateurDao administrateurDao;

	@Autowired
	private ProfilDao profilDao;

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
	void VerifierSi_DevisDao_EstImplemente() {
		assertThat(devisDao).isNotNull();
	}

	@Test
	void VerifierSi_FactureDao_EstImplemente() {
		assertThat(factureDao).isNotNull();
	}

	@Test
	void VerifierSi_NumerosDocumentsDao_EstImplemente() {
		assertThat(numerosDocumentsDao).isNotNull();
	}

	@Test
	void VerifierSi_PersonneDao_EstImplemente() {
		assertThat(personneDao).isNotNull();
	}

	@Test
	void VerifierSi_EntrepriseDao_EstImplemente() {
		assertThat(entrepriseDao).isNotNull();
	}

	@Test
	void VerifierSi_GroupesDao_EstImplemente() {
		assertThat(groupesDao).isNotNull();
	}

	@Test
	void VerifierSi_UtilisateurDao_EstImplemente() {
		assertThat(utilisateurDao).isNotNull();
	}

	@Test
	void VerifierSi_AdministrateurDao_EstImplemente() {
		assertThat(administrateurDao).isNotNull();
	}

	@Test
	void VerifierSi_ProfilDao_EstImplemente() {
		assertThat(profilDao).isNotNull();
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
