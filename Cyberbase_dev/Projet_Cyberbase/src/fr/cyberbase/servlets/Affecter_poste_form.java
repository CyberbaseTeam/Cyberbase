package fr.cyberbase.servlets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;

import fr.cyberbase.entities.AffectationEntity;
import fr.cyberbase.entities.DemarcheEntity;
import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SalleEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.UsagerEntity;
import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;
import Services.AffectationService;
import Services.DemarcheService;
import Services.PosteService;
import Services.ProfessionnelService;
import Services.SalleService;
import Services.SiteService;
import Services.UsagerService;

/**
 * Servlet implementation class Affecter_poste
 */
@WebServlet("/affecter_poste_form")
public class Affecter_poste_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	PosteService posteService;
	@EJB
	ProfessionnelService proService;
	@EJB
	SiteService siteService;
	@EJB
	SalleService salleService;
	@EJB
	UsagerService userService;
	@EJB
	DemarcheService demarcheService;
	@EJB
	AffectationService affectationService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Affecter_poste_form() {
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
		Integer idProfessionnel = professionnel.getId_professionnel();
				
		//Récupération de l'id du site du professionnel connecté
		Integer idSiteProfessionnel = login.getSiteId();
		request.setAttribute("idPro", idProfessionnel);
				
		//Récupération du site du professionnel connecté
		SiteEntity siteProfessionnel = siteService.findById(idSiteProfessionnel);
			
		//Récupération de l'id poste passé en paramètre d'URL pour une affectation d'un poste spécifique
		String idPosteParameter = request.getParameter("id");
		if (idPosteParameter != null) {
			Integer idPoste = Integer.valueOf(idPosteParameter);
			PosteEntity poste = posteService.findById(idPoste);
			request.setAttribute("inputPoste", poste);
		}
		
		//Récupération de l'id affectation passé en paramètre d'URL pour une modification d'une affectation spécifique
		String idAffParameter = request.getParameter("affectation");
		if (idAffParameter != null) {
			Integer idAff = Integer.valueOf(idAffParameter);
			AffectationEntity affectation = affectationService.findById(idAff);
			request.setAttribute("affectation", affectation);
			}
		
		//Récupération de la liste des usagers pour chaque site
		List<SiteEntity> sites = siteService.findAll();
		String siteName = "";
		Integer ii = 0;
		Hashtable<String, List<UsagerEntity>> users = new Hashtable<String, List<UsagerEntity>>();
		for (SiteEntity site : sites) {
			siteName = site.getNom_site();
			List<UsagerEntity> usersSite = userService.findAllUsersBySite(site);
			users.put(siteName, usersSite);
		}
		request.setAttribute("users", users);
		
		//Récupération de la liste des postes pour chaque salle du site du professionnel connecté
		Set<SalleEntity> salles = siteProfessionnel.getSalles();
		String salleName = "";
		Hashtable<String, List<PosteEntity>> postes = new Hashtable<String, List<PosteEntity>>();
		for (SalleEntity salle : salles){
			salleName = salle.getNom_salle();
			List<PosteEntity> postesSalle = salle.getPostes();
			postes.put(salleName, postesSalle);
		}
		request.setAttribute("postes", postes);
		
		//Récupération des affectations
		List<DemarcheEntity> demarches = demarcheService.findAll();
		request.setAttribute("demarches", demarches);
		
		request.getRequestDispatcher("/WEB-INF/affecter_poste_form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("create") != null) {
			String[] inputIdUserTable = request.getParameterValues("user");
			Integer idUser = null;
			for(int ii=0;ii<inputIdUserTable.length;ii++)
		       {
				String inputIdUser = inputIdUserTable[ii];
				idUser = Integer.valueOf(inputIdUser);
		       }
			String inputIdPro = request.getParameter("inputIdPro");
			Integer idPro = Integer.valueOf(inputIdPro);
			String[] inputIdPosteTable = request.getParameterValues("poste");
			Integer idPoste = null;
			for(int jj=0;jj<inputIdPosteTable.length;jj++)
		       {
				String inputIdPoste = inputIdPosteTable[jj];
				idPoste = Integer.valueOf(inputIdPoste);
		       }
			String[] inputIdDemarcheTable = request.getParameterValues("demarche");
			Integer idDemarche = null;
			for(int kk=0;kk<inputIdDemarcheTable.length;kk++)
		       {
				String inputIdDemarche = inputIdDemarcheTable[kk];
				idDemarche = Integer.valueOf(inputIdDemarche);
		       }
			String inputMinutes = request.getParameter("time");
			Integer minutes = Integer.valueOf(inputMinutes);
			DateTime dateStart = new DateTime();
			DateTime dateEnd = dateStart.plusMinutes(minutes);
			Timestamp tsDateStart = new Timestamp(dateStart.getMillis());
			Timestamp tsDateEnd = new Timestamp(dateEnd.getMillis());
			AffectationEntity affectation = new AffectationEntity();
			affectation.setDate_debut_affectation(tsDateStart);
			affectation.setDate_fin_affectation(tsDateEnd);
			DemarcheEntity demarche = demarcheService.findById(idDemarche);
			affectation.setDemarche(demarche);
			PosteEntity poste = posteService.findById(idPoste);
			poste.setDisponibilite(false);
			posteService.updatePoste(poste);
			affectation.setPoste(poste);
			ProfessionnelEntity professionnel = proService.findById(idPro);
			affectation.setProfessionnel(professionnel);
			UsagerEntity usager = userService.findById(idUser);
			affectation.setUsager(usager);
			affectationService.createAffectation(affectation);
			response.sendRedirect("salle_list");
		} else if (request.getParameter("edit") != null) {
			String inputIdAffectation = request.getParameter("inputIdAffectation");
			Integer idAffectation = Integer.valueOf(inputIdAffectation);
			AffectationEntity affectation = affectationService.findById(idAffectation);
			String[] inputIdUserTable = request.getParameterValues("user");
			Integer idUser = null;
			for(int ii=0;ii<inputIdUserTable.length;ii++)
		       {
				String inputIdUser = inputIdUserTable[ii];
				idUser = Integer.valueOf(inputIdUser);
		       }
			String inputIdPro = request.getParameter("inputIdPro");
			Integer idPro = Integer.valueOf(inputIdPro);
			String[] inputIdPosteTable = request.getParameterValues("poste");
			Integer idPoste = null;
			for(int jj=0;jj<inputIdPosteTable.length;jj++)
		       {
				String inputIdPoste = inputIdPosteTable[jj];
				idPoste = Integer.valueOf(inputIdPoste);
		       }
			String[] inputIdDemarcheTable = request.getParameterValues("demarche");
			Integer idDemarche = null;
			for(int kk=0;kk<inputIdDemarcheTable.length;kk++)
		       {
				String inputIdDemarche = inputIdDemarcheTable[kk];
				idDemarche = Integer.valueOf(inputIdDemarche);
		       }
			String inputMinutes = request.getParameter("time");
			Integer minutes = Integer.valueOf(inputMinutes);
			Timestamp tsDateEnd = affectation.getDate_fin_affectation();
			DateTime dateEnd = new DateTime(tsDateEnd);
			dateEnd = dateEnd.plusMinutes(minutes);
			Timestamp tsDateEndEdit = new Timestamp(dateEnd.getMillis());
			affectation.setDate_fin_affectation(tsDateEndEdit);
			DemarcheEntity demarche = demarcheService.findById(idDemarche);
			affectation.setDemarche(demarche);
			PosteEntity poste = posteService.findById(idPoste);
			affectation.setPoste(poste);
			ProfessionnelEntity professionnel = proService.findById(idPro);
			affectation.setProfessionnel(professionnel);
			UsagerEntity usager = userService.findById(idUser);
			affectation.setUsager(usager);
			affectationService.updateAffectation(affectation);
			response.sendRedirect("salle_list");
		}
	}

}
