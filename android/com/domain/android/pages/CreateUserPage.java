package com.domain.android.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.support.CacheLookup;

import org.openqa.selenium.support.PageFactory;

import com.domain.android.generic.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CreateUserPage extends BasePage 
{

	Logger log=Logger.getLogger("CreateUserPage");

	
	@AndroidFindBy(id="io.selendroid.testapp:id/inputUsername")
	private MobileElement username;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc='email of the customer']")
	private MobileElement email;
	
	@AndroidFindBy(id="io.selendroid.testapp:id/inputPassword")
	private MobileElement password;
	
	@AndroidFindBy(id="io.selendroid.testapp:id/inputName")
	private MobileElement name;
	
	@AndroidFindBy(id="io.selendroid.testapp:id/input_preferedProgrammingLanguage")
	private MobileElement programmingLanguage;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@resource-id='android:id/text1']")
	private List<MobileElement> listOfProgrammingLanguages;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='io.selendroid.testapp:id/input_adds']")
	private MobileElement termsAndCondition;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Register User (verify)']")
	private MobileElement registerUser;
	
	
	
	
	public CreateUserPage(AppiumDriver driver) 
	{
		super(driver);
		//PageFactory.initElements(driver, this);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	

	public void enterUsername(String username_credentials)
	{
		log.info("Enter the username");
		username.sendKeys(username_credentials);
	}
	public void enterMail(String email_credentials)
	{
		log.info("Enter the mail credentials");
		email.sendKeys(email_credentials);
	}
	public void enterPassword(String password_credentials)
	{
		log.info("Enter the password");
		password.sendKeys(password_credentials);
	}
	public void clearNameField()
	{
		log.info("Clearing the name field");
		name.clear();
	}
	public void enterName(String name_field)
	{
		log.info("Enter the user's name");
		name.sendKeys(name_field);
	}
	public void click_on_programming_language()
	{
		log.info("Click on programming language drop box");
		programmingLanguage.click();
	}
	public void select_the_particular_programming_language(String programming_language_name)
	{
		log.info("Select the programming language as per provided");
		for(MobileElement language:listOfProgrammingLanguages)
		{
			String lang= language.getText();
			if(lang.contains(programming_language_name))
			{
				language.click();
				break;
			}
		}
	}
	
	public void click_on_terms_and_conditions()
	{
//		try
//		{
			log.info("Click on terms and conditions check box -- try 1");
			termsAndCondition.click();
		/*}
		catch(Exception exp)
		{
			log.info("Click on terms and conditions check box -- try 2");
			((Object) termsAndCondition).tap(1, 2);
		}*/
	}

	public void click_on_register_user()
	{
		log.info("Click on register user button");
		registerUser.click();
	}
}
