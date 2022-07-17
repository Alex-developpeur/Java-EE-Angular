package dev.fatum.www.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.fatum.www.model.entities.documents.Devis;
import dev.fatum.www.model.entities.documents.Facture;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Objet métier représentant un Entreprise
 *
 * @author LEONARD
 */
@Entity
@Table(name = "ENTREPRISE")
public class Entreprise implements Serializable {

	private static final long serialVersionUID = 8061591290272450702L;

	// ==================== Attributs ====================
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 50, message = "{entreprise.raisonSociale}")
    @Column(name = "RAISON_SOCIALE")
    protected String raisonSociale;
    @Column(name = "FORME_JURIDIQUE")
    protected String formeJuridique;
    @Column(name = "NATURE")
    protected String nature;
	@Pattern(regexp = "(\\d{3}[ ]?){3}\\d{5}", message = "{entreprise.siret}")
    @Column(name = "SIRET")
    protected String numeroSiret;
	@Pattern(regexp = "[A-Z]{2}([ ]?\\d{3}){3}", message = "{entreprise.tva}")
    @Column(name = "NUMERO_TVA")
    protected String numeroTva;
    @JoinColumn(name = "PROFIL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
	@JsonIgnoreProperties({"entrepriseList"})
    private Profil profil;
    @JoinColumn(name = "ENTREPRISE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = true)
//	@JsonIgnoreProperties({ "entrepriseList", "personneList", "factureList", "devisList" })
	@JsonIgnoreProperties({ "entrepriseList", "coordonneesList", "personneList", "factureList", "devisList" })
    private Entreprise entreprise;
	//@JsonIgnoreProperties({"entreprise"})
	@JsonIgnoreProperties({"entrepriseList"})
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "entreprise")
    private List<Entreprise> entrepriseList;
	//@JsonIgnoreProperties({"entreprise"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entreprise")
    private List<Coordonnees> coordonneesList;
	@JsonIgnoreProperties({"entreprise"})
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "entreprise")
    private List<Personne> personneList;
	@JsonIgnoreProperties({"entreprise", "personne"})
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="entreprise")
	private List<Facture> factureList;
	@JsonIgnoreProperties({"entreprise", "personne"})
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="entreprise")
	private List<Devis> devisList;


	// ==================== Getters/Setters ====================
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRaisonSociale() {
		return raisonSociale;
	}
	
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}
	
	public String getFormeJuridique() {
		return formeJuridique;
	}
	
	public void setFormeJuridique(String formeJuridique) {
		this.formeJuridique = formeJuridique;
	}
	
	public String getNature() {
		return nature;
	}
	
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public String getNumeroSiret() {
		return numeroSiret;
	}
	
	public void setNumeroSiret(String numeroSiret) {
		this.numeroSiret = numeroSiret;
	}
	
    public String getNumeroTva() {
		return numeroTva;
	}

	public void setNumeroTva(String numeroTva) {
		this.numeroTva = numeroTva;
	}

	public Profil getProfil() {
		return profil;
	}
    
	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<Entreprise> getEntrepriseList() {
		return entrepriseList;
	}

	public void setEntrepriseList(List<Entreprise> entrepriseList) {
		this.entrepriseList = entrepriseList;
	}

	public List<Coordonnees> getCoordonneesList() {
		return coordonneesList;
	}
	
	public void setCoordonneesList(List<Coordonnees> coordonneesList) {
		this.coordonneesList = coordonneesList;
	}
	
	public List<Personne> getPersonneList() {
		return personneList;
	}
	
	public void setPersonneList(List<Personne> personneList) {
		this.personneList = personneList;
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
        if (!(object instanceof Entreprise)) {
            return false;
        }
        Entreprise other = (Entreprise) object;
		return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
	}

    @Override
    public String toString() {
        return "dev.fatum.entity.Entreprise[ id=" + id + " ]";
    }
}
