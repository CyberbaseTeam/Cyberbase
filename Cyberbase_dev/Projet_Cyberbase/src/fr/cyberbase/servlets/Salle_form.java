package fr.cyberbase.servlets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
 * Servlet implementation class Salle_form
 */
@WebServlet("/salle_form")
public class Salle_form extends HttpServlet {
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
    public Salle_form() {
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
		
		//Récupération de l'id salle passé en paramètre d'URL dans le cas d'une modification 
		String idSalleParameter = request.getParameter("id");
		if (idSalleParameter != null) {
			Integer idSalle = Integer.valueOf(idSalleParameter);
			request.setAttribute("salle", salleService.findById(idSalle));
		}
		
		request.getRequestDispatcher("/WEB-INF/salle_form.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("createSalle") != null) {
			SalleEntity salle = new SalleEntity();
			salle.setNom_salle(request.getParameter("nomSalle"));
			String idSiteParameter = request.getParameter("idSite");
			Integer idSite = Integer.valueOf(idSiteParameter);
			SiteEntity site = siteService.findById(idSite);
			salle.setSite(site);
			salleService.createSalle(salle);
			response.sendRedirect("salle_list");
		} else if(request.getParameter("confirmEditSalle") != null){
			String idSalleParameter = request.getParameter("idSalle");
			Integer idSalle = Integer.valueOf(idSalleParameter);
			String nomSalle = request.getParameter("nomSalle");
			SalleEntity salle = salleService.findById(idSalle);
			salle.setNom_salle(nomSalle);
			salleService.updateSalle(salle);
			response.sendRedirect("poste_list");
		} else if (request.getParameter("editPoste") != null){
			String idPosteParameter = request.getParameter("idPoste");
			Integer idPoste = Integer.valueOf(idPosteParameter);
			response.sendRedirect("poste_form?id="+idPoste);
		} else if (request.getParameter("deletePoste") != null){
			String idPosteParameter = request.getParameter("idPoste");
			Integer idPoste = Integer.valueOf(idPosteParameter);
			String idSalleParameter = request.getParameter("idSalle");
			Integer idSalle = Integer.valueOf(idSalleParameter);
			PosteEntity poste = new PosteEntity();
			poste.setId_poste(idPoste);
			posteService.deletePoste(poste);
			response.sendRedirect("salle_form?id="+idSalle);
		} 
		
	}

}
