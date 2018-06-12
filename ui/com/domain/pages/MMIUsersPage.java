package com.domain.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.domain.generic.BasePage;

public class MMIUsersPage extends BasePage
{
	//Getting the logger for logging activity in console
	Logger logger = Logger.getLogger("MMI Users Page");
	
	@FindBy (xpath = "//a[.='Edit Details']")
	private WebElement editDetailslabel;
	
	@FindBy(xpath = "//div[contains(label,'Name ')]/label")
	private WebElement nameLabel;
	
	@FindBy(xpath = "//input[@ng-model='userData.name']")
	private WebElement nameTextField;
	
	@FindBy(xpath = "//div[contains(label,'Designation')]/label")
	private WebElement designationLabel;
	
	@FindBy(xpath = "//input[@ng-model='userData.designation']")
	private WebElement designationTextField;
	
	@FindBy(xpath = "//div[contains(label,'Phone')]/label")
	private WebElement phoneLabel;
	
	@FindBy(xpath = "//input[@ng-model='userData.phone']")
	private WebElement phoneTextField;
	
	@FindBy(xpath = "//div[contains(label,'Console Access')]/label")
	private WebElement consoleAccessLabel;
	
	@FindBy(xpath = "//a[.='Set Permissions']")
	private WebElement setPermission;
	
	@FindBy(xpath = "//div[contains(label,'Email Id')]/label")
	private WebElement emailIDLabel;
	
	@FindBy(xpath = "//input[@ng-model='userData.email']")
	private WebElement emailIDTextField;
	
	@FindBy(xpath = "//div[contains(label,'Password')]/label")
	private WebElement passwordLabel;
	
	@FindBy(xpath = "//input[@ng-model='userData.password']")
	private WebElement passwordTextField;
	
	@FindBy(xpath = "//a[.='All User']")
	private WebElement allUser;
	
	@FindBy(xpath = "//a[.='Add New User']")
	private WebElement addNewUser;
	
	//Web Console Access pop up
	@FindBy(xpath = "//span[.='Employee Name']//..//span[1]")
	private WebElement webConsoleEmployeeNameLabel;
	
	@FindBy(xpath = "//span[.='Employee Name']//..//span[2]")
	private WebElement webConsoleEmployeeName;
	
	@FindBy(xpath = "//span[.='Designation']//..//span[1]")
	private WebElement webConsoleDesignationLabel;
	
	@FindBy(xpath = "//span[.='Designation']//..//span[2]")
	private WebElement webConsoleDesignation;
	
	@FindBy(xpath = "//span[.='Email']//..//span[1]")
	private WebElement webConsoleEmailLabel;
	
	@FindBy(xpath = "//span[.='Email']//..//span[2]")
	private WebElement webConsoleEmail;
	
	@FindBy(xpath = "//div[@id='webconsoleModal']//label[contains(.,'Console Access')]")
	private WebElement webConsoleAccessLabel;
	
	@FindBy(xpath = "//a[.='Done']")
	private WebElement webConsoleAccessDone;
	
	@FindBy(xpath = "//a[.='Cancel']")
	private WebElement webConsoleAccessCancel;
	
	@FindBy(xpath = "//a[.='Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//a[.='Cancel']")
	private WebElement cancelBtn;
	
	public MMIUsersPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void click_on_add_new_user()
	{
		logger.info("Click on new user link");
		addNewUser.click();
	}
	
	public void enter_user_name(String newUserName)
	{
		logger.info("Enter the new user's name");
		nameTextField.sendKeys(newUserName);
	}
	
	public void enter_user_designation(String newUserDesignation)
	{
		logger.info("Enter the new user's designation");
		designationTextField.sendKeys(newUserDesignation);
	}
	
	public void enter_user_phone(String phoneNumber)
	{
		logger.info("Enter the new user's phone number");
		phoneTextField.sendKeys(phoneNumber);
	}
	
	public void click_on_set_permission()
	{
		logger.info("Click on set permission link for setting up the rights for the new user");
		setPermission.click();
	}
	
	public List<String> get_the_user_details()
	{
		logger.info("New User data saved information");
		 String empName= webConsoleEmployeeName.getText();
		 String empDesignation = webConsoleDesignation.getText();
		 String empEmail = webConsoleEmail.getText();
		 List<String>details=new ArrayList<String>();
		 details.add(empName);
		 details.add(empDesignation);
		 details.add(empEmail);
		 return details;
	}
	
	
	
	public void enter_user_email_id(String emailID)
	{
		logger.info("Enter the new user's email ID");
		emailIDTextField.sendKeys(emailID);
	}
	
	
	
	public void enter_user_password(String password)
	{
		logger.info("Enter the new user's password");
		passwordTextField.sendKeys(password);
	}
	
	public void click_on_save_new_user_details()
	{
		logger.info("Click on save button for saving the details of the new user");
		saveBtn.click();
	}
	
	
	
	
}
