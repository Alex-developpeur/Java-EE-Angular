package dev.fatum.www.model.entities.documents;

import javax.persistence.*;

/**
 * Objet métier représentant un Profil
 *
 * @author LEONARD
 */
@Entity
@Table(name = "LIGNE_DEVIS")
public class LigneDevis {

    // ==================== Attributs ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;
    private String designation;
    private double quantite;
    @Column(name = "PRIX_UNITAIRE")
    private double prixUnitaire;
    @JoinColumn(name = "DEVIS_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private Devis devis;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public LigneDevis() {}

    /**
     * Constructeur.
     *
     * @param id -
     * @param designation -
     * @param quantite -
     * @param prixUnitaire -
     * @param devis -
     */
    public LigneDevis(Integer id, String designation, double quantite,
                      double prixUnitaire, Devis devis) {
        this.id = id;
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.devis = devis;
    }
    /**
     * Constructeur.
     *
     * @param designation -
     * @param quantite -
     * @param prixUnitaire -
     * @param devis -
     */
    public LigneDevis(String designation, double quantite, double prixUnitaire,
                      Devis devis) {
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.devis = devis;
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
    public Devis getDevis() {
        return devis;
    }
    public void setDevis(Devis devis) {
        this.devis = devis;
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
        if (!(object instanceof LigneDevis)) {
            return false;
        }
        LigneDevis other = (LigneDevis) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.documents.LigneDevis[ id=" + id + " ]";
    }

}
