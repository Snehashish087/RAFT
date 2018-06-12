package com.domain.tests;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.domain.generic.BasePage;
import com.domain.generic.BaseTest;
import com.domain.generic.ExcelData;
import com.domain.generic.GenericUtility;
import com.domain.pages.MetalmannHomePage;

import com.domain.pages.LoginPage;

@Listeners(com.domain.generic.TestListeners.class)
public class LoginTest extends BaseTest
{
	String LOGIN_TEST_DATA_PATH = "/Users/snehashish/shopkick_automation/CIService/RAFT/ui/com/domain/testdata/LoginPageData.xlsx";
	String LOGIN_DATA_ENGINE = "/Users/snehashish/shopkick_automation/CIService/RAFT/ui/com/domain/dataengine/LoginDataEngine.xlsx";
	String HOMEPAGE_TEST_DATA_PATH = "/Users/snehashish/shopkick_automation/CIService/RAFT/ui/com/domain/testdata/MetalManHomePageData.xlsx";

	@Test(enabled = true)
	
	public void verify_login_with_valid_username_and_password() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		logger=extent.startTest("Verify the actual title with the expected title in login and home page and check the functionality for login and logout.", "As a user I should be able to login to the application with valid data and should be able to logout and land on the same login page");

		Logger logger = Logger.getLogger("Login Test");
		String username = ExcelData.getData(LOGIN_TEST_DATA_PATH, "TestData", 1, 1);
		String password = ExcelData.getData(LOGIN_TEST_DATA_PATH, "TestData",1,2);

		//*******Object Repository***********//

		LoginPage login = new LoginPage(driver);
		MetalmannHomePage home = new MetalmannHomePage(driver);

		//************************************//

		// Iteration starting after navigating to the URL 
		for(int i = 3; i<=7; i++)
		{
			String sActionKeyword = ExcelData.getData(LOGIN_DATA_ENGINE, "TestSteps", i, 5);

			if(sActionKeyword.equalsIgnoreCase("input_username"))
			{

				login.input_username(username);
			}
			else if(sActionKeyword.equals("input_password"))
			{

				login.input_password(password);
			}
			else if(sActionKeyword.equals("click_login"))
			{

				login.click_login();
			}
			else if(sActionKeyword.equals("wait_for"))
			{

				login.wait_for(4000);
			}
			else if(sActionKeyword.equals("click_on_logout_btn"))
			{
				home.click_on_logout_btn();;
			}

		}
	}

	@Test(enabled = true)
	public void verify_login_and_logout_with_valid_user_name_and_password() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		logger=extent.startTest("Verify the expected login page and home page title once user login to the application with valid username and password", "As a user, I should be able to land on to proper homepage when I am trying to login to the application with valid user name and password.");
		Logger logger = Logger.getLogger("Login - Logout Test");
		String loginPageTitle = ExcelData.getData(LOGIN_TEST_DATA_PATH, "TestData", 1, 0);
		String homePageTitle = ExcelData.getData(HOMEPAGE_TEST_DATA_PATH, "TestData", 1, 0);
		String username = ExcelData.getData(LOGIN_TEST_DATA_PATH, "TestData", 1, 1);
		String password = ExcelData.getData(LOGIN_TEST_DATA_PATH, "TestData",1,2);
		System.out.println(loginPageTitle);
		System.out.println(homePageTitle);

		//*******Object Repository***********//


		LoginPage login = new LoginPage(driver);
		MetalmannHomePage home = new MetalmannHomePage(driver);
		GenericUtility util = new GenericUtility(driver);

		//************************************//

		// Iteration starting after navigating to the URL 
		for(int i=10; i<18; i++)
		{
			String sActionKeyword = ExcelData.getData(LOGIN_DATA_ENGINE, "TestSteps", i, 5);
			if(sActionKeyword.equals("verify_title"))
			{
				BasePage.verifyTitle(loginPageTitle);
			}
			else if(sActionKeyword.equals("input_username"))
			{
				login.input_username(username);
			}
			else if(sActionKeyword.equals("input_password"))
			{
				login.input_password(password);
			}
			else if(sActionKeyword.equals("click_login"))
			{
				login.click_login();
			}
			else if(sActionKeyword.equals("wait_for"))
			{
				util.waitForMilliSeconds(5000);
			}
			else if(sActionKeyword.equals("verify_title"))
			{
				BasePage.verifyTitle(homePageTitle);
			}
			else if(sActionKeyword.equals("click_on_logout_btn"))
			{
				home.click_on_logout_btn();
			}
			else if(sActionKeyword.equals("wait_for"))
			{
				util.waitForMilliSeconds(5000);
			}
			else if(sActionKeyword.equals("verify_title"))
			{
				BasePage.verifyTitle(loginPageTitle);
			}


		}
	}
	
}





