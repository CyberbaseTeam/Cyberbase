package fr.cyberbase.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.PosteService;

/**
 * Servlet implementation class Affecter_poste
 */
@WebServlet("/affecter_poste")
public class Affecter_poste extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	PosteService posteService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Affecter_poste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPoste = request.getParameter("id");
		if (idPoste != null) {
			Integer id = Integer.valueOf(idPoste);
			request.setAttribute("poste", posteService.findById(id));
		}
		request.getRequestDispatcher("/WEB-INF/affecter_poste.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
