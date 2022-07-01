package dev.fatum.www.model.entities.documents;

import dev.fatum.www.model.entities.Profil;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Objet métier représentant un Profil
 *
 * @author LEONARD
 */
@Entity
@Table(name = "NUMEROS_DOCUMENTS")
public class NumerosDocuments implements Serializable {

    // ==================== Attributs ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;
    @Column(name = "ENTREPRISE_ID")
    protected Integer entrepriseId;
    @Column(name = "NUMERO_DEVIS")
    protected Integer numeroDevis;
    @Column(name = "NUMERO_FACTURE")
    protected Integer numeroFacture;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public NumerosDocuments() {}

    /**
     * Constructeur.
     *
     * @param entrepriseId -
     */
    public NumerosDocuments(Integer entrepriseId) {
        this.entrepriseId = entrepriseId;
        this.numeroDevis = 0;
        this.numeroFacture = 0;
    }

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Integer entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public Integer getNumeroDevis() {
        return numeroDevis;
    }

    public void setNumeroDevis(Integer numeroDevis) {
        this.numeroDevis = numeroDevis;
    }

    public Integer getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(Integer numeroFacture) {
        this.numeroFacture = numeroFacture;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NumerosDocuments)) {
            return false;
        }
        NumerosDocuments other = (NumerosDocuments) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.NumerosDocuments[ id=" + id + " ]";
    }
}
