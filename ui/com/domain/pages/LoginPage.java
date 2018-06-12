package com.domain.pages;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.domain.generic.BasePage;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;




public class LoginPage extends BasePage
{
	//Getting the logger for logging activity in console
	Logger logger = Logger.getLogger("Login Page");
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement mailID;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;
	
	@FindBy(xpath = "//button[@id='login_btn']")
	private WebElement submit;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	public void input_username(String user_mailID) throws InterruptedException
	{
		
		try
		{
			logger.info("Enter the username in the username field");
			mailID.sendKeys(user_mailID);
		}
		catch(ElementNotFoundException e)
		{
			logger.info("Username element not found. Message: "+e.getMessage());
		}
	}
	
	public void input_password(String user_password)
	{
		try
		{
			logger.info("Enter the password in the password field");
			password.sendKeys(user_password);
		}
		catch(ElementNotFoundException e)
		{
			logger.info("Password element not found. Message: "+e.getMessage());
		}
		
	}
	
	public void click_login()
	{
		try
		{
			logger.info("Click on login button");
			submit.click();
		}
		catch(ElementNotFoundException e)
		{
			logger.info("Submit button not found. Message: "+e.getMessage());
		}
	}
	
	public void wait_for(int time_in_milliseconds)
	{
		try 
			{
				Thread.sleep(time_in_milliseconds);
			}
		catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
	}
	

}
