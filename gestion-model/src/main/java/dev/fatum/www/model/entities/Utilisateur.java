package dev.fatum.www.model.entities;

import java.util.HashSet;

import javax.persistence.Entity;

/**
 * Objet métier représentant un Utilisateur
 *
 * @author LEONARD
 */
@Entity
public class Utilisateur extends Profil {

	private static final long serialVersionUID = 3734409829741348491L;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public Utilisateur() {
        this.groupesSet = new HashSet<>();
    }

    /**
     * Constructeur.
     *
     * @param id -
     */
    public Utilisateur(Integer id) {
        this.id = id;
        this.groupesSet = new HashSet<>();
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Utilisateur[ id=" + id + " ]";
    }
}
