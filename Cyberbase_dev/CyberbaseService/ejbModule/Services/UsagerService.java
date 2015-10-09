package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class UsagerService {

	@PersistenceContext
	EntityManager entityManager;

	public List<UsagerEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<UsagerEntity> listing = entityManager.createNamedQuery("usagerEntity.findAll", UsagerEntity.class).getResultList();
		return listing;
	}
	
	public UsagerEntity findById(Integer id) {
		UsagerEntity user = entityManager.find(UsagerEntity.class, id);
		return user;
	}

	public void add(UsagerEntity usager) {
		entityManager.persist(usager);
	}

	public void update(UsagerEntity usager) {
		entityManager.merge(usager);
	}
	
	public void delete(UsagerEntity usagerEntity) {
		usagerEntity = entityManager.merge(usagerEntity);
		entityManager.remove(usagerEntity);		
	}
	
	public List<UsagerEntity> findAllUsersBySite(SiteEntity site){
		Query query = entityManager.createNamedQuery(
				"usagerEntity.findAllUsersBySite");
		Integer id_site = site.getId_site();
		query.setParameter("id_site", site);
		@SuppressWarnings("unchecked")
		List<UsagerEntity> usagers = query.getResultList();
		return usagers;
	}

}
