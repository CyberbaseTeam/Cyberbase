package Services;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.cyberbase.entities.AffectationEntity;
import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.SalleEntity;

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
	
	public AffectationEntity findById(Integer id) {
		return entityManager.find(AffectationEntity.class, id);
	}
	
	public AffectationEntity createAffectation(AffectationEntity affectation) {
		entityManager.persist(affectation);
		return affectation;
	}

	public List<AffectationEntity> findAllOnGoing(Timestamp tsReference) {
		Query query = entityManager.createNamedQuery("affectationEntity.findAllOnGoing");
		query.setParameter("date_fin_affectation", tsReference);
		@SuppressWarnings("unchecked")
		List<AffectationEntity> affectations = query.getResultList();
		return affectations;
	}

	public List<AffectationEntity> findAllPast(Timestamp tsReference) {
		Query query = entityManager.createNamedQuery("affectationEntity.findAllPast");
		query.setParameter("date_fin_affectation", tsReference);
		@SuppressWarnings("unchecked")
		List<AffectationEntity> affectations = query.getResultList();
		return affectations;
	}

	public AffectationEntity updateAffectation(AffectationEntity affectation) {
		return entityManager.merge(affectation);
	}

	public void deleteAffectation(AffectationEntity affectation) {
		affectation = entityManager.merge(affectation);
		entityManager.remove(affectation);
	}
	
}
