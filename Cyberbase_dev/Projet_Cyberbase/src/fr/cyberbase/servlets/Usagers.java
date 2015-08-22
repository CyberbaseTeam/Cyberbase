package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.UsagerService;
import fr.cyberbase.entities.UsagerEntity;

/**
 * Servlet implementation class Usagers
 */
@WebServlet("/usagers")
public class Usagers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsagerService usagerService;

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
		List<UsagerEntity> usagerEntities = usagerService.findAll();
		request.setAttribute("usagers", usagerEntities);
		this.getServletContext().getRequestDispatcher("/WEB-INF/usager.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("actionSupprimer") != null)
		{
			String idParameter = request.getParameter("inputId");
			Integer id = Integer.valueOf(idParameter);
			UsagerEntity usagerEntity = new UsagerEntity();
			usagerEntity.setId_usager(id);
			usagerService.delete(usagerEntity);
			request.setAttribute("usagers", usagerService.findAll());
			request.getRequestDispatcher("/WEB-INF/usagers.jsp").forward(request, response);
		}
	}

}
