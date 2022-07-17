package dev.fatum.www.webapp.business.documents;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.entities.documents.Facture;
import dev.fatum.www.model.entities.documents.LigneFacture;
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
class FactureManagerImplTests {

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
	void enregistrerUneNouveauFacture() {

		System.out.println("##### enregistrerUneNouveauFacture ");

		// GIVEN
		// Création de la facture
		Facture facture = new Facture();
		String numeroFacture = "Numero facture";
		String dateEmission = "Date emission";
		String dateReglement = "Date réglement";
		String modeDeReglement = "Mode de reglement";
		double tva = 20;
		double acompte = 100;
		String etatFacture = "Etat facture";
		facture.setNumeroFacture(numeroFacture);
		facture.setDateEmission(dateEmission);
		facture.setDateReglement(dateReglement);
		facture.setModeDeReglement(modeDeReglement);
		facture.setTva(tva);
		facture.setAcompte(acompte);
		facture.setEtatFacture(etatFacture);

		LigneFacture ligneFacture = new LigneFacture();
		String designation = "Designation";
		double quantite = 2;
		double prixUnitaire = 50;
		ligneFacture.setDesignation(designation);
		ligneFacture.setQuantite(quantite);
		ligneFacture.setPrixUnitaire(prixUnitaire);

		List<LigneFacture> ligneFactureList = new ArrayList<>();
		ligneFactureList.add(ligneFacture);
		facture.setLigneFactureList(ligneFactureList);

		// WHEN
		// Enregistrement de la facture
		managerFactory.getFactureManager().saveFacture(facture);

		// THEN
		// Chercher si la facture est dans la base de données
		Facture factureTest = managerFactory.getFactureManager().findFactureById(facture.getId());
		LigneFacture ligneFactureTest = factureTest.getLigneFactureList().get(0);
		// Vérication des informations
		assertThat(factureTest.getNumeroFacture()).isEqualTo(numeroFacture);
		assertThat(factureTest.getDateEmission()).isEqualTo(dateEmission);
		assertThat(factureTest.getDateReglement()).isEqualTo(dateReglement);
		assertThat(factureTest.getModeDeReglement()).isEqualTo(modeDeReglement);
		assertThat(factureTest.getTva()).isEqualTo(tva);
		assertThat(factureTest.getAcompte()).isEqualTo(acompte);
		assertThat(factureTest.getEtatFacture()).isEqualTo(etatFacture);

		assertThat(ligneFactureTest.getDesignation()).isEqualTo(designation);
		assertThat(ligneFactureTest.getQuantite()).isEqualTo(quantite);
		assertThat(ligneFactureTest.getPrixUnitaire()).isEqualTo(prixUnitaire);

	}

	@Test
	void trouverToutesLesFactures() {

		System.out.println("##### trouverToutesLesFactures ");

		// GIVEN
		List<Facture> factureList;

		// WHEN
		factureList = managerFactory.getFactureManager().findAllFacturesDesc();

		// THEN
		assertThat(factureList).isNotNull();
	}

	@Test
	void trouverUneFactureAvecSonIdentifiant() {

		System.out.println("##### trouverUneFactureAvecSonIdentifiant ");

		// GIVEN
		int idFacture = 1;
		// Valeurs à vérifier
		String numeroFacture = "F2021-0001";
		String dateEmission = "30 04 2021";
		String dateReglement = "30 04 2021";
		String modeDeReglement = "Carte bleu";
		double tva = 20;
		double acompte = 50;
		String etatFacture = "Payée";

		String designation = "Tours";
		double quantite = 2;
		double prixUnitaire = 200;

		// WHEN
		// Chercher si la facture est dans la base de données
		Facture facture = managerFactory.getFactureManager().findFactureById(idFacture);
		LigneFacture ligneFacture = facture.getLigneFactureList().get(0);

		// THEN
		// Vérication des informations
		assertThat(facture.getNumeroFacture()).isEqualTo(numeroFacture);
		assertThat(facture.getDateEmission()).isEqualTo(dateEmission);
		assertThat(facture.getDateReglement()).isEqualTo(dateReglement);
		assertThat(facture.getModeDeReglement()).isEqualTo(modeDeReglement);
		assertThat(facture.getTva()).isEqualTo(tva);
		assertThat(facture.getAcompte()).isEqualTo(acompte);
		assertThat(facture.getEtatFacture()).isEqualTo(etatFacture);

		assertThat(ligneFacture.getDesignation()).isEqualTo(designation);
		assertThat(ligneFacture.getQuantite()).isEqualTo(quantite);
		assertThat(ligneFacture.getPrixUnitaire()).isEqualTo(prixUnitaire);

	}

	@Test
	void modifierUnFacture() {

		System.out.println("##### modifierUnFacture ");

		// GIVEN
		int idFacture = 1;
		// informations facture
		String numeroFacture = "Numero facture modif";
		String dateEmission = "Date emission modif";
		String modeDeReglement = "Mode de reglement modif";
		double tva = 200;
		double acompte = 1000;
		String etatFacture = "Etat facture modif";
		// informations ligne de facture
		String designation = "Designation modif";
		double quantite = 20;
		double prixUnitaire = 500;

		// WHEN
		// Chercher si la personne est dans la base de données
		Facture facture = managerFactory.getFactureManager().findFactureById(idFacture);
		LigneFacture ligneFacture = facture.getLigneFactureList().get(0);
		// Valeurs à vérifier
		// informations facture
		facture.setNumeroFacture(numeroFacture);
		facture.setDateEmission(dateEmission);
		facture.setModeDeReglement(modeDeReglement);
		facture.setTva(tva);
		facture.setAcompte(acompte);
		facture.setEtatFacture(etatFacture);
		// informations ligne de facture
		ligneFacture.setDesignation(designation);
		ligneFacture.setQuantite(quantite);
		ligneFacture.setPrixUnitaire(prixUnitaire);

		// THEN
		// Vérication des informations
		// informations facture
		assertThat(facture.getNumeroFacture()).isEqualTo(numeroFacture);
		assertThat(facture.getDateEmission()).isEqualTo(dateEmission);
		assertThat(facture.getModeDeReglement()).isEqualTo(modeDeReglement);
		assertThat(facture.getTva()).isEqualTo(tva);
		assertThat(facture.getAcompte()).isEqualTo(acompte);
		assertThat(facture.getEtatFacture()).isEqualTo(etatFacture);
		// informations ligne de facture
		assertThat(ligneFacture.getDesignation()).isEqualTo(designation);
		assertThat(ligneFacture.getQuantite()).isEqualTo(quantite);
		assertThat(ligneFacture.getPrixUnitaire()).isEqualTo(prixUnitaire);

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
