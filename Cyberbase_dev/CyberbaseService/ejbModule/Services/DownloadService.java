package Services;


import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class DownloadService {

	
	public void createQueryFile(){
		
	}
	
	public void writeCsv(String content, String fileName) throws IOException{
		
		File f = new File (fileName);
		
		PrintWriter out = new PrintWriter( new OutputStreamWriter( new FileOutputStream( f, true ), "UTF-8" )); 
		
		out.write(content);
		
		 
		out.close();
		
	}
		
}
	
	
	
	

