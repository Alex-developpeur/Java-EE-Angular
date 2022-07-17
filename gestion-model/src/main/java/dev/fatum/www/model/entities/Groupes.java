package dev.fatum.www.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Objet métier représentant un Groupes
 *
 * @author LEONARD
 */
@Entity
@Table(name = "GROUPES")
@NamedQuery(name = "Groupes.nomDuplicated", query = "SELECT g FROM Groupes g WHERE g.nom = :nom AND g.id != :id")
public class Groupes implements Serializable {
    
    private static final long serialVersionUID = 1205082528194257031L;

    // ==================== Attributs ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50, message="{groupes.nom}")
    @Column(unique = true, name = "NOM")
    private String nom;
    @Size(max = 300, message="{groupes.description}")
    @Column(name = "DESCRIPTION")
    private String description;
    @JsonIgnore
    @ManyToMany(mappedBy = "groupesSet")
    @XmlTransient
    private Set<Profil> profilSet;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public Groupes() {
    }
    /**
     * Constructeur.
     *
     * @param id -
     */
    public Groupes(Integer id) {
        this.id = id;
    }

    /**
     * Constructeur.
     *
     * @param id -
     * @param nom -
     */
    public Groupes(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlTransient
    public Set<Profil> getProfilSet() {
        return profilSet;
    }

    public void setProfilSet(Set<Profil> profilSet) {
        this.profilSet = profilSet;
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
        if (!(object instanceof Groupes)) {
            return false;
        }
        Groupes other = (Groupes) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Groupes[ id=" + id + " ]";
    }

}
