package dev.fatum.www.model.entities.documents;

import javax.persistence.*;

/**
 * Objet métier représentant un Profil
 *
 * @author LEONARD
 */
@Entity
@Table(name = "LIGNE_FACTURE")
public class LigneFacture {

    // ==================== Attributs ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;
    private String designation;
    private double quantite;
    @Column(name = "PRIX_UNITAIRE")
    private double prixUnitaire;
    @JoinColumn(name = "FACTURE_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private Facture facture;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public LigneFacture() {}

    /**
     * Constructeur.
     *
     * @param id -
     * @param designation -
     * @param quantite -
     * @param prixUnitaire -
     * @param facture -
     */
    public LigneFacture(Integer id, String designation, double quantite,
                      double prixUnitaire, Facture facture) {
        this.id = id;
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.facture = facture;
    }
    /**
     * Constructeur.
     *
     * @param designation -
     * @param quantite -
     * @param prixUnitaire -
     * @param facture -
     */
    public LigneFacture(String designation, double quantite, double prixUnitaire,
                      Facture facture) {
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.facture = facture;
    }

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public Facture getFacture() {
        return facture;
    }
    public void setFacture(Facture facture) {
        this.facture = facture;
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
        if (!(object instanceof LigneFacture)) {
            return false;
        }
        LigneFacture other = (LigneFacture) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.documents.LigneFacture[ id=" + id + " ]";
    }

}
