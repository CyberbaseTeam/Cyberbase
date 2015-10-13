package Services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.joda.time.DateTime;

public class DownloadService {

	
	public void createQueryFile(){
		
	}
	
	public void writeCsv(String content) throws IOException{
		DateTime currentDate = new DateTime();
		File f = new File ("/home/imie/lala.csv");
		 
		
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f)));
		 
		
		pw.write(content);
		
		 
		pw.close();
		
	}
		
}
	
	
	
	

