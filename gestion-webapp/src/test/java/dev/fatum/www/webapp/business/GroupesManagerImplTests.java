package dev.fatum.www.webapp.business;

import static org.assertj.core.api.Assertions.assertThat;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.entities.Groupes;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@SpringBootTest
class GroupesManagerImplTests {

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
	void enregistrerUnNouveauGroupe() throws ResourceAlreadyExists {

		System.out.println("##### enregistrerUnNouveauGroupe ");

		// GIVEN
		// Enregistrement du groupe
		String nom = "CUSTUMER";
		String description = "Groupe des clients";
		Groupes groupes = new Groupes();
		groupes.setNom(nom);
		groupes.setDescription(description);

		// WHEN
		managerFactory.getGroupesManager().saveGroupe(groupes);

		// THEN
		// Chercher le groupe dans la base de données
		Groupes groupesTest =  managerFactory.getGroupesManager().findGroupeById(groupes.getId());
		assertThat(groupesTest.getNom()).isEqualTo(nom);
		assertThat(groupesTest.getDescription()).isEqualTo(description);
	}

	@Test
	void trouverToutLesGroupes() {

		System.out.println("##### trouverToutLesGroupes ");

		List<Groupes> groupesList = managerFactory.getGroupesManager().findAllGroupesDesc();

		assertThat(groupesList).isNotNull();
	}

	@Test
	void trouverUnGroupeAvecSonIdentifiant() {

		System.out.println("##### trouverUnGroupeAvecSonIdentifiant ");

		// GIVEN
		String nom = "USER";
		String description = "Utilisateurs du site.";
		int idGroupe = 1;

		// WHEN
		Groupes groupes = managerFactory.getGroupesManager().findGroupeById(idGroupe);

		// THEN
		assertThat(groupes.getNom()).isEqualTo(nom);
		assertThat(groupes.getDescription()).isEqualTo(description);
	}

	@Test
	void modifierUnGroupe() throws ResourceAlreadyExists {

		System.out.println("##### modifierUnGroupe ");

		// GIVEN
		int idGroupe = 1;

		// WHEN
		// Modification du groupe
		Groupes groupes = managerFactory.getGroupesManager().findGroupeById(idGroupe);
		String nom = "VISITOR";
		String description = "Groupe des visiteurs";
		groupes.setNom(nom);
		groupes.setDescription(description);
		managerFactory.getGroupesManager().updateGroupe(groupes);

		// THEN
		// Chercher le groupe dans la base de données
		Groupes groupesTest =  managerFactory.getGroupesManager().findGroupeById(groupes.getId());
		// Vérification
		assertThat(groupesTest.getNom()).isEqualTo(nom);
		assertThat(groupesTest.getDescription()).isEqualTo(description);
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
