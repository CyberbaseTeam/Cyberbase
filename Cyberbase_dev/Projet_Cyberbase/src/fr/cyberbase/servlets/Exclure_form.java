package fr.cyberbase.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	private static final String ATTR_USAGERS 			= "usagers";
	private static final String ATTR_SECTION  			= "section";
	private static final String ATTR_EXCLUS  			= "exclusions";
	private static final String ATTR_MESSAGE 			= "message";
	
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

		request.setAttribute(ATTR_SECTION, "USAGERS");	

		if(request.getParameter("create") != null){
			ExclusionEntity exclusion = new ExclusionEntity();
			String inputStatut = request.getParameter("statut");
			exclusion.setStatut_exclusion(inputStatut);
			String inputIdUsager = request.getParameter("idUsager");
			Integer idUsager = Integer.valueOf(inputIdUsager);
			UsagerEntity usager = usagerService.findById(idUsager);
			exclusion.setUsager(usager);
				
				Calendar calendar = Calendar.getInstance();
				java.util.Date currentDate = calendar.getTime();
				java.sql.Date dateDebut = new java.sql.Date(currentDate.getTime());
				exclusion.setDate_debut(dateDebut);
				
				if ("temporaire".equals(inputStatut)){
					String inputDateFin = request.getParameter("dateExclusion");
				
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        Date parsed = null;
					try {
						parsed = format.parse(inputDateFin);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        java.sql.Date sql = new java.sql.Date(parsed.getTime());
					exclusion.setDate_fin(sql);
				}
			exclusionService.createExclusion(exclusion);
			
			String message = "Usager exclu avec succès";
			request.setAttribute(ATTR_MESSAGE, message);
			List<UsagerEntity> usagers = usagerService.findAll();
			request.setAttribute(ATTR_USAGERS, usagers);
			request.getRequestDispatcher("/WEB-INF/usagers.jsp").forward(request, response);
		}

	}

}
