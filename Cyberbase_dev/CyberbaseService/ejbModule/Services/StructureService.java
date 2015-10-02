package Services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.cyberbase.entities.ProfessionnelEntity;
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
	
	public StructureEntity findById(Integer id_structure) {
		Query query = entityManager.createNamedQuery("structureEntity.findById");
		query.setParameter("id_structure", id_structure);
		@SuppressWarnings("unchecked")
		StructureEntity structureEntity = (StructureEntity) query.getSingleResult();
		return structureEntity;
	}
}

