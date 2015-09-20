package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.CspEntity;
import fr.cyberbase.entities.FormationEntity;
import fr.cyberbase.entities.QuartierEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class FormationService {

	@PersistenceContext
	EntityManager entityManager;

	public List<FormationEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<FormationEntity> listing = entityManager.createNamedQuery("formationEntity.findAll", FormationEntity.class).getResultList();
		return listing;
	}
	
	
}