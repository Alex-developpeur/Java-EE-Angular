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
@Table(name = "FACTURE")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;
    @Column(name = "NUMERO_FACTURE")
    private String numeroFacture;
    @Column(name = "DATE_EMISSION")
    private String dateEmission;
    @Column(name = "DATE_REGLEMENT")
    private String dateReglement;
    @Column(name = "MODE_DE_REGLEMENT")
    private String modeDeReglement;
    private double tva;
    private double acompte;
    @Column(name = "ETAT_FACTURE")
    private String etatFacture;
    private String type;
    @ManyToOne(optional = true)
    @JoinColumn(name = "ENTREPRISE_ID")
    private Entreprise entreprise;
    @ManyToOne(optional = true)
    @JoinColumn(name = "PERSONNE_ID")
    private Personne personne;
    @JsonIgnoreProperties({"facture"})
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="facture")
    private List<LigneFacture> ligneFactureList;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public Facture() {}

    /**
     * Constructeur.
     *
     * @param id -
     * @param numeroFacture -
     * @param dateEmission -
     * @param dateReglement -
     * @param modeDeReglement -
     * @param tva -
     * @param acompte -
     * @param etatFacture -
     * @param type -
     * @param entreprise -
     * @param personne -
     * @param ligneFactureList -
     */
    public Facture(Integer id,
                   String numeroFacture,
                   String dateEmission,
                   String dateReglement,
                   String modeDeReglement,
                   double tva,
                   double acompte,
                   String etatFacture,
                   String type,
                   Entreprise entreprise,
                   Personne personne,
                   List<LigneFacture> ligneFactureList) {
        this.id = id;
        this.numeroFacture = numeroFacture;
        this.dateEmission = dateEmission;
        this.dateReglement = dateReglement;
        this.modeDeReglement = modeDeReglement;
        this.tva = tva;
        this.acompte = acompte;
        this.etatFacture = etatFacture;
        this.type = type;
        this.entreprise = entreprise;
        this.personne = personne;
        this.ligneFactureList = ligneFactureList;
    }

    /**
     * Constructeur.
     *
     * @param numeroFacture -
     * @param dateEmission -
     * @param dateReglement -
     * @param modeDeReglement -
     * @param tva -
     * @param acompte -
     * @param etatFacture -
     * @param type -
     * @param entreprise -
     * @param personne -
     * @param ligneFactureList -
     */
    public Facture(String numeroFacture,
                   String dateEmission,
                   String dateReglement,
                   String modeDeReglement,
                   double tva,
                   double acompte,
                   String etatFacture,
                   String type,
                   Entreprise entreprise,
                   Personne personne,
                   List<LigneFacture> ligneFactureList) {
        this.numeroFacture = numeroFacture;
        this.dateEmission = dateEmission;
        this.dateReglement = dateReglement;
        this.modeDeReglement = modeDeReglement;
        this.tva = tva;
        this.acompte = acompte;
        this.etatFacture = etatFacture;
        this.type = type;
        this.entreprise = entreprise;
        this.personne = personne;
        this.ligneFactureList = ligneFactureList;
    }

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public String getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(String dateEmission) {
        this.dateEmission = dateEmission;
    }

    public String getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(String dateReglement) {
        this.dateReglement = dateReglement;
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

    public String getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(String etatFacture) {
        this.etatFacture = etatFacture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<LigneFacture> getLigneFactureList() {
        return ligneFactureList;
    }

    public void setLigneFactureList(List<LigneFacture> ligneFactureList) {
        this.ligneFactureList = ligneFactureList;
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
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.documents.Facture[ id=" + id + " ]";
    }

}
