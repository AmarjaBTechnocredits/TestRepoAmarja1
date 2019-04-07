package com.tc.ak.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.tc.ak.base.PredefinedActions;
import com.tc.ak.constant.ConstantPath;
import com.tc.ak.utility.PropertyFileOperation;

public class RegistrationPage extends PredefinedActions {


	PropertyFileOperation propOperation;
	static RegistrationPage registrationPage=null;
	
	private RegistrationPage() throws IOException
	{
		try
		{
		 propOperation = new PropertyFileOperation(ConstantPath.REGISTRATIONPAGEPROPERTY);
		}
		catch  (IOException e)
		{
			System.out.println("Filenotfound");
			e.printStackTrace();
		}
		
	}
	
	/*public RegistrationPage() throws IOException
	{
		try
		{
		 propOperation = new PropertyFileOperation(ConstantPath.REGISTRATIONPAGEPROPERTY);
		}
		catch  (IOException e)
		{
			System.out.println("Filenotfound");
			e.printStackTrace();
		}
		
	}*/
	
	
	
	public static RegistrationPage getInstance() throws IOException
	{
		if (registrationPage == null)
			registrationPage= new RegistrationPage();
		return registrationPage;
		
	}
	public void login(String username,String password) throws FileNotFoundException {

		enterTextonRegistrationPage("regusernamelocator",username);
		enterTextonRegistrationPage("regpasswordlocator",password);
		ButtonClickonRegistrationPage("regloginbuttonlocator");

	}
	
	private void enterTextonRegistrationPage(String key,String text) throws FileNotFoundException 
	{		
		enterText(propOperation.readData(key),text);
	}

	private void ButtonClickonRegistrationPage(String key) throws FileNotFoundException {
				click(propOperation.readData(key));
			}

	public String hanldeAlertonRegistration() {
		return acceptAlert();
	}
	
	public void goToReg() throws IOException
	{
		System.out.println(propOperation.readData("registrationmenu"));
		ButtonClickonRegistrationPage("registrationmenu");
		
		
	}
}
