package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SalleEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class ProfessionnelService {
		
	@PersistenceContext
	EntityManager entityManager;
	
	public List<ProfessionnelEntity> findAll(){
	
		@SuppressWarnings("unchecked")
		List<ProfessionnelEntity> listing = entityManager.createNamedQuery("professionnelEntity.findAll", ProfessionnelEntity.class).getResultList();
		return listing;
	}	
	
	public void add(ProfessionnelEntity employe){
		entityManager.persist(employe);
	}
		
	public void update(ProfessionnelEntity employe){
			entityManager.merge(employe);
		}
		
	public ProfessionnelEntity findByTechId(String tech_id) throws NoResultException, NonUniqueResultException, IllegalStateException,
	QueryTimeoutException, PersistenceException 
	{
		Query query = entityManager.createNamedQuery("professionnelEntity.findByTechId");
		query.setParameter("tech_id",tech_id);
		
		@SuppressWarnings("unchecked")
		ProfessionnelEntity professionnel =  (ProfessionnelEntity) query.getSingleResult();
	
		return professionnel;
	}
	
	public ProfessionnelEntity checkLogin(ProfessionnelEntity professionnelEntity)
	{
		ProfessionnelEntity existingPro = findByTechId(professionnelEntity.getTech_id());
		
		if(existingPro.getPassword().equals(professionnelEntity.getPassword()))
		{
			return existingPro;
		}
		else 
			return null;
	}
	
	public void delete(ProfessionnelEntity professionnelEntity) {
		professionnelEntity = entityManager.merge(professionnelEntity);
		entityManager.remove(professionnelEntity);

	}

	public List<ProfessionnelEntity> findBySite(SiteEntity siteEntity) {
		Query query = entityManager
				.createNamedQuery("professionnelEntity.findBySite");
		query.setParameter("site_reference", siteEntity);
		@SuppressWarnings("unchecked")
		List<ProfessionnelEntity> professionnelEntity = query.getResultList();
		return professionnelEntity;
	}

	public List<ProfessionnelEntity> findById(Integer id_professionnel) {
		Query query = entityManager
				.createNamedQuery("professionnelEntity.findById");
		query.setParameter("id_professionnel", id_professionnel);
		@SuppressWarnings("unchecked")
		List<ProfessionnelEntity> professionnelEntity = query.getResultList();
		return professionnelEntity;
	}

	
	
}
