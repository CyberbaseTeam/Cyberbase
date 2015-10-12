package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.AffectationEntity;

@Stateless
public class AffectationService {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<AffectationEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<AffectationEntity> affectations = entityManager.createNamedQuery(
				"AffectationEntity.findAll").getResultList();
		return affectations;
	}
	
	public AffectationEntity createAffectation(AffectationEntity affectation) {
		entityManager.persist(affectation);
		return affectation;
	}
	
}
