package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.SalleEntity;
import fr.cyberbase.entities.SiteEntity;


@Stateless
public class SalleService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public SalleEntity findById(Integer id) {
		return entityManager.find(SalleEntity.class, id);
	}
	
	public List<SalleEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<SalleEntity> sallesEntities = entityManager.createNamedQuery("SalleEntity.findAll").getResultList();
		return sallesEntities;
	}

	public List<SalleEntity> findSallesBySite(SiteEntity site) {
		Query query = entityManager.createNamedQuery("SalleEntity.findSallesBySite");
		query.setParameter("id_site", "%"+site.getId_site()+"%");
		@SuppressWarnings("unchecked")
		List<SalleEntity> sallesEntities = query.getResultList();
		return sallesEntities;
	}
	
	
}