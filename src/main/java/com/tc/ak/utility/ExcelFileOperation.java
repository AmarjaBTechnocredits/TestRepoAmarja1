package com.tc.ak.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileOperation {
	Workbook myworkbook=null;
	public ExcelFileOperation(String path) throws IOException
	{
		File file =new File(path);
		FileInputStream input=new FileInputStream(file);
		myworkbook=new XSSFWorkbook(input);
	}
	public String[][] getRow(String sheetName,int rowIndex)
	{
		Sheet sheet=myworkbook.getSheet(sheetName);
		Row row=sheet.getRow(rowIndex);
		int start=row.getFirstCellNum();
		int end=row.getLastCellNum();
		String data[] [] =new String [1][end];
		for(int i=start;i<end;i++)
		{
			data[0][i]=(row.getCell(i).getStringCellValue());
		}
		return data;
	}
	
public Object [][] getAllrows(String sheetName,boolean isheader)
{
	Sheet sheet=myworkbook.getSheet(sheetName);
	int count=0;
	int firstrow = sheet.getFirstRowNum();
	System.out.println("Firstrow"+firstrow);
	int lastrow= sheet.getLastRowNum();
	System.out.println("Lastrow"+lastrow);
	int firstcol = sheet.getRow(firstrow).getFirstCellNum();
	int lastcol=sheet.getRow(0).getLastCellNum();
	System.out.println("Lastcol"+lastcol);
	Object[][] data =new Object [lastrow][lastcol];
	System.out.println("rownumber" + firstrow);
	if (isheader) {
		firstrow++;
		 count=1;
		System.out.println("first rowi increment"+firstrow);
		}
	
	for (int row = firstrow; row <= lastrow; row++) {
		for (int col = firstcol; col <lastcol ; col++) {
			try {
				if (CellType.NUMERIC == sheet.getRow(row).getCell(col).getCellType()) {
					double cellData = sheet.getRow(row).getCell(col).getNumericCellValue();
					System.out.print(cellData + "\t");
					data[row-count][col]=sheet.getRow(row).getCell(col).getNumericCellValue();
				} else if (CellType.STRING == sheet.getRow(row).getCell(col).getCellType()) {
					String cellData = sheet.getRow(row).getCell(col).getStringCellValue();
					System.out.print(cellData + "\t");
					data[row-count][col]=cellData;}
				else if (CellType.BOOLEAN == sheet.getRow(row).getCell(col).getCellType())
				{
					data[row-count][col]=sheet.getRow(row).getCell(col).getBooleanCellValue();
				}							
			} catch (NullPointerException e) {
			System.out.println("Data unavailable");
			data[row-count][col]="";
			}
		}
		System.out.println();
	}
	return data;
}



}
