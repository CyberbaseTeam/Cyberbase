package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.QuartierEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class QuartierService {

	@PersistenceContext
	EntityManager entityManager;

	public List<QuartierEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<QuartierEntity> listing = entityManager.createNamedQuery("quartierEntity.findAll", QuartierEntity.class).getResultList();
		return listing;
	}
	
	
}