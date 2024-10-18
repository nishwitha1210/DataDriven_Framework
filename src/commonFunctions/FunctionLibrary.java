package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtils;

public class FunctionLibrary extends AppUtils
{
	//method for login
	public static boolean adminLogin(String username,String password) throws Throwable 
	{
		driver.get(prop.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.id(prop.getProperty("Objreset"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("Objuser"))).sendKeys(username);
		driver.findElement(By.xpath(prop.getProperty("Objpass"))).sendKeys(password);
		driver.findElement(By.xpath(prop.getProperty("Objlogin"))).click();
         
		String expected = "dashboard";
		String actual = driver.getCurrentUrl();
		
		if(actual.contains(expected))
		{
			Reporter.log("valid credentilas: "+expected+"    "+actual,true);
			driver.findElement(By.xpath(prop.getProperty("Objlogout"))).click();
			Thread.sleep(3000);
			return true;
		}
		else
		{
			String errormsg = driver.findElement(By.xpath(prop.getProperty("Objerror"))).getText();
			driver.findElement(By.xpath(prop.getProperty("ObjOk"))).click();
			
			Reporter.log("error_message: "+errormsg +expected+"        "+actual,true);
			return false;
		}
	}
}
