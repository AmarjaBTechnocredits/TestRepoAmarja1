package com.tc.ak.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tc.ak.constant.ConstantPath;

abstract public class PredefinedActions {
	//private WebDriverWait wait = new WebDriverWait(driver, 30);
	

private WebElement getElement(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(getObject(locator)));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",element);
		return element;
	}

	protected String acceptAlert() {
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		alert.accept();
		return actual;
		}

	protected String dismissAlert() {
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		alert.dismiss();
		return actual;
	}
	
	static public WebDriver driver = null;
	public static WebDriver browserinit()
	{		
		browserinit("http://automationbykrishna.com/#");
		return driver;
	}
	static public WebDriver browserinit(String url)
	{
		System.setProperty("webdriver.chrome.driver", ConstantPath.CHROMEDRIVERPATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	protected void enterText(String locator,String text)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		getElement(locator).sendKeys(text);;
		
	}
	
	protected void click(String locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element=getElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	private By getObject(String locator)
	{
		String locatorType=locator.split(":")[0].replace("[","").replace("]", "").toUpperCase();
		System.out.println("locatortype"+locator);
		String locatorValue=locator.split(":")[1];
		System.out.println("locatorvalue"+locatorValue);
		switch (locatorType)
		{
			case "ID":
			return By.id(locatorValue);
				case "XPATH":
			return By.xpath(locatorValue);
			case "CSS":
			return By.cssSelector(locatorValue);
		}
		return null;
		
	}
	
	protected String getText(String locator)
	{
		
		return getElement(locator).getText();
	}
	protected String getAttribute(String locator,String attributeName)
	{
		
		return getElement(locator).getAttribute(attributeName);
	}
	public static void closeSession()
	{
		driver.close();
	}
	public static void getScreenshot(String fileName) throws IOException
	{
		
		TakesScreenshot screenshot=(TakesScreenshot) driver;//Explicit Typecasting
		File evidence=screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(evidence,new File(".//Snapshot//"+fileName+"_"+getTimeStamp()+".png"));
	}
	private static String getTimeStamp()
	{
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("ddMMyyyyhhmmss");
		return simpleDateFormat.format(new Date());
		
	}
	
	

}
