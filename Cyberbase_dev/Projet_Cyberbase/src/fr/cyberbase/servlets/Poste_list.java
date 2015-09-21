package fr.cyberbase.servlets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
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

import Services.PosteService;
import Services.ProfessionnelService;
import Services.SalleService;
import Services.SiteService;
import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SalleEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;

/**
 * Servlet implementation class Poste_list
 */
@WebServlet("/poste_list")
public class Poste_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	SiteService siteService;
	@EJB
	ProfessionnelService proService;
	@EJB
	PosteService posteService;
	@EJB
	SalleService salleService;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Poste_list() {
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
		request.setAttribute("login", techId);
		
		//Récupération du professionnel connecté
		ProfessionnelEntity professionnel = proService.findByTechId(techId);
				
		//Récupération de l'id du site du professionnel connecté
		Integer idSiteProfessionnel = login.getSiteId();
				
		//Récupération du site du professionnel connecté
		SiteEntity siteProfessionnel = siteService.findById(idSiteProfessionnel);
				
		List<SiteEntity> siteEntities = siteService.findAll();
		request.setAttribute("sitePro", siteProfessionnel);
		
		request.getRequestDispatcher("/WEB-INF/poste_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("libererPoste") != null) {
			String idParameterPoste = request.getParameter("inputIdPoste");
			Integer idPoste = Integer.valueOf(idParameterPoste);
			PosteEntity poste = posteService.findById(idPoste);
			posteService.changeDisponibility(poste);
			response.sendRedirect("poste_list");
		} else if (request.getParameter("libererPostesSalle") != null) {
			String idParameterSalle = request.getParameter("inputIdSalle");
			Integer idSalle = Integer.valueOf(idParameterSalle);
			SalleEntity salle = salleService.findById(idSalle);
			List<PosteEntity> postes = salle.getPostes();
			for (PosteEntity poste : postes) {
				if (poste.getDisponibilite() == false){
					posteService.changeDisponibility(poste);
				}
	        }
			response.sendRedirect("poste_list");
		} else if (request.getParameter("libererPostesSite") != null) {
			String idParameterSite = request.getParameter("inputIdSite");
			Integer idSite = Integer.valueOf(idParameterSite);
			SiteEntity site = siteService.findById(idSite);
			Set<SalleEntity> salles = site.getSalles();
			for (SalleEntity salle : salles) {
				List<PosteEntity> postes = salle.getPostes();
				for (PosteEntity poste : postes) {
					if (poste.getDisponibilite() == false){
						posteService.changeDisponibility(poste);
					}
		        }
	        }
			response.sendRedirect("poste_list");
		} else if (request.getParameter("editSalle") != null) {
			String idParameterSalle = request.getParameter("idSalle");
			Integer idSalle = Integer.valueOf(idParameterSalle);
			response.sendRedirect("salle_form?id="+idSalle);
		}
	}
	
	

}
