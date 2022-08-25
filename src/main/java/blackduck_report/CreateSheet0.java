 package blackduck_report;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import integration.values.ExcelValues;
import integration.values.IdentifiedFilesValues;

public class CreateSheet0 extends PoiStyle{
	
	public CreateSheet0(){
		super();
	}
	
	ExcelValues excelValues = ExcelValues.getInstance();
	XSSFSheet sheet0 = excelValues.getSheet0();
	IdentifiedFilesValues identifiedFilesValues = IdentifiedFilesValues.getInstance();
	
	Row row = null;
	Cell cell = null;
		
	public void writeSheet() {		
		
		System.out.println();
		System.out.println("Create Blac Duck report");
		
		setStyle();
		
		sheet0 = excelValues.getWB().createSheet("SRC");
		
		sheet0.setColumnWidth(0, 1500); //ID
		sheet0.setColumnWidth(1, 7000); //Path
		sheet0.setColumnWidth(2, 5000); //CoName
		sheet0.setColumnWidth(3, 4000);	//CoVersion
		sheet0.setColumnWidth(4, 5000); //License
		sheet0.setColumnWidth(5, 7000); //Download
		sheet0.setColumnWidth(6, 7000); //Homepage
		sheet0.setColumnWidth(7, 7000); //Copyright
		sheet0.setColumnWidth(8, 3000); //Exclude
		sheet0.setColumnWidth(9, 6000); //Comment

		row = sheet0.createRow(0);
		row.setHeight((short) 400);
		
		cell = row.createCell(0);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("ID");
		
		cell = row.createCell(1);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("Source Name or Path");
		
		cell = row.createCell(2);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("OSS Name");
		
		cell = row.createCell(3);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("OSS Version");
		
		cell = row.createCell(4);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("License");
		
		cell = row.createCell(5);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("Download Location");
		
		cell = row.createCell(6);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("Homepage");
		
		cell = row.createCell(7);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("Copyright Text");
		
		cell = row.createCell(8);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("Exclude");
		
		cell = row.createCell(9);				
		cell.setCellStyle(sh0TitleFormat1);		
		cell.setCellValue("Comment");
		
		
		row = sheet0.createRow(1);
		row.setHeight((short) 400);
		
		cell = row.createCell(0);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("-");

		cell = row.createCell(1);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("[Name of the Source File or Path / Source File 혹은 Path]");

		cell = row.createCell(2);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("[Name of the OSS used / 사용한 Open Source 이름]");

		cell = row.createCell(3);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("[Version Number of the OSS / Open Source의 Version]");

		cell = row.createCell(4);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("[License of the OSS. Use SPDX Identifier : https://spdx.org/licenses/]");

		cell = row.createCell(5);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("[Download URL or a specific location within a VCS for the OSS / Open Source를 Download 받을 수 있는 URL 혹은 VCS 위치]");

		cell = row.createCell(6);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("[Web site that serves as the OSS's home page / Open Source의 Homepage 역할을 하는 website]");

		cell = row.createCell(7);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("[The copyright holders of the OSS / OSS의 Copyright 정보. 예: Copyright (c) 2021 fosslight.org]");

		cell = row.createCell(8);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("[If this OSS is not included in the final version, Exclude / 이 Open Source가 최종 version에 포함되지 않은 경우, \"Exclude\"라고 기재하세요.]]");

		cell = row.createCell(9);				
		cell.setCellStyle(sh0TitleFormat2);		
		cell.setCellValue("");
		
		for(int i = 0; i < identifiedFilesValues.getfilepath().size(); i++) {
			
			row = sheet0.createRow(i + 2);
			
			cell = row.createCell(0);				
			cell.setCellStyle(sh0TitleFormat3);		
			cell.setCellValue(Integer.toString(i + 1));
			
			cell = row.createCell(1);				
			cell.setCellStyle(sh0TitleFormat3);		
			cell.setCellValue(identifiedFilesValues.getfilepath().get(i).toString());
			
			cell = row.createCell(2);				
			cell.setCellStyle(sh0TitleFormat3);		
			cell.setCellValue(identifiedFilesValues.getcomponenetName().get(i).toString());
			
			cell = row.createCell(3);				
			cell.setCellStyle(sh0TitleFormat3);		
			cell.setCellValue(identifiedFilesValues.getcomponentVersion().get(i).toString());			
			
			cell = row.createCell(4);				
			cell.setCellStyle(sh0TitleFormat3);		
			cell.setCellValue(identifiedFilesValues.getcomponentLicenseId().get(i).toString());
			
			cell = row.createCell(5);				
			cell.setCellStyle(sh0TitleFormat3);		
			//cell.setCellValue(identifiedFilesValues.getcomponentDownload().get(i).toString());
			
			cell = row.createCell(6);				
			cell.setCellStyle(sh0TitleFormat3);		
			cell.setCellValue(identifiedFilesValues.getcomponentHompage().get(i).toString());
			
			cell = row.createCell(7);				
			cell.setCellStyle(sh0TitleFormat3);		
			cell.setCellValue(identifiedFilesValues.getCopyright().get(i).toString());
			
			cell = row.createCell(8);				
			cell.setCellStyle(sh0TitleFormat3);
	
			XSSFDataValidationHelper dvHelper9 = new XSSFDataValidationHelper(sheet0);
			XSSFDataValidationConstraint dvConstraint9 = (XSSFDataValidationConstraint)
			dvHelper9.createExplicitListConstraint(new String[]{"Exclude"});
			CellRangeAddressList addressList9 = new CellRangeAddressList(i+2, i+2, 8, 8);
			XSSFDataValidation validation9 = (XSSFDataValidation)dvHelper9.createValidation(
			dvConstraint9, addressList9);
			validation9.setShowErrorBox(true);
			sheet0.addValidationData(validation9);
			
			cell = row.createCell(9);				
			cell.setCellStyle(sh0TitleFormat3);		
			//cell.setCellValue(identifiedFilesValues.getcommnet().get(i).toString());
			
		}
		
		System.out.println("The Blac Duck report file is created");
		
	}
}