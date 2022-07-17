package dev.fatum.www.webapp.business.documents;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.model.entities.Coordonnees;
import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.entities.documents.Devis;
import dev.fatum.www.model.entities.documents.LigneDevis;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
class DevisManagerImplTests {

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
	@WithMockUser(username="jack@example.com")
	void enregistrerUnNouveauDevis() {

		System.out.println("##### enregistrerUnNouveauDevis ");

		// GIVEN
		int idEntreprise = 2;
		// Création du devis
		Devis devis = new Devis();
		Entreprise entreprise = managerFactory.getEntrepriseManager().findEntrepriseById(idEntreprise);
		devis.setEntreprise(entreprise);
		String numeroDevis = "D2021-0003";
		String modeDeReglement = "Mode de reglement";
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		String dateEmission = formatter.format(date);
		double tva = 20;
		double acompte = 100;
		String etatDevis = "En attente";
		devis.setNumeroDevis(numeroDevis);
		devis.setModeDeReglement(modeDeReglement);
		devis.setTva(tva);
		devis.setAcompte(acompte);

		LigneDevis ligneDevis = new LigneDevis();
		String designation = "Designation";
		double quantite = 2;
		double prixUnitaire = 50;
		ligneDevis.setDesignation(designation);
		ligneDevis.setQuantite(quantite);
		ligneDevis.setPrixUnitaire(prixUnitaire);

		List<LigneDevis> ligneDevisList = new ArrayList<>();
		ligneDevisList.add(ligneDevis);
		devis.setLigneDevisList(ligneDevisList);

		// WHEN
		// Enregistrement du devis
		managerFactory.getDevisManager().saveDevis(devis);

		// THEN
		// Chercher si le devis est dans la base de données
		Devis devisTest = managerFactory.getDevisManager().findDevisById(devis.getId());
		LigneDevis ligneDevisTest = devisTest.getLigneDevisList().get(0);
		// Vérication des informations
		assertThat(devisTest.getNumeroDevis()).isEqualTo(numeroDevis);
		assertThat(devisTest.getModeDeReglement()).isEqualTo(modeDeReglement);
		assertThat(devisTest.getTva()).isEqualTo(tva);
		assertThat(devisTest.getAcompte()).isEqualTo(acompte);
		assertThat(devisTest.getEtatDevis()).isEqualTo(etatDevis);

		assertThat(ligneDevisTest.getDesignation()).isEqualTo(designation);
		assertThat(ligneDevisTest.getQuantite()).isEqualTo(quantite);
		assertThat(ligneDevisTest.getPrixUnitaire()).isEqualTo(prixUnitaire);

	}

	@Test
	void trouverToutesLesDevis() {

		System.out.println("##### trouverToutesLesDevis ");

		// GIVEN
		List<Devis> devisList;

		// WHEN
		devisList = managerFactory.getDevisManager().findAllDevisDesc();

		// THEN
		assertThat(devisList).isNotNull();
	}

	@Test
	void trouverUnDevisAvecSonIdentifiant() {

		System.out.println("##### trouverUnDevisAvecSonIdentifiant ");

		// GIVEN
		int idDevis = 3;
		// Valeurs à vérifier
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		String dateEmission = formatter.format(date);
		String modeDeReglement = "Mode de reglement";
		double tva = 20;
		double acompte = 100;
		String etatDevis = "En attente";

		String designation = "Designation";
		double quantite = 2;
		double prixUnitaire = 50;

		// WHEN
		// Chercher si le devis est dans la base de données
		Devis devis = managerFactory.getDevisManager().findDevisById(idDevis);
		LigneDevis ligneDevis = devis.getLigneDevisList().get(0);

		// THEN
		// Vérication des informations
		assertThat(devis.getDateEmission()).isEqualTo(dateEmission);
		assertThat(devis.getDateEmission()).isEqualTo(dateEmission);
		assertThat(devis.getModeDeReglement()).isEqualTo(modeDeReglement);
		assertThat(devis.getTva()).isEqualTo(tva);
		assertThat(devis.getAcompte()).isEqualTo(acompte);
		assertThat(devis.getEtatDevis()).isEqualTo(etatDevis);

		assertThat(ligneDevis.getDesignation()).isEqualTo(designation);
		assertThat(ligneDevis.getQuantite()).isEqualTo(quantite);
		assertThat(ligneDevis.getPrixUnitaire()).isEqualTo(prixUnitaire);

	}

	@Test
	void modifierUnDevis() {

		System.out.println("##### modifierUnDevis ");

		// GIVEN
		int idDevis = 1;
		// Informations devis
		String numeroDevis = "Numero devis modif";
		String dateEmission = "Date emission modif";
		String modeDeReglement = "Mode de reglement modif";
		double tva = 200;
		double acompte = 1000;
		String etatDevis = "Etat devis modif";
		// Informations ligne de devis
		String designation = "Designation modif";
		double quantite = 20;
		double prixUnitaire = 500;

		// WHEN
		// Chercher si la personne est dans la base de données
		Devis devis = managerFactory.getDevisManager().findDevisById(idDevis);
		LigneDevis ligneDevis = devis.getLigneDevisList().get(0);

		// THEN
		// Valeurs à vérifier
		// Informations devis
		devis.setNumeroDevis(numeroDevis);
		devis.setDateEmission(dateEmission);
		devis.setModeDeReglement(modeDeReglement);
		devis.setTva(tva);
		devis.setAcompte(acompte);
		devis.setEtatDevis(etatDevis);
		// Informations ligne de devis
		ligneDevis.setDesignation(designation);
		ligneDevis.setQuantite(quantite);
		ligneDevis.setPrixUnitaire(prixUnitaire);

		// Vérication des informations
		assertThat(devis.getNumeroDevis()).isEqualTo(numeroDevis);
		assertThat(devis.getDateEmission()).isEqualTo(dateEmission);
		assertThat(devis.getModeDeReglement()).isEqualTo(modeDeReglement);
		assertThat(devis.getTva()).isEqualTo(tva);
		assertThat(devis.getAcompte()).isEqualTo(acompte);
		assertThat(devis.getEtatDevis()).isEqualTo(etatDevis);

		assertThat(ligneDevis.getDesignation()).isEqualTo(designation);
		assertThat(ligneDevis.getQuantite()).isEqualTo(quantite);
		assertThat(ligneDevis.getPrixUnitaire()).isEqualTo(prixUnitaire);

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
