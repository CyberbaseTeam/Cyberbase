package fr.cyberbase.servlets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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

import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SalleEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.UsagerEntity;
import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;
import Services.PosteService;
import Services.ProfessionnelService;
import Services.SiteService;
import Services.UsagerService;

/**
 * Servlet implementation class Affecter_poste
 */
@WebServlet("/affecter_poste")
public class Affecter_poste extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	PosteService posteService;
	@EJB
	ProfessionnelService proService;
	@EJB
	SiteService siteService;
	@EJB
	UsagerService userService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Affecter_poste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookieTools cookieTools = new CookieTools();
		String techId = null;
		Login login = new Login();
		
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
		
		//Récupération du professionnel connecté
		ProfessionnelEntity professionnel = proService.findByTechId(techId);
				
		//Récupération de l'id du site du professionnel connecté
		Integer idSiteProfessionnel = login.getSiteId();
				
		//Récupération du site du professionnel connecté
		SiteEntity siteProfessionnel = siteService.findById(idSiteProfessionnel);
		
		//Récupération des salles du site concerné
		Set<SalleEntity> salles = siteProfessionnel.getSalles();
		request.setAttribute("salles", salles);
		
		//Récupération de la liste des usagers d'un site
		List<UsagerEntity> users = userService.findAllUsersBySite(siteProfessionnel);
		request.setAttribute("users", users);
		request.setAttribute("site", siteProfessionnel);
		
		//Récupération de l'id poste passé en paramètre d'URL pour une affectation
		String idPosteParameter = request.getParameter("id");
		if (idPosteParameter != null) {
			Integer idPoste = Integer.valueOf(idPosteParameter);
			PosteEntity poste = posteService.findById(idPoste);
			Integer idSalle = poste.getSalle().getId_salle();
			request.setAttribute("poste", poste);
		}
		
		request.getRequestDispatcher("/WEB-INF/affecter_poste.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
