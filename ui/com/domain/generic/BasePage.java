package com.domain.generic;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
public class BasePage 
{
	

	public static WebDriver driver;
	
	public BasePage(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	
	//To verify the title of the web pages
	public static void verifyTitle(String actualTitle)  
	{
		Logger logger = Logger.getLogger("Base Page");
		logger.info("Checking the actual title with our expected title");
		WebDriverWait wait=new WebDriverWait(driver, 15);
		try
		{
		
			wait.until(ExpectedConditions.titleIs(actualTitle));
			Reporter.log("The Actual title "+actualTitle+" matches our expected title",true);
		}
		catch(Exception e)
		{
			Reporter.log("The Actual title "+actualTitle+" doesnot match our expected title",true);
			Assert.fail();
		}
	}
	
	//To verify the element's presence in the webpage
	public static void verifyElement(WebElement element)
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
	
	//Check for the equality of two elements
	public static void verifyTwoElements(String expected,String actual)
	{
		if(actual.contains(expected))
		{
			Reporter.log("The actual output which is "+actual+" matches our expected output which is "+expected,true);
		}
		else
		{
			Reporter.log("The actual and expected are not matching with actual output as "+actual+" and expected as "+expected,true);
			Assert.fail();
		}
	}
	
	
		

}
