package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.util.CookieTools;
import Services.ProfessionnelService;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private final String PATH_CONNEXION = "/WEB-INF/connexion.jsp";
	private final String ERROR_MSG		= "erreur dans le traitement des données. Echec de la connexion";
	private final String PARAM_PASSWORD = "pwd";
	private final String PARAM_TECH_ID 	= "techId";
	
	@EJB
	ProfessionnelService professionnelService;
	
	
	CookieTools cookieTools = new CookieTools();
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
		List<String> errorMessages = new ArrayList<String>();
		
		try
		{
			ProfessionnelEntity pro = new ProfessionnelEntity();
			pro.setTech_id(techId);
			pro.setPassword(password);
			
			ProfessionnelEntity loggedProfessional = professionnelService.checkLogin(pro);
			
			String token = "";
			if(loggedProfessional != null)
			{
				if(loggedProfessional.getAdmin() == true)
				{
					token = cookieTools.generateNewAdminToken(loggedProfessional);
				}
				else
				{
					token = cookieTools.generateNewProfessionalToken(loggedProfessional);
				}
				
				Cookie cookie = new Cookie(cookieTools.COOKIE_KEY, token);
				response.addCookie(cookie);
				System.out.println(cookie);
				response.sendRedirect("accueil");		
			}
		}
		catch (Exception e) {
			
			e.printStackTrace ();
			errorMessages.add(ERROR_MSG);
			request.setAttribute("error", errorMessages);
			request.getRequestDispatcher(PATH_CONNEXION).forward(request, response);

			}
		
		
		
		
	}

}
