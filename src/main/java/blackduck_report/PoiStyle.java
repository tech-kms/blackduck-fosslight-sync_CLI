package blackduck_report;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

import integration.values.ExcelValues;

public class PoiStyle {
	
	ExcelValues excelValues = ExcelValues.getInstance();
	XSSFCellStyle sh0TitleFormat1 = excelValues.getWB().createCellStyle();
	XSSFFont font1 = excelValues.getWB().createFont();
	
	XSSFCellStyle sh0TitleFormat2 = excelValues.getWB().createCellStyle();
	XSSFFont font2 = excelValues.getWB().createFont();
	
	XSSFCellStyle sh0TitleFormat3 = excelValues.getWB().createCellStyle();
	XSSFFont font3 = excelValues.getWB().createFont();	
			
	public void setStyle(){
		
			font1.setFontName("맑은 고딕");
			font1.setFontHeightInPoints((short)11);
			font1.setColor(IndexedColors.WHITE.getIndex());
			font1.setBold(true);
			
			sh0TitleFormat1.setAlignment(HorizontalAlignment.LEFT);
			sh0TitleFormat1.setVerticalAlignment(VerticalAlignment.CENTER);
			sh0TitleFormat1.setFillForegroundColor(new XSSFColor(new java.awt.Color(0,32,96), new DefaultIndexedColorMap()));
			sh0TitleFormat1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			sh0TitleFormat1.setFont(font1);						

			font2.setFontName("맑은 고딕");
			font2.setFontHeightInPoints((short)11);
			font2.setColor(IndexedColors.GREY_50_PERCENT.getIndex());
			
			sh0TitleFormat2.setAlignment(HorizontalAlignment.LEFT);
			sh0TitleFormat2.setVerticalAlignment(VerticalAlignment.CENTER);
			sh0TitleFormat2.setFillForegroundColor(new XSSFColor(new java.awt.Color(255,255,204), new DefaultIndexedColorMap()));
			sh0TitleFormat2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			sh0TitleFormat2.setFont(font2);
			
			font3.setFontName("맑은 고딕");
			font3.setFontHeightInPoints((short)10);
			font3.setColor(IndexedColors.BLACK.getIndex());
			
			sh0TitleFormat3.setAlignment(HorizontalAlignment.LEFT);
			sh0TitleFormat3.setVerticalAlignment(VerticalAlignment.CENTER);			
			sh0TitleFormat3.setBorderTop(BorderStyle.THIN);
			sh0TitleFormat3.setBorderRight(BorderStyle.THIN);
			sh0TitleFormat3.setBorderLeft(BorderStyle.THIN);
			sh0TitleFormat3.setBorderBottom(BorderStyle.THIN);
			sh0TitleFormat3.setFont(font3);	
			sh0TitleFormat3.setWrapText(true);
			
	}
}