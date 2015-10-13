package fr.cyberbase.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.DownloadService;

/**
 * Servlet implementation class Download
 */
@WebServlet("/download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			
			DownloadService ds = new DownloadService();
			ds.writeCsv(fileContent);
			System.out.println(request.getParameter("exportValue"));
				 String filepath = "/home/imie/lala.csv";
				 try{
				    File file = new File(filepath);
				    if(!file.exists())
				    {
				        throw new ServletException("File doesn't exists on server.");
				    }

				    response.setContentType("application/csv");
				    response.setHeader("Content-Disposition","attachment; filename=\"" + filepath + "\""); 

				    java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath);

				    int i; 
				    while ((i=fileInputStream.read()) != -1) 
				    {
				         response.getWriter().write(i); 
				    } 
				    fileInputStream.close();
				}
				catch(Exception e)
				{
				    System.err.println("Error while downloading file["+filepath+"]"+e);
				}
			
			
		}
	}

}
