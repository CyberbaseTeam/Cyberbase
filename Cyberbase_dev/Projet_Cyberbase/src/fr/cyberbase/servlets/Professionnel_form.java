package fr.cyberbase.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.StructureEntity;
import Services.ProfessionnelService;
import Services.SiteService;
import Services.StructureService;

/**
 * Servlet implementation class Professionnel_form
 */
@WebServlet("/add_or_update_pro")
public class Professionnel_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_SITES 	= "siteList";
	private static final String ATTR_PRO 	= "professionnelList";
	private static final String ATTR_SECTION= "section";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@EJB
	SiteService siteService;
	@EJB
	ProfessionnelService professionnelService;
	@EJB
	StructureService structureService;
	
    public Professionnel_form() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "ADMINISTRATION");	
		request.setAttribute("sites", siteService.findAll());
		request.setAttribute("structures", structureService.findAll());
		request.getRequestDispatcher("/WEB-INF/ajout_professionnel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "ADMINISTRATION");	
		if (request.getParameter("create") != null) {
			ProfessionnelEntity entity = new ProfessionnelEntity();
			
			
			entity.setNom_professionnel(request.getParameter("name"));
			entity.setPrenom_professionnel(request.getParameter("firstName"));
			entity.setPassword(request.getParameter("password"));
			SiteEntity site = new SiteEntity();
			site = siteService.findById(Integer.valueOf(request.getParameter("site")));
			StructureEntity structure = new StructureEntity();
			structure = structureService.findById(Integer.valueOf(request.getParameter("structure")));
			entity.setSite_reference(site);
			entity.setStructure(structure);
			try{
				professionnelService.add(entity);
				
			}
			catch(Exception e){
				
			}
			
			request.setAttribute(ATTR_PRO,professionnelService.findAll());
			request.setAttribute(ATTR_SITES, siteService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp").forward(request, response);
			
			
		}
		else if (request.getParameter("retour") != null) {
			response.sendRedirect("administration");
		}
		else if (request.getParameter("update") != null) {
			ProfessionnelEntity entity = new ProfessionnelEntity();
		
			entity = professionnelService.findByTechId(request.getParameter("inputId"));
			entity.setNom_professionnel(request.getParameter("name"));
			entity.setPrenom_professionnel(request.getParameter("firstName"));
			entity.setPassword(request.getParameter("password"));
			SiteEntity site = new SiteEntity();
			site = siteService.findById(Integer.valueOf(request.getParameter("site")));
			StructureEntity structure = new StructureEntity();
			structure = structureService.findById(Integer.valueOf(request.getParameter("structure")));
			entity.setSite_reference(site);
			entity.setStructure(structure);
			try{
				professionnelService.update(entity);
				
			}
			catch(Exception e){
				
			}
			
			
			
			
		}
	
		
		
	}

}
