package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.ExclusionEntity;


@Stateless
public class ExclusionService {

	@PersistenceContext
	EntityManager entityManager;

	
	public ExclusionEntity createExclusion(ExclusionEntity exclusion) {
		entityManager.persist(exclusion);
		return exclusion;
	}
}