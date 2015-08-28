package fr.cyberbase.filters;

import java.io.IOException;
import java.util.Calendar;

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


/**
 * Servlet Filter implementation class RequestFilter
 */
@WebFilter(urlPatterns = "/*")
public class RequestFilter implements Filter {
  

   
    
    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException, ServletException {
      
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();
        if (path.contains("connexion")) {
            chain.doFilter( request, response );
            return;
        }
        
        Cookie cookies [] = request.getCookies();
        for(Cookie cookie: cookies)
        {
        	if(cookies != null && cookie.getName().equals("auth")) 
        	{
        		String tokenCookie = cookie.getValue();
        		if(tokenCookie != null)
        		{
        			System.out.println("2");
        			chain.doFilter(request, response);
        		}	
        	}	
        	else
    		{
    			request.getRequestDispatcher( "WEB-INF/connexion.jsp" ).forward( request, response );
    		}
        }
        
        }
    public void destroy() {
    }
}
