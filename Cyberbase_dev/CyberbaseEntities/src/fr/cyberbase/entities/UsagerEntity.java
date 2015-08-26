package fr.cyberbase.entities;


import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UsagerEntity
 *
 */
@Entity
@Table(name="usager")
@NamedQueries({
	@NamedQuery(name="usagerEntity.findAll", query="SELECT e FROM UsagerEntity e ORDER BY e.nom_usager")
})
public class UsagerEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_usager;
	private String tech_id;
	private String civilite_usager;
	private String nom_usager;
	private String prenom_usager;
	private Date date_naissance_usager;
	private String ville_usager;
	private String adresse_usager;
	private Integer code_postal_usager;
	private String email_usager;
	private Boolean accompagnement;
	private Integer site_inscription_id;
	
	// à gérer en JPA avec @onetoOne
//	private Integer id_quartier;
//	private Integer id_csp;
//	private Integer id_formation;
	private static final long serialVersionUID = 1L;

	public UsagerEntity() {
		super();
	}   
	public Integer getId_usager() {
		return this.id_usager;
	}

	public void setId_usager(Integer id_usager) {
		this.id_usager = id_usager;
	}   
	public String getTech_id() {
		return this.tech_id;
	}

	public void setTech_id(String tech_id) {
		this.tech_id = tech_id;
	}   
	public String getCivilite_usager() {
		return this.civilite_usager;
	}

	public void setCivilite_usager(String civilite_usager) {
		this.civilite_usager = civilite_usager;
	}   
	public String getNom_usager() {
		return this.nom_usager;
	}

	public void setNom_usager(String nom_usager) {
		this.nom_usager = nom_usager;
	}   
	public String getPrenom_usager() {
		return this.prenom_usager;
	}

	public void setPrenom_usager(String prenom_usager) {
		this.prenom_usager = prenom_usager;
	}   
	public Date getDate_naissance_usager() {
		return this.date_naissance_usager;
	}

	public void setDate_naissance_usager(Date date_naissance_usager) {
		this.date_naissance_usager = date_naissance_usager;
	}   
	public Integer getCode_postal_usager() {
		return this.code_postal_usager;
	}

	public void setCode_postal_usager(Integer code_postal_usager) {
		this.code_postal_usager = code_postal_usager;
	}   
	public String getEmail_usager() {
		return this.email_usager;
	}

	public void setEmail_usager(String email_usager) {
		this.email_usager = email_usager;
	}   
	public Boolean getAccompagnement() {
		return this.accompagnement;
	}

	public void setAccompagnement(Boolean accompagnement) {
		this.accompagnement = accompagnement;
	}   
	public Integer getSite_inscription_id() {
		return this.site_inscription_id;
	}

	public void setSite_inscription_id(Integer site_inscription_id) {
		this.site_inscription_id = site_inscription_id;
	}   
//	public Integer getId_quartier() {
//		return this.id_quartier;
//	}
//
//	public void setId_quartier(Integer id_quartier) {
//		this.id_quartier = id_quartier;
//	}   
//	public Integer getId_csp() {
//		return this.id_csp;
//	}
//
//	public void setId_csp(Integer id_csp) {
//		this.id_csp = id_csp;
//	}   
//	public Integer getId_formation() {
//		return this.id_formation;
//	}
//
//	public void setId_formation(Integer id_formation) {
//		this.id_formation = id_formation;
//	}
	public String getVille_usager() {
		return ville_usager;
	}
	public void setVille_usager(String ville_usager) {
		this.ville_usager = ville_usager;
	}
	public String getAdresse_usager() {
		return adresse_usager;
	}
	public void setAdresse_usager(String adresse_usager) {
		this.adresse_usager = adresse_usager;
	}
   
	
}
