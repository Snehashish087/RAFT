package com.domain.cucumber.stepdefinations;

import java.awt.Robot;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.domain.cucumber.generic.AutoConstVariables;
import com.domain.cucumber.generic.GenericUtility;
import com.domain.cucumber.pagedefinations.CreateContactPageDefinition;
import com.domain.cucumber.pagedefinations.LoginPageDefination;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CreateContactTests

{
	WebDriver driver;

	Logger logger = Logger.getLogger(CreateContactTests.class);



	@Given("^User is in the login page$")

	public void user_is_in_the_login_page() throws Throwable 

	{		



		System.setProperty(AutoConstVariables.CHROME_KEY, AutoConstVariables.CHROME_VALUE);

		driver = new ChromeDriver();

		logger.info("Enter the URL");



		driver.get(AutoConstVariables.URL);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}



	@When("^He enters the username and password$")

	public void enter_username_password(DataTable user_details)

	{

		List<List<String>> details = user_details.raw(); //Data table with list

		LoginPageDefination  login = new LoginPageDefination (driver);
		
		String username = details.get(0).get(0);
		
		String password = details.get(0).get(1);

		logger.info("Enter the username");
		
		login.enter_username(username);
		
		logger.info("Enter the password");
		
		login.enter_password(password);
	
	}

	
		
		
		@And("^Clicks on Login$")
		public void clicks_on_Login() throws Throwable
		{
			LoginPageDefination  login = new LoginPageDefination (driver);
			login.click_on_login_button();
			logger.info("Clicked on login button and proceeding towards the home page.");
			
		}

		@Then("^User should be in the home page of the application$")
		public void user_should_be_in_the_home_page_of_the_application() throws Throwable 
		{
			logger.info("Retrieve the home page title");
			String actualTitle = driver.getTitle();
			logger.info("Check the retrieved title of the home page matches our expected home page title");
			Assert.assertEquals("CRMPRO", actualTitle);
		}

		@When("^User hovers the mouse on contacts option$")
		public void user_hovers_the_mouse_on_contacts_option() throws Throwable 
		{
			GenericUtility.waitForMilliSeconds(8000);
			CreateContactPageDefinition selectContacts = new CreateContactPageDefinition(driver);
			selectContacts.hover_mouse_to_contacts_dropdown();
			logger.info("Selected the contacts dropdown by hovering the mouse over it");
			GenericUtility.waitForMilliSeconds(8000);
		    
		}

		@Then("^User should be able to select the option New Contact$")
		public void user_should_be_able_to_select_the_option_New_Contact() throws Throwable 
		{
			CreateContactPageDefinition contact = new CreateContactPageDefinition(driver);
			contact.click_on_new_contact_from_contacts_dropdown();
			logger.info("Selected the new contacts option from the drop down to create a new contact.");
		   
		}

		@And("^User should be able to select the Title$")
		public void user_should_be_able_to_select_the_Title() throws Throwable 
		{
			CreateContactPageDefinition contactDetails = new CreateContactPageDefinition(driver);
			contactDetails.click_on_contact_title("Dr.");
			logger.info("Selected the initials of the contact person");
		}

		@And("^User should be able to enter the contact person's first name$")
		public void user_should_be_able_to_enter_the_contact_person_s_first_name(DataTable contactDetails) throws Throwable 
		{
			CreateContactPageDefinition firstName = new CreateContactPageDefinition(driver);
			firstName.enter_the_contact_person_first_name(contactDetails);
			logger.info("Entered the first name of the contact person");
			
		 
		}

		@And("^User should be able to enter the contact person's last name$")
		public void user_should_be_able_to_enter_the_contact_person_s_last_name(DataTable contactDetails) throws Throwable 
		{
			CreateContactPageDefinition lastName = new CreateContactPageDefinition(driver);
			lastName.enter_the_contact_person_last_name(contactDetails);
			logger.info("Entered the last name of the contact person");
		}

		@And("^Once the User provides the mandatory fields he should be able to save the contact details$")
		public void once_the_User_provides_the_mandatory_fields_he_should_be_able_to_save_the_contact_details() throws Throwable 
		{
			CreateContactPageDefinition contactSaveBtn = new CreateContactPageDefinition(driver);
			contactSaveBtn.click_on_contact_save_btn();
			logger.info("Clicked on save button of the contact");
		}

		@When("^The contactName details are saved user should be able to view the contact in contacts table$")
		public void the_contactName_details_are_saved_user_should_be_able_to_view_the_contact_in_contacts_table(DataTable contactName) throws Throwable
		{
			logger.info("Once the contact is saved, user tries to click on the contacts button once again to view the saved details");
			driver.findElement(By.xpath("//a[@title='Contacts']")).click();
			Thread.sleep(5000);
			logger.info("Storing all the contact information into a list");
			 List<List<String>> contact_full_details = contactName.raw();
			 String full_name = contact_full_details.get(0).get(0);
			 List<WebElement>table_contents= driver.findElements(By.xpath("//form[@id='vContactsForm']/table"));
			 logger.info("Getting the size of the list table contents");
	         int table_size=table_contents.size();
	         for(int i=0;i<table_size;i++)
	         {
	        	 String content = table_contents.get(i).toString();
	        	 try
	        	 {
	        		 logger.info("Check if the contact person created is available in the contacts table.");
	        		 content.contains(full_name);
	        		 System.out.println("Table contains the contact person name that is created.");
	        	 }
	        	 catch(Exception exp)
	        	 {
	        		 logger.info("If the contact person name is not present, throw an error");
	        		 System.out.println("Table does not contain the contact person name");
	        		 exp.getMessage();
	        	 }
	         }
	         Thread.sleep(5000);
		}


		@When("^The user clicks on logout$")
		public void the_user_clicks_on_logout() throws Throwable
		{
			CreateContactPageDefinition logoutBtn = new CreateContactPageDefinition(driver);
			logoutBtn.click_on_logout_button();
			logger.info("Clicked on logout button and moving towards login page");
			

		}

		@Then("^The user should be able view the login page with the login page title$")
		public void the_user_should_be_able_view_the_login_page_with_the_login_page_title() throws Throwable 
		{
			Thread.sleep(7000);
			logger.info("User retrieves the title of the login page");
			String loginPageTitle=driver.getTitle();
			try
			{
				logger.info("User checks if the title matches the original login title");
				Assert.assertEquals("Free CRM software in the cloud powers sales and customer service", loginPageTitle);
				System.out.println("Results are matching");
			}
			catch(Exception exp)
			{
				logger.info("User finds the title is not matching and fails the test case.");
				System.out.println("Titles are not matching");
				exp.getMessage();
			}
			Thread.sleep(6000);
		}
		



}
