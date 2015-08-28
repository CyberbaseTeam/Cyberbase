//package fr.cyberbase.servlets;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.ejb.EJB;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import Services.StatistiqueService;
//
///**
// * Servlet implementation class statistiques
// */
//@WebServlet("/statistiques")
//public class Statistiques extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	private static final String FIELD_DISPLAY_DATA 	= "displayData";
//	private static final String FIELD_SEARCH_PANEL 	= "searchPanel";
//	private static final String FIELD_GENDER 		= "gender";
//	private static final String FIELD_CITY 			= "city";
//	private static final String FIELD_DISTRICT 		= "district";
//	private static final String FIELD_FORMATION 	= "formation";
//	private static final String FIELD_OBJECTIVE 	= "objective";
//	private static final String FIELD_VISIT_MIN 	= "visitMin";
//	private static final String FIELD_VISIT_MAX 	= "visitMax";
//	private static final String FIELD_DATE_START 	= "dateStart";
//	private static final String FIELD_DATE_END 		= "dateEnd";
//	private static final String FIELD_SAVE_QUERY 	= "saveQuery";
//	 
//	
//	@EJB
//	StatistiqueService statistiqueService;
//	
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Statistiques() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		this.getServletContext().getRequestDispatcher("/WEB-INF/statistiques.jsp").forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String, String> queryObjects = new HashMap<String, String>();
//		List<String> querySelectObjects = new ArrayList<String>();
//		
//		if(request.getParameter(FIELD_SEARCH_PANEL)!= "")
//			queryObjects.put(FIELD_SEARCH_PANEL, request.getParameter(FIELD_SEARCH_PANEL));
//		if(request.getParameter(FIELD_GENDER)!= "")
//			queryObjects.put(FIELD_GENDER, request.getParameter(FIELD_GENDER));
//		if(request.getParameter(FIELD_CITY)!= "")
//			queryObjects.put(FIELD_CITY, request.getParameter(FIELD_CITY));
//		if(request.getParameter(FIELD_DISTRICT)!= "")
//			queryObjects.put(FIELD_DISTRICT, request.getParameter(FIELD_DISTRICT));
//		if(request.getParameter(FIELD_FORMATION)!= "")
//			queryObjects.put(FIELD_FORMATION, request.getParameter(FIELD_FORMATION));
//		if(request.getParameter(FIELD_OBJECTIVE)!= "")
//				queryObjects.put(FIELD_OBJECTIVE, request.getParameter(FIELD_OBJECTIVE));
//		if(request.getParameter(FIELD_VISIT_MIN)!= "")
//			queryObjects.put(FIELD_VISIT_MIN, request.getParameter(FIELD_VISIT_MIN));
//		if(request.getParameter(FIELD_VISIT_MAX)!= "")
//			queryObjects.put(FIELD_VISIT_MAX, request.getParameter(FIELD_VISIT_MAX));
//		if(request.getParameter(FIELD_DATE_START)!= "")
//			queryObjects.put(FIELD_DATE_START, request.getParameter(FIELD_DATE_START));
//		if(request.getParameter(FIELD_DATE_END)!= "")
//			queryObjects.put(FIELD_DATE_END, request.getParameter(FIELD_DATE_END));
//		if(request.getParameter(FIELD_SAVE_QUERY)!= "")
//			queryObjects.put(FIELD_SAVE_QUERY, request.getParameter(FIELD_SAVE_QUERY));
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	}
//
//}
