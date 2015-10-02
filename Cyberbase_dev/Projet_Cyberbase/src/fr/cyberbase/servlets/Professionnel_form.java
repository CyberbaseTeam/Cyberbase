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
@WebServlet("/ajout_professionnel")
public class Professionnel_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		request.setAttribute("sites", siteService.findAll());
		request.setAttribute("structures", structureService.findAll());
		request.getRequestDispatcher("/WEB-INF/ajout_professionnel.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("creer") != null) {
			ProfessionnelEntity professionnelEntity = new ProfessionnelEntity();
			professionnelEntity.setNom_professionnel(request
					.getParameter("inputNom"));
			try{
			SiteEntity siteEntity = new SiteEntity();
			siteEntity.setId_site(Integer.valueOf(request
					.getParameter("site")));
			professionnelEntity.setSite_reference(siteEntity);
			}catch (Exception e) { System.out.println("site");
			}
			professionnelEntity.setPrenom_professionnel(request
					.getParameter("inputPrenom"));
			professionnelEntity.setPassword(request
					.getParameter("inputPassword"));
			
			try{
			StructureEntity structureEntity = new StructureEntity();
			structureEntity.setId_structure(Integer.valueOf(request
					.getParameter("structure")));
			professionnelEntity.setStructure(structureEntity);}
			catch (Exception e) { System.out.println("structure");
			}
			try{
			professionnelService.add(professionnelEntity);
			}catch (Exception e) { System.out.println("creation");
			}
			request.getRequestDispatcher("administration")
					.forward(request, response);
		}
		else if (request.getParameter("retour") != null) {
			
		}
		request.getRequestDispatcher("administration")
		.forward(request, response);
		
	}

}
