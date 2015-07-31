package fr.cyberbase.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: SalleEntity
 *
 */
@Entity
@Table(name="salle")

public class SalleEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_salle;
	private String nom_salle;
	private Integer id_site;
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
   
}
