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
	@NamedQuery(name = "affectationEntity.findAll", query="SELECT a FROM AffectationEntity a"),
	@NamedQuery(name = "affectationEntity.findByUsager", query = "SELECT a FROM AffectationEntity a WHERE a.usager = :usager"),
	@NamedQuery(name = "affectationEntity.findAllOnGoing", query = "SELECT a FROM AffectationEntity a WHERE a.date_fin_affectation > :date_fin_affectation"),
	@NamedQuery(name = "affectationEntity.findAllPast", query = "SELECT a FROM AffectationEntity a WHERE a.date_fin_affectation < :date_fin_affectation"),
})
public class AffectationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_affectation;
	
	private Timestamp date_debut_affectation;
	
	private Timestamp date_fin_affectation;
	
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
	private DemarcheEntity demarche;

	public Integer getId_affectation() {
		return id_affectation;
	}

	public void setId_affectation(Integer id_affectation) {
		this.id_affectation = id_affectation;
	}

	public Timestamp getDate_debut_affectation() {
		return date_debut_affectation;
	}

	public void setDate_debut_affectation(Timestamp date_debut_affectation) {
		this.date_debut_affectation = date_debut_affectation;
	}

	public Timestamp getDate_fin_affectation() {
		return date_fin_affectation;
	}

	public void setDate_fin_affectation(Timestamp date_fin_affectation) {
		this.date_fin_affectation = date_fin_affectation;
	}

	public ProfessionnelEntity getProfessionnel() {
		return professionnel;
	}

	public void setProfessionnel(ProfessionnelEntity professionnel) {
		this.professionnel = professionnel;
	}

	public UsagerEntity getUsager() {
		return usager;
	}

	public void setUsager(UsagerEntity usager) {
		this.usager = usager;
	}

	public PosteEntity getPoste() {
		return poste;
	}

	public void setPoste(PosteEntity poste) {
		this.poste = poste;
	}

	public DemarcheEntity getDemarche() {
		return demarche;
	}

	public void setDemarche(DemarcheEntity demarche) {
		this.demarche = demarche;
	}


	
	
}
