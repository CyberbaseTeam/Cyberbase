package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.ExclusionService;
import Services.UsagerService;
import fr.cyberbase.entities.ExclusionEntity;
import fr.cyberbase.entities.PosteEntity;
import fr.cyberbase.entities.UsagerEntity;

/**
 * Servlet implementation class Exclure_form
 */
@WebServlet("/exclure_form")
public class Exclure_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_USAGER 			= "usager";
	private static final String ATTR_SECTION  = "section";
	@EJB
	UsagerService usagerService;
	@EJB
	ExclusionService exclusionService;
       
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
		request.setAttribute(ATTR_SECTION, "USAGERS");	
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
<<<<<<< HEAD
		request.setAttribute(ATTR_SECTION, "USAGERS");	
=======
		if(request.getParameter("create") != null){
			ExclusionEntity exclusion = new ExclusionEntity();
			String inputStatut = request.getParameter("statut");
			exclusion.setStatut_exclusion(inputStatut);
			String inputIdUsager = request.getParameter("idUsager");
			Integer idUsager = Integer.valueOf(inputIdUsager);
			UsagerEntity usager = usagerService.findById(idUsager);
			exclusion.setUsager(usager);
			if (inputStatut == "temporaire"){
				 Date aujourdhui = new Date();
				 exclusion.setDate_debut((java.sql.Date) aujourdhui);
			}
			exclusionService.createExclusion(exclusion);
		}
>>>>>>> 0e6b1d09676c01b9a75d2336bbd1a7b652939fb0
	}

}
