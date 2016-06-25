package com.fv.shiftreport.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIUtil {

	
	public static HSSFWorkbook createWorkBook(){
		return new HSSFWorkbook();
	}
	
	public static HSSFSheet createWorkSheet(HSSFWorkbook workBook,String sheetName){
		return workBook.createSheet(sheetName);
	}
	
	public static void writeToCell(HSSFSheet sheet,int x, int y, String value){
		HSSFRow row = sheet.getRow(x);
		if(null==sheet.getRow(x)){
			row = sheet.createRow(x);
		}
		HSSFCell cell = row.createCell(y);
		cell.setCellValue(value);
	}
	
	public static void writeExccelToFile(HSSFWorkbook workbook,File dir){
		try {
		    FileOutputStream out = 
		            new FileOutputStream(dir);
		    workbook.write(out);
		    out.close();
		} catch (FileNotFoundException e) {
			Util.writeToFile("error", e.getMessage());
		    e.printStackTrace();
		} catch (IOException e) {
			Util.writeToFile("error", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[]rags){
		HSSFWorkbook workbook = createWorkBook();
		HSSFSheet sheet = createWorkSheet(workbook, "test");
		writeToCell(sheet, 1, 1, "test");
		writeToCell(sheet, 1, 2, "test1");
		writeToCell(sheet, 10, 11, "testxxx");
		writeToCell(sheet, 0, 0, "testxxx");
		//writeExccelToFile(workbook, "D:/sample.xls");
		
		
	}
}
