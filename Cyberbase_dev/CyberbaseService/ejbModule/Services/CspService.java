package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.CspEntity;
import fr.cyberbase.entities.QuartierEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class CspService {

	@PersistenceContext
	EntityManager entityManager;

	public List<CspEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<CspEntity> listing = entityManager.createNamedQuery("cspEntity.findAll", CspEntity.class).getResultList();
		return listing;
	}
	
	public void add(CspEntity csp) {
		entityManager.persist(csp);
		
	}
	
	
}