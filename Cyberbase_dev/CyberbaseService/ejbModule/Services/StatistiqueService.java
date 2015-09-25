package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.cyberbase.entities.RequeteEntity;
import fr.cyberbase.entities.UsagerEntity;

@Stateless
public class StatistiqueService {

	private final String ATTR_DISPLAY_ALL 			= "displayAll";
	private final String ATTR_DISPLAY_GENDER 		= "displayGender";
	private final String ATTR_DISPLAY_SURNAME 		= "displaySurname";
	private final String ATTR_DISPLAY_NAME 			= "displayName";
	private final String ATTR_DISPLAY_DOB 			= "displayDOB";
	private final String ATTR_DISPLAY_ADDRESS 		= "displayAddress";
	private final String ATTR_DISPLAY_CITY 			= "displayCity";
	private final String ATTR_DISPLAY_ZIPCODE 		= "displayZipCode";
	private final String ATTR_DISPLAY_EMAIL	 		= "displayEmail";
	private final String ATTR_DISPLAY_PATRONAGE 	= "displayPatronage";
	private final String ATTR_DISPLAY_SITE 			= "displaySite";
	private final String ATTR_DISPLAY_DISTRICT 		= "displayDistrict";
	private final String ATTR_DISPLAY_CSP 			= "displayCsp";
	private final String ATTR_DISPLAY_FORMATION 	= "displayFormation";
	private final String ATTR_DISPLAY_VISITCOUNT 	= "displayVisitCount";
	
	private final String SAVE_QUERY				 	= "saveQuery";
	
	private final String FIELD_CITY					= "city";
	private final String FIELD_VISITMIN				= "visitMin";
	private final String FIELD_VISITMAX				= "visitMax";
	private final String FIELD_DATE_START			= "dateStart";
	private final String FIELD_DATE_END				= "dateEnd";
	
	
	
	private Map<String, String> sqlEquivalence;
		
	@PersistenceContext
	EntityManager entityManager;
	
	public List<RequeteEntity> findAll(){
		@SuppressWarnings("unchecked")
		List<RequeteEntity> listing = entityManager.createNamedQuery("requeteEntity.findAll", RequeteEntity.class).getResultList();
		return listing;
	}
	
	private void savePersonalQuery(String query){
		
	}
	
	
	private void initializeSqlEquivalence()	
	{
		this.sqlEquivalence = new HashMap<String, String>();
		this.sqlEquivalence.put(ATTR_DISPLAY_ALL, "*");
		this.sqlEquivalence.put(ATTR_DISPLAY_GENDER, "civilite_usager");
		this.sqlEquivalence.put(ATTR_DISPLAY_SURNAME, "prenom_usager");
		this.sqlEquivalence.put(ATTR_DISPLAY_NAME, "nom_usager");
		this.sqlEquivalence.put(ATTR_DISPLAY_DOB, "date_naissance_usager");
		this.sqlEquivalence.put(ATTR_DISPLAY_ADDRESS, "adresse_usager");
		this.sqlEquivalence.put(ATTR_DISPLAY_CITY, "ville_usager");
		this.sqlEquivalence.put(ATTR_DISPLAY_ZIPCODE, "code_postal_usager");
		this.sqlEquivalence.put(ATTR_DISPLAY_EMAIL, "email_usager");
		this.sqlEquivalence.put(ATTR_DISPLAY_PATRONAGE, "accompagnement");
		this.sqlEquivalence.put(ATTR_DISPLAY_SITE, "nom_site");
		this.sqlEquivalence.put(ATTR_DISPLAY_DISTRICT, "nom_quartier");
		this.sqlEquivalence.put(ATTR_DISPLAY_CSP, "libelle_csp");		
		this.sqlEquivalence.put(ATTR_DISPLAY_FORMATION, "nom_formation");
		this.sqlEquivalence.put(ATTR_DISPLAY_VISITCOUNT, "COUNT(usager.id_usager)");	
			
	}
	
	
		
	
	
	
	public void createPersonalQuery(Map<String, String> queryObjects, List<String> querySelectObjects){
		initializeSqlEquivalence();
		String personalQuery = "";
		personalQuery = createSelect(querySelectObjects);
		personalQuery = personalQuery.concat(createFrom(querySelectObjects));
		personalQuery = personalQuery.concat(createWhere(queryObjects, querySelectObjects));
		personalQuery = personalQuery.concat(createGroupBy(querySelectObjects));
		personalQuery = personalQuery.concat(createOrderBy(querySelectObjects));
		personalQuery = personalQuery.concat(";");
		System.out.println(personalQuery);
		
//		if(queryObjects.containsKey(SAVE_QUERY))
//			savePersonalQuery(personalQuery);
		
		//executePersonalQuery(personalQuery, queryObjects, querySelectObjects);
		
		
	}

