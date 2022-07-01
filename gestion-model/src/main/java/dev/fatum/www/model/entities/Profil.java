package dev.fatum.www.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Objet métier représentant un Profil
 *
 * @author LEONARD
 */
@NamedEntityGraph(
        name = "Profil.groupes",
        attributeNodes = @NamedAttributeNode("groupesSet")
)
@Entity
@Table(name = "PROFIL")
@NamedQuery(name = "Profil.emailDuplicated", query = "SELECT p FROM Profil p WHERE p.email = :email AND p.id != :id")
public class Profil implements Serializable {

	private static final long serialVersionUID = 3734409829741348491L;

    // ==================== Attributs ====================
    @JoinTable(name = "PROFIL_GROUPES", joinColumns = {
        @JoinColumn(name = "ID_PROFIL", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_GROUPES", referencedColumnName = "ID")})
    @ManyToMany
    protected Set<Groupes> groupesSet;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;
    @Size(min = 3, max = 50, message = "{profil.nom}")
    @Column(name = "NOM")
    protected String nom;
    @Pattern(regexp = "^\\S+@[\\w-.]+\\.\\w+$", message = "{profil.email}")
    @Size(min = 5, max = 50, message = "{profil.longueuEmail}")
    @Basic(optional = false)
    @Column(unique = true, name = "EMAIL")
    protected String email;
    @Size(min = 6, max = 100, message = "{profil.longueuMDP}")
    @NotEmpty(message = "{profil.mdpVide}")
    @Basic(optional = false)
    @Column(name = "MDP")
    protected String mdp;
    @Column(name = "EXPIRATION")
    @Temporal(TemporalType.DATE)
    protected Date expiration;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "profil")
    private List<Entreprise> entrepriseList;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public Profil() {
        this.groupesSet = new HashSet<>();
    }

    /**
     * Constructeur.
     *
     * @param id -
     */
    public Profil(Integer id) {
        this.id = id;
        this.groupesSet = new HashSet<>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
    public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Set<Groupes> getGroupesSet() {
		return groupesSet;
	}

	public void setGroupesSet(Set<Groupes> groupesSet) {
		this.groupesSet = groupesSet;
	}

	public List<Entreprise> getEntrepriseList() {
		return entrepriseList;
	}

	public void setEntrepriseList(List<Entreprise> entrepriseList) {
		this.entrepriseList = entrepriseList;
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
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Profil[ id=" + id + " ]";
    }
}
