package com.domain.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.domain.generic.BasePage;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class MetalmannHomePage extends BasePage 
{
	Logger logger = Logger.getLogger("MetalmannHomePage");
	
	@FindBy(xpath = "//a[@id='logout_btn']")
	private WebElement logoutBtn;
	@FindBy(xpath = "//a[.='Manage Console']")
	private WebElement manageConsole;
	@FindBy(xpath = "//a[.='MMI Users']")
	private WebElement mmiUsers;
	
	
	
	public MetalmannHomePage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void click_on_logout_btn()
	{
		try
		{
			logger.info("Click on logout button");
			logoutBtn.click();
		}
		catch(ElementNotFoundException e)
		{
			logger.info("Logout button element not found. Message: "+e.getMessage());
		}
	}
	public void click_on_manage_console_module()
	{
		logger.info("Click on manage console module");
		manageConsole.click();
	}
	public void click_on_mmi_users_sub_module()
	{
		logger.info("Click on mmi users sub module of manage console");
		mmiUsers.click();
	}
}

