package Adhoc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	private static FileInputStream fi;
	private static FileOutputStream fo;

	private static XSSFWorkbook wb;
	private static XSSFSheet ws;
	private static XSSFRow row;
	private static XSSFCell cell;

//	private static String testDataFilepath = System.getProperty("user.dir")
//			+ "/src/test/java/com/cpm/testdata/testdata.xlsx";

	private static File fs;

	public static int getRowCount(String filePath, String sheetName){
		try {
			fs = new File(filePath);
			fi = new FileInputStream(fs);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			wb.close();
			fi.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return ws.getPhysicalNumberOfRows();
	}

	public static int getColumnCount(String filePath, String sheetName){
		try {
			fs = new File(filePath);
			fi = new FileInputStream(fs);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(1);
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			wb.close();
			fi.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return row.getPhysicalNumberOfCells();
	}

	public static String getData(String filePath, String sheetName, int rowNum, int colNum) {
		try {
			fs = new File(filePath);
			fi = new FileInputStream(fs);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rowNum);
			cell = row.getCell(colNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data;
		try {
			DataFormatter format = new DataFormatter();
			data = format.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		try {
			wb.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static double getDataAsDouble(String filePath, String sheetName, int rowNum, int colNum) {
		try {
			fs = new File(filePath);
			fi = new FileInputStream(fs);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rowNum);
			cell = row.getCell(colNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data;
		try {
			DataFormatter format = new DataFormatter();
			data = format.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		try {
			wb.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Double.parseDouble(data);
	}
	
	public static long getDataAsLong(String filePath, String sheetName, int rowNum, int colNum) {
		try {
			fs = new File(filePath);
			fi = new FileInputStream(fs);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rowNum);
			cell = row.getCell(colNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data;
		try {
			DataFormatter format = new DataFormatter();
			data = format.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		try {
			wb.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Long.parseLong(data);
	}
	
	public static int getDataAsInteger(String filePath, String sheetName, int rowNum, int colNum) {
		try {
			fs = new File(filePath);
			fi = new FileInputStream(fs);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rowNum);
			cell = row.getCell(colNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data;
		try {
			DataFormatter format = new DataFormatter();
			data = format.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		try {
			wb.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(data);
	}

	public static void setData(String filePath, String sheetName, int rowNum, int colNum, String str) {
		try {
			fs = new File(filePath);
			fi = new FileInputStream(fs);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rowNum);
			if(row == null)
				 row = ws.createRow(rowNum);
			cell = row.getCell(colNum);
			if(cell==null)
				cell = row.createCell(colNum);
			cell.setCellValue(str);
			fo = new FileOutputStream(fs);
			wb.write(fo);
			fo.flush();
			fo.close();
			wb.close();
			fi.close();
		} catch (IOException e) {
			System.out.println("File not found/Corrupted- Detailed message:"+e.getMessage());
		} 
	}
}

