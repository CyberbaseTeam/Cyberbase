package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.RequeteEntity;
import fr.cyberbase.entities.StructureEntity;

@Stateless
public class StructureService {
	@PersistenceContext
	EntityManager entityManager;
	
	public List<StructureEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<StructureEntity> listing = entityManager.createNamedQuery("structureEntity.findAll", StructureEntity.class).getResultList();
		return listing;
	}	
}

