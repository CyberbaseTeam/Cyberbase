package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.RequeteEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class StatistiqueService {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<RequeteEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<RequeteEntity> listing = entityManager.createNamedQuery("requeteEntity.findAll", RequeteEntity.class).getResultList();
		return listing;
	}	
	
	public String createPersonnalQuery(Map<String, String> queryObjects){
		String personnalQuery = "SELECT ";
		return personnalQuery;	
	}
	

	
	public List<UsagerEntity> executePersonnalQuery(String personnalQuery){
		List<UsagerEntity> personnalQueryresult = new ArrayList<UsagerEntity>();
		return personnalQueryresult;
	}
}
