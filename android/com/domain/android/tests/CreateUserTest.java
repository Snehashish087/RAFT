package com.domain.android.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import com.domain.android.generic.InitialSetup;
import com.domain.android.generic.MobileUtility;
import com.domain.android.pages.CreateUserPage;
import com.domain.android.pages.HomePage;
import com.domain.cucumber.generic.GenericUtility;
import com.domain.generic.ExcelData;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
@Listeners(com.domain.android.generic.TestListeners.class)
public class CreateUserTest extends InitialSetup
{
	



	String CREATE_USER_TEST_DATA_PATH = "/Users/snehashish/shopkick_automation/CIService/RAFT/android/com/domain/android/testdata/CreateUserData.xlsx";
	String CREATE_USER_DATA_ENGINE_PATH = "/Users/snehashish/shopkick_automation/CIService/RAFT/android/com/domain/android/dataengine/CreateUser.xlsx";
	
	
	
	@Test
	public void verify_the_creation_of_a_user() throws EncryptedDocumentException, InvalidFormatException, IOException 
	{
		logger=extent.startTest("Verify creating user", "As a user I should be able to create a user and assign him/her the properties based on his/her personal details.");

		
		GenericUtility.waitForMilliSeconds(5000);
		

		Logger log=Logger.getLogger("CreateUserTest");
		
		log.info("Retrieving the information of test data required for the test engine to run.");
		
		String username = ExcelData.getData(CREATE_USER_TEST_DATA_PATH, "TestData", 1, 1);
	
		String email = ExcelData.getData(CREATE_USER_TEST_DATA_PATH, "TestData", 1, 2);
		String password = ExcelData.getData(CREATE_USER_TEST_DATA_PATH, "TestData", 1, 3);
		String name = ExcelData.getData(CREATE_USER_TEST_DATA_PATH, "TestData", 1, 4);
		String programming_language = ExcelData.getData(CREATE_USER_TEST_DATA_PATH, "TestData", 1, 5);
		CreateUserPage createUser = new CreateUserPage(driver);
		HomePage home = new HomePage(driver);
		int i;
		log.info("Creating a loop for looping through the driver engine steps provided.");
		try
		{
		for(i=2;i<=13;i++)
		{
			String sActionKeyword = ExcelData.getData(CREATE_USER_DATA_ENGINE_PATH, "TestSteps", i, 5);
			System.out.println(sActionKeyword);
			if(sActionKeyword.equalsIgnoreCase("click_on_create_user"))
			{
				log.info("Try to click on the create user icon");
				home.clickOnCreateUser();
			}
			else if(sActionKeyword.equalsIgnoreCase("wait_for_two_seconds"))
			{
				MobileUtility.waitForMilliSeconds(4000);
			}
			else if(sActionKeyword.equalsIgnoreCase("enter_the_user_name"))
			{
				createUser.enterUsername(username);
			}
			else if(sActionKeyword.equalsIgnoreCase("enter_the_email"))
			{
				createUser.enterMail(email);
			}
			else if(sActionKeyword.equalsIgnoreCase("enter_the_password"))
			{
				createUser.enterPassword(password);
				
			}
			else if(sActionKeyword.equalsIgnoreCase("swipe_down"))
			{
				TouchAction ts = new TouchAction(driver);
				ts.press(207, 582).moveTo(8, -360).release().perform();
			}
			else if(sActionKeyword.equalsIgnoreCase("clear_name_field"))
			{
				createUser.clearNameField();
			}
			else if(sActionKeyword.equalsIgnoreCase("enter_name"))
			{
				createUser.enterName(name);
			}
			else if(sActionKeyword.equalsIgnoreCase("select_programming_language_drop_down"))
			{
				createUser.click_on_programming_language();
			}
			else if(sActionKeyword.equalsIgnoreCase("select_the_programming_language_from_the_list"))
			{
				createUser.select_the_particular_programming_language(programming_language);
			}
			else if(sActionKeyword.equalsIgnoreCase("check_off_terms_and_condition_check_box"))
			{
				createUser.click_on_terms_and_conditions();
			}
			else if(sActionKeyword.equalsIgnoreCase("click_on_register_user"))
			{
				createUser.click_on_register_user();
			}
		
		}
			logger.log(LogStatus.PASS, "Creating a user test script has passed the current scenario");
		}
		catch(Exception exp)
		{
			logger.log(LogStatus.FAIL, "Test case failed. Check the failed steps.");
		}
			
	}
}


