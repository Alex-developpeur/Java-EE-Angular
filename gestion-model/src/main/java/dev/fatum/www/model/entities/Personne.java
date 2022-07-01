package dev.fatum.www.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.fatum.www.model.entities.documents.Devis;
import dev.fatum.www.model.entities.documents.Facture;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Objet métier représentant un Personne
 *
 * @author LEONARD
 */
@Entity
@Table(name = "PERSONNE")
public class Personne implements Serializable {
    
	private static final long serialVersionUID = 895180102852661783L;

    // ==================== Attributs ====================
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Integer id;
    @Basic(optional = false)
    @Column(name = "CIVILITE")
    protected String civilite;
    @Basic(optional=false)
    @Size(min = 3, max = 50, message = "{personne.nom}")
    @Column(name = "NOM")
    protected String nom;
    @Basic(optional=false)
    @Size(min = 3, max = 100, message = "{personne.prenom}")
    @Column(name = "PRENOM")
    protected String prenom;
    @JoinColumn(name = "COORDONNEES_ID", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Coordonnees coordonnees;
    @JoinColumn(name = "ENTREPRISE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    @JsonIgnoreProperties({ "entrepriseList", "coordonneesList", "personneList", "factureList", "devisList" })
//    @JsonIgnoreProperties({ "entrepriseList", "personneList", "factureList", "devisList" })
    private Entreprise entreprise;
    @JsonIgnoreProperties({"personne", "entreprise"})
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="personne")
    private List<Facture> factureList;
    @JsonIgnoreProperties({"personne", "entreprise"})
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy="personne")
    private List<Devis> devisList;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public Personne() {}

    /**
     * Constructeur.
     *
     * @param id -
     * @param nom -
     * @param prenom -
     */
    public Personne(Integer id,
            String nom,
            String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

    public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

    public List<Facture> getFactureList() {
        return factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
    }

    public List<Devis> getDevisList() {
        return devisList;
    }

    public void setDevisList(List<Devis> devisList) {
        this.devisList = devisList;
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
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "dev.fatum.entity.Personne[ id=" + id + " ]";
    }

}
