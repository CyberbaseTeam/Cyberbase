package fr.cyberbase.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProfessionnelEntity
 *
 */
@Entity
@Table(name="professionnel")
@NamedQueries({
	@NamedQuery(name="professionnelEntity.findAll", query="SELECT p FROM ProfessionnelEntity p ORDER BY p.nom_professionnel"),
	@NamedQuery(name = "professionnelEntity.findByTechId", query = "SELECT p FROM ProfessionnelEntity p WHERE p.tech_id = :tech_id"),
	@NamedQuery(name = "professionnelEntity.findById", query = "SELECT u FROM ProfessionnelEntity u WHERE u.id_professionnel = :id_professionnel"),
	@NamedQuery(name = "professionnelEntity.findBySite", query = "SELECT u FROM ProfessionnelEntity u WHERE u.site_reference = :site_reference"),
	
	
})
public class ProfessionnelEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_professionnel;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tech_id", insertable = false)
	private String tech_id;
	
	
	private String nom_professionnel;
	private String prenom_professionnel;
	
	@OneToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="site_reference")
	private SiteEntity site_reference;
	
	private String password;
	private Boolean admin;
	
	@OneToOne
	@JoinColumn(name="id_structure")
	private StructureEntity structure;
	
	private static final long serialVersionUID = 1L;

	public ProfessionnelEntity() {
		super();
	}

	public Integer getId_professionnel() {
		return id_professionnel;
	}

	public void setId_professionnel(Integer id_professionnel) {
		this.id_professionnel = id_professionnel;
	}

	public String getTech_id() {
		return tech_id;
	}

	public void setTech_id(String tech_id) {
		this.tech_id = tech_id;
	}

	public String getNom_professionnel() {
		return nom_professionnel;
	}

	public void setNom_professionnel(String nom_professionnel) {
		this.nom_professionnel = nom_professionnel;
	}

	public String getPrenom_professionnel() {
		return prenom_professionnel;
	}

	public void setPrenom_professionnel(String prenom_professionnel) {
		this.prenom_professionnel = prenom_professionnel;
	}

	public SiteEntity getSite_reference() {
		return site_reference;
	}

	public void setSite_reference(SiteEntity site_reference) {
		this.site_reference = site_reference;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public StructureEntity getStructure() {
		return structure;
	}

	public void setStructure(StructureEntity structure) {
		this.structure = structure;
	}   
	
	
   
}
