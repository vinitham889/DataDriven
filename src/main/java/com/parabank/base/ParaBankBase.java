package com.parabank.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class ParaBankBase {
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browserName)
	{
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if(prop.getProperty("headless").equals("yes"))
			{
			options.addArguments("--headless");
	        driver = new ChromeDriver(options);
			}else
			{
				options.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(options);
			}
		}
		return driver;
	}
	public Properties init_properties() throws IOException
	{
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\vinitham\\eclipse-workspace\\PARABANK\\src\\main\\java\\com"
		                             +"\\para\\config\\config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	

}
