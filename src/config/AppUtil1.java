package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.hpsf.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunctions.LoginPage;
import commonFunctions.LogoutPage;

public class AppUtil1 {
public static	WebDriver driver;
public static Properties prop;

@BeforeTest
public static void setUp() throws Throwable
{
	prop = new Properties();
	prop.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	
	if(prop.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(prop.getProperty("Url"));
		
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		login.adminLogin("admin", "master");
		
	}
	else if(prop.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(prop.getProperty("Url"));
		
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		login.adminLogin("admin", "master");
	}
	else
	{
		Reporter.log("Browser value not matching",true);
		
	}
	

}
@AfterTest
public static void tearDown()
{
	LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
	logout.adminLogout();
	driver.quit();
}	

}
