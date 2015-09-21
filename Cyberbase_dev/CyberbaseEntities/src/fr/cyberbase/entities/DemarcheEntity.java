package fr.cyberbase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="demarche")
@NamedQueries({
	@NamedQuery(name="demarcheEntity.findAll", query="SELECT d FROM DemarcheEntity d ORDER BY d.nom_demarche")
})
public class DemarcheEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_demarche;
	
	private String nom_demarche;

	public Integer getId_demarche() {
		return id_demarche;
	}

	public void setId_demarche(Integer id_demarche) {
		this.id_demarche = id_demarche;
	}

	public String getNom_demarche() {
		return nom_demarche;
	}

	public void setNom_demarche(String nom_demarche) {
		this.nom_demarche = nom_demarche;
	}
	
}	
