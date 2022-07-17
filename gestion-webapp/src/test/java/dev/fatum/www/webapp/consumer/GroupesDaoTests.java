package dev.fatum.www.webapp.consumer;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.consumer.dao.*;
import dev.fatum.www.consumer.dao.documents.DevisDao;
import dev.fatum.www.consumer.dao.documents.FactureDao;
import dev.fatum.www.consumer.dao.documents.NumerosDocumentsDao;
import dev.fatum.www.model.entities.Groupes;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GroupesDaoTests {

	private static Instant startTime;

	private static Instant eachTime;

	@Autowired
	private GroupesDao groupesDao;

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
	void VerifierSi_ProfilDao_EstImplemente() {
//		Groupes groupes = groupesDao.nomDuplicated("USER", 1);
//		Groupes groupes = groupesDao.findByNom("USER");
		Groupes groupes = groupesDao.findById(1);
//		List<Groupes> groupes = groupesDao.findAllByOrderByIdDesc();
		System.out.println(groupes.getNom());
		assertThat(groupes).isNotNull();
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
