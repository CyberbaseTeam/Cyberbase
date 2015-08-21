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
	@NamedQuery(name = "SalleEntity.findAll", query = "SELECT s FROM SalleEntity s"),
	@NamedQuery(name = "SalleEntity.findSallesBySite", query = "SELECT s FROM SalleEntity s WHERE s.id_site like :id_site")
	 })
public class SalleEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_salle;
	private String nom_salle;
	private Integer id_site;
	@OneToMany
	@JoinColumn(name="id_site")
	private List<PosteEntity> postes;

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
	public Integer getId_site() {
		return this.id_site;
	}

	public void setId_site(Integer id_site) {
		this.id_site = id_site;
	}
	
	public List<PosteEntity> getPostes() {
		        return postes;
		    }

   
}
