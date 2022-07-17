package dev.fatum.www.model.entities.documents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.fatum.www.model.entities.Entreprise;
import dev.fatum.www.model.entities.Personne;

import javax.persistence.*;
import java.util.List;

/**
 * Objet métier représentant un Profil
 *
 * @author LEONARD
 */
@Entity
@Table(name = "DEVIS")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;
    @Column(name = "NUMERO_DEVIS")
    private String numeroDevis;
    @Column(name = "DATE_EMISSION")
    private String dateEmission;
    @Column(name = "MODE_DE_REGLEMENT")
    private String modeDeReglement;
    @Column(name = "TVA")
    private double tva;
    @Column(name = "ACOMPTE")
    private double acompte;
    @Column(name = "ETAT_DEVIS")
    private String etatDevis;
    @JsonIgnoreProperties({"devisList", "factureList", "entrepriseList", "personneList", "coordonneesList"})
//    @JsonIgnoreProperties({"devisList", "factureList", "entrepriseList", "personneList"})
    @ManyToOne(optional = true)
    @JoinColumn(name = "ENTREPRISE_ID")
    private Entreprise entreprise;
    @JsonIgnoreProperties({"devisList", "factureList", "entreprise"})
    @ManyToOne(optional = true)
    @JoinColumn(name = "PERSONNE_ID")
    private Personne personne;
    @JsonIgnoreProperties({"devis"})
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="devis")
    private List<LigneDevis> ligneDevisList;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public Devis() {}

    /**
     * Constructeur.
     *
     * @param numeroDevis -
     * @param dateEmission -
     * @param modeDeReglement -
     * @param tva -
     * @param acompte -
     * @param etatDevis -
     * @param entreprise -
     * @param personne -
     * @param ligneDevisList -
     */
    public Devis(String numeroDevis,
                 String dateEmission,
                 String modeDeReglement,
                 double tva,
                 double acompte,
                 String etatDevis,
                 Entreprise entreprise,
                 Personne personne,
                 List<LigneDevis> ligneDevisList) {
        this.numeroDevis = numeroDevis;
        this.dateEmission = dateEmission;
        this.modeDeReglement = modeDeReglement;
        this.tva = tva;
        this.acompte = acompte;
        this.etatDevis = etatDevis;
        this.entreprise = entreprise;
        this.personne = personne;
        this.ligneDevisList = ligneDevisList;
    }

    /**
     * Constructeur.
     *
     * @param id -
     * @param numeroDevis -
     * @param dateEmission -
     * @param modeDeReglement -
     * @param tva -
     * @param acompte -
     * @param etatDevis -
     * @param entreprise -
     * @param personne -
     * @param ligneDevisList -
     */
    public Devis(Integer id,
                 String numeroDevis,
                 String dateEmission,
                 String modeDeReglement,
                 double tva,
                 double acompte,
                 String etatDevis,
                 Entreprise entreprise,
                 Personne personne,
                 List<LigneDevis> ligneDevisList) {
        this.id = id;
        this.numeroDevis = numeroDevis;
        this.dateEmission = dateEmission;
        this.modeDeReglement = modeDeReglement;
        this.tva = tva;
        this.acompte = acompte;
        this.etatDevis = etatDevis;
        this.entreprise = entreprise;
        this.personne = personne;
        this.ligneDevisList = ligneDevisList;
    }

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroDevis() {
        return numeroDevis;
    }

    public void setNumeroDevis(String numeroDevis) {
        this.numeroDevis = numeroDevis;
    }

    public String getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(String dateEmission) {
        this.dateEmission = dateEmission;
    }

    public String getModeDeReglement() {
        return modeDeReglement;
    }

    public void setModeDeReglement(String modeDeReglement) {
        this.modeDeReglement = modeDeReglement;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getAcompte() {
        return acompte;
    }

    public void setAcompte(double acompte) {
        this.acompte = acompte;
    }

    public String getEtatDevis() {
        return etatDevis;
    }

    public void setEtatDevis(String etatDevis) {
        this.etatDevis = etatDevis;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public List<LigneDevis> getLigneDevisList() {
        return ligneDevisList;
    }

    public void setLigneDevisList(List<LigneDevis> ligneDevisList) {
        this.ligneDevisList = ligneDevisList;
    }

    // ==================== Méthodes ====================
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Devis)) {
            return false;
        }
        Devis other = (Devis) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.documents.Devis[ id=" + id + " ]";
    }

}