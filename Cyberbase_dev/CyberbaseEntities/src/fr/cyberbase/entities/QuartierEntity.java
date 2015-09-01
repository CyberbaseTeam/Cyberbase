package fr.cyberbase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="quartier")
@NamedQueries({
	@NamedQuery(name="quartierEntity.findAll", query="SELECT q FROM QuartierEntity q ORDER BY q.nom_quartier")
})
public class QuartierEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_quartier;
	
	private String nom_quartier;
}
