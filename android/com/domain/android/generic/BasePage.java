package com.domain.android.generic;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BasePage
{
	public static AppiumDriver<MobileElement> driver;
	public BasePage(AppiumDriver driver)
	{
		this.driver=driver;
	}
	
	public static void verifyTitleElement(String expectedTitleElement,String actualTitleElement)  
	{
		Logger logger = Logger.getLogger("Base Elements");
		logger.info("Verifying the title/label of the app as per the requirement.");
		try
		{
		Assert.assertEquals(actualTitleElement, expectedTitleElement);
		}
		catch(Exception exp)
		{
			Reporter.log("The title/label of the app is missing");
			Assert.fail();
		}
	}
		
	public static void verifyPresenceOfElement(MobileElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
			
		try
			{
				wait.until(ExpectedConditions.visibilityOf(element));
				Reporter.log("The specified element is present on the screen",true);
			}
		catch(Exception e)
			{
				Reporter.log("The specified element is not present on the screen",true);
				Assert.fail();
			}
			
	}
		
	
}
