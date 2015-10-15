package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.SiteService;
import Services.UsagerService;
import fr.cyberbase.entities.CspEntity;
import fr.cyberbase.entities.DemarcheEntity;
import fr.cyberbase.entities.FormationEntity;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.QuartierEntity;
import fr.cyberbase.entities.RequeteEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.UsagerEntity;

/**
 * Servlet implementation class Usagers
 */
@WebServlet("/usagers")
public class Usagers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_SITES 			= "siteList";

	List<SiteEntity> siteList;
	List<UsagerEntity> usagerList;
	
	
	
	
	@EJB
	UsagerService usagerService;
	
	@EJB
	SiteService siteService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Usagers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initializeData(request);
		
		String idSiteParameter = request.getParameter("site");
		Integer idSite = null;
		if (idSiteParameter != null) {
			idSite = Integer.valueOf(idSiteParameter);
			SiteEntity site = siteService.findById(idSite);
			List<UsagerEntity> usagers = usagerService.findAllUsersBySite(site);
			request.setAttribute("usagers", usagers);
		} else {
			List<UsagerEntity> usagers = usagerService.findAll();
			request.setAttribute("usagers", usagers);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/usagers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("deleteUsager") != null){
			String idParameter = request.getParameter("inputId");
			Integer id = Integer.valueOf(idParameter);
			UsagerEntity usagerEntity = new UsagerEntity();
			usagerEntity.setId_usager(id);
			usagerService.delete(usagerEntity);
			request.setAttribute("usagers", usagerService.findAll());
			request.getRequestDispatcher("/WEB-INF/usager.jsp").forward(request, response);
		} else if (request.getParameter("editUsager") != null){
			String idParameter = request.getParameter("inputId");
			Integer id = Integer.valueOf(idParameter);
			response.sendRedirect("usager_form?id="+id);
		}
	}
	
	private void initializeData(HttpServletRequest request){
		siteList = siteService.findAll();
		
		
		request.setAttribute(ATTR_SITES, siteList);
		
	}

}
