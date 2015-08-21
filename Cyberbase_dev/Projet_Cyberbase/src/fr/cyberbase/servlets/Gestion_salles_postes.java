package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cyberbase.entities.SiteEntity;
import Services.SiteService;

/**
 * Servlet implementation class Gestion_salles_postes
 */
@WebServlet("/gestion_salles_postes")
public class Gestion_salles_postes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	SiteService siteService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gestion_salles_postes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SiteEntity> siteEntities = siteService.findAll();
		request.setAttribute("sites", siteEntities);
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestion_salles_postes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
