package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement ObjReset;
	@FindBy(xpath = "//input[@id='username']")
	WebElement ObjUser;
	@FindBy(xpath = "//input[@id='password']")
	WebElement ObjPass;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement ObjLogin;
	
	public void adminLogin(String username,String password)
	{
		ObjReset.click();
		ObjUser.sendKeys(username);
		ObjPass.sendKeys(password);
		ObjLogin.click();
	}
	
	
	

}
