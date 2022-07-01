package dev.fatum.www.webapp.business;

import static org.assertj.core.api.Assertions.assertThat;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.entities.Profil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
class ProfilManagerImplTests {

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
	void trouverUnProfilAvecSonEmail() {

		System.out.println("##### trouverUnProfilAvecSonEmail ");

		// GIVEN
		String nom = "Robert";
		String email = "robert@example.com";
		String mdp = "$2a$10$cZJ.MxGi00YnJO8ds3SJV.xHozpz2sJYru3mnInQnu0YhXubXRnmi";
		String groupe = "USER";

		// WHEN
		Profil profil = managerFactory.getProfilManager().getProfilByEmail("robert@example.com");

		// THEN
		assertThat(profil.getNom()).isEqualTo(nom);
		assertThat(profil.getEmail()).isEqualTo(email);
		assertThat(profil.getMdp()).isEqualTo(mdp);
		assertThat(profil.getGroupesSet().iterator().next().getNom()).isEqualTo(groupe);
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
