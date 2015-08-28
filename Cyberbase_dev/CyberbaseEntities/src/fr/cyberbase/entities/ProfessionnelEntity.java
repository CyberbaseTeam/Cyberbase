package fr.cyberbase.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProfessionnelEntity
 *
 */
@Entity
@Table(name="professionnel")
@NamedQueries({
	@NamedQuery(name="professionnelEntity.findAll", query="SELECT p FROM ProfessionnelEntity p ORDER BY p.nom_professionnel"),
	@NamedQuery(name = "professionnelEntity.findByTechId", query = "SELECT p FROM ProfessionnelEntity p WHERE p.tech_id like :tech_id")
})
public class ProfessionnelEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_professionnel;
	private String tech_id;
	private String nom_professionnel;
	private String prenom_professionnel;
	private Integer site_reference;
	private String password;
	private Boolean admin;
	private Integer id_structure;
	private static final long serialVersionUID = 1L;

	public ProfessionnelEntity() {
		super();
	}   
	public Integer getId_professionnel() {
		return this.id_professionnel;
	}

	public void setId_professionnel(Integer id_professionnel) {
		this.id_professionnel = id_professionnel;
	}   
	public String getTech_id() {
		return this.tech_id;
	}

	public void setTech_id(String tech_id) {
		this.tech_id = tech_id;
	}   
	public String getNom_professionnel() {
		return this.nom_professionnel;
	}

	public void setNom_professionnel(String nom_professionnel) {
		this.nom_professionnel = nom_professionnel;
	}   
	public String getPrenom_professionnel() {
		return this.prenom_professionnel;
	}

	public void setPrenom_professionnel(String prenom_professionnel) {
		this.prenom_professionnel = prenom_professionnel;
	}   
	public Integer getSite_reference() {
		return this.site_reference;
	}

	public void setSite_reference(Integer site_reference) {
		this.site_reference = site_reference;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public Boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}   
	public Integer getId_structure() {
		return this.id_structure;
	}

	public void setId_structure(Integer  id_structure) {
		this.id_structure = id_structure;
	}
   
}
