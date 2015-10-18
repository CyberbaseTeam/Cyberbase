package fr.cyberbase.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ExclusionEntity
 *
 */
@Entity
@Table(name="exclusion")
@NamedQueries({
	@NamedQuery(name="exclusionEntity.findAll", query="SELECT e FROM ExclusionEntity e")
})
public class ExclusionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_exclusion;
	
	private String statut_exclusion;
	
	private Date date_debut;
	
	private Date date_fin;
	
	@OneToOne
	@JoinColumn(name="id_usager")
	private UsagerEntity usager;

	public Integer getId_exclusion() {
		return id_exclusion;
	}

	public void setId_exclusion(Integer id_exclusion) {
		this.id_exclusion = id_exclusion;
	}

	public String getStatut_exclusion() {
		return statut_exclusion;
	}

	public void setStatut_exclusion(String statut_exclusion) {
		this.statut_exclusion = statut_exclusion;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public UsagerEntity getUsager() {
		return usager;
	}

	public void setUsager(UsagerEntity usager) {
		this.usager = usager;
	}
	
	
	
}
