package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.SiteEntity;

@Stateless
public class SiteService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<SiteEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<SiteEntity> sitesEntities = entityManager.createNamedQuery(
				"SiteEntity.findAll").getResultList();
		return sitesEntities;
	}

	public SiteEntity findById(Integer id) {
		SiteEntity site = entityManager.find(SiteEntity.class, id);
		return site;
	}	
	

}
	
