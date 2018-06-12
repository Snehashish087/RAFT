package com.domain.android.pages;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import com.domain.android.generic.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;

public class HomePage extends BasePage
{
	
	Logger log=Logger.getLogger("CreateUserPage");
	@CacheLookup
	@AndroidFindBy(id="io.selendroid.testapp:id/startUserRegistration")
	private MobileElement createUser;
	
	public HomePage(AppiumDriver driver) 
	{
		super(driver);
		//PageFactory.initElements(driver, this);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	public void clickOnCreateUser()
	{
		log.info("Clicking on Create User button");
		createUser.click();
	}

}
