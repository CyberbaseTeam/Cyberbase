package fr.cyberbase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="csp")
@NamedQueries({
	@NamedQuery(name="cspEntity.findAll", query="SELECT c FROM CspEntity c ORDER BY c.libelle_csp")
})
public class CspEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_csp;
	
	private String libelle_csp;

	public Integer getId_csp() {
		return id_csp;
	}

	public void setId_csp(Integer id_csp) {
		this.id_csp = id_csp;
	}

	public String getLibelle_csp() {
		return libelle_csp;
	}

	public void setLibelle_csp(String libelle_csp) {
		this.libelle_csp = libelle_csp;
	}
	
	
	
}
