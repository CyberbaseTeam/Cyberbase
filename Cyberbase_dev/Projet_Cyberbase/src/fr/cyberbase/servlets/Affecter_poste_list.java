package fr.cyberbase.servlets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

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

import Services.AffectationService;
import fr.cyberbase.entities.AffectationEntity;
import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;

/**
 * Servlet implementation class Affecter_poste_list
 */
@WebServlet("/affecter_poste_list")
public class Affecter_poste_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_SECTION= "section";
	@EJB
	AffectationService affectationService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Affecter_poste_list() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "CONSOLE");	
		CookieTools cookieTools = new CookieTools();
		String techId = null;
		Login login = new Login();
		String pageParameter = "";
		Integer idPageParameter = null;
		
		pageParameter = request.getParameter("page");
		if (pageParameter != null) {
			idPageParameter = Integer.valueOf(pageParameter);
		}
		Integer caseList = null;
		
		//Récupéraction du cookie
		//Décryptage du cookie
		//Récupération du TechID du professionnel connecté
		Cookie cookies [] = request.getCookies();
		for(Cookie cookie: cookies)
		{
			if(cookie.getName().equals(CookieTools.COOKIE_KEY)) 
	        {
				String tokenCookie = cookie.getValue();
				try {
					login = cookieTools.getLogin(tokenCookie);
				} catch (InvalidKeyException | NoSuchPaddingException
						| NoSuchAlgorithmException | IllegalBlockSizeException
						| BadPaddingException e) {
					e.printStackTrace();
				}
	        	techId = login.getLoginTechId();
	        }
		}
		
		//Récupération de l'id du site du professionnel connecté
		Integer idSiteProfessionnel = login.getSiteId();
		request.setAttribute("idSite", idSiteProfessionnel);
		
		//Récupération de la date + heure actuelles comme référence
		java.util.Date date= new java.util.Date();
		Timestamp tsReference = new Timestamp(date.getTime());
		
		//Récupération de toutes les affectations actuelles du site du professionnel connecté (celles dont la date de fin est supérieure à la date actuelle)
		if (idPageParameter == null || idPageParameter == 1) {
			List<AffectationEntity> affectationsOnGoing = affectationService.findAllOnGoing(tsReference);
			request.setAttribute("affectations", affectationsOnGoing);
			caseList = 1;
			request.setAttribute("caseList", caseList);
		}
		//Récupération de toutes les affectations passées du site
		else if (idPageParameter == 2) {
			List<AffectationEntity> affectationsPast = affectationService.findAllPast(tsReference);
			request.setAttribute("affectations", affectationsPast);
			caseList = 2;
			request.setAttribute("caseList", caseList);
		}
		request.getRequestDispatcher("/WEB-INF/affecter_poste_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "CONSOLE");	
		if (request.getParameter("edit") != null){
			String idAffectationParameter = request.getParameter("inputIdAffectation");
			Integer idAffectation = Integer.valueOf(idAffectationParameter);
			response.sendRedirect("affecter_poste_form?affectation="+idAffectation);
		}  else if (request.getParameter("delete") != null){
			String idAffectationParameter = request.getParameter("inputIdAffectation");
			Integer idAffectation = Integer.valueOf(idAffectationParameter);
			AffectationEntity affectation = affectationService.findById(idAffectation);
			affectationService.deleteAffectation (affectation);
			response.sendRedirect("affecter_poste_list");
		}
	}

}
