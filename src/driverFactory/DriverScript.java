package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtils;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtils
{
	String inputpath="./FileInput/loginData.xlsx";
	String outputpath="./FileOutput/DataDrivenResults.xlsx";
	String TCSheet="login";
	
	@Test
	public void startTest() throws Throwable
	{
		
		ExcelFileUtil xl = new ExcelFileUtil(inputpath); //creating reference object for Excelfileutil class
		
		int rc = xl.rowCount(TCSheet); //counting no.of rows in a sheet
		System.out.println(rc);
		
		for(int i=1;i<=rc;i++)
		{
			//reading username and password from sheet
			
			String username=xl.getCellData(TCSheet,i,0);
			String password=xl.getCellData(TCSheet,i,1);
			
			//calling adminLogin method from functionlibrary class
			// adminLogin method is static.so, we call it directly without creating object
			
			boolean res = FunctionLibrary.adminLogin(username, password);
			
			if(res)
			{
				
				xl.setCellData(TCSheet,i,2,"Login Success",outputpath );
				xl.setCellData(TCSheet,i,3,"pass",outputpath );
			}
			else
			{
				
				xl.setCellData(TCSheet, i, 2, "Login Fail", outputpath);
				xl.setCellData(TCSheet, i, 3, "Fail", outputpath);
				
				//Taking screenshot for failed iterations
				File SS =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(SS, new File("./ScreenShot/Iteration/"+i+"-----------"+"LoginPage.png"));
			}
			
		}
	}
	
}
