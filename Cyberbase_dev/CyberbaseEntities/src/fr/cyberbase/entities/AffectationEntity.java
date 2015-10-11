package fr.cyberbase.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="affectation")
@NamedQueries({
	@NamedQuery(name="affectationEntity.findAll", query="SELECT a FROM AffectationEntity a"),
	@NamedQuery(name = "affectationEntity.findByUsager", query = "SELECT a FROM AffectationEntity a WHERE a.usager = :usager"),
	
})
public class AffectationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_affectation;
	
	private Timestamp data_debut_affectation;
	
	private Timestamp data_fin_affectation;
	
	@ManyToOne
	@JoinColumn(name="id_professionnel")
	private ProfessionnelEntity professionnel;
	
	@ManyToOne
	@JoinColumn(name="id_usager")
	private UsagerEntity usager;
	
	@ManyToOne
	@JoinColumn(name="id_poste")
	private PosteEntity poste;
	
	@ManyToOne
	@JoinColumn(name="id_demarche")
	private UsagerEntity demarche;
	
	
	
}
