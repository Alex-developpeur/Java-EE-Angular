package dev.fatum.www.webapp.business;

import static org.assertj.core.api.Assertions.assertThat;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.entities.Coordonnees;
import dev.fatum.www.model.entities.Personne;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@SpringBootTest
class PersonneManagerImplTests {

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
	void enregistrerUneNouvellePersonne() {

		System.out.println("##### enregistrerUneNouvellePersonne ");

		// GIVEN
		// Création de la personne et de ses coordonnées
		Personne personne = new Personne();
		String civilite = "Mlle";
		String nom = "alex";
		String prenom = "terrieur";
		personne.setCivilite(civilite);
		personne.setNom(nom);
		personne.setPrenom(prenom);

		Coordonnees coordonnees = new Coordonnees();
		String rue = "rue test";
		String codePostal = "12 345";
		String ville = "Tours";
		coordonnees.setRue(rue);
		coordonnees.setCodePostal(codePostal);
		coordonnees.setVille(ville);

		personne.setCoordonnees(coordonnees);

		// WHEN
		// Enregistrement de la personne
		managerFactory.getPersonneManager().savePersonne(personne);

		// THEN
		// Chercher si la personne est dans la base de données
		Personne personneTest = managerFactory.getPersonneManager().findPersonneById(personne.getId());
		Coordonnees coordonneesTest = personneTest.getCoordonnees();
		// Vérication des informations
		assertThat(personneTest.getCivilite()).isEqualTo(civilite);
		assertThat(personneTest.getNom()).isEqualTo(nom);
		assertThat(personneTest.getPrenom()).isEqualTo(prenom);

		assertThat(coordonneesTest.getRue()).isEqualTo(rue);
		assertThat(coordonneesTest.getCodePostal()).isEqualTo(codePostal);
		assertThat(coordonneesTest.getVille()).isEqualTo(ville);
	}

	@Test
	void trouverToutesLesPersonnes() {

		System.out.println("##### trouverToutesLesPersonnes ");

		// GIVEN
		List<Personne> personneList;

		// WHEN
		personneList = managerFactory.getPersonneManager().findAllPersonnesDesc();

		// THEN
		assertThat(personneList).isNotNull();
	}

	@Test
	void trouverUnePersonneAvecSonIdentifiant() {

		System.out.println("##### trouverUnePersonneAvecSonIdentifiant ");

		// GIVEN
		int idPersone = 1;
		String civilite = "Mlle";
		String nom = "PHILO";
		String prenom = "Sophie";

		// WHEN
		Personne personne = managerFactory.getPersonneManager().findPersonneById(idPersone);

		// THEN
		assertThat(personne.getCivilite()).isEqualTo(civilite);
		assertThat(personne.getNom()).isEqualTo(nom);
		assertThat(personne.getPrenom()).isEqualTo(prenom);
	}

	@Test
	void modifierUnePersonne() {

		System.out.println("##### modifierUnePersonne ");

		// GIVEN
		int idPersone = 1;
		String civilite = "Mlle";
		String nom = "PHILO";
		String prenom = "Sophie";

		String rue = "6 rue des méditation.";
		String codePostal = "37 520";
		String ville = "Décartes";

		// Chercher si la personne est dans la base de données
		Personne personne = managerFactory.getPersonneManager().findPersonneById(1);
		Coordonnees coordonnees = personne.getCoordonnees();

		// WHEN
		// Modification de la personne et de ses coordonnées
		personne.setCivilite(civilite);
		personne.setNom(nom);
		personne.setPrenom(prenom);

		coordonnees.setRue(rue);
		coordonnees.setCodePostal(codePostal);
		coordonnees.setVille(ville);

		managerFactory.getPersonneManager().updatePersonne(personne);

		// THEN
		// Vérication des informations
		Personne personneTest = managerFactory.getPersonneManager().findPersonneById(1);
		Coordonnees coordonneesTest = personneTest.getCoordonnees();

		assertThat(personneTest.getCivilite()).isEqualTo(civilite);
		assertThat(personneTest.getNom()).isEqualTo(nom);
		assertThat(personneTest.getPrenom()).isEqualTo(prenom);

		assertThat(coordonneesTest.getRue()).isEqualTo(rue);
		assertThat(coordonneesTest.getCodePostal()).isEqualTo(codePostal);
		assertThat(coordonneesTest.getVille()).isEqualTo(ville);
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
