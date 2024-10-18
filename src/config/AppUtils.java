package config;

import java.io.FileInputStream;

import java.util.Properties;
import org.testng.Reporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtils 
{
	public static WebDriver driver;
	public static Properties prop;
	
	@BeforeTest
	public static void setup() throws Throwable
	{
		prop = new Properties();
		//loading property file
		prop.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		
		if(prop.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
		}
		else if(prop.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		else
		{
			Reporter.log("Browser value is NOt Matching",true);
		}
	}
	
	@AfterTest
	public static void tearDown()
	{
		driver.quit();
		
	}

}
