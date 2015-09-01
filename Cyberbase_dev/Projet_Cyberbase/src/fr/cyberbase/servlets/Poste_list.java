package fr.cyberbase.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.ProfessionnelService;
import Services.SiteService;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SiteEntity;

/**
 * Servlet implementation class Poste_list
 */
@WebServlet("/poste_list")
public class Poste_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	SiteService siteService;
	@EJB
	ProfessionnelService proService;
       
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
		
		//Simulation de récupération de l'idTech d'un professionnel via le cookie
		String idTechProfessionnel = "P0000003";
				
		//Récupération du professionnel connecté
		ProfessionnelEntity professionnel = proService.findByTechId(idTechProfessionnel);
				
		//Récupération de l'id du site du professionnel connecté
		Integer idSiteProfessionnel = professionnel.getSite_reference().getId_site();
				
		//Récupération du site du professionnel connecté
		SiteEntity siteProfessionnel = siteService.findById(idSiteProfessionnel);
		
		request.setAttribute("sitePro", siteProfessionnel);
		
		request.getRequestDispatcher("/WEB-INF/poste_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
