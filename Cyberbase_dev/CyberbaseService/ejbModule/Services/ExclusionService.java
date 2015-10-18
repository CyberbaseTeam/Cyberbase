package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.AffectationEntity;
import fr.cyberbase.entities.ExclusionEntity;
import fr.cyberbase.entities.PosteEntity;


@Stateless
public class ExclusionService {

	@PersistenceContext
	EntityManager entityManager;

	
	public List<ExclusionEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<ExclusionEntity> exclusions = entityManager.createNamedQuery(
				"exclusionEntity.findAll").getResultList();
		return exclusions;
	}
	
	public ExclusionEntity findById(Integer id) {
		return entityManager.find(ExclusionEntity.class, id);
	}
	
	public ExclusionEntity createExclusion(ExclusionEntity exclusion) {
		entityManager.persist(exclusion);
		return exclusion;
	}
	
	public void deleteExclusion (ExclusionEntity exclusion) {
		exclusion = entityManager.merge(exclusion);
		entityManager.remove(exclusion);
	}
}