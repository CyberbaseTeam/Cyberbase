package Services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;

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
	
	
	
	

