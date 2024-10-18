package commonFunctions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage {
	
WebDriver driver;

//constructor to accessing webdriver methods
public CustomerPage(WebDriver driver)
{
	this.driver=driver;
	
}

//defining repository
@FindBy(xpath="(//a[contains(.,'Customers')])[2]")
WebElement clickCustomer;

@FindBy(xpath="(//span[@data-phrase='AddLink'])[1]")
WebElement clickAddIcon;
@FindBy(name="x_Customer_Number")
WebElement customerNumber;
@FindBy(name="x_Customer_Name")
WebElement EntercustomerName;
@FindBy(name="x_Address")
WebElement EnterAddress;
@FindBy(name="x_City")
WebElement EnterCity;
@FindBy(name="x_Country")
WebElement EnterCountry;
@FindBy(name="x_Contact_Person")
WebElement EnterContactPerson;
@FindBy(name="x_Phone_Number")
WebElement EnterPhoneNumber;
@FindBy(name="x__Email")
WebElement EnterEmail;
@FindBy(name="x_Mobile_Number")
WebElement EnterMobileNumber;
@FindBy(name="x_Notes")
WebElement EnterNotes;
@FindBy(id="btnAction")
WebElement ClickAddBtn;
@FindBy(xpath = "//button[normalize-space()='OK!']")
WebElement ClickConfirmOk;
@FindBy(xpath="//button[@class='ajs-button btn btn-primary']")
WebElement ClickAlertOk;
@FindBy(xpath="(//span[contains(@data-caption,'Search')])[1]")
WebElement ClickSearchIcon;
@FindBy(xpath="//input[@id='psearch']")
WebElement EnterSearch;
@FindBy(xpath="//button[@id='btnsubmit']")
WebElement ClickSearch;
@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
WebElement webtable;

//method for adding customer
public boolean addCustomer(String customername,String Address,String City,String Country,String ContactPerson,String PhoneNumber,
		String Email,String MobileNumber,String Notes) throws Throwable
{
	Actions ac = new Actions(driver);
	ac.moveToElement(this.clickCustomer).click().perform();
	ac.moveToElement(this.clickAddIcon).click().perform();
	String exp_data = this.customerNumber.getAttribute("value");
	this.EntercustomerName.sendKeys(customername);
	this.EnterAddress.sendKeys(Address);
	this.EnterCity.sendKeys(City);
	this.EnterCountry.sendKeys(Country);
	this.EnterContactPerson.sendKeys(ContactPerson);
	this.EnterPhoneNumber.sendKeys(PhoneNumber);
	this.EnterEmail.sendKeys(Email);
	this.EnterMobileNumber.sendKeys(MobileNumber);
	this.EnterNotes.sendKeys(Notes);
	this.ClickAddBtn.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	this.ClickConfirmOk.click();
	Thread.sleep(2000);
	this.ClickAlertOk.click();
	Thread.sleep(2000);
	
	//click search icon if search textbox is not dislayed
	if(!this.EnterSearch.isDisplayed())
		this.ClickSearchIcon.click();
	this.EnterSearch.clear();
	this.EnterSearch.sendKeys(exp_data);
	this.ClickSearch.click();
	Thread.sleep(2000);
	
	String act_data = webtable.getText();
	if(exp_data.equals(act_data))
	{
		Reporter.log("Add Customer Success",true);
		return true;
		
	}else
	{
		Reporter.log("Add Customer Fail",true);
		return false;
	}
	
}

}
