package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class ProfessionnelService {
		
	@PersistenceContext
	EntityManager entityManager;
	
	public List<ProfessionnelEntity> findAll(){
	
		@SuppressWarnings("unchecked")
		List<ProfessionnelEntity> listing = entityManager.createNamedQuery("ProfessionnelEntity.findAll", ProfessionnelEntity.class).getResultList();
		
		return listing;
	}	
	
	public void add(UsagerEntity employe){
		entityManager.persist(employe);
	}
	
	
	public void update(UsagerEntity employe){
			entityManager.merge(employe);
		}
		
	
	
	
	
	
}
