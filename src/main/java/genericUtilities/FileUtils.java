package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUtils {
	
	/**
	 * this method is used to read the data from property file
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromPropertyFile(String key) throws Throwable
	{
		FileInputStream fisp = new FileInputStream(IpathConstants.PropertyFilePath);
		Properties pobj = new Properties();
		pobj.load(fisp);
		String value = pobj.getProperty(key);
		return value;
	}
	
    /**
     * this method is used to get last row number
     * @param sheetName
     * @return
     * @throws Throwable
     */
	public int getLastRowNo(String sheetName) throws Throwable
	{
		FileInputStream fisxl = new FileInputStream(IpathConstants.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fisxl);
		
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		return lastRow;
		
	}
	
	/**
	 * this method is used to fetch multiple data from excel sheet
	 * @param sheetName

	 * @param cell
	 * @return
	 * @throws Throwable
	 */
	public HashMap<String,String> hashMapData(String sheetName, int cell) throws Throwable
	{
		FileInputStream fisxl = new FileInputStream(IpathConstants.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fisxl);
		Sheet sh = wb.getSheet(sheetName);
		int rowcount = sh.getLastRowNum();
		
		HashMap<String, String> map = new HashMap<String, String>();//empty map
		
		for (int i = 0; i <=rowcount; i++) {
			String key = sh.getRow(i).getCell(cell).getStringCellValue();
			String value = sh.getRow(i).getCell(cell+1).getStringCellValue();
			map.put(key, value);
		}
//		for ( Entry<String, String> set : map.entrySet()) {
//			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
//		}
		return map;
		
	}
	
	/**
	 * writing the single data into excel sheet
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @param data
	 * @throws Throwable
	 */
	public void writeDataIntoExcel(String sheetName, int rowNo, int cellNo, String data) throws Throwable
	{
		FileInputStream fisxl = new FileInputStream(IpathConstants.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fisxl);
		Sheet sh = wb.getSheet(sheetName);
		sh.createRow(rowNo).createCell(cellNo).setCellValue(data);
		FileOutputStream fosxl=new FileOutputStream(IpathConstants.ExcelFilePath);
		wb.write(fosxl);
		wb.close();
		
	}
	
	public String readSingleDataFromExcel(String sheetName, int rowNo, int cellNo) throws Throwable
	{
		FileInputStream fisxl = new FileInputStream(IpathConstants.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fisxl);
		Sheet sh = wb.getSheet(sheetName);
		String value = sh.getRow(rowNo).getCell(cellNo).getStringCellValue();
		return value;
		
	}
	
	/**
	 * this method fetch multiple data from excel acts as dataProvider
	 * @return
	 * @throws Throwable
	 */
	public Object[][] readMultipleDataFromExcel(String sheetName) throws Throwable {
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum()+1;
		int lastCell = sh.getRow(0).getLastCellNum();
		
		Object[][] obj=new Object[lastRow][lastCell];
		
		for (int i = 0; i < lastRow; i++) {
			for (int j = 0; j < lastCell; j++) {
				
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
			
		}
		return obj;
	
	}

}
