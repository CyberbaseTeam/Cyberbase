package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	public List<SiteEntity> findAllExceptMine(SiteEntity site) {
		Query query = entityManager.createNamedQuery(
				"SiteEntity.findAllExceptMine");
		Integer id_site = site.getId_site();
		query.setParameter("id_site", id_site);
		@SuppressWarnings("unchecked")
		List<SiteEntity> sites = query.getResultList();
		return sites;
	}	
	public void add(SiteEntity site) {
		entityManager.persist(site);
		
	}
	

}
	
