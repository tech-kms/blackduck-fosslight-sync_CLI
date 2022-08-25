package integration.values;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelValues {
	private static ExcelValues values = new ExcelValues();
	
	private XSSFSheet sheet0;
	XSSFWorkbook wb = new XSSFWorkbook();;
	
	private String filepath;
	
	private ExcelValues() {
	}

	public static ExcelValues getInstance() {
		return values;
	}
	
	public XSSFWorkbook getWB(){
		return wb;
	}	
	
	public XSSFSheet getSheet0(){
		return sheet0;
	}
	
	public String getfilepath() {
		return filepath;
	}
	
	public void setfilepath(String filepath) {
		this.filepath = filepath;
	}
}