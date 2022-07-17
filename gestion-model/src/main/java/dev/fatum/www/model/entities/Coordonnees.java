package dev.fatum.www.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 * Objet métier représentant un Coordonnees
 *
 * @author LEONARD
 */
@Entity
@Table(name = "COORDONNEES")
public class Coordonnees implements Serializable {

	private static final long serialVersionUID = -7678154805196836390L;

	// ==================== Attributs ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Integer id;
    @Basic(optional = false)
    @Size(min = 3, max = 50, message = "{coordonnees.rue}")
    @Column(name = "RUE")
    protected String rue;
    @Basic(optional = false)
    @Pattern(regexp = "\\d{2}[ ]?\\d{3}", message = "{coordonnees.codePostal}")
    @Column(name = "CODE_POSTAL")
    protected String codePostal;
    @Basic(optional = false)
    @Size(min = 1, max = 50, message = "{coordonnees.ville}")
    @Column(name = "VILLE")
    protected String ville;
    @Basic(optional = true)
    @Size(min = 3, max = 50, message ="{coordonnees.pays}")
    @Column(name = "PAYS")
    protected String pays;
    @Pattern(regexp = "^\\S+@[\\w-.]+\\.\\w+$", message = "{coordonnees.email}")
    @Size(min = 6, max = 50, message = "{coordonnees.longueuEmail}")
    @Column(name = "EMAIL")
    protected String email;
    @Basic(optional = true)
    @Size(min = 10, max = 15, message = "{coordonnees.tel}")
    @Column(name = "TEL")
    protected String tel;
    @Basic(optional = true)
    @Size(min = 10, max = 15, message = "{coordonnees.fax}")
    @Column(name = "FAX")
    protected String fax;
	@JsonIgnore
    @JoinColumn(name = "ENTREPRISE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Entreprise entreprise;
	@JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "coordonnees")
    private List<Personne> personneList;

	// ==================== Getters/Setters ====================
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

    public List<Personne> getPersonneList() {
		return personneList;
	}
	public void setPersonneList(List<Personne> personneList) {
		this.personneList = personneList;
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
        if (!(object instanceof Coordonnees)) {
            return false;
        }
        Coordonnees other = (Coordonnees) object;
		return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
	}

    @Override
    public String toString() {
        return "dev.fatum.entity.Coordonnees[ id=" + id + " ]";
    }
}
