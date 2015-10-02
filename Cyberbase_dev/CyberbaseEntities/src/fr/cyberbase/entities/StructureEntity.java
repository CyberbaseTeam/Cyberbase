package fr.cyberbase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="structure_appartenance")
@NamedQueries({
	@NamedQuery(name="sutructureEntity.findAll", query="SELECT s FROM StructureEntity s ORDER BY s.nom_structure")		
})
public class StructureEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_structure;
	
	private String nom_structure;

	public Integer getId_structure() {
		return id_structure;
	}

	public void setId_structure(Integer id_structure) {
		this.id_structure = id_structure;
	}

	public String getNom_structure() {
		return nom_structure;
	}

	public void setNom_structure(String nom_structure) {
		this.nom_structure = nom_structure;
	}
	
	
}
