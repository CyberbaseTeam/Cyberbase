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
	
	public PosteEntity findById(PosteEntity poste) {
		return entityManager.find(PosteEntity.class, poste.getId_poste());
	}
	
}