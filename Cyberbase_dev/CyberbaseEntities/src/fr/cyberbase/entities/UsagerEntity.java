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
	@NamedQuery(name="usagerEntity.findAll", query="SELECT e FROM UsagerEntity e ORDER BY e.nom_usager"),
	@NamedQuery(name="usagerEntity.findAllUsersBySite", query="SELECT e FROM UsagerEntity e WHERE e.site_inscription = :id_site ORDER BY e.nom_usager")
})
public class UsagerEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_usager;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tech_id", insertable = false)
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_site_inscription")
	private SiteEntity site_inscription;
	
	private Date date_inscription;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_quartier")
	private QuartierEntity quartier;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_csp")
	private CspEntity csp;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_formation")
	private FormationEntity niveau_formation;
	private static final long serialVersionUID = 1L;

	public UsagerEntity() {
		super();
	}

	public Integer getId_usager() {
		return id_usager;
	}

	public void setId_usager(Integer id_usager) {
		this.id_usager = id_usager;
	}

	public String getTech_id() {
		return tech_id;
	}

	public void setTech_id(String tech_id) {
		this.tech_id = tech_id;
	}

	public String getCivilite_usager() {
		return civilite_usager;
	}

	public void setCivilite_usager(String civilite_usager) {
		this.civilite_usager = civilite_usager;
	}

	public String getNom_usager() {
		return nom_usager;
	}

	public void setNom_usager(String nom_usager) {
		this.nom_usager = nom_usager;
	}

	public String getPrenom_usager() {
		return prenom_usager;
	}

	public void setPrenom_usager(String prenom_usager) {
		this.prenom_usager = prenom_usager;
	}

	public Date getDate_naissance_usager() {
		return date_naissance_usager;
	}

	public void setDate_naissance_usager(Date date_naissance_usager) {
		this.date_naissance_usager = date_naissance_usager;
	}

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

	public Integer getCode_postal_usager() {
		return code_postal_usager;
	}

	public void setCode_postal_usager(Integer code_postal_usager) {
		this.code_postal_usager = code_postal_usager;
	}

	public String getEmail_usager() {
		return email_usager;
	}

	public void setEmail_usager(String email_usager) {
		this.email_usager = email_usager;
	}

	public Boolean getAccompagnement() {
		return accompagnement;
	}

	public void setAccompagnement(Boolean accompagnement) {
		this.accompagnement = accompagnement;
	}

	public SiteEntity getSite_inscription() {
		return site_inscription;
	}

	public void setSite_inscription(SiteEntity site_inscription) {
		this.site_inscription = site_inscription;
	}

	public Date getDate_inscription() {
		return date_inscription;
	}

	public void setDate_inscription(Date date_inscription) {
		this.date_inscription = date_inscription;
	}

	public QuartierEntity getQuartier() {
		return quartier;
	}

	public void setQuartier(QuartierEntity quartier) {
		this.quartier = quartier;
	}

	public CspEntity getCsp() {
		return csp;
	}

	public void setCsp(CspEntity csp) {
		this.csp = csp;
	}

	public FormationEntity getNiveau_formation() {
		return niveau_formation;
	}

	public void setNiveau_formation(FormationEntity niveau_formation) {
		this.niveau_formation = niveau_formation;
	}

	 
	
	
   
	
}
