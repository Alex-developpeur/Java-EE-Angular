package dev.fatum.www.webapp.business;

import static org.assertj.core.api.Assertions.assertThat;

import dev.fatum.www.business.ManagerFactory;
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

@SpringBootTest
class UtilisateurManagerImplTests {

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
	void enregistrerNouvelUtilisateur_groupe_USER() throws ResourceAlreadyExists {

		System.out.println("##### enregistrerNouvelUtilisateur_groupe_USER ");

		// GIVEN
		// Création de l'utilisateur
		Utilisateur utilisateur = new Utilisateur();
		String nom = "alex";
		String email = "testalex@example.com";
		String mpd = "123456";
		String groupe = "USER";
		utilisateur.setNom(nom);
		utilisateur.setEmail(email);
		utilisateur.setMdp(mpd);

		// WHEN
		// Enregistrer l'utilisateur dans la base de données
		managerFactory.getUtilisateurManager().saveUtilisateur(utilisateur);

		// THEN
		// Chercher l'utilisateur dans la base de données
		Utilisateur found = managerFactory.getUtilisateurManager().findUtilisateurById(utilisateur.getId());
		assertThat(found.getNom()).isEqualTo(nom);
		assertThat(found.getEmail()).isEqualTo(email);
		assertThat(found.getMdp()).isEqualTo(utilisateur.getMdp());
		// Vérifier que le groupe de l'utilisateur soit USER
		assertThat(found.getGroupesSet().iterator().next().getNom()).isEqualTo(groupe);
	}

	@Test
	void trouverToutLesUtilisateurs() {

		System.out.println("##### trouverToutsLesUtilisateurs ");

		// GIVEN
		List<Utilisateur> utilisateurList;

		// WHEN
		utilisateurList = managerFactory.getUtilisateurManager().findAllUtilisateursDesc();

		// THEN
		assertThat(utilisateurList).isNotNull();
	}

	@Test
	void trouverUnUtilisateurAvecSonIdentifiant() {

		System.out.println("##### trouverUnUtilisateurAvecSonIdentifiant ");

		// GIVEN
		String nom = "Robert";
		String email = "robert@example.com";
		String mdp = "$2a$10$cZJ.MxGi00YnJO8ds3SJV.xHozpz2sJYru3mnInQnu0YhXubXRnmi";
		int idUtilisateur = 1;

		// WHEN
		Utilisateur found = managerFactory.getUtilisateurManager().findUtilisateurById(idUtilisateur);

		// THEN
		assertThat(found.getNom()).isEqualTo(nom);
		assertThat(found.getEmail()).isEqualTo(email);
		assertThat(found.getMdp()).isEqualTo(mdp);
	}

	@Test
	void modifierUnUtilisateur() throws ResourceAlreadyExists {

		System.out.println("##### modifierUnUtilisateur ");

		// GIVEN
		// Recherche de l'utilisateur
		String nom = "test";
		String email = "testuser@example.com";
		String mpd = "123456";
		String groupe = "USER";
		Utilisateur utilisateur = managerFactory.getUtilisateurManager().findUtilisateurById(3);
		utilisateur.setNom(nom);
		utilisateur.setEmail(email);
		utilisateur.setMdp(mpd);

		// WHEN
		// Enregistrer l'utilisateur dans la base de données
		managerFactory.getUtilisateurManager().updateUtilisateur(utilisateur);

		// THEN
		// Chercher l'utilisateur dans la base de données
		Utilisateur found = managerFactory.getUtilisateurManager().findUtilisateurById(utilisateur.getId());

		assertThat(found.getNom()).isEqualTo(nom);
		assertThat(found.getEmail()).isEqualTo(email);
		assertThat(found.getMdp()).isEqualTo(utilisateur.getMdp());
		// Vérifier que le groupe de l'utilisateur soit USER
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
