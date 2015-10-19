package fr.cyberbase.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

import Services.ProfessionnelService;
import fr.cyberbase.entities.ProfessionnelEntity;
import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;

/**
 * Servlet implementation class Deconnexion
 */
@WebServlet("/deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	@EJB
	ProfessionnelService professionnelService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deconnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CookieTools cookieTools = new CookieTools();
		Cookie[] cookies = request.getCookies();
		Login login = new Login();	
		
		login = getLoginFromCookie(cookies);
		
		ProfessionnelEntity logged = professionnelService.findByTechId(login.getLoginTechId());
		for(Cookie cookie: cookies)
		{
			if(cookie.getName().equals(CookieTools.COOKIE_KEY)) 
	        {
				try{
					cookie.setValue(cookieTools.generateWrongToken(logged));
					response.addCookie(cookie);
				}
				catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
	        }
		}
		
		response.sendRedirect("accueil");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
