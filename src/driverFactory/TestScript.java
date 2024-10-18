package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.CustomerPage;
import config.AppUtil1;
import utilities.ExcelFileUtil;

public class TestScript extends AppUtil1 {
String inputpath ="./FileInput/Customers.xlsx";
String outputpath="./FileOutput/POMResults.xlsx";
String TCSheet="CustomerData";

@Test
public void startTest() throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	
	int rc=xl.rowCount(TCSheet);
	Reporter.log("No.of rows are: "+rc,true);
	
	for(int i=1;i<=rc;i++)
	{
		//read all cells fro TCSheet
		String Cname = xl.getCellData(TCSheet, i, 0);
		String Address = xl.getCellData(TCSheet, i, 1);
		String City = xl.getCellData(TCSheet, i, 2);
		String Country = xl.getCellData(TCSheet, i, 3);
		String Cperson = xl.getCellData(TCSheet, i, 4);
		String PhoneNo = xl.getCellData(TCSheet, i, 5);
		String Email = xl.getCellData(TCSheet, i, 6);
		String MobileNo = xl.getCellData(TCSheet, i, 7);
		String Notes = xl.getCellData(TCSheet, i, 8);
		
		CustomerPage addcus = PageFactory.initElements(driver, CustomerPage.class);
		boolean res = addcus.addCustomer(Cname, Address, City, Country, Cperson, PhoneNo, Email, MobileNo, Notes);
		
	    
		if(res)
		{
			xl.setCellData(TCSheet, i, 9, "Pass", outputpath);
		}
		else
		{
			xl.setCellData(TCSheet, i, 9, "Fail", outputpath);
		}
	}
}
}
