package fr.cyberbase.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.CspService;
import Services.FormationService;
import Services.QuartierService;
import Services.SiteService;
import Services.UsagerService;
import fr.cyberbase.entities.CspEntity;
import fr.cyberbase.entities.FormationEntity;
import fr.cyberbase.entities.QuartierEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.UsagerEntity;

/**
 * Servlet implementation class Usager_form
 */
@WebServlet("/usager_form")
public class Usager_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_USAGER 			= "usager";
	private static final String ATTR_SITES 				= "sites";
	private static final String ATTR_QUARTIERS 			= "quartiers";
	private static final String ATTR_CSPS 				= "csps";
	private static final String ATTR_FORMATIONS 		= "formations";
	private static final String ATTR_SECTION= "section";
	
	@EJB
	UsagerService usagerService;
	@EJB
	SiteService siteService;
	@EJB
	QuartierService quartierService;
	@EJB
	CspService cspService;
	@EJB
	FormationService formationService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usager_form() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "USAGERS");	
		//Récupération de l'id usager passé en paramètre d'URL dans le cas d'une modification 
		String idUsagerParameter = request.getParameter("id");
		if (idUsagerParameter != null) {
			Integer idUsager = Integer.valueOf(idUsagerParameter);
			UsagerEntity usager = usagerService.findById(idUsager);
			request.setAttribute(ATTR_USAGER, usager);
		}
		List<SiteEntity> sites = siteService.findAll();
		request.setAttribute(ATTR_SITES, sites);
		List<QuartierEntity> quartiers = quartierService.findAll();
		request.setAttribute(ATTR_QUARTIERS, quartiers);
		List<CspEntity> csps = cspService.findAll();
		request.setAttribute(ATTR_CSPS, csps);
		List<FormationEntity> formations = formationService.findAll();
		request.setAttribute(ATTR_FORMATIONS, formations);
		
		request.getRequestDispatcher("/WEB-INF/usager_form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "USAGERS");	
		if(request.getParameter("edit") != null){
			UsagerEntity usager = new UsagerEntity();
			String inputAccompagnement = request.getParameter("accompagnement");
			Boolean accompagnement = Boolean.valueOf(inputAccompagnement);
			usager.setAccompagnement(accompagnement);
			String adresseUsager = request.getParameter("adresseUsager");
			usager.setAdresse_usager(adresseUsager);
			String civiliteUsager = request.getParameter("civilite");
			usager.setCivilite_usager(civiliteUsager);
			String inputCpUsager = request.getParameter("cpUsager");
			Integer cpUsager = Integer.valueOf(inputCpUsager);
			usager.setCode_postal_usager(cpUsager);
			String inputIdCsp = request.getParameter("cspUsager");
			Integer idCsp = Integer.valueOf(inputIdCsp);
			CspEntity csp = cspService.findById(idCsp);
			usager.setCsp(csp);
			String inputDateInscription = request.getParameter("dateInscriptionUsager");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			java.util.Date dateInscription = null;
			try{
				dateInscription = formatter.parse(inputDateInscription);
			}catch(ParseException e){
			e.printStackTrace();
			}
			usager.setDate_inscription(dateInscription);
			String inputDateNaissanceUsager= request.getParameter("dateUsager");
			java.util.Date dateNaissanceUsager = null;
			try{
				dateNaissanceUsager = formatter.parse(inputDateNaissanceUsager);
			}catch(ParseException e){
			e.printStackTrace();
			}
			usager.setDate_naissance_usager(dateNaissanceUsager);
			String inputEmailUsager = request.getParameter("emailUsager");
			usager.setEmail_usager(inputEmailUsager);
			String inputIdUsager = request.getParameter("idUsager");
			Integer idUsager = Integer.valueOf(inputIdUsager);
			usager.setId_usager(idUsager);
			String inputIdFormation = request.getParameter("formationUsager");
			Integer idFormation = Integer.valueOf(inputIdFormation);
			FormationEntity niveauFormation = formationService.findById(idFormation);
			usager.setNiveau_formation(niveauFormation);
			String inputNomUsager = request.getParameter("nomUsager");
			usager.setNom_usager(inputNomUsager);
			String inputPrenomUsager = request.getParameter("prenomUsager");
			usager.setPrenom_usager(inputPrenomUsager);
			String inputIdQuartier = request.getParameter("quartierUsager");
			Integer idQuartier = Integer.valueOf(inputIdQuartier);
			QuartierEntity quartier = quartierService.findById(idQuartier);
			usager.setQuartier(quartier);
			String inputIdSite = request.getParameter("siteUsager");
			Integer idSite = Integer.valueOf(inputIdSite);
			SiteEntity site = siteService.findById(idSite);
			usager.setSite_inscription(site);
			String techId = request.getParameter("techIdUsager");
			usager.setTech_id(techId);
			String villeUsager = request.getParameter("villeUsager");
			usager.setVille_usager(villeUsager);
			usagerService.update(usager);
			response.sendRedirect("usagers");
		}
	}

}
