package fr.cyberbase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="niveau_formation")
@NamedQueries({
	@NamedQuery(name="formationEntity.findAll", query="SELECT f FROM FormationEntity f ORDER BY f.nom_formation")
})
public class FormationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_formation;
	
	private String nom_formation;

	public Integer getId_formation() {
		return id_formation;
	}

	public void setId_formation(Integer id_formation) {
		this.id_formation = id_formation;
	}

	public String getNom_formation() {
		return nom_formation;
	}

	public void setNom_formation(String nom_formation) {
		this.nom_formation = nom_formation;
	}
	
	
}
