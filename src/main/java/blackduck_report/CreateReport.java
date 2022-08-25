package blackduck_report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import integration.values.ExcelValues;
import integration.values.ProjectValues;



public class CreateReport {	
	
	ProjectValues projectValues = ProjectValues.getInstance();
	ExcelValues excelValues = ExcelValues.getInstance();
	
	public void writeReport(String filepath)  {
		
		CreateSheet0 sheet0 = new CreateSheet0();		
		sheet0.writeSheet();
		
		closeExcel(filepath);
	}
	
	public void closeExcel(String filepath){
		
		String date = new DateTime().toString(DateTimeFormat.forPattern("yyyyMMdd_HHmmss"));		
				
		try {
		
				FileOutputStream output = new FileOutputStream(projectValues.getBDprojectName()+ "_"+ projectValues.getBDprojectVersionName() + "_" + date +  "_Report.xlsx");
				//excelValues.setfilepath(projectValues.getBDprojectName()+"_"+projectValues.getBD_projectVersionName() + date +  "_Report.xlsx");
				excelValues.setfilepath(projectValues.getBDprojectName()+ "_"+ projectValues.getBDprojectVersionName() + "_" + date +  "_Report.xlsx");
				excelValues.getWB().write(output);
				output.close();
				excelValues.getWB().close();
			
			
			System.out.println("- Blac Duck report file: " + excelValues.getfilepath());
			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
	        return (OS.indexOf("win") >= 0);
	}
	  
	public static boolean isMac() {	  
	        return (OS.indexOf("mac") >= 0);	  
	}
	  
	public static boolean isUnix() {
	         return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
	  
	public static boolean isSolaris() {	 
        return (OS.indexOf("sunos") >= 0);	  
    }
    
}