package fr.cyberbase.servlets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
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

import org.joda.time.DateTime;

import Services.AffectationService;
import Services.PosteService;
import Services.ProfessionnelService;
import Services.SalleService;
import Services.SiteService;
import fr.cyberbase.entities.AffectationEntity;
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
	private static final String ATTR_SECTION	= "section";
	private static final String ATTR_MESS	= "message";
	
	
	
	@EJB
	SiteService siteService;
	@EJB
	ProfessionnelService proService;
	@EJB
	PosteService posteService;
	@EJB
	SalleService salleService;
	@EJB
	AffectationService affectationService;
       
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
		
		List<PosteEntity> postes = posteService.findAll();
	    List<AffectationEntity> affectations = affectationService.findAll();
	      
	      for(PosteEntity poste : postes){
	    	  if(poste.getDisponibilite() == false){
	    		  for (AffectationEntity affectation : affectations){
	    			  if (poste.getId_poste() == affectation.getPoste().getId_poste()){
	    				  DateTime dateActuelTest = new DateTime();
	    				  Timestamp tsDateActuelTest = new Timestamp(dateActuelTest.getMillis());
	    				  long tsNow = tsDateActuelTest.getTime();
	    				  long tsAff = affectation.getDate_fin_affectation().getTime();
	    				  if ( tsNow > tsAff ){
	    					  poste.setDisponibilite(true);
	  						  posteService.updatePoste(poste);
	    				  }
	    			  }
	    		  }
	    	  }
	      }
				
		List<SiteEntity> siteEntities = siteService.findAll();
		request.setAttribute("sitePro", siteProfessionnel);
		
		String statutParameter = request.getParameter("statut");
		
		if (statutParameter != null) {
			
			String message = "";
			
			switch (statutParameter){
			
				case "edit":
					message = "Modification bien effectuée";
					request.setAttribute(ATTR_MESS, message);
					break;
					
				case "libererPoste":
					message = "Poste libéré";
					request.setAttribute(ATTR_MESS, message);
					break;
					
				case "libererSalle":
					message = "Tous les postes de la salle viennent d'être libérés";
					request.setAttribute(ATTR_MESS, message);
					break;
			}
			
		}
		
		request.getRequestDispatcher("/WEB-INF/poste_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "CONSOLE");	
		if (request.getParameter("libererPoste") != null) {
			String inputIdPoste = request.getParameter("inputIdPoste");
			Integer idPoste = Integer.valueOf(inputIdPoste);
			PosteEntity poste = posteService.findById(idPoste);
			posteService.changeDisponibility(poste);
			response.sendRedirect("poste_list?statut=libererPoste");
		} else if (request.getParameter("libererPostesSalle") != null) {
			String inputIdSalle = request.getParameter("inputIdSalle");
			Integer idSalle = Integer.valueOf(inputIdSalle);
			SalleEntity salle = salleService.findById(idSalle);
			List<PosteEntity> postes = salle.getPostes();
			for (PosteEntity poste : postes) {
				PosteEntity poste2 = posteService.findById(poste.getId_poste());
				if (poste2.getDisponibilite() == false){
					posteService.changeDisponibility(poste2);
					posteService.updatePoste(poste2);
				}
			}
			response.sendRedirect("poste_list?statut=libererSalle");
		} else if (request.getParameter("libererPostesSite") != null) {
			String inputIdSite = request.getParameter("inputIdSite");
			Integer idSite = Integer.valueOf(inputIdSite);
			SiteEntity site = siteService.findById(idSite);
			Set<SalleEntity> salles = site.getSalles();
			for (SalleEntity salle : salles) {
				List<PosteEntity> postes = salle.getPostes();
				for (PosteEntity poste : postes){
					if (poste.getDisponibilite() == false){
						posteService.changeDisponibility(poste);
						posteService.updatePoste(poste);
					}
				}
			}
			response.sendRedirect("poste_list");
		} else if (request.getParameter("editSalle") != null) {
			String inputIdSalle = request.getParameter("inputIdSalle");
			Integer idSalle = Integer.valueOf(inputIdSalle);
			response.sendRedirect("salle_form?id="+idSalle);
		}else if (request.getParameter("deleteSalle") != null){
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
			request.setAttribute("sitePro", siteProfessionnel);
			String message = "Suppression bien effectuée";
			request.setAttribute(ATTR_MESS, message);
			request.getRequestDispatcher("/WEB-INF/poste_list.jsp").forward(request, response);
		}
	}
	
	

}
