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

import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SalleEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;
import Services.PosteService;
import Services.ProfessionnelService;
import Services.SalleService;
import Services.SiteService;

/**
 * Servlet implementation class Poste_form
 */
@WebServlet("/poste_form")
public class Poste_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_SECTION= "section";
	@EJB
	PosteService posteService;
	@EJB
	SalleService salleService;
	@EJB
	ProfessionnelService proService;
	@EJB
	SiteService siteService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Poste_form() {
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
				
		List<SiteEntity> siteEntities = siteService.findAll();
		request.setAttribute("sitePro", siteProfessionnel);
		
		//Récupération de l'id poste passé en paramètre d'URL dans le cas d'une modification 
		String idPosteParameter = request.getParameter("id");
		if (idPosteParameter != null) {
			Integer idPoste = Integer.valueOf(idPosteParameter);
			PosteEntity poste = posteService.findById(idPoste);
			Integer idSalle = poste.getSalle().getId_salle();
			request.setAttribute("idSalle", idSalle);
			request.setAttribute("poste", poste);
		}
		
		request.getRequestDispatcher("/WEB-INF/poste_form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "CONSOLE");
		if (request.getParameter("createPoste") != null) {
			PosteEntity poste = new PosteEntity();
			poste.setNom_poste(request.getParameter("nomPoste"));
			poste.setDisponibilite(true);
			String idSalleParameter = request.getParameter("salles");
			Integer idSalle = Integer.valueOf(idSalleParameter);
			SalleEntity salle = salleService.findById(idSalle);
			poste.setSalle(salle);
			posteService.createPoste(poste);
			response.sendRedirect("poste_list");
		} else if (request.getParameter("confirmEditPoste") != null){
			PosteEntity poste = new PosteEntity();
			poste.setNom_poste(request.getParameter("nomPoste"));
			String idSalleParameter = request.getParameter("salles");
			Integer idSalle = Integer.valueOf(idSalleParameter);
			SalleEntity salle = salleService.findById(idSalle);
			poste.setSalle(salle);
			String idPosteParameter = request.getParameter("idPoste");
			Integer idPoste = Integer.valueOf(idPosteParameter);
			poste.setId_poste(idPoste);
			String dispoPosteParameter = request.getParameter("disponibilitePoste");
			Boolean dispoPoste = Boolean.valueOf(dispoPosteParameter);
			poste.setDisponibilite(dispoPoste);
			posteService.updatePoste(poste);
			response.sendRedirect("salle_form?id="+idSalle);
		}
		
	}

}
