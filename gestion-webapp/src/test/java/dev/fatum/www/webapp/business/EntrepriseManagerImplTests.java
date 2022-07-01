package dev.fatum.www.webapp.business;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.entities.Coordonnees;
import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.entities.Personne;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EntrepriseManagerImplTests {

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
	void enregistrerUneNouvelleEntreprise() {

		System.out.println("##### enregistrerUneNouvelPersonne ");

		// GIVEN
		// Création de l'entreprise et de ses coordonnées
		Entreprise entreprise = new Entreprise();
		String raisonSociale = "Raison sociale";
		String formeJuridique = "Forme juridique";
		String nature = "nature";
		entreprise.setRaisonSociale(raisonSociale);
		entreprise.setFormeJuridique(formeJuridique);
		entreprise.setNature(nature);

		Coordonnees coordonnees = new Coordonnees();
		String rue = "rue test";
		String codePostal = "12 345";
		String ville = "Tours";
		coordonnees.setRue(rue);
		coordonnees.setCodePostal(codePostal);
		coordonnees.setVille(ville);

		List<Coordonnees> coordonneesList = new ArrayList<>();
		coordonneesList.add(coordonnees);
		entreprise.setCoordonneesList(coordonneesList);

		// WHEN
		// Enregistrement de l'entreprise
		managerFactory.getEntrepriseManager().saveEntreprise(entreprise);
		// Chercher si la personne est dans la base de données
		Entreprise entrepriseTest = managerFactory.getEntrepriseManager().findEntrepriseById(entreprise.getId());
		Coordonnees coordonneesTest = entrepriseTest.getCoordonneesList().get(0);
		// Vérication des informations
		assertThat(entrepriseTest.getRaisonSociale()).isEqualTo(raisonSociale);
		assertThat(entrepriseTest.getFormeJuridique()).isEqualTo(formeJuridique);
		assertThat(entrepriseTest.getNature()).isEqualTo(nature);

		assertThat(coordonneesTest.getRue()).isEqualTo(rue);
		assertThat(coordonneesTest.getCodePostal()).isEqualTo(codePostal);
		assertThat(coordonneesTest.getVille()).isEqualTo(ville);
	}

	@Test
	void trouverToutesLesEntreprise() {

		System.out.println("##### trouverToutesLesEntreprise ");

		// GIVEN
		List<Entreprise> entrepriseList;

		// WHEN
		entrepriseList = managerFactory.getEntrepriseManager().findAllEntreprisesDesc();

		// THEN
		assertThat(entrepriseList).isNotNull();
	}

	@Test
	void trouverUneEntrepriseAvecSonIdentifiant() {

		System.out.println("##### trouverUneEntrepriseAvecSonIdentifiant ");

		// GIVEN
		// Entreprise
		String raisonSociale = "Bank s";
		String formeJuridique = "SAS";
		String nature = "Commerciale";
		// Coordonnees
		String rue = "Avenue dorée";
		String codePostal = "75 001";
		String ville = "Paris";
		// id entreprise
		int idEntreprise = 1;

		// WHEN
		// Chercher si la personne est dans la base de données
		Entreprise entreprise = managerFactory.getEntrepriseManager().findEntrepriseById(idEntreprise);
		Coordonnees coordonnees = entreprise.getCoordonneesList().get(0);

		// THEN
		// Vérication des informations
		assertThat(entreprise.getRaisonSociale()).isEqualTo(raisonSociale);
		assertThat(entreprise.getFormeJuridique()).isEqualTo(formeJuridique);
		assertThat(entreprise.getNature()).isEqualTo(nature);

		assertThat(coordonnees.getRue()).isEqualTo(rue);
		assertThat(coordonnees.getCodePostal()).isEqualTo(codePostal);
		assertThat(coordonnees.getVille()).isEqualTo(ville);

	}

	@Test
	void modifierUneEntreprise() {

		System.out.println("##### modifierUneEntreprise ");

		// GIVEN
		// Entreprise
		String raisonSociale = "Raison sociale";
		String formeJuridique = "Forme juridique";
		String nature = "nature";
		// Coordonnees
		String rue = "rue test";
		String codePostal = "12 345";
		String ville = "Tours";
		// id entreprise
		int idEntreprise = 2;

		// WHEN
		// Chercher si la personne est dans la base de données
		Entreprise entreprise = managerFactory.getEntrepriseManager().findEntrepriseById(idEntreprise);
		Coordonnees coordonnees = entreprise.getCoordonneesList().get(0);
		// Modification de la personne et de ses coordonnées
		entreprise.setRaisonSociale(raisonSociale);
		entreprise.setFormeJuridique(formeJuridique);
		entreprise.setNature(nature);

		coordonnees.setRue(rue);
		coordonnees.setCodePostal(codePostal);
		coordonnees.setVille(ville);

		managerFactory.getEntrepriseManager().updateEntreprise(entreprise);

		// THEN
		// Vérication des informations
		Entreprise entrepriseTest = managerFactory.getEntrepriseManager().findEntrepriseById(2);
		Coordonnees coordonneesTest = entrepriseTest.getCoordonneesList().get(0);

		assertThat(entrepriseTest.getRaisonSociale()).isEqualTo(raisonSociale);
		assertThat(entrepriseTest.getFormeJuridique()).isEqualTo(formeJuridique);
		assertThat(entrepriseTest.getNature()).isEqualTo(nature);

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
