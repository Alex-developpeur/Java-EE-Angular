package dev.fatum.www.webapp.business;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.entities.Administrateur;
import dev.fatum.www.model.entities.Profil;
import dev.fatum.www.model.entities.Utilisateur;
import dev.fatum.www.model.exceptions.ResourceAlreadyExists;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdministrateurManagerImplTests {

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
	void enregistrerNouvelAdministrateur_groupe_ADMIN() throws ResourceAlreadyExists {

		System.out.println("##### enregistrerNouvelAdministrateur_groupe_ADMIN ");

		// GIVEN
		// Création de l'utilisateur
		Administrateur administrateur = new Administrateur();
		String nom = "alex";
		String email = "alex@example.com";
		String mpd = "123456";
		String groupe = "ADMIN";
		administrateur.setNom(nom);
		administrateur.setEmail(email);
		administrateur.setMdp(mpd);

		// WHEN
		// Enregistrer l'utilisateur dans la base de données
		managerFactory.getAdministrateurManager().saveAdministrateur(administrateur);

		// THEN
		// Chercher l'utilisateur dans la base de données
		Administrateur found = managerFactory.getAdministrateurManager()
				.findAdministrateurById(administrateur.getId());
		assertThat(found.getNom()).isEqualTo(nom);
		assertThat(found.getEmail()).isEqualTo(email);
		assertThat(found.getMdp()).isEqualTo(administrateur.getMdp());
		// Vérifier que le groupe de l'utilisateur soit ADMIN
		assertThat(found.getGroupesSet().iterator().next().getNom()).isEqualTo(groupe);
	}

	@Test
	void trouverToutLesAdministrateur() {

		System.out.println("##### trouverToutLesAdministrateur ");

		// GIVEN
		List<Administrateur> administrateurList;

		// WHEN
		administrateurList = managerFactory.getAdministrateurManager().findAllAdministrateursDesc();

		// THEN
		assertThat(administrateurList).isNotNull();
	}

	@Test
	void trouverUnUtilisateurAvecSonIdentifiant() {

		System.out.println("##### trouverUnUtilisateurAvecSonIdentifiant ");

		// GIVEN
		String nom = "Robert";
		String email = "robert@example.com";
		String mpd = "$2a$10$cZJ.MxGi00YnJO8ds3SJV.xHozpz2sJYru3mnInQnu0YhXubXRnmi";

		int idUtilisateur = 1;

		// WHEN
		Utilisateur found = managerFactory.getUtilisateurManager().findUtilisateurById(1);

		// THEN
		assertThat(found.getNom()).isEqualTo(nom);
		assertThat(found.getEmail()).isEqualTo(email);
		assertThat(found.getMdp()).isEqualTo(mpd);
	}

	@Test
	void modifierUnAdministarteur() throws ResourceAlreadyExists {

		System.out.println("##### modifierUnAdministarteur ");

		// GIVEN
		// Recherche de l'utilisateur
		String nom = "test";
		String email = "modifadmin@example.com";
		String mpd = "123456";
		String groupe = "ADMIN";
		int idAdministrateur = 6;
		Administrateur administrateur = managerFactory.getAdministrateurManager().findAdministrateurById(idAdministrateur);
		administrateur.setNom(nom);
		administrateur.setEmail(email);
		administrateur.setMdp(mpd);

		// WHEN
		// Enregistrer l'utilisateur dans la base de données
		managerFactory.getAdministrateurManager().updateAdministrateur(administrateur);

		// THEN
		// Chercher l'utilisateur dans la base de données
		Administrateur found = managerFactory.getAdministrateurManager()
				.findAdministrateurById(idAdministrateur);

		assertThat(found.getNom()).isEqualTo(nom);
		assertThat(found.getEmail()).isEqualTo(email);
		assertThat(found.getMdp()).isEqualTo(administrateur.getMdp());
		// Vérifier que le groupe de l'utilisateur soit ADMIN
		assertThat(found.getGroupesSet().iterator().next().getNom()).isEqualTo(groupe);
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
