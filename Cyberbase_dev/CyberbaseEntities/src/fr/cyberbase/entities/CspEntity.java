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
	@NamedQuery(name="cspEntity.findAll", query="SELECT c FROM CspEntity c ORDER BY c.nom_csp")
})
public class CspEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer csp_id;
	
	private String nom_csp;
}
