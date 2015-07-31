package fr.cyberbase.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: SiteEntity
 *
 */
@Entity
@Table(name="site")

public class SiteEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_site;
	private String nom_site;
	private String adresse_site;
	private String ville_site;
	private Integer code_postal_site;
	private static final long serialVersionUID = 1L;

	public SiteEntity() {
		super();
	}   
	public Integer getId_site() {
		return this.id_site;
	}

	public void setId_site(Integer id_site) {
		this.id_site = id_site;
	}   
	public String getNom_site() {
		return this.nom_site;
	}

	public void setNom_site(String nom_site) {
		this.nom_site = nom_site;
	}   
	public String getAdresse_site() {
		return this.adresse_site;
	}

	public void setAdresse_site(String adresse_site) {
		this.adresse_site = adresse_site;
	}   
	public String getVille_site() {
		return this.ville_site;
	}

	public void setVille_site(String ville_site) {
		this.ville_site = ville_site;
	}   
	public Integer getCode_postal_site() {
		return this.code_postal_site;
	}

	public void setCode_postal_site(Integer code_postal_site) {
		this.code_postal_site = code_postal_site;
	}
   
}
