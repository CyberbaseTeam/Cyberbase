package fr.cyberbase.filters;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.constraints.Length;

import fr.cyberbase.util.CookieTools;
import fr.cyberbase.util.Login;


/**
 * Servlet Filter implementation class RequestFilter
 */
@WebFilter(urlPatterns = "/*")
public class RequestFilter implements Filter {
  
	private final String PATH_CONNEXION = "connexion"; 
   
    CookieTools cookieTools = new CookieTools();
    
    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException, ServletException 
    {
      
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();
        Boolean securedResource = true;
        
        try
        {
	        if (path.contains("connexion") || path.contains("/inc") ) {
	        	securedResource = false;
	        	
	            chain.doFilter( request, response );
	            return;
	        }
	        
	        else{
	    	Cookie cookies [] = request.getCookies();
	    	
	    	if(cookies == null)
	    		response.sendRedirect(PATH_CONNEXION);
	    	else
	    	{
	    		for(Cookie cookie: cookies)
	    		{
	    			if(cookie.getName().equals(CookieTools.COOKIE_KEY)) 
	    	        {
	    				
	    				String tokenCookie = cookie.getValue();
	    	        	Login login = cookieTools.getLogin(tokenCookie);
	    	        	if(login == null)
	    	        		response.sendRedirect(PATH_CONNEXION);
	    	        	
	    	        	Calendar maxAge = login.getMaxAge();
	    	        	Calendar now = Calendar.getInstance();
	    	        	
	    	        	if(maxAge.before(now))
	    	        		response.sendRedirect(PATH_CONNEXION);
	    	        	else
	    	        		chain.doFilter(request, response);
	    	        	return;
	    	        }
	    			
	    		}
	    		
	    		response.sendRedirect(PATH_CONNEXION);
	    	}
	        }
        
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        finally{
        	
        }
        
        
    }      

    public void destroy() {
    	
    }
}
