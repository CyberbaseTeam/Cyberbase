package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.DemarcheEntity;
import fr.cyberbase.entities.QuartierEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class DemarcheService {

	@PersistenceContext
	EntityManager entityManager;

	public List<DemarcheEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<DemarcheEntity> listing = entityManager.createNamedQuery("demarcheEntity.findAll", DemarcheEntity.class).getResultList();
		return listing;
	}
	
	
}
