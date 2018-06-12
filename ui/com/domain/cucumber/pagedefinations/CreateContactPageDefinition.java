package com.domain.cucumber.pagedefinations;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.domain.cucumber.generic.BasePage;
import com.domain.cucumber.generic.GenericUtility;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import cucumber.api.DataTable;

public class CreateContactPageDefinition 
{
	Logger logger = Logger.getLogger("Create Contact");
	WebDriver driver;
	@FindBy(how = How.XPATH, using ="//a[contains(text(),'Contacts')]")
	private WebElement contacts;
	
	@FindBy(how = How.XPATH, using ="//a[@title='New Contact']")
	private WebElement newContact;
	
	@FindBy(how = How.XPATH, using ="//select[@name='title']")
	private WebElement titleDropdown;
	
	@FindBy(how = How.XPATH, using ="//input[@id='first_name']")
	private WebElement firstName;
	
	@FindBy(how = How.XPATH, using ="//input[@id='surname']")
	private WebElement lastName;
	
	@FindBy(how = How.XPATH, using ="//input[@value='Save']")
	private WebElement contactSaveBtn;
	
	@FindBy(how = How.XPATH, using ="(//a[@class='topnavlink'])[3]")
	private WebElement logoutBtn;
	
	

	public CreateContactPageDefinition(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public void hover_mouse_to_contacts_dropdown() throws AWTException
	{
		
		try
		{
		driver.switchTo().frame("mainpanel");
	    Actions action=new Actions(driver);
	    logger.info("Hover the mouse to the contacts button on the top bar");
	    
	    action.moveToElement(contacts).build().perform();
		}
		catch(ElementNotFoundException exp)
		{
		logger.info("Trying with second catch block to re-try hovering the mouse to the contacts button.");
		System.out.println("First Try");	
	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='navmenu']/ul/li[4]/a")));
	    }
		catch(Exception exp)
		{
			logger.info("Trying with third catch block to re-try the hovering of the mouse to the contacts button.");
			System.out.println("Second try");
			Point coordinates = driver.findElement(By.xpath("//a[@title='Contacts']")).getLocation();
			Robot robot = new Robot();
			System.out.println(coordinates.getX()+"  "+coordinates.getY());
			robot.mouseMove(coordinates.getX(), coordinates.getY());
		}
	}
	public void click_on_new_contact_from_contacts_dropdown()
	{
		logger.info("Once the sub menu opens trying to clock on the new contact option to create a new contact");
		Actions action = new Actions(driver);
		action.moveToElement(newContact).click().perform();
	}
	public void click_on_contact_title(String initials)
	{
		logger.info("Once in the new contact page, user tries to click on the title drop down menu");
		Select title =  new Select(titleDropdown);
		title.selectByVisibleText(initials);
	}
	
	public void enter_the_contact_person_first_name(DataTable contactDetails)
	{
	     logger.info("Enter the first name using list");
		 List<List<String>> contact_first_name = contactDetails.raw();
		 String first_name=contact_first_name.get(0).get(0);
		 firstName.sendKeys(first_name);
	}
	
	public void enter_the_contact_person_last_name(DataTable contactDetails)
	{
		logger.info("Enter the last name using list");	
		 List<List<String>> contact_last_name = contactDetails.raw(); 
		 String last_name=contact_last_name.get(0).get(0);
		 lastName.sendKeys(last_name);
	}
	
	public void click_on_contact_save_btn()
	{
		logger.info("Click on save button");
	    contactSaveBtn.click();
	    GenericUtility.waitForMilliSeconds(5000);
	}
	
	public void click_on_logout_button()
	{
		try
		{
		logger.info("Click on logout button");	
		logoutBtn.sendKeys(Keys.ENTER);
		}
		catch(NoSuchFrameException exp)
		{
			logger.info("Click on logout button for a second try if the first try could not get the logout element");	
			System.out.println("Second Try");
			logoutBtn.click();
		}
		catch(Exception exp)
		{
			
			logger.info("Click on logout button for a third try if the first try could not get the logout element");
		
			Point logout=logoutBtn.getLocation();
			Actions action =new Actions(driver);
			action.moveByOffset(logout.getX(), logout.getY()).sendKeys(Keys.ENTER).build().perform();
		
			
		}
		
	}
}
