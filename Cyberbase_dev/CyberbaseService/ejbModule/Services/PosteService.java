package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.PosteEntity;


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
	
	public void changeDisponibility (PosteEntity poste){
		Boolean disponibility = poste.getDisponibilite();
		if (disponibility){
			PosteEntity poste2 = entityManager.find(PosteEntity.class, poste.getId_poste());
			poste2.setDisponibilite(false);
			entityManager.merge(poste2);
		} else {
			PosteEntity poste2 = entityManager.find(PosteEntity.class, poste.getId_poste());
			poste2.setDisponibilite(true);
			entityManager.merge(poste2);
		}
	}
	
}