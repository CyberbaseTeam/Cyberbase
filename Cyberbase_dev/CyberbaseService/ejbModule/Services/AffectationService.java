package Services;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.cyberbase.entities.AffectationEntity;
import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SalleEntity;
import fr.cyberbase.entities.SiteEntity;

@Stateless
public class AffectationService {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<AffectationEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<AffectationEntity> affectations = entityManager.createNamedQuery(
				"affectationEntity.findAll").getResultList();
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
	
	public Long findVisitCountByPeriodAndSite(SiteEntity site, Timestamp start, Timestamp end){

		Query query = entityManager.createNamedQuery("affectationEntity.findOurVisitsInCertainPeriod");
		query.setParameter("id_site", site.getId_site());
		query.setParameter("start", start);
		query.setParameter("end", end);
		
		Long count =  (Long) query.getSingleResult();
		
		return count;
		
	}
	
	
	
	public BigInteger findNewCommersByPeriodAndSite(SiteEntity site, Timestamp start, Timestamp end){
		Query executedQuery = entityManager.createNativeQuery(
				"SELECT COUNT(*) FROM ( SELECT MIN(date_debut_affectation), affectation.id_professionnel FROM affectation, professionnel, site "
				+ "WHERE professionnel.site_reference = site.id_site AND affectation.id_professionnel = professionnel.id_professionnel AND site_reference = :id_site "
				+ "group by id_usager, affectation.id_professionnel HAVING MIN(date_debut_affectation) BETWEEN :start AND :end) AS visitCount");
		
		executedQuery.setParameter("id_site", site.getId_site());
		executedQuery.setParameter("start", start);
		executedQuery.setParameter("end", end);
		
		BigInteger count =  (BigInteger) executedQuery.getSingleResult();
		
		return count;
		
	}
	
	public Long getDistinctAffectedUsers(SiteEntity site){
		Query query = entityManager.createNamedQuery("affectationEntity.findDistinctUsers");
		query.setParameter("id_site", site.getId_site());
	
		
		Long count =  (Long) query.getSingleResult();
		
		return count;
		
	}

	public List<AffectationEntity> findByUserId(Integer id_usager) {
		Query query = entityManager.createNamedQuery("affectationEntity.findByUserId");
		query.setParameter("id_usager", id_usager);
		@SuppressWarnings("unchecked")
		List<AffectationEntity> affectations = query.getResultList();
		return affectations;
	}
	
	
	
	
	
}
