package blackduck_report;

import java.io.File;

import integration.values.ExcelValues;



public class DeleteReportfile {
	
	ExcelValues excelValues = ExcelValues.getInstance();
	
	public void deleteReportfile() {	
		
		File file = new File(excelValues.getfilepath()); 
		
		if(file.exists() ) {
			
			if(file.delete()) {
				System.out.println();
				System.out.println("The BD report file is deleted");				
				System.out.println("- BD report file: " + excelValues.getfilepath());
			} else { 			
				System.out.println();
				System.out.println("FAILED: Deleting the BD report file is failed");
				System.out.println("- BD report file: " + excelValues.getfilepath());				
			} 
			
		} else {
			System.out.println();
			System.out.println("ERROR: The BD report file is not exist"); 
			System.out.println("- BD report file: " + excelValues.getfilepath());			
			System.exit(1);
		}		
		
		System.out.println();
		System.out.println("The Project data migration from BD to FOSSLight is finished");
		System.exit(1);
	}

}