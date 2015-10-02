package fr.cyberbase.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
	
	 
	List<SiteEntity> siteList;
	List<UsagerEntity> usagerList;
	List<CspEntity> cspList;
	List<FormationEntity> formationList;
	List<DemarcheEntity> demarcheList;
	List<QuartierEntity> quartierList;
	List<RequeteEntity> requeteList;
	
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
		initializeData(request);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/statistiques.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Login login = new Login();	
		Cookie cookies [] = request.getCookies();
		login = getLoginFromCookie(cookies);
		
		ProfessionnelEntity logged = professionnelService.findByTechId(login.getLoginTechId());
		System.out.println(login.getLoginTechId());
		
		Map<String, String> queryObjects = new HashMap<String, String>();
		
		List<String> querySelectObjects = new ArrayList<String>();
		List<String> columnNames = new ArrayList<String>();
		
		
		if(request.getParameterValues(FIELD_DISPLAY_DATA) == null)
		{
			querySelectObjects.add("displayGender");
			querySelectObjects.add("displaySurname");
			querySelectObjects.add("displayName");
			querySelectObjects.add("displayDOB");
			querySelectObjects.add("displayAddress");
			querySelectObjects.add("displayCity");
			querySelectObjects.add("displayZipCode");
			querySelectObjects.add("displayEmail");
			querySelectObjects.add("displayPatronage");
			querySelectObjects.add("displaySite");
			querySelectObjects.add("displayDistrict");
			querySelectObjects.add("displayCsp");
			querySelectObjects.add("displayFormation");						
		}
		else{
			for(int i = 0; i < request.getParameterValues(FIELD_DISPLAY_DATA).length; i++){
				querySelectObjects.add(request.getParameterValues(FIELD_DISPLAY_DATA)[i]);				
				
			}				
		}
			
		for(int i = 0; i < querySelectObjects.size(); i++){
			System.out.println(querySelectObjects.get(i));
		}
		
		String searchPanel = request.getParameter(FIELD_SEARCH_PANEL);
			queryObjects.put(FIELD_SEARCH_PANEL, request.getParameter(FIELD_SEARCH_PANEL).toString());
		String gender = request.getParameter(FIELD_GENDER);
		if(gender != null)
			queryObjects.put(FIELD_GENDER, gender);
		System.out.println(gender);
		String city = request.getParameter(FIELD_CITY);
		if(!city.equals(""))
			queryObjects.put(FIELD_CITY, city);
		System.out.println(city.equals(""));
		String district = request.getParameter(FIELD_DISTRICT);
		if(!district.equals(""))
			queryObjects.put(FIELD_DISTRICT, district);		
		System.out.println(district.equals(""));
		String csp = request.getParameter(FIELD_CSP);
		if(!csp.equals(""))
			queryObjects.put(FIELD_CSP, csp);		
		System.out.println(csp.equals(""));		
		String formation = request.getParameter(FIELD_FORMATION);
		if(!formation.equals(""))
			queryObjects.put(FIELD_FORMATION, formation);	
		System.out.println(formation.equals(""));
		String objective = request.getParameter(FIELD_OBJECTIVE);
		if(!objective.equals(""))
			queryObjects.put(FIELD_OBJECTIVE, objective);	
		System.out.println(objective.equals(""));
		String visitMin = request.getParameter(FIELD_VISIT_MIN);
		if(!visitMin.equals(""))
			queryObjects.put(FIELD_VISIT_MIN, visitMin);	
		System.out.println(visitMin.equals(""));
		String visitMax = request.getParameter(FIELD_VISIT_MAX);
		if(!visitMax.equals(""))
			queryObjects.put(FIELD_VISIT_MAX, visitMax);	
		System.out.println(visitMax.equals(""));
		String dateStart = request.getParameter(FIELD_DATE_START);
		if(!dateStart.equals(""))
			queryObjects.put(FIELD_DATE_START, dateStart);	
		System.out.println(dateStart.equals(""));
		String dateEnd = request.getParameter(FIELD_DATE_END);
		if(!dateEnd.equals(""))
			queryObjects.put(FIELD_DATE_END, dateEnd);	
		System.out.println(dateEnd.equals(""));
		String saveQuery = request.getParameter(FIELD_SAVE_QUERY);
		if(saveQuery != null)
			queryObjects.put(FIELD_SAVE_QUERY, saveQuery);	
		System.out.println(saveQuery==null);
		String queryName = request.getParameter(FIELD_QUERY_NAME);
		if(!queryName.equals(""))
			queryObjects.put(FIELD_QUERY_NAME, queryName);	
		System.out.println(queryName.equals(""));
			
		Set listKeys=queryObjects.keySet();  
		Iterator iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			System.out.println (key+"=>"+queryObjects.get(key));		
		}	
		
		List<UsagerEntity> queryResult = statistiqueService.createPersonalQuery(queryObjects, querySelectObjects, logged);
		request.setAttribute("queryResult", queryResult);
		this.getServletContext().getRequestDispatcher("/WEB-INF/statistiques.jsp").forward(request, response);		
	}
	
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

	private void initializeData(HttpServletRequest request){
		siteList = siteService.findAll();
		quartierList = quartierService.findAll();
		cspList = cspService.findAll();
		formationList = formationService.findAll();
		demarcheList = demarcheService.findAll();
		
		request.setAttribute(ATTR_SITES, siteList);
		request.setAttribute(ATTR_QUARTIERS, quartierList);
		request.setAttribute(ATTR_CSP, cspList);
		request.setAttribute(ATTR_FORMATION, formationList);
		request.setAttribute(ATTR_DEMARCHE, demarcheList);
	}

}
