package fr.cyberbase.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: SalleEntity
 *
 */
@Entity
@Table(name="salle")
@NamedQueries({
	@NamedQuery(name = "SalleEntity.findAll", query = "SELECT s FROM SalleEntity s")
	 })
public class SalleEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_salle;
	private String nom_salle;
	
	@OneToMany(mappedBy="salle", fetch=FetchType.EAGER)
	@OrderBy("nom_poste ASC")
	private List<PosteEntity> postes;
	
	@ManyToOne
	@JoinColumn(name = "id_site")
	private SiteEntity site;


	private static final long serialVersionUID = 1L;

	public SalleEntity() {
		super();
	}   
	public Integer getId_salle() {
		return this.id_salle;
	}

	public void setId_salle(Integer id_salle) {
		this.id_salle = id_salle;
	}   
	public String getNom_salle() {
		return this.nom_salle;
	}

	public void setNom_salle(String nom_salle) {
		this.nom_salle = nom_salle;
	}
	public List<PosteEntity> getPostes() {
		return postes;
	}
	public void setPostes(List<PosteEntity> postes) {
		this.postes = postes;
	}  
	
	

}