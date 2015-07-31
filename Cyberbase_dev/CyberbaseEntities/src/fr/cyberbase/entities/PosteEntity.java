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

public class PosteEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_poste;
	private String nom_poste;
	private Boolean disponibilite;
	private Integer id_salle;
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
	public Integer getId_salle() {
		return this.id_salle;
	}

	public void setId_salle(Integer id_salle) {
		this.id_salle = id_salle;
	}
   
}
