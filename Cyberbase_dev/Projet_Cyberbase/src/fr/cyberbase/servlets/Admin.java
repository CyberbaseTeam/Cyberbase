package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class Admin
 */
@WebServlet("/administration")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@EJB
	ProfessionnelService professionnelService;
	@EJB
	SiteService siteService;
	@EJB
	StructureService structureService;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("site") != null) {
			Integer id = Integer.valueOf(request.getParameter("inputIdSite"));
			SiteEntity siteEntity = new SiteEntity();
			siteEntity.setId_site(id);
			request.setAttribute("professionnels",
					professionnelService.findBySite(siteEntity));
			request.setAttribute("sites", siteService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("professionnels",
					professionnelService.findAll());
			request.setAttribute("sites", siteService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("actionSupprimer") != null) {
			Integer id = Integer.valueOf(request.getParameter("inputId"));
			ProfessionnelEntity professionnelEntity = new ProfessionnelEntity();
			professionnelEntity.setId_professionnel(id);
			professionnelService.delete(professionnelEntity);
			request.setAttribute("professionnels",
					professionnelService.findAll());
			request.setAttribute("sites", siteService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		} 
		else if (request.getParameter("actionModifier") != null) {
			Integer id = Integer.valueOf(request.getParameter("inputId"));
			request.setAttribute("professionnels",
					professionnelService.findById(id));
			request.setAttribute("sites", siteService.findAll());
			request.setAttribute("structures", structureService.findAll());
			request.getRequestDispatcher("/WEB-INF/modification.jsp").forward(
					request, response);
		} 
		else if (request.getParameter("retour") != null) {
			request.setAttribute("professionnels",
					professionnelService.findAll());
			request.setAttribute("sites", siteService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		} 
		else if (request.getParameter("modifier") != null) {
			ProfessionnelEntity professionnelEntity = new ProfessionnelEntity();
			professionnelEntity= professionnelService.findByTechId(request
					.getParameter("inputId"));	
			try{
			SiteEntity siteEntity = new SiteEntity();
			siteEntity.setId_site(Integer.valueOf(request
					.getParameter("site")));
			professionnelEntity.setSite_reference(siteEntity);
			}catch (Exception e) {
			}
			try{
			StructureEntity structureEntity = new StructureEntity();
			structureEntity.setId_structure(Integer.valueOf(request
					.getParameter("structure")));
			professionnelEntity.setStructure(structureEntity);
			}catch (Exception e) {
			}
			professionnelEntity.setNom_professionnel(request
					.getParameter("inputNom"));
			professionnelEntity.setPrenom_professionnel(request
					.getParameter("inputPrenom"));
			professionnelEntity.setPassword(request
					.getParameter("inputPassword"));
			
			professionnelService.update(professionnelEntity);
			request.setAttribute("sites", siteService.findAll());
			request.setAttribute("professionnels",
					professionnelService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		}
	}

}
