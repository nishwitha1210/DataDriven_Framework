package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
	@FindBy(xpath="//a[@id='logout']")
	WebElement logout;
	
	public void adminLogout()
	{
		logout.click();
	}

}
