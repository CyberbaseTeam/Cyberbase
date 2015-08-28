package fr.cyberbase.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cyberbase.entities.ProfessionnelEntity;
import Services.ProfessionnelService;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private final String PATH_CONNEXION = "/WEB-INF/connexion.jsp";
	private final String PARAM_PASSWORD = "pwd";
	private final String PARAM_TECH_ID = "techId";
	
	@EJB
	ProfessionnelService professionnelService;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(PATH_CONNEXION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String techId = request.getParameter(PARAM_TECH_ID);
		String password = request.getParameter(PARAM_PASSWORD);
		
		try
		{
			ProfessionnelEntity pro = new ProfessionnelEntity();
			pro.setTech_id(techId);
			pro.setPassword(password);
			
			ProfessionnelEntity loggedProfessionnal = professionnelService.checkLogin(pro);
			
			request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		

		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace ();
			request.getRequestDispatcher(PATH_CONNEXION).forward(
					request, response);

			}
		
		
		
		
	}

}
