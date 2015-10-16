package fr.cyberbase.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import Services.AffectationService;
import Services.CspService;
import Services.DemarcheService;
import Services.FormationService;
import Services.ProfessionnelService;
import Services.QuartierService;
import Services.SiteService;
import Services.StatistiqueService;
import Services.UsagerService;
import fr.cyberbase.entities.CspEntity;
import fr.cyberbase.entities.DemarcheEntity;
import fr.cyberbase.entities.FormationEntity;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.QuartierEntity;
import fr.cyberbase.entities.RequeteEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.UsagerEntity;
import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;

/**
 * Servlet implementation class statistiques
 */
@WebServlet("/statistiques")
public class Statistiques extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATTR_SITES 			= "siteList";
	private static final String ATTR_QUARTIERS 		= "quartierList";
	private static final String ATTR_CSP	 		= "cspList";
	private static final String ATTR_FORMATION	 	= "formationList";
	private static final String ATTR_DEMARCHE	 	= "demarcheList";
	private static final String ATTR_REQUETES	 	= "requeteList";
	private static final String ATTR_CURRENTSTATS	= "currentStats";
	private static final String ATTR_SECTION		= "sectionName";
	private static final String ATTR_LOGIN			= "login";
	
	
	
	
	private static final String FIELD_DISPLAY_DATA 	= "displayData[]";
	private static final String FIELD_SEARCH_PANEL 	= "searchPanel";
	private static final String FIELD_GENDER 		= "gender";
	private static final String FIELD_CITY 			= "city";
	private static final String FIELD_DISTRICT 		= "district";
	private static final String FIELD_FORMATION 	= "formation";
	private static final String FIELD_CSP 			= "csp";
	private static final String FIELD_OBJECTIVE 	= "objective";
	private static final String FIELD_VISIT_MIN 	= "visitMin";
	private static final String FIELD_VISIT_MAX 	= "visitMax";
	private static final String FIELD_DATE_START 	= "dateStart";
	private static final String FIELD_DATE_END 		= "dateEnd";
	private static final String FIELD_SAVE_QUERY 	= "saveQuery";
	private static final String FIELD_QUERY_NAME 	= "queryName";
	
	private static final String CSV_SEPARATOR	 	= ",";
		
	List<SiteEntity> siteList;
	List<UsagerEntity> usagerList;
	List<CspEntity> cspList;
	List<FormationEntity> formationList;
	List<DemarcheEntity> demarcheList;
	List<QuartierEntity> quartierList;
	List<RequeteEntity> requeteList;
	
	Map<String, String> queryObjects = new HashMap<String, String>();
	
	List<String> querySelectObjects = new ArrayList<String>();
	List<String> columnNames = new ArrayList<String>();
	
	Map<String, Object> currentStats;
	
	@EJB
	StatistiqueService statistiqueService;
	
	@EJB
	SiteService siteService;
	
	@EJB
	QuartierService  quartierService;
	
	@EJB
	CspService  cspService;
	
	@EJB
	FormationService  formationService;
	
	@EJB
	DemarcheService  demarcheService;
	
	@EJB
	ProfessionnelService  professionnelService;
	
	@EJB	
	AffectationService affectationService;
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistiques() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		queryObjects = new HashMap<String, String>();
		
		querySelectObjects = new ArrayList<String>();
		columnNames = new ArrayList<String>();
				
		Login login = new Login();	
		Cookie cookies [] = request.getCookies();
		login = getLoginFromCookie(cookies);
		
		ProfessionnelEntity logged = professionnelService.findByTechId(login.getLoginTechId());
		initializeData(request, logged);
				
		if(request.getParameter("action") != null && request.getParameter("action").equals("personalQuery"))
		{
			RequeteEntity query = new RequeteEntity();
			query.setId_requete(Integer.valueOf(request.getParameter("queryId")));
			query.setId_professionnel(logged.getId_professionnel());
			RequeteEntity queryInfo = statistiqueService.findSpecificQuery(query);
			
			String[] displayData = statistiqueService.getDisplayData(queryInfo.getContenu_requete());
			prepareSelectObjectsAndColums(displayData);
			queryObjects = statistiqueService.getQueryParameter(queryInfo.getContenu_requete());
					
			List<Object> queryResult = statistiqueService.createPersonalQuery(queryObjects, querySelectObjects, logged);
			String htmlResult = queryResultToHtml(queryResult, querySelectObjects.size(), columnNames  );
					
			request.setAttribute("columnNames", columnNames);
			request.setAttribute("htmlResult", htmlResult);
			initializeData(request, logged);
							
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/statistiques.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		queryObjects = new HashMap<String, String>();
		
		querySelectObjects = new ArrayList<String>();
		columnNames = new ArrayList<String>();
		Login login = new Login();	
		Cookie cookies [] = request.getCookies();
		login = getLoginFromCookie(cookies);
		request.setAttribute(ATTR_LOGIN, login);
		ProfessionnelEntity logged = professionnelService.findByTechId(login.getLoginTechId());
				
		prepareSelectObjectsAndColums(request.getParameterValues(FIELD_DISPLAY_DATA));
						
		String searchPanel = request.getParameter(FIELD_SEARCH_PANEL);
		queryObjects.put(FIELD_SEARCH_PANEL, request.getParameter(FIELD_SEARCH_PANEL).toString());
		String gender = request.getParameter(FIELD_GENDER);
		if(gender != null)
			queryObjects.put(FIELD_GENDER, gender);
		
		String city = request.getParameter(FIELD_CITY);
		if(!city.equals(""))
			queryObjects.put(FIELD_CITY, city);
		
		String district = request.getParameter(FIELD_DISTRICT);
		if(!district.equals(""))
			queryObjects.put(FIELD_DISTRICT, district);		
		
		String csp = request.getParameter(FIELD_CSP);
		if(!csp.equals(""))
			queryObjects.put(FIELD_CSP, csp);		
		
		String formation = request.getParameter(FIELD_FORMATION);
		if(!formation.equals(""))
			queryObjects.put(FIELD_FORMATION, formation);	
		
		String objective = request.getParameter(FIELD_OBJECTIVE);
		if(!objective.equals(""))
			queryObjects.put(FIELD_OBJECTIVE, objective);	
		
		String visitMin = request.getParameter(FIELD_VISIT_MIN);
		if(!visitMin.equals(""))
			queryObjects.put(FIELD_VISIT_MIN, visitMin);	
		
		String visitMax = request.getParameter(FIELD_VISIT_MAX);
		if(!visitMax.equals(""))
			queryObjects.put(FIELD_VISIT_MAX, visitMax);	
		
		String dateStart = request.getParameter(FIELD_DATE_START);
		if(!dateStart.equals(""))
			queryObjects.put(FIELD_DATE_START, dateStart);	
		
		String dateEnd = request.getParameter(FIELD_DATE_END);
		if(!dateEnd.equals(""))
			queryObjects.put(FIELD_DATE_END, dateEnd);	
		
		String saveQuery = request.getParameter(FIELD_SAVE_QUERY);
		if(saveQuery != null)
			queryObjects.put(FIELD_SAVE_QUERY, saveQuery);	
		
		String queryName = request.getParameter(FIELD_QUERY_NAME);
		if(!queryName.equals(""))
			queryObjects.put(FIELD_QUERY_NAME, queryName);	
				
		List<Object> queryResult = statistiqueService.createPersonalQuery(queryObjects, querySelectObjects, logged);
		String htmlResult = queryResultToHtml(queryResult, querySelectObjects.size(), columnNames  );
				
		request.setAttribute("columnNames", columnNames);
		request.setAttribute("htmlResult", htmlResult);
		initializeData(request, logged);
		this.getServletContext().getRequestDispatcher("/WEB-INF/statistiques.jsp").forward(request, response);		
	}
	
	/**
	 * Fonction de création de la session à partir du cookie 
	 * @param cookies ensembles des cookies récupérés du navigateur
	 * @return Login	infos utilisateur connecté
	 * @throws UnsupportedEncodingException
	 */
	private Login getLoginFromCookie(Cookie[] cookies) throws UnsupportedEncodingException {
		Login login = new Login();
		CookieTools cookieTools = new CookieTools();
		for(Cookie cookie: cookies)
		{
			if(cookie.getName().equals(CookieTools.COOKIE_KEY)) 
	        {
				String tokenCookie = cookie.getValue();
				try {
					login = cookieTools.getLogin(tokenCookie);
					return login;
				} catch (InvalidKeyException | NoSuchPaddingException
						| NoSuchAlgorithmException | IllegalBlockSizeException
						| BadPaddingException e) {
					e.printStackTrace();
				}
	        }
		}
		return null;
	}

	/**
	 * Fonction qui initialise les infos envoyées systématiquement dans la requête
	 * @param request HttpServletRequest
	 * @param pro ProfessionnelEntity utilisateur actuel loggué sur l'application
	 */
	private void initializeData(HttpServletRequest request, ProfessionnelEntity pro){
		currentStats = getCurrentStats(request, pro.getSite_reference());
		siteList = siteService.findAll();
		quartierList = quartierService.findAll();
		cspList = cspService.findAll();
		formationList = formationService.findAll();
		demarcheList = demarcheService.findAll();
		requeteList = statistiqueService.findPersonalQueries(pro);
		
		request.setAttribute(ATTR_SITES, siteList);
		request.setAttribute(ATTR_QUARTIERS, quartierList);
		request.setAttribute(ATTR_CSP, cspList);
		request.setAttribute(ATTR_FORMATION, formationList);
		request.setAttribute(ATTR_DEMARCHE, demarcheList);
		request.setAttribute(ATTR_REQUETES, requeteList);
		request.setAttribute(ATTR_CURRENTSTATS, currentStats);
		request.setAttribute(ATTR_SECTION, "STATISTIQUES");	
	}

	/**
	 * Fonction d'initialisation des élémnets demandés à l'affichage ,et des noms de colonnes à afficher 
	 * @param displayData les éléments que l'utilisateur souhaite voir s'afficher
	 */
	private void prepareSelectObjectsAndColums(String[] displayData){
		if(displayData == null)
		{
			querySelectObjects.add("displayGender");
			columnNames.add("Civilité");
			querySelectObjects.add("displaySurname");
			columnNames.add("Prénom");
			querySelectObjects.add("displayName");
			columnNames.add("Nom");
			querySelectObjects.add("displayDOB");
			columnNames.add("Date de naissance");
			querySelectObjects.add("displayAddress");
			columnNames.add("Adresse");
			querySelectObjects.add("displayCity");
			columnNames.add("Ville");
			querySelectObjects.add("displayZipCode");
			columnNames.add("Code postal");
			querySelectObjects.add("displayEmail");
			columnNames.add("email");
			querySelectObjects.add("displayPatronage");
			columnNames.add("Acompagnement");
			querySelectObjects.add("displaySite");
			columnNames.add("Site de référence");
			querySelectObjects.add("displayDistrict");
			columnNames.add("Quartier");
			querySelectObjects.add("displayCsp");
			columnNames.add("CSP");
			querySelectObjects.add("displayFormation");	
			columnNames.add("Niveau de formation");
		}
		else{
			for(int i = 0; i < displayData.length; i++){
				querySelectObjects.add(displayData[i]);	
				
				switch(displayData[i]){
					case "displayGender":
						columnNames.add("Civilité");
						break;	
					
					case "displayName":
						columnNames.add("Nom");
						break;				
									
					case "displaySurname":
							columnNames.add("Prénom");
							break;
						
					case "displayDOB":
						columnNames.add("Date de naissance");
						break;
						
					case "displayAddress":
						columnNames.add("Adresse");
						break;
				
					case "displayCity":
						columnNames.add("Ville");
						break;
				
					case "displayZipCode":
						columnNames.add("Code Postal");
						break;
						
					case "displayEmail":
						columnNames.add("Email");
						break;
						
					case "displayPatronage":
						columnNames.add("Accompagnement");
						break;
						
					case "displaySite":
						columnNames.add("Site référence");
						break;
						
					case "displayDistrict":
						columnNames.add("Quartier");
						break;
						
					case "displayCsp":
						columnNames.add("CSP");
						break;
						
					case "displayFormation":
						columnNames.add("Niveau de formation");
						break;
						
					case "displayVisitCount":
						columnNames.add("Nombre de visites");
						break;
				}
			
			}				
		}
	}
	
	/**
	 * Fonction de transformation d'un resultat de requête SQL en HTML
	 * @param queryResult	résultat d'une requête SQL
	 * @param maxIndex		le nombre de colonnes à afficher
	 * @param columnNames	le nom des colonnes à afficher
	 * @return				le texte HTML affichant le résultat
	 */
	private String queryResultToHtml (List<Object> queryResult, Integer maxIndex, List<String> columnNames)
	{
		String htmlResult = "";		
		String prepareCsv = prepareCsv(columnNames, maxIndex, queryResult);		
		if(queryResult.size() == 0)
		{
			htmlResult = "<p class=\"noResultQuery\">Cette requète ne retourne aucune valeur.</p>";
		}
		else
		{
			htmlResult = "<table class=\"table queryResult\"><tr>";
			for(String name : columnNames)
			{
				htmlResult = htmlResult.concat("<th>");
				htmlResult = htmlResult.concat(name);
				htmlResult = htmlResult.concat("</th>");
			}
			htmlResult = htmlResult.concat("</tr>");
			
			Iterator itr = queryResult.iterator();
			while(itr.hasNext())
			{
			   Object[] obj = (Object[]) itr.next();
			   htmlResult = htmlResult.concat("<tr>");
			   for(int i = 0; i < maxIndex; i++)
			   {
				   htmlResult = htmlResult.concat("<td>");
				   htmlResult = htmlResult.concat(String.valueOf(obj[i]));
				   htmlResult = htmlResult.concat("</td>");
			   }
			}
			htmlResult = htmlResult.concat("</tr></table>");
			
			htmlResult = htmlResult.concat("<form method=\"post\" action=\"download\"\"><input type=\"submit\" name=\"export\" id=\"export\" value=\"export\"/></input>");
			htmlResult = htmlResult.concat("<input type=\"hidden\" value=\"");
			htmlResult = htmlResult.concat(prepareCsv);
			htmlResult = htmlResult.concat("\" name=\"exportValue\" id=\"exportValue\"/></input></form>");
		}
		return htmlResult;
	}
	
	/**
	 * Fonction de préparation des données de résultat SQL pour être transformée en fichier tableur .csv
	 * @param columnNames	nom des colonnes
	 * @param maxIndex		nombre de colonnes
	 * @param queryResult	résultat de requête SQL
	 * @return				les données au format .csv
	 */
	private String prepareCsv(List<String> columnNames, Integer maxIndex, List<Object> queryResult)
	{
		String csvContent = "";
		for(Integer i = 0; i < columnNames.size(); i++)
		{
			csvContent = csvContent.concat("'" + columnNames.get(i) + "'");
			if(i != columnNames.size() - 1)
				csvContent = csvContent.concat(CSV_SEPARATOR);
			
		}
		csvContent = csvContent.concat("\n");
		Iterator itr = queryResult.iterator();
		while(itr.hasNext())
		{
		   Object[] obj = (Object[]) itr.next();
		   
		   for(int i = 0; i < maxIndex; i++)
		   {
			   
			   csvContent = csvContent.concat(String.valueOf(obj[i]));
			   if(i != maxIndex -1)
					csvContent = csvContent.concat(CSV_SEPARATOR);
			   
		   }
		   csvContent = csvContent.concat("\n");
		}
		return csvContent;
	}
	

	/**
	 * Fonction de récupération des statistiques actuelles
	 * @param request	la requête entrante
	 * @param site		le site de la personne connectée
	 * @return			l'ensemble des statistiques actuelles
	 */
	private Map<String, Object> getCurrentStats(HttpServletRequest request, SiteEntity site){
		Map<String, Object> currentStats = new HashMap<String, Object>();
			
		Calendar todayStart = Calendar.getInstance();
		Calendar todayEnd = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		
		Timestamp currentDayStart = new Timestamp(todayStart.getTimeInMillis());
		Timestamp currentDayEnd = new Timestamp(todayEnd.getTimeInMillis());
		
		Calendar thisMonthStart = Calendar.getInstance();
		Calendar thisMonthEnd = Calendar.getInstance();
		
		thisMonthStart.set(Calendar.DAY_OF_MONTH, 1);
		thisMonthStart.set(Calendar.HOUR_OF_DAY, 0);
		thisMonthStart.set(Calendar.MINUTE, 0);
		thisMonthStart.set(Calendar.SECOND, 0);
		thisMonthStart.set(Calendar.MILLISECOND, 0);
		
		thisMonthEnd.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		thisMonthEnd.set(Calendar.HOUR_OF_DAY, 23);
		thisMonthEnd.set(Calendar.MINUTE, 59);
		thisMonthEnd.set(Calendar.SECOND, 59);
		thisMonthEnd.set(Calendar.MILLISECOND, 999);
				
		Timestamp currentMonthStart = new Timestamp(thisMonthStart.getTimeInMillis());
		Timestamp currentMonthEnd = new Timestamp(thisMonthEnd.getTimeInMillis());
		
		Calendar thisYearStart = Calendar.getInstance();
		Calendar thisYearEnd = Calendar.getInstance();
		
		thisYearStart.set(Calendar.MONTH, Calendar.JANUARY);
		thisYearStart.set(Calendar.DAY_OF_MONTH, 1);
		thisYearStart.set(Calendar.HOUR_OF_DAY, 0);
		thisYearStart.set(Calendar.MINUTE, 0);
		thisYearStart.set(Calendar.SECOND, 0);
		thisYearStart.set(Calendar.MILLISECOND, 0);
		
		thisYearEnd.set(Calendar.MONTH, Calendar.DECEMBER);
		thisYearEnd.set(Calendar.DAY_OF_MONTH, 31);
		thisYearEnd.set(Calendar.HOUR_OF_DAY, 23);
		thisYearEnd.set(Calendar.MINUTE, 59);
		thisYearEnd.set(Calendar.SECOND, 59);
		thisYearEnd.set(Calendar.MILLISECOND, 999);
		
		Timestamp currentYearStart = new Timestamp(thisYearStart.getTimeInMillis());
		Timestamp currentYearEnd = new Timestamp(thisYearEnd.getTimeInMillis());
		
		Long todaysVisits = affectationService.findVisitCountByPeriodAndSite(site, currentDayStart, currentDayEnd);	
		Long thisMonthsVisits = affectationService.findVisitCountByPeriodAndSite(site, currentMonthStart, currentMonthEnd);
		Long thisYearsVisits = affectationService.findVisitCountByPeriodAndSite(site, currentYearStart, currentYearEnd);
		
		BigInteger todaysNewCommers = affectationService.findNewCommersByPeriodAndSite(site, currentDayStart, currentDayEnd);	
		BigInteger thisMonthsNewCommers = affectationService.findNewCommersByPeriodAndSite(site, currentMonthStart, currentMonthEnd);
		BigInteger thisYearsNewCommers = affectationService.findNewCommersByPeriodAndSite(site, currentYearStart, currentYearEnd);
		
		Long mySiteUsers = affectationService.getDistinctAffectedUsers(site);
		
		currentStats.put("todaysVisits", todaysVisits);
		currentStats.put("thisMonthsVisits", thisMonthsVisits);
		currentStats.put("thisYearsVisits", thisYearsVisits);
		currentStats.put("todaysNewCommers", todaysNewCommers);
		currentStats.put("thisMonthsNewCommers", thisMonthsNewCommers);
		currentStats.put("thisYearsNewCommers", thisYearsNewCommers);
		currentStats.put("mySiteUsers", mySiteUsers);
		
		return currentStats;
	}
		
	
}
