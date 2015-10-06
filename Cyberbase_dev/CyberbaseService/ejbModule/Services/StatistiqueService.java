package Services;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.cyberbase.entities.ProfessionnelEntity;
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
			
	private final String FIELD_SEARCH_PANEL 		= "searchPanel";
	private final String FIELD_GENDER 				= "gender";
	private final String FIELD_CITY 				= "city";
	private final String FIELD_CSP					= "csp";
	private final String FIELD_DISTRICT 			= "district";
	private final String FIELD_FORMATION 			= "formation";
	private final String FIELD_OBJECTIVE 			= "objective";
	private final String FIELD_VISIT_MIN 			= "visitMin";
	private final String FIELD_VISIT_MAX 			= "visitMax";
	private final String FIELD_DATE_START 			= "dateStart";
	private final String FIELD_DATE_END 			= "dateEnd";
	
	private final String SAVE_QUERY				 	= "saveQuery";
	private final String FIELD_QUERY_NAME 			= "queryName";
	
	
	
	private Map<String, String> sqlEquivalence;
		
	@PersistenceContext
	EntityManager entityManager;
	
	public List<RequeteEntity> findAll(){
		
		List<RequeteEntity> listing = entityManager.createNamedQuery("requeteEntity.findAll", RequeteEntity.class).getResultList();
		return listing;
	}
	
	
	public List<RequeteEntity> findPersonalQueries(ProfessionnelEntity pro){		
		Query query = entityManager.createNamedQuery("requeteEntity.findPersonalQueries", RequeteEntity.class);
		query.setParameter("id", pro.getId_professionnel());
		@SuppressWarnings("unchecked")
		List<RequeteEntity> queryList = query.getResultList();
		return queryList;
	}
	
	
	private void initializeSqlEquivalence()	
	{
		this.sqlEquivalence = new HashMap<String, String>();
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
	

	public List<UsagerEntity> createPersonalQuery(Map<String, String> queryObjects, List<String> querySelectObjects, ProfessionnelEntity logged){
		initializeSqlEquivalence();
		String personalQuery = "";
		String selectPart = "SELECT ";
		String fromPart = " FROM usager ";
		String wherePart = "";
		Boolean whereIsEmpty = true;
		Boolean firstWhere = true;
		String groupByPart = "";
		String havingPart = "";
		String orderByPart = "";
		Map<String, Object> setParameterElements = new HashMap<String, Object>();
		Boolean saveQuery = false;
		String queryName = "";
		
		for (Integer i = 0; i < querySelectObjects.size(); i ++){			
			switch(querySelectObjects.get(i)){
					
				case ATTR_DISPLAY_GENDER:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_GENDER));
					break;
					
				case ATTR_DISPLAY_SURNAME:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_SURNAME));
					break;
					
				case ATTR_DISPLAY_NAME:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_NAME));
					break;
					
				case ATTR_DISPLAY_DOB:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_DOB));
					break;
					
				case ATTR_DISPLAY_ADDRESS:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_ADDRESS));
					break;
					
				case ATTR_DISPLAY_CITY:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_CITY));
					break;
					
				case ATTR_DISPLAY_ZIPCODE:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_ZIPCODE));
					break;
					
				case ATTR_DISPLAY_EMAIL:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_EMAIL));
					break;
					
				case ATTR_DISPLAY_PATRONAGE:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_PATRONAGE));
					break;
					
				case ATTR_DISPLAY_SITE:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_SITE));
					fromPart = fromPart.concat(", site");
					if(whereIsEmpty){
						wherePart = wherePart.concat(" WHERE ");
						whereIsEmpty = false;
					}
					if(!firstWhere){
						wherePart = wherePart.concat(" AND ");
					}
					wherePart = wherePart.concat("usager.id_site_inscription = site.id_site");
					firstWhere = false;					
					break;
					
				case ATTR_DISPLAY_DISTRICT:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_DISTRICT));
					fromPart = fromPart.concat(", quartier");
					if(whereIsEmpty){
						wherePart = wherePart.concat(" WHERE ");
						whereIsEmpty = false;
					}
					if(!firstWhere){
						wherePart = wherePart.concat(" AND ");
					}
					wherePart = wherePart.concat("usager.id_quartier = quartier.id_quartier");
					firstWhere = false;
					break;
					
				case ATTR_DISPLAY_CSP:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_CSP));
					fromPart = fromPart.concat(", csp");
					if(whereIsEmpty){
						wherePart = wherePart.concat(" WHERE ");
						whereIsEmpty = false;
					}
					if(!firstWhere){
						wherePart = wherePart.concat(" AND ");
					}
					wherePart = wherePart.concat("usager.id_csp = csp.id_csp");
					firstWhere = false;
					break;
					
				case ATTR_DISPLAY_FORMATION:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_FORMATION));
					fromPart = fromPart.concat(", niveau_formation");
					if(whereIsEmpty){
						wherePart = wherePart.concat(" WHERE ");
						whereIsEmpty = false;
					}
					if(!firstWhere){
						wherePart = wherePart.concat(" AND ");
					}
					wherePart = wherePart.concat("usager.id_formation = niveau_formation.id_formation");
					firstWhere = false;
					break;
					
				case ATTR_DISPLAY_VISITCOUNT:
					if(i > 0){
						selectPart = selectPart.concat(", ");
					}
					selectPart = selectPart.concat(sqlEquivalence.get(ATTR_DISPLAY_VISITCOUNT));					
					groupByPart = groupByPart.concat(" GROUP BY usager.id_usager");
					if(querySelectObjects.contains(ATTR_DISPLAY_SITE))
						groupByPart = groupByPart.concat(", site.nom_site");
					if(querySelectObjects.contains(ATTR_DISPLAY_FORMATION))
						groupByPart = groupByPart.concat(", niveau_formation.nom_formation");
					if(querySelectObjects.contains(ATTR_DISPLAY_DISTRICT))
						groupByPart = groupByPart.concat(", quartier.nom_quartier");
					if(querySelectObjects.contains(ATTR_DISPLAY_CSP))
						groupByPart = groupByPart.concat(", csp.libelle_csp");
					break;						
			}
		}	
		
		
		Set listKeys=queryObjects.keySet();  
		Iterator iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			
							
			if(queryObjects.get(key) != null)
			{
				switch((String)key)
				{
					case FIELD_SEARCH_PANEL:
						
						
						if(!queryObjects.get(FIELD_SEARCH_PANEL).equals("all"))
						{
							if(whereIsEmpty){
								wherePart = wherePart.concat(" WHERE ");
								whereIsEmpty = false;
							}
							if(!firstWhere){
								wherePart = wherePart.concat(" AND ");
							}
							wherePart = wherePart.concat(" usager.id_site_inscription = :id_site ");
							firstWhere = false;
							setParameterElements.put("id_site", Integer.valueOf(queryObjects.get(FIELD_SEARCH_PANEL)));
						}
						break;
						
					case FIELD_GENDER:
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						wherePart = wherePart.concat(" usager.civilite_usager = :civilite ");
						firstWhere = false;
						setParameterElements.put("civilite", queryObjects.get(FIELD_GENDER));
						break;
						
					case FIELD_CITY:						
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						wherePart = wherePart.concat(" usager.ville_usager = :ville ");
						firstWhere = false;
						setParameterElements.put("ville", queryObjects.get(FIELD_CITY));
						break;
						
					case FIELD_DISTRICT:
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						wherePart = wherePart.concat(" usager.id_quartier = :id_quartier ");
						firstWhere = false;
						setParameterElements.put("id_quartier", Integer.valueOf(queryObjects.get(FIELD_DISTRICT)));
						break;
						
					case FIELD_FORMATION:
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						wherePart = wherePart.concat(" usager.id_formation = :id_formation ");
						firstWhere = false;
						setParameterElements.put("id_formation",Integer.valueOf(queryObjects.get(FIELD_FORMATION)));
						break;
					
					case FIELD_CSP:
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						wherePart = wherePart.concat(" usager.id_csp = :id_csp ");
						firstWhere = false;
						setParameterElements.put("id_csp",Integer.valueOf(queryObjects.get(FIELD_CSP)));
						break;
					case FIELD_OBJECTIVE:
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						fromPart = fromPart.concat(",affectation");
						wherePart = wherePart.concat(" usager.id_usager = affectation.id_usager AND affectation.id_demarche = :id_demarche ");
						firstWhere = false;
						setParameterElements.put("id_demarche", Integer.valueOf(queryObjects.get(FIELD_OBJECTIVE)));
						break;
						
					case FIELD_VISIT_MIN:
						
						fromPart = fromPart.concat(", affectation");
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						wherePart = wherePart.concat(" usager.id_usager = affectation.id_usager");
						if(groupByPart.equals(""))
							groupByPart = groupByPart.concat(" GROUP BY usager.id_usager");
						havingPart = havingPart.concat(" HAVING COUNT(affectation.id_affectation) BETWEEN :visitMin AND :visitMax ");
						setParameterElements.put("visitMin", Integer.valueOf(queryObjects.get(FIELD_VISIT_MIN)));
						setParameterElements.put("visitMax", Integer.valueOf(queryObjects.get(FIELD_VISIT_MAX)));
						break;
						
						
					case FIELD_VISIT_MAX:
						break;
						
					case FIELD_DATE_START:
						fromPart = fromPart.concat(", affectation");
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						
						wherePart = wherePart.concat(" affectation.date_debut_affectation >= :dateStart AND affectation.date_fin_affectation <= :dateEnd ");
						firstWhere = false;
						setParameterElements.put("dateStart", Timestamp.valueOf(queryObjects.get(FIELD_DATE_START)+ " 00:00:00"));
						setParameterElements.put("dateEnd", Timestamp.valueOf(queryObjects.get(FIELD_DATE_END)+ " 23:59:59"));					
						break;
						
					case SAVE_QUERY:
						saveQuery = true;
						break;
						
					case FIELD_QUERY_NAME:
						queryName = queryObjects.get(FIELD_QUERY_NAME);
						break;
				}	
					
						
				
			}
		}
		
		personalQuery = personalQuery.concat(selectPart);
		personalQuery = personalQuery.concat(fromPart);
		personalQuery = personalQuery.concat(wherePart);
		personalQuery = personalQuery.concat(groupByPart);
		personalQuery = personalQuery.concat(havingPart);
		personalQuery = personalQuery.concat(orderByPart);
		personalQuery = personalQuery.concat(";");
		
		System.out.println(selectPart);
		System.out.println(fromPart);
		System.out.println(wherePart);
		System.out.println(groupByPart);
		System.out.println(havingPart);
		System.out.println(orderByPart);
		
		if(saveQuery)
		{
			savePersonalQuery(personalQuery, setParameterElements, queryName, logged);
		}
		List<UsagerEntity> result = executePersonalQuery(personalQuery, setParameterElements);
		System.out.println(setParameterElements.toString());
		return result;
			
	}		
			
	private void savePersonalQuery(String personalQuery, Map<String, Object> setParameterElements, String queryName, ProfessionnelEntity logged)
	{	
		RequeteEntity savedQuery = new RequeteEntity();
		savedQuery.setNom_requete(queryName);
		savedQuery.setId_professionnel(logged.getId_professionnel());
		String contenuRequete = personalQuery;
		contenuRequete = personalQuery.concat(setParameterElements.toString());
		System.out.println("PQ: " + contenuRequete);
		savedQuery.setContenu_requete(contenuRequete);
			
		entityManager.persist(savedQuery);			
	}
	
	public void executeSavedQuery(RequeteEntity savedQuery){
		Map<String, Object> setParameterElements = new HashMap<String, Object>();
		Integer index = savedQuery.getContenu_requete().lastIndexOf(";");
		String setParameterElementsToString = savedQuery.getContenu_requete().substring(index+1);
		System.out.println(setParameterElementsToString);
	}
	

	@SuppressWarnings("unchecked")
	private List<UsagerEntity> executePersonalQuery(String query,Map<String, Object> setParameterElements){
		Query executedQuery = entityManager.createNativeQuery(query) ;
		List<UsagerEntity> result = new ArrayList();
		Set listKeys = setParameterElements.keySet();  
		
		Iterator iterateur = listKeys.iterator();
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			System.out.println((String)key + setParameterElements.get(key) );
			executedQuery.setParameter((String)key, setParameterElements.get(key));
			
			
		}	
		System.out.println(executedQuery.toString().getClass());
		result = executedQuery.getResultList();
		
	    for(int i = 0; i < result.size(); i++)
	    	 System.out.println("i" + result.get(i));
		
	    return result;
	}
}
