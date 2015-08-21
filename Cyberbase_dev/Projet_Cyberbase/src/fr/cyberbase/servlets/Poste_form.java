package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.SalleEntity;
import Services.PosteService;
import Services.SalleService;

/**
 * Servlet implementation class Poste_form
 */
@WebServlet("/poste_form")
public class Poste_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PosteService posteService;
	SalleService salleService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Poste_form() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poste_id = request.getParameter("id");
		if (poste_id != null) {
			Integer id = Integer.valueOf(poste_id);
			PosteEntity poste = new PosteEntity();
			poste.setId_poste(id);
			poste = posteService.findById(poste);
			request.setAttribute("poste", poste);
		}
		List<SalleEntity> salles = salleService.findAll();
		request.setAttribute("salles", salles);
		request.getRequestDispatcher("/WEB-INF/poste_form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
