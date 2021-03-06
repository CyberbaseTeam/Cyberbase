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
 * Servlet implementation class Salle_list_edit
 */
@WebServlet("/salle_list")
public class Salle_list_edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_SECTION= "section";
	private static final String ATTR_MESS	= "message";
	
	@EJB
	SiteService siteService;
	@EJB
	SalleService salleService;
	@EJB
	PosteService posteService;
	@EJB
	ProfessionnelService proService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Salle_list_edit() {
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
		request.setAttribute("loginId", techId);
		
		//Récupération du professionnel connecté
		ProfessionnelEntity professionnel = proService.findByTechId(techId);
				
		//Récupération de l'id du site du professionnel connecté
		Integer idSiteProfessionnel = login.getSiteId();
				
		//Récupération du site du professionnel connecté
		SiteEntity siteProfessionnel = siteService.findById(idSiteProfessionnel);
				
		List<SiteEntity> siteEntities = siteService.findAll();
		request.setAttribute("sitePro", siteProfessionnel);
		
		String statutParameter = request.getParameter("statut");
		
		if (statutParameter != null) {
			
			String message = "";
			
			switch (statutParameter){
			
				case "createSalle":
					message = "Salle bien créée";
					request.setAttribute(ATTR_MESS, message);
					break;
					
			}
			
		}
		
		request.getRequestDispatcher("/WEB-INF/salle_list_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "CONSOLE");	
		if (request.getParameter("editSalle") != null) {
			String inputIdSalle = request.getParameter("inputIdSalle");
			Integer idSalle = Integer.valueOf(inputIdSalle);
			response.sendRedirect("salle_form?id="+idSalle);
		} else if (request.getParameter("deleteSalle") != null) {
			String inputIdSalle = request.getParameter("inputIdSalle");
			Integer idSalle = Integer.valueOf(inputIdSalle);
			SalleEntity salle = salleService.findById(idSalle);
			List<PosteEntity> postes = salle.getPostes();
			for (PosteEntity poste : postes) {
				PosteEntity poste2 = posteService.findById(poste.getId_poste());
				posteService.deletePoste(poste2);
			}
			Integer idSiteProfessionnel = salle.getSite().getId_site();
			salleService.deleteSalle(salle);
			SiteEntity siteProfessionnel = siteService.findById(idSiteProfessionnel);
			response.sendRedirect("salle_list");
		}
		
	}

}
