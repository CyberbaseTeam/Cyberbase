package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class UsagerService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<UsagerEntity> findAll(){
	
		@SuppressWarnings("unchecked")
		List<UsagerEntity> listing = entityManager.createNamedQuery("EmployeEntity.findAll", UsagerEntity.class).getResultList();
		
		return listing;
	}	
	
	public void add(UsagerEntity employe){
		entityManager.persist(employe);
	}
	
	
	public void update(UsagerEntity employe){
			entityManager.merge(employe);
		}
	
}
