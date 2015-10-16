package fr.cyberbase.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.UsagerService;
import fr.cyberbase.entities.UsagerEntity;

/**
 * Servlet implementation class Exclure_form
 */
@WebServlet("/exclure_form")
public class Exclure_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_USAGER 			= "usager";
	
	@EJB
	UsagerService usagerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exclure_form() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération de l'id usager passé en paramètre d'URL dans le cas d'une modification 
		String idUsagerParameter = request.getParameter("id");
		if (idUsagerParameter != null) {
			Integer idUsager = Integer.valueOf(idUsagerParameter);
			UsagerEntity usager = usagerService.findById(idUsager);
			request.setAttribute(ATTR_USAGER, usager);
		}
		
		request.getRequestDispatcher("/WEB-INF/exclure_form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
