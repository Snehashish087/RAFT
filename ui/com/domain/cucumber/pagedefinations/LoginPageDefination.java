package com.domain.cucumber.pagedefinations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.domain.cucumber.generic.BasePage;
import com.domain.cucumber.stepdefinations.CreateContactTests;

import cucumber.api.DataTable;

public class LoginPageDefination 
{
Logger logger = Logger.getLogger(LoginPageDefination.class);


@FindBy(how = How.XPATH, using ="//input[@name='username']")
private WebElement username;

@FindBy(how = How.XPATH, using ="//input[@name='password']")
private WebElement password;

@FindBy(how = How.XPATH, using ="//input[@value='Login']")
private WebElement loginBtn;

public LoginPageDefination(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

public void enter_username(String login_username)
{
	
	logger.info("Enter the username");
	username.sendKeys(login_username);
}

public void enter_password(String login_password)
{
	
	logger.info("Enter the password");
	password.sendKeys(login_password);
}

public void click_on_login_button()
{
	
	loginBtn.sendKeys(Keys.ENTER);
	
}

}
