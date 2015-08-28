package fr.cyberbase.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PosteEntity
 *
 */
@Entity
@Table(name="poste")
@NamedQueries({
	@NamedQuery(name = "PosteEntity.findAll", query = "SELECT p FROM PosteEntity p ORDER BY p.nom_poste"),
	 })
public class PosteEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_poste;
	private String nom_poste;
	private Boolean disponibilite;
	
	@ManyToOne
	@JoinColumn(name = "id_salle")
	private SalleEntity salle;

	private static final long serialVersionUID = 1L;

	public PosteEntity() {
		super();
	}   
	public Integer getId_poste() {
		return this.id_poste;
	}

	public void setId_poste(Integer id_poste) {
		this.id_poste = id_poste;
	}   
	public String getNom_poste() {
		return this.nom_poste;
	}

	public void setNom_poste(String nom_poste) {
		this.nom_poste = nom_poste;
	}   
	public Boolean getDisponibilite() {
		return this.disponibilite;
	}

	public void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}   

}
