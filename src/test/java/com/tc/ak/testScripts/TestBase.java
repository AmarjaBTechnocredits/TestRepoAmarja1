package com.tc.ak.testScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.tc.ak.base.PredefinedActions;
import com.tc.ak.pages.RegistrationPage;

public class TestBase {
	private WebDriver driver;
	
	RegistrationPage gotoRegistrationPage() throws IOException {

		//driver.findElement(By.id("registration2")).click();
		RegistrationPage registrationPage = RegistrationPage.getInstance();
		registrationPage.goToReg();
		return registrationPage  ;
	}
	void start()
	{
		//Initialization init = new Initialization();
		driver= PredefinedActions.browserinit();
	}
	void start(String url)//Overloaded method
	{
		//Initialization init = new Initialization();
		driver= PredefinedActions.browserinit(url);
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()== ITestResult.FAILURE)
			System.out.println("Result"+result.getMethod());
		PredefinedActions.getScreenshot(result.getMethod().getMethodName());
		PredefinedActions.closeSession();
		
	}
	
	

}
