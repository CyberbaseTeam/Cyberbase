package fr.cyberbase.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.entities.SiteEntity;
import fr.cyberbase.entities.StructureEntity;
import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;
import Services.ProfessionnelService;
import Services.SiteService;
import Services.StructureService;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/administration")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_SITES 	= "siteList";
	private static final String ATTR_PRO 	= "professionnelList";
	

	List<SiteEntity> siteList;
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@EJB
	ProfessionnelService professionnelService;
	@EJB
	SiteService siteService;
	@EJB
	StructureService structureService;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("admin!!");
		String action = request.getParameter("action");
			
		if(action != null && action.equals("listBySite"))
		{			
			SiteEntity siteEntity = new SiteEntity();
			siteEntity.setId_site(Integer.valueOf(request.getParameter("id")));
			request.setAttribute(ATTR_PRO, professionnelService.findBySite(siteEntity));	
			request.setAttribute(ATTR_SITES, siteService.findAll());
			this.getServletContext().getRequestDispatcher("/WEB-INF/administration.jsp").forward(request, response);
		}	
		
		if (request.getParameter("actionModifier") != null)
		{
			String techid = request.getParameter("inputId");
			ProfessionnelEntity searchedEntity = professionnelService.findByTechId(techid);
			request.setAttribute("modifiedProfessional", searchedEntity);
			System.out.println(searchedEntity.getNom_professionnel());
			System.out.println(searchedEntity.getPrenom_professionnel());
			System.out.println(searchedEntity.getTech_id());
			
			request.setAttribute("sites", siteService.findAll());
			request.setAttribute("structures", structureService.findAll());
			
			request.getRequestDispatcher("/WEB-INF/modification_professionnel.jsp").forward(request, response);
		}
		
		if (request.getParameter("actionSupprimer") != null) {
			Integer id = Integer.valueOf(request.getParameter("inputId"));
			ProfessionnelEntity professionnelEntity = new ProfessionnelEntity();
			professionnelEntity.setId_professionnel(id);
			professionnelService.delete(professionnelEntity);
			request.setAttribute("professionnels",professionnelService.findAll());
			request.setAttribute("sites", siteService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		} 
		
		if (request.getParameter("action") != null && request.getParameter("action").equals("creerPro"))
		{
			
			
			request.setAttribute("sites", siteService.findAll());
			request.setAttribute("structures", structureService.findAll());
			
			request.getRequestDispatcher("/WEB-INF/ajout_professionnel.jsp").forward(request, response);
		}
		else{	
			request.setAttribute(ATTR_PRO, professionnelService.findAll());
			request.setAttribute(ATTR_SITES, siteService.findAll());
			this.getServletContext().getRequestDispatcher("/WEB-INF/administration.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("actionSupprimer") != null) {
			Integer id = Integer.valueOf(request.getParameter("inputId"));
			ProfessionnelEntity professionnelEntity = new ProfessionnelEntity();
			professionnelEntity.setId_professionnel(id);
			professionnelService.delete(professionnelEntity);
			request.setAttribute("professionnels",professionnelService.findAll());
			request.setAttribute("sites", siteService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		} 
		else if (request.getParameter("action") != null) {
			String id = request.getParameter("inputId");
			request.setAttribute("modifiedProfessional", professionnelService.findByTechId(id));
			request.setAttribute("sites", siteService.findAll());
			request.setAttribute("structures", structureService.findAll());
			
			request.getRequestDispatcher("/WEB-INF/modification.jsp").forward(request, response);
		} 
		else if (request.getParameter("retour") != null) {
			request.setAttribute("professionnels",
					professionnelService.findAll());
			request.setAttribute("sites", siteService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		} 
		else if (request.getParameter("modifier") != null) {
			ProfessionnelEntity professionnelEntity = new ProfessionnelEntity();
			professionnelEntity= professionnelService.findByTechId(request
					.getParameter("inputId"));	
			try{
			SiteEntity siteEntity = new SiteEntity();
			siteEntity.setId_site(Integer.valueOf(request
					.getParameter("site")));
			professionnelEntity.setSite_reference(siteEntity);
			}catch (Exception e) {
			}
			try{
			StructureEntity structureEntity = new StructureEntity();
			structureEntity.setId_structure(Integer.valueOf(request
					.getParameter("structure")));
			professionnelEntity.setStructure(structureEntity);
			}catch (Exception e) {
			}
			professionnelEntity.setNom_professionnel(request
					.getParameter("inputNom"));
			professionnelEntity.setPrenom_professionnel(request
					.getParameter("inputPrenom"));
			professionnelEntity.setPassword(request
					.getParameter("inputPassword"));
			
			professionnelService.update(professionnelEntity);
			request.setAttribute("sites", siteService.findAll());
			request.setAttribute("professionnels",
					professionnelService.findAll());
			request.getRequestDispatcher("/WEB-INF/administration.jsp")
					.forward(request, response);
		}
	}
	
	private Login getLoginFromCookie(Cookie[] cookies) throws UnsupportedEncodingException {
		Login login = new Login();
		CookieTools cookieTools = new CookieTools();
		for(Cookie cookie: cookies)
		{
			if(cookie.getName().equals(CookieTools.COOKIE_KEY)) 
	        {
				String tokenCookie = cookie.getValue();
				try {
					login = cookieTools.getLogin(tokenCookie);
					return login;
				} catch (InvalidKeyException | NoSuchPaddingException
						| NoSuchAlgorithmException | IllegalBlockSizeException
						| BadPaddingException e) {
					e.printStackTrace();
				}
	        }
		}
		return null;
	}
	
	
	

}
