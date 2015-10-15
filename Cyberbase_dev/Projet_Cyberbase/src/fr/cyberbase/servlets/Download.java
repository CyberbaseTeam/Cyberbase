package fr.cyberbase.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import Services.DownloadService;

/**
 * Servlet implementation class Download
 */
@WebServlet("/download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final String CSVFILE_PATH = "/home/imie/stats Ref_" + (new DateTime()).getMillis() + ".csv";
	
	DownloadService ds = new DownloadService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("export") != null)
		{
			String fileContent = request.getParameter("exportValue");	
			
			
			String fileName = CSVFILE_PATH;
			
			ds.writeCsv(fileContent, fileName);
			System.out.println(request.getParameter("exportValue"));
			
			try{
			    File file = new File(fileName);
			    if(!file.exists())
			    {
			        throw new ServletException("File doesn't exists on server.");
			    }
			    String encoding = request.getCharacterEncoding();
	            if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8")))
	            {
	                response.setContentType("text/csv; charset=utf-8");
	            }
			    
			    
	            response.setHeader("content-type:application/csv","charset=UTF-8");
			    response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\""); 

			    java.io.FileInputStream fileInputStream = new java.io.FileInputStream(fileName);
			    InputStreamReader isr = new InputStreamReader(fileInputStream, "UTF8");

			    int i; 
			    while ((i=isr.read()) != -1) 
			    {
			         response.getWriter().write(i); 
			         file.delete();
			    } 
			    fileInputStream.close();
			}
			catch(Exception e)
			{
			    System.err.println("Error while downloading file["+fileName+"]"+e);
			}
			
			
		}
	}

}
