package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.AffectationService;
import Services.UsagerService;
import fr.cyberbase.entities.AffectationEntity;
import fr.cyberbase.entities.UsagerEntity;

/**
 * Servlet implementation class Usager_history
 */
@WebServlet("/usager_history")
public class Usager_history extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_USAGER 			= "usager";
	private static final String ATTR_AFF 				= "affectations";
	private static final String ATTR_SECTION= "section";
	
	@EJB
	UsagerService usagerService;
	@EJB
	AffectationService affectationService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usager_history() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_SECTION, "USAGERS");	
		//Récupération de l'id usager passé en paramètre d'URL dans le cas d'une modification 
		String idUsagerParameter = request.getParameter("id");
		if (idUsagerParameter != null) {
			Integer idUsager = Integer.valueOf(idUsagerParameter);
			UsagerEntity usager = usagerService.findById(idUsager);
			request.setAttribute(ATTR_USAGER, usager);
			
			List<AffectationEntity> affectations = affectationService.findByUserId(idUsager);
			request.setAttribute(ATTR_AFF, affectations);
		}
		
		request.getRequestDispatcher("/WEB-INF/usager_history.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
