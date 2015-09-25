package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.SalleEntity;


@Stateless
public class PosteService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<PosteEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<PosteEntity> postesEntities = entityManager.createNamedQuery(
				"PosteEntity.findAll").getResultList();
		return postesEntities;
	}	
	
	public PosteEntity findById(Integer id) {
		return entityManager.find(PosteEntity.class, id);
	}
	
	public PosteEntity createPoste(PosteEntity poste) {
		entityManager.persist(poste);
		return poste;
	}
	
	public PosteEntity updatePoste(PosteEntity poste) {
		return entityManager.merge(poste);
	}
	
	public void deletePoste(PosteEntity poste) {
		poste = entityManager.merge(poste);
		entityManager.remove(poste);
	}
	
	public void changeDisponibility (PosteEntity poste){
		Boolean disponibility = poste.getDisponibilite();
		if (disponibility){
			poste.setDisponibilite(false);
			entityManager.merge(poste);
		} else {
			poste.setDisponibilite(true);
			entityManager.merge(poste);
		}
	}
	
}