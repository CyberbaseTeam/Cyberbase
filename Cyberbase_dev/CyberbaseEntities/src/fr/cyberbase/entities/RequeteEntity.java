package fr.cyberbase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: UsagerEntity
 *
 */
@Entity
@Table(name="requete_favorite")
@NamedQueries({
	@NamedQuery(name="requeteEntity.findAll", query="SELECT r FROM RequeteEntity r "),
	@NamedQuery(name="requeteEntity.findPersonalQueries", query="SELECT r FROM RequeteEntity r WHERE r.id_professionnel = :id"),
	@NamedQuery(name="requeteEntity.findSpecificQuery", query="SELECT r FROM RequeteEntity r WHERE r.id_professionnel = :idPro AND r.id_requete = :id")
})
public class RequeteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_requete;
	
	private String nom_requete;
	
	private String contenu_requete;
	
	private Integer id_professionnel;

	public Integer getId_requete() {
		return id_requete;
	}

	public void setId_requete(Integer id_requete) {
		this.id_requete = id_requete;
	}

	public String getNom_requete() {
		return nom_requete;
	}

	public void setNom_requete(String nom_requete) {
		this.nom_requete = nom_requete;
	}

	public String getContenu_requete() {
		return contenu_requete;
	}

	public void setContenu_requete(String contenu_requete) {
		this.contenu_requete = contenu_requete;
	}

	public Integer getId_professionnel() {
		return id_professionnel;
	}

	public void setId_professionnel(Integer id_professionnel) {
		this.id_professionnel = id_professionnel;
	}
	
	
}