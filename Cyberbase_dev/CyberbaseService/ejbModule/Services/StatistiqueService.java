package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.cyberbase.entities.RequeteEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class StatistiqueService {

	private static final String ATTR_ALL 			= "displayAll";
	private static final String ATTR_GENDER 		= "displayGender";
	private static final String ATTR_SURNAME 		= "displaySurname";
	private static final String ATTR_NAME 			= "displayName";
	private static final String ATTR_DOB 			= "displayDOB";
	private static final String ATTR_ADDRESS 		= "displayAddress";
	private static final String ATTR_CITY 			= "displayCity";
	private static final String ATTR_ZIPCODE 		= "displayZipCode";
	private static final String ATTR_EMAIL	 		= "displayEmail";
	private static final String ATTR_PATRONAGE 		= "displayPatronage";
	private static final String ATTR_SITE 			= "displaySite";
	private static final String ATTR_DISTRICT 		= "displayDistrict";
	private static final String ATTR_CSP 			= "displayCsp";
	private static final String ATTR_FORMATION 		= "displayFormation";
	private static final String ATTR_VISITCOUNT 	= "displayVisitCount";

	
	
	private Map<String, String> sqlEquivalence;
		
	@PersistenceContext
	EntityManager entityManager;
	
	private void initializeSqlEquivalence()	
	{
		this.sqlEquivalence = new HashMap<String, String>();
		this.sqlEquivalence.put(ATTR_ALL, "*");
		this.sqlEquivalence.put(ATTR_GENDER, "civilite_usager");
		this.sqlEquivalence.put(ATTR_SURNAME, "prenom_usager");
		this.sqlEquivalence.put(ATTR_NAME, "nom_usager");
		this.sqlEquivalence.put(ATTR_DOB, "date_naissance_usager");
		this.sqlEquivalence.put(ATTR_ADDRESS, "adresse_usager");
		this.sqlEquivalence.put(ATTR_CITY, "ville_usager");
		this.sqlEquivalence.put(ATTR_ZIPCODE, "code_postal_usager");
		this.sqlEquivalence.put(ATTR_EMAIL, "email_usager");
		this.sqlEquivalence.put(ATTR_PATRONAGE, "accompagnement");
		this.sqlEquivalence.put(ATTR_SITE, "nom_site");
		this.sqlEquivalence.put(ATTR_DISTRICT, "nom_quartier");
		this.sqlEquivalence.put(ATTR_CSP, "libelle_csp");		
		this.sqlEquivalence.put(ATTR_FORMATION, "nom_formation");
		this.sqlEquivalence.put(ATTR_VISITCOUNT, "COUNT(usager.id_usager)");		
	}
	
	
	public List<RequeteEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<RequeteEntity> listing = entityManager.createNamedQuery("requeteEntity.findAll", RequeteEntity.class).getResultList();
		return listing;
	}	
	
	public String createPersonalQuery(Map<String, String> queryObjects, Map<String, String> querySelectObjects ){
		String personalQuery = "";
		return personalQuery;	
	}
	
	public void executePersonalQuery(Map<String, String> queryObjects, List<String> querySelectObjects){
		initializeSqlEquivalence();
		String personalQuery = "";
		personalQuery = createSelect(querySelectObjects);
		personalQuery = personalQuery.concat(createFrom(querySelectObjects));
		System.out.println(personalQuery);
		
	}

	private String createSelect(List<String> querySelectObjects){
		String personalQuery = "SELECT ";
		for (Integer i = 0; i < querySelectObjects.size(); i ++){
			if(i > 0)
				personalQuery = personalQuery.concat(", ");
			
			switch(querySelectObjects.get(i)){
				case ATTR_ALL:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_ALL));
					break;
				case ATTR_GENDER:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_GENDER));
					break;
				case ATTR_SURNAME:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_SURNAME));
					break;
				case ATTR_NAME:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_NAME));
					break;
				case ATTR_DOB:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DOB));
					break;
				case ATTR_ADDRESS:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_ADDRESS));
					break;
				case ATTR_CITY:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_CITY));
					break;
				case ATTR_ZIPCODE:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_ZIPCODE));
					break;
				case ATTR_EMAIL:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_EMAIL));
					break;
				case ATTR_PATRONAGE:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_PATRONAGE));
					break;
				case ATTR_SITE:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_SITE));
					break;
				case ATTR_DISTRICT:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISTRICT));
					break;
				case ATTR_CSP:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_CSP));
					break;
				case ATTR_FORMATION:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_FORMATION));
					break;
				case ATTR_VISITCOUNT:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_VISITCOUNT));
					break;								
			}			
		}		
		return personalQuery;
	}
	
	private String createFrom(List<String> querySelectObjects){
		String personalQuery = " FROM usager";
		if(querySelectObjects.contains(ATTR_SITE))
			personalQuery = personalQuery.concat(", site");
		if(querySelectObjects.contains(ATTR_DISTRICT))
			personalQuery = personalQuery.concat(", quartier");
		if(querySelectObjects.contains(ATTR_CSP))
			personalQuery = personalQuery.concat(", csp");
		if(querySelectObjects.contains(ATTR_FORMATION))
			personalQuery = personalQuery.concat(", niveau_formation");
		
		return personalQuery;
	}
	
	private String createWhere(Map<String, String> queryObjects, List<String> querySelectObjects){
		String personalQuery = " WHERE ";
		Boolean whereEmpty = true;
		for (Integer i = 0; i < querySelectObjects.size(); i ++){
			if(i > 0)
				personalQuery = personalQuery.concat("AND ");
	
			if(querySelectObjects.get(i) == ATTR_SITE){
				personalQuery = personalQuery.concat("usager.id_site_inscription = site.id_site ");
				whereEmpty = false;
			}
			if(querySelectObjects.get(i) == ATTR_DISTRICT){
				personalQuery = personalQuery.concat("usager.id_quartier = quartier.id_quartier ");
				whereEmpty = false;
			}				
			if(querySelectObjects.get(i) == ATTR_CSP){
				personalQuery = personalQuery.concat("usager.id_csp = csp.id_csp ");
				whereEmpty = false;
			}
			if(querySelectObjects.get(i) == ATTR_FORMATION){
				personalQuery = personalQuery.concat("usager.id_formation = csp.formation ");
				whereEmpty = false;
			}
		}
		
		
		
		return personalQuery;
	}
	
	
}
