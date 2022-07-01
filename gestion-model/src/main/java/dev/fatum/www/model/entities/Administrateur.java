package dev.fatum.www.model.entities;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Entity;

/**
 * Objet métier représentant un Administrateur
 *
 * @author LEONARD
 */
@Entity
public class Administrateur extends Profil {

	private static final long serialVersionUID = -4684469601613601235L;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public Administrateur() {
        this.groupesSet = new HashSet<>();
    }

    /**
     * Constructeur.
     *
     * @param id -
     */
    public Administrateur(Integer id) {
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
        if (!(object instanceof Administrateur)) {
            return false;
        }
        Administrateur other = (Administrateur) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Administrateur[ id=" + id + " ]";
    }
}
