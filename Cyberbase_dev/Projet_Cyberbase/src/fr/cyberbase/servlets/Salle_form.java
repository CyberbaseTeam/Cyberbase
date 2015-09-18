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
import fr.cyberbase.entities.ProfessionnelEntity;
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
		
		//Récupéraction du cookie
		//Décryptage du cookie
		//Récupération du TechID du professionnel connecté
		Cookie cookies [] = request.getCookies();
		for(Cookie cookie: cookies)
		{
			if(cookie.getName().equals(CookieTools.COOKIE_KEY)) 
	        {
				String tokenCookie = cookie.getValue();
	        	Login login = new Login();
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
		
		//Simulation de récupération de l'idTech d'un professionnel via le cookie
		String idTechProfessionnel = "P0000003";
				
		//Récupération du professionnel connecté
		ProfessionnelEntity professionnel = proService.findByTechId(idTechProfessionnel);
				
		//Récupération de l'id du site du professionnel connecté
		Integer idSiteProfessionnel = professionnel.getSite_reference().getId_site();
				
		//Récupération du site du professionnel connecté
		SiteEntity siteProfessionnel = siteService.findById(idSiteProfessionnel);
				
		List<SiteEntity> siteEntities = siteService.findAll();
		request.setAttribute("sitePro", siteProfessionnel);
		
		request.getRequestDispatcher("/WEB-INF/salle_form.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
