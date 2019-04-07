package com.tc.ak.testScripts;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tc.ak.base.PredefinedActions;
import com.tc.ak.constant.ConstantPath;
import com.tc.ak.pages.RegistrationPage;
import com.tc.ak.utility.ExcelFileOperation;

public class LoginTest extends TestBase {
	@Test(dataProvider = "Logindataprovider")
public final void login(String username, String password, String expected) throws IOException {
//public final void login(HashMap<String,String> logindata) throws IOException {
		System.out.println(" Start : Browser Initialization");
		start();
		System.out.println(" End : Browser Initialization completed");
		System.out.println(" Start : Navigation to Registration page");
		RegistrationPage registrationPage= RegistrationPage.getInstance();
		gotoRegistrationPage();
		//RegistrationPage registrationPage= new RegistrationPage();
	//	registrationPage.goToReg();
		System.out.println(" End : Navigation to Registration page completed");
		System.out.println(" Start : Invoking Login method");
		registrationPage.login(username,password);
		//registrationPage.login(logindata.get("username"), logindata.get("password"));
		System.out.println(" End : Login completed");
		//Assert.assertEquals(registrationPage.hanldeAlertonRegistration(),logindata.get("expected"),
		Assert.assertEquals(registrationPage.hanldeAlertonRegistration(),expected,
				"Testcase fail: actual and expected does not match");
		
	}
	

	@DataProvider(name = "Logindataprovider")
	public Object[][] provideRegisrationData()  {
		try{
		ExcelFileOperation excelread = new ExcelFileOperation(ConstantPath.REGISTRATIONEXCELDATA);
		Object[][] data = excelread.getAllrows(ConstantPath.LOGINDATASHEET, true);
		/*HashMap<String,String> logindata= new HashMap<String,String>();
		logindata.put("username", data[0][0].toString());
		System.out.println(logindata.put("username", data[0][0].toString()));
		logindata.put("password", data[0][1].toString());
		logindata.put("expected", data[0][2].toString());
		logindata.put("username", data[1][0].toString());
		logindata.put("password", data[1][1].toString());
		logindata.put("expected", data[1][2].toString());
		Object[][] obj = new Object[1][1];
		obj[0][0] = logindata;*/
		
		//data[0][0] = logindata; 
		//return obj;
		return data;
		
		}
		catch (IOException e)
		{
			System.out.println("Provide correct data");
			e.printStackTrace();
		}
		return null;

	}
}