	private String createSelect(List<String> querySelectObjects){
		String personalQuery = "SELECT ";
		for (Integer i = 0; i < querySelectObjects.size(); i ++){
			if(i > 0)
				personalQuery = personalQuery.concat(", ");
			
			switch(querySelectObjects.get(i)){
				case ATTR_DISPLAY_ALL:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_ALL));
					break;
				case ATTR_DISPLAY_GENDER:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_GENDER));
					break;
				case ATTR_DISPLAY_SURNAME:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_SURNAME));
					break;
				case ATTR_DISPLAY_NAME:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_NAME));
					break;
				case ATTR_DISPLAY_DOB:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_DOB));
					break;
				case ATTR_DISPLAY_ADDRESS:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_ADDRESS));
					break;
				case ATTR_DISPLAY_CITY:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_CITY));
					break;
				case ATTR_DISPLAY_ZIPCODE:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_ZIPCODE));
					break;
				case ATTR_DISPLAY_EMAIL:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_EMAIL));
					break;
				case ATTR_DISPLAY_PATRONAGE:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_PATRONAGE));
					break;
				case ATTR_DISPLAY_SITE:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_SITE));
					break;
				case ATTR_DISPLAY_DISTRICT:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_DISTRICT));
					break;
				case ATTR_DISPLAY_CSP:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_CSP));
					break;
				case ATTR_DISPLAY_FORMATION:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_FORMATION));
					break;
				case ATTR_DISPLAY_VISITCOUNT:
					personalQuery = personalQuery.concat(sqlEquivalence.get(ATTR_DISPLAY_VISITCOUNT));
					break;								
			}			
		}		
		return personalQuery;
	}
	
	private String createFrom(List<String> querySelectObjects){
		String personalQuery = " FROM usager";
		if(querySelectObjects.contains(ATTR_DISPLAY_SITE))
			personalQuery = personalQuery.concat(", site");
		if(querySelectObjects.contains(ATTR_DISPLAY_DISTRICT))
			personalQuery = personalQuery.concat(", quartier");
		if(querySelectObjects.contains(ATTR_DISPLAY_CSP))
			personalQuery = personalQuery.concat(", csp");
		if(querySelectObjects.contains(ATTR_DISPLAY_FORMATION))
			personalQuery = personalQuery.concat(", niveau_formation");
		
		return personalQuery;
	}
	
	private String createWhere(Map<String, String> queryObjects, List<String> querySelectObjects){
		String personalQuery = " WHERE ";
		Boolean whereIsEmpty = true;		
		for (Integer i = 0; i < querySelectObjects.size(); i ++){	
			if(querySelectObjects.get(i).equals(ATTR_DISPLAY_ALL))
				personalQuery =  "";
						
			switch(querySelectObjects.get(i)){
			case ATTR_DISPLAY_SITE:
				
				personalQuery = personalQuery.concat("usager.id_site_inscription = site.id_site AND id_site_inscription = :id_site ");
				whereIsEmpty = false;
				break;
				
			case ATTR_DISPLAY_DISTRICT:
				personalQuery = personalQuery.concat("usager.id_quartier = quartier.id_quartier AND id_quartier = :id_quartier ");
				whereIsEmpty = false;
				break;
			
			case ATTR_DISPLAY_CSP:
				personalQuery = personalQuery.concat("usager.id_csp = csp.id_csp AND id_csp = :id_csp ");
				whereIsEmpty = false;
				break;
				
			case ATTR_DISPLAY_FORMATION:
				personalQuery = personalQuery.concat("usager.id_formation = csp.formation AND id_formation = :id_formation ");
				whereIsEmpty = false;
				break;			
			}		
			
//			for (Integer j = 0; j < queryObjects.size(); j ++){	
//				
//				
//				switch(queryObjects.get(j)){
//				case FIELD_CITY:
//					
//					personalQuery = personalQuery.concat("usager.id_site_inscription = site.id_site AND id_site_inscription = :id_site ");
//					System.out.println(personalQuery);
//					break;
//					
//				case FIELD_VISITMIN:
//					personalQuery = personalQuery.concat("usager.id_quartier = quartier.id_quartier AND id_quartier = :id_quartier ");
//					System.out.println(personalQuery);
//					break;
//				
//				case FIELD_VISITMAX:
//					personalQuery = personalQuery.concat("usager.id_quartier = quartier.id_quartier AND id_quartier = :id_quartier ");
//					System.out.println(personalQuery);
//					break;
//				
//				case FIELD_DATE_START:
//					personalQuery = personalQuery.concat("usager.id_csp = csp.id_csp AND id_csp = :id_csp ");
//					System.out.println(personalQuery);
//					break;
//					
//				case FIELD_DATE_END:
//					personalQuery = personalQuery.concat("usager.id_formation = csp.formation AND id_formation = :id_formation ");
//					System.out.println(personalQuery);
//					break;			
//				}		
//			}
			
		}						
		return personalQuery;
	}
	
	private String createGroupBy(List<String> querySelectObjects){
		
		if(querySelectObjects.contains(ATTR_DISPLAY_VISITCOUNT))
			return " GROUP BY id_usager";
		else 
			return "";
	}
	
	private String createOrderBy(List<String> querySelectObjects){
		if(querySelectObjects.contains(ATTR_DISPLAY_ALL))
			return " ORDER BY nom_usager, prenom_usager";
		
		if(querySelectObjects.contains(ATTR_DISPLAY_NAME))
			return " ORDER BY nom_usager";	
		
		if(querySelectObjects.contains(ATTR_DISPLAY_SURNAME))
			return " ORDER BY prenom_usager";	
		
		return "";		
	}
	
	private void executePersonalQuery(String query, Map<String, String> queryObjects, List<String> querySelectObjects){
		
		

	}
}
