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
import javax.persistence.EntityExistsException;
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
	
	
	/**
	 * Fonction qui récupère toutes les requètes de la base
	 * @return List<RequeteEntity>	liste des requetes de type 
	 */
	public List<RequeteEntity> findAll(){
		
		List<RequeteEntity> listing = entityManager.createNamedQuery("requeteEntity.findAll", RequeteEntity.class).getResultList();
		return listing;
	}
	
	/**
	 * 
	 * @param	pro ProfessionnelEntity
	 * @return	List<RequeteEntity>	 liste des requetes du professionnel
	 */
	public List<RequeteEntity> findPersonalQueries(ProfessionnelEntity pro){		
		Query query = entityManager.createNamedQuery("requeteEntity.findPersonalQueries", RequeteEntity.class);
		query.setParameter("id", pro.getId_professionnel());
		@SuppressWarnings("unchecked")
		List<RequeteEntity> queryList = query.getResultList();
		return queryList;
	}
	
	/**
	 * 
	 * @param	requete RequeteEntitty
	 * @return	RequeteEntity	la requète demandée 
	 */
	public RequeteEntity findSpecificQuery(RequeteEntity requete){
		Query query = entityManager.createNamedQuery("requeteEntity.findSpecificQuery", RequeteEntity.class);
		query.setParameter("idPro", requete.getId_professionnel());
		query.setParameter("id", requete.getId_requete());
		RequeteEntity requestedQuery = (RequeteEntity) query.getSingleResult();
		return requestedQuery;
	}
	
	/**
	 * Fonction qui transforme les parametres d'affichage en nom de colonne de la BDD
	 */
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
		this.sqlEquivalence.put(ATTR_DISPLAY_VISITCOUNT, "COUNT(affectation.id_usager)");			
	}
	
	/**
	 * Fonction de création dynamique d'une requête SQL selon les choix de l'utilisateur
	 * @param queryObjects			Ensemble des objets utilisés dans le setParameter
	 * @param querySelectObjects	Ensemble des objets d'affichage demandés utilisés pour créer le SELECT de la requête SQL	
	 * @param logged				ProfessionnelEntity	Professionnel qui effectue la requête
	 * @return
	 */
	public List<Object> createPersonalQuery(Map<String, String> queryObjects, List<String> querySelectObjects, ProfessionnelEntity logged){
		initializeSqlEquivalence();
		String personalQuery = "";
		String selectPart = "SELECT ";
		String fromPart = " FROM usager ";
		String wherePart = "";
		Boolean whereIsEmpty = true;
		Boolean fromAffectation = false;
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
					if(!fromAffectation){
						fromPart = fromPart.concat(", affectation" );
						fromAffectation = true;
					}
					if(whereIsEmpty){
						wherePart = wherePart.concat(" WHERE ");
						whereIsEmpty = false;
					}
					if(!firstWhere){
						wherePart = wherePart.concat(" AND ");
					}
					wherePart = wherePart.concat("affectation.id_usager = usager.id_usager");
					groupByPart = groupByPart.concat(" GROUP BY affectation.id_usager");
					
					if(querySelectObjects.contains(ATTR_DISPLAY_GENDER))
						groupByPart = groupByPart.concat(", usager.civilite_usager");
					if(querySelectObjects.contains(ATTR_DISPLAY_NAME))
						groupByPart = groupByPart.concat(", usager.nom_usager");
					if(querySelectObjects.contains(ATTR_DISPLAY_SURNAME))
						groupByPart = groupByPart.concat(", usager.prenom_usager");
					if(querySelectObjects.contains(ATTR_DISPLAY_ADDRESS))
						groupByPart = groupByPart.concat(", usager.adresse_usager");
					if(querySelectObjects.contains(ATTR_DISPLAY_CITY))
						groupByPart = groupByPart.concat(", usager.ville_usager");
					if(querySelectObjects.contains(ATTR_DISPLAY_DOB))
						groupByPart = groupByPart.concat(", usager.date_naissance_usager");
					if(querySelectObjects.contains(ATTR_DISPLAY_EMAIL))
						groupByPart = groupByPart.concat(", usager.email_usager");
					if(querySelectObjects.contains(ATTR_DISPLAY_PATRONAGE))
						groupByPart = groupByPart.concat(", usager.accompagnement");
					if(querySelectObjects.contains(ATTR_DISPLAY_ZIPCODE))
						groupByPart = groupByPart.concat(", usager.code_postal_usager");
				
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
						
						if(!fromAffectation){
							fromPart = fromPart.concat(", affectation" );
							fromAffectation = true;
						}
						wherePart = wherePart.concat(" usager.id_usager = affectation.id_usager AND affectation.id_demarche = :id_demarche ");
						firstWhere = false;
						setParameterElements.put("id_demarche", Integer.valueOf(queryObjects.get(FIELD_OBJECTIVE)));
						break;
						
					case FIELD_VISIT_MIN:
						if(!fromAffectation){
							fromPart = fromPart.concat(", affectation" );
							fromAffectation = true;
						}
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
						if(querySelectObjects.contains(ATTR_DISPLAY_SITE))
							groupByPart = groupByPart.concat(", site.nom_site");
						if(querySelectObjects.contains(ATTR_DISPLAY_FORMATION))
							groupByPart = groupByPart.concat(", niveau_formation.nom_formation");
						if(querySelectObjects.contains(ATTR_DISPLAY_DISTRICT))
							groupByPart = groupByPart.concat(", quartier.nom_quartier");
						if(querySelectObjects.contains(ATTR_DISPLAY_CSP))
							groupByPart = groupByPart.concat(", csp.libelle_csp");
						havingPart = havingPart.concat(" HAVING COUNT(affectation.id_affectation) BETWEEN :visitMin AND :visitMax ");
						setParameterElements.put("visitMin", Integer.valueOf(queryObjects.get(FIELD_VISIT_MIN)));
						setParameterElements.put("visitMax", Integer.valueOf(queryObjects.get(FIELD_VISIT_MAX)));
						break;
						
						
					case FIELD_VISIT_MAX:
						break;
						
					case FIELD_DATE_START:
						if(!fromAffectation){
							fromPart = fromPart.concat(", affectation" );
							fromAffectation = true;
						}
						if(whereIsEmpty){
							wherePart = wherePart.concat(" WHERE ");
							whereIsEmpty = false;
						}
						if(!firstWhere){
							wherePart = wherePart.concat(" AND ");
						}
						
						wherePart = wherePart.concat(" affectation.date_debut_affectation BETWEEN :dateStart AND :dateEnd AND affectation.id_usager = usager.id_usager");
						firstWhere = false;
						if(groupByPart.equals(""))
							groupByPart = groupByPart.concat(" GROUP BY usager.id_usager");
						if(querySelectObjects.contains(ATTR_DISPLAY_SITE))
							groupByPart = groupByPart.concat(", site.nom_site");
						if(querySelectObjects.contains(ATTR_DISPLAY_FORMATION))
							groupByPart = groupByPart.concat(", niveau_formation.nom_formation");
						if(querySelectObjects.contains(ATTR_DISPLAY_DISTRICT))
							groupByPart = groupByPart.concat(", quartier.nom_quartier");
						if(querySelectObjects.contains(ATTR_DISPLAY_CSP))
							groupByPart = groupByPart.concat(", csp.libelle_csp");
					
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
		System.out.println(personalQuery);
			
		if(saveQuery)
		{
			savePersonalQuery(queryObjects, querySelectObjects, queryName, logged);
		}
		List<Object> result = executePersonalQuery(personalQuery, setParameterElements);
		System.out.println(setParameterElements.toString());
		return result;
			
	}		
	
	/**
	 * Fonction d'enregistrement d'une requête personnalisée
	 * @param queryObjects			Ensemble des objets utilisés dans le setParameter
	 * @param querySelectObjects	Ensemble des objets d'affichage demandés utilisés pour créer le SELECT de la requête SQL	
	 * @param queryName		String	Nom sous lequel sera enregistré la requête
	 * @param logged				ProfessionnelEntity	Professionnel qui effectue la requête
	 */
	private void savePersonalQuery(Map<String, String> queryObjects, List<String> querySelectObjects, String queryName, ProfessionnelEntity logged)
			
	{	
		RequeteEntity savedQueryInfo = new RequeteEntity();
		savedQueryInfo.setNom_requete(queryName);
		savedQueryInfo.setId_professionnel(logged.getId_professionnel());
		String contenuRequete = querySelectObjects.toString();
		contenuRequete = contenuRequete.concat(";");
		contenuRequete = contenuRequete.concat(queryObjects.toString());
		
		savedQueryInfo.setContenu_requete(contenuRequete);			
		entityManager.persist(savedQueryInfo);	
		
	}
	
	/**
	 * Fonction de récupération des noms de colonnes, à partir d'une requête enregistrée dans la BDD
	 * @param queryContent	String	contenu de la requête enregistrée dans la BDD
	 * @return l'ensemble des noms de colonnes
	 */
	public String[] getDisplayData(String queryContent){
		String[] dataSplit = queryContent.split(";");
		String displayDataToString = dataSplit[0];
		displayDataToString = displayDataToString.replace("[", "");
		displayDataToString = displayDataToString.replace("]", "");
		displayDataToString = displayDataToString.replace(" ", "");
		
		String[] displayData  = displayDataToString.trim().split(",");
		
		return displayData;	
	}
	
	/**
	 * Fonction de récupération des objets pour le setParameter d'une requête enregistrée dans la BDD
	 * @param queryContent	la requête enregistrée dans la BDD
	 * @return les objets au format String qui seront passés en paramètres du setParameter
	 */
	public Map<String, String> getQueryParameter(String queryContent){
		String[] dataSplit = queryContent.split(";");
		String queryObjectsToString = dataSplit[1];
		
		queryObjectsToString = queryObjectsToString.replace("{", "");
		queryObjectsToString = queryObjectsToString.replace("}", "");
		Map<String, String> queryObjects  = new HashMap<String, String>();
		String[] queryObjectsData = queryObjectsToString.split(",");
		for(Integer i = 0; i < queryObjectsData.length; i++)
		{
			String key = queryObjectsData[i].split("=")[0].trim();
			String value = queryObjectsData[i].split("=")[1].trim();	
			
			if(key.equals("saveQuery") || key.equals("queryName"))
				continue;
			else
				queryObjects.put(key, value);
		}
		
		return queryObjects;	
	}
	

	/**
	 * Fonction d'exécution d'une requête déjà enregistrée dans la BDD
	 * @param requete	RequeteEntity à exécuter
	 * @return List<Object> l'ensemble des résultats de la requête
	 */
	public List<Object>  executeSavedQuery(RequeteEntity requete){
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		List<Object> result = new ArrayList<Object>();
		
		Integer index = requete.getContenu_requete().lastIndexOf(";");
		String query = requete.getContenu_requete().substring(0, index);
		String queryParametersToString = requete.getContenu_requete().substring(index+1);
		queryParametersToString = queryParametersToString.replace("{", "");
		queryParametersToString = queryParametersToString.replace("}", "");
		
		if(!queryParametersToString.equals(""))
		{
			String[] parameterElements = queryParametersToString.split(",");
			for(String element : parameterElements)
			{
				String keyValueTab[] = element.split("=");
				String key = keyValueTab[0].trim();			
				String value = keyValueTab[1].trim();
				
				if(key.startsWith("id") || key.startsWith("visit"))
					queryParameters.put(key, Integer.valueOf(value));
				else if(key.startsWith("date"))
					queryParameters.put(key, Timestamp.valueOf(value));
				else
					queryParameters.put(key,value);
			}
		}
		
		result = executePersonalQuery(query, queryParameters);
		
		return result;
		
	}
	
	/**
	 * Fonction d'execution d'une nouvelle requête
	 * @param query	La requêteSQL à exécuter
	 * @param setParameterElements les éléments du setParameter
	 * @return les résultat de la requête
	 */
	@SuppressWarnings("unchecked")
	private List<Object> executePersonalQuery(String query, Map<String, Object> setParameterElements){
		Query executedQuery = entityManager.createNativeQuery(query) ;
		List<Object> result = new ArrayList();
		Set listKeys = setParameterElements.keySet();  
		
		Iterator iterateur = listKeys.iterator();
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			
			executedQuery.setParameter((String)key, setParameterElements.get(key));			
		}	
		
		result = executedQuery.getResultList();
		
	    for(int i = 0; i < result.size(); i++)
	    	 System.out.println("result " + result.get(i));
		
	    return result;
	}
	
	
	

}
