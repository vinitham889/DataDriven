package com.parabank.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.parabank.base.ParaBankBase;

public class KeywordDriven {
	

	public WebDriver driver;
	public Properties prop;
	public WebElement element;
	
	
	public static Workbook book;
	public static Sheet sheet;
	public ParaBankBase base;
	
	//Excel path
	public final String SCENARIO_SHEET_PATH = "C:\\Users\\vinitham\\eclipse-workspace\\PARABANK\\src\\main\\java\\com\\parabank\\keyword\\scenario\\KeywordDriven.xlsx";
	
	public void startExecution(String sheetName) throws EncryptedDocumentException, IOException
	{
		String locatorName = null;
		String locatorValue = null;
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
			}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			book = WorkbookFactory.create(file);
		}catch(InvalidFormatException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		int k=0;
		
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
//			try
//		{                            //getRow(i+1) starts from second row getCell(k+1) 
			String locatorColValue = sheet.getRow(i+1).getCell(k+1).toString().trim();// fetch xpath from excel by removing space xpath id=username
			if(locatorColValue.equalsIgnoreCase("NA")) 
			{
				locatorName = locatorColValue.split("=")[0].trim(); //id
				locatorValue = locatorColValue.split("=")[0].trim(); //username
			}
			String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
			
			switch(action)
			{
			case "open browser":
				base = new ParaBankBase();
				prop = base.init_properties();
				if(value.isEmpty() || value.equals("NA"))
				{
					driver = base.init_driver(prop.getProperty("browser"));
					}else
					{
						driver = base.init_driver(value);
					}
				break;
				
			case "enter url":
				if(value.isEmpty() || value.equals("NA")) {
					driver.get(prop.getProperty("url"));
				}else
				{
					driver.get(value);	
				}
				break;
			case "quit":
				driver.quit();
				break;
				default :
					break;
				}
			switch(locatorName) {
			
			case "id":
				element = driver.findElement(By.id(locatorValue));
				if(action.equalsIgnoreCase("sendkeys"))
				{
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}
				locatorName = null;
				break;	
			case "linkText":
				element =driver.findElement(By.linkText(locatorValue));
				element.click();
				locatorName=null;
				break;
			
			case "classname":
				element = driver.findElement(By.className(locatorValue));
				if(action.equalsIgnoreCase("sendKeys"))
				{
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}
				locatorName = null;
				break;
				
				
				default :
					break;
			}
				
			
			
//		}catch(Exception e) {
//						
//					}
//				
			
			
		}
	}
}
		
		
	
	

	


