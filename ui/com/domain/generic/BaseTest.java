package com.domain.generic;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class BaseTest implements AutoConstVariables
{
	
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static WebDriver driver;

	@BeforeSuite
	public void startReport()
	{
		//ExtentReports(String filePath,Boolean replaceExisting) 
		//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		extent = new ExtentReports (System.getProperty("user.dir") +"/extent-report/STMExtentReport.html", true);
		//extent.addSystemInfo("Environment","Environment Name")
				extent.addSystemInfo("Host Name", "Domain")
	            .addSystemInfo("Environment", "Automation Testing")
	            .addSystemInfo("User Name", "Snehashish Chakraborty");
	            //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
	            //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
				//extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	            extent.loadConfig(new File("/Users/snehashish/shopkick_automation/CIService/RAFT/extent-config/extent-config.xml"));
	}




	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
	            //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir")+screenshotName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}



	@AfterMethod
	public void getResult(ITestResult result)
	{
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			try 
			{
				String screenshotPath=BaseTest.getScreenhot(driver, result.getName());
				logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		else
		{
			logger.log(LogStatus.PASS, "Test Case Passed");
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
		
		
		
	}
	@AfterSuite
	public void endReport() throws IOException
	{
		// writing everything to document
		//flush() - to write or update test information to your report. 
	        extent.flush();  
	            
	            //Call close() at the very end of your session to clear all resources. 
	            //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing),
	            	//this method will ensure that the test is still appended to the report with a warning message.
	            //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
	            //Once this method is called, calling any Extent method will throw an error.
	            //close() - To close all the operation
	        try 
	        {
				BaseTest.sendMail();
			} 	
	        catch (MessagingException e) 
	        {
	        	e.printStackTrace();
			}
	           
	}

		@BeforeMethod
		public void preCondition()
		{	
			Logger logger = Logger.getLogger("Pre-Condition");
			PropertyConfigurator.configure("/Users/snehashish/shopkick_automation/CIService/RAFT/log-config/UILogger");
			//DOMConfigurator.configure("/Users/snehashish/shopkick_automation/CIService/RAFT/log4j.xml");
			try
			{
			logger.info("Open the Browser");
			System.setProperty(CHROME_KEY, CHROME_VALUE);
			driver=new ChromeDriver();
			}
			catch(Exception e)
			{
				logger.info("Cannot open the chrome driver. Message:"+e.getMessage());
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			try
			{
				logger.info("Navigate to the website");
				driver.get(URL);
			}
			catch(Exception e)
			{
				logger.info("Cannot navigate to the given URL. Message: "+e.getMessage());
			}
			
		}
	
	@AfterMethod
		public void postCondition()
		{
			Logger logger = Logger.getLogger("Post-Condition");
			try
			{
				logger.info("Close the browser");
				driver.quit();
			}
			catch(Exception e)
			{
				logger.info("Cannot close the browser. Message: "+e.getMessage());
			}
		}
	
	
	
	
	
	 public static void sendMail() throws MessagingException 
	 {
		 	Logger logger = Logger.getLogger("sendMail");
	        String host = "smtp.gmail.com";
	        String Password = "1ep10cs087";
	        String from = "snehashish.chakraborty@goodworklabs.com";
	     
	        
	  
	  
	        String uiExtentReport = "/Users/snehashish/shopkick_automation/CIService/RAFT/extent-report/STMExtentReport.html";
	        String uiLoggerReport = "/Users/snehashish/shopkick_automation/CIService/RAFT/log/UI/application.html";
	        String testNGIndexReport = "/Users/snehashish/shopkick_automation/CIService/RAFT/test-output/index.html";
	        String testNGEmailableReport = "/Users/snehashish/shopkick_automation/CIService/RAFT/test-output/emailable-report.html";
	     
	        // Get system properties
	        Properties props = System.getProperties();
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtps.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.port", "587");
	        Session session = Session.getInstance(props, null);

	        MimeMessage message = new MimeMessage(session);

	        message.setFrom(new InternetAddress(from));
	        message.addRecipients(Message.RecipientType.TO, "snehashish.chakraborty77@gmail.com");
	        
	        //***Use the below code to add the recepient and CC ***// 
	       
	         message.addRecipients(Message.RecipientType.TO, "kavya.anand@goodworklabs.com");
	        message.addRecipients(Message.RecipientType.TO, "ramesh.babu@goodworklabs.com ");
	        message.addRecipients(Message.RecipientType.TO, "aayushi.jain@goodworklabs.com");
	        message.addRecipients(Message.RecipientType.CC, "snehashish.chakraborty@goodworklabs.com");
	        //***----------------------------------------------***//

	        message.setSubject("RE: Test Report for the current run");

	        BodyPart messageBodyPart = new MimeBodyPart();

	        messageBodyPart.setText("Hi,"
	        		+"\n\n The current attachment contains the report for the current run for the User Interface script. Please have a look into the report for passed and failed test scripts / cases."
	        		+ "\n\n This is an auto-generated mail.");

	        Multipart multipart = new MimeMultipart();

	        multipart.addBodyPart(messageBodyPart);

	        messageBodyPart = new MimeBodyPart();

	       BaseTest.addAttachment(multipart, uiExtentReport);
	       BaseTest.addAttachment(multipart, uiLoggerReport);
	       BaseTest.addAttachment(multipart, testNGIndexReport);
	       BaseTest.addAttachment(multipart, testNGEmailableReport);
	        
	        
	        
	        
	        
	        
	        

	        message.setContent(multipart);

	        try {
	            Transport tr = session.getTransport("smtps");
	            tr.connect(host, from, Password);
	            tr.sendMessage(message, message.getAllRecipients());
	            logger.info("Mail sent successfully");
	            Reporter.log("Mail Sent Successfully",true);
	            
	            tr.close();

	        } catch (SendFailedException sfe) 
	        {
	        	logger.info("Mail could not be dispatched. Please check the mail trace or the recipients mail ID");
	            Reporter.log("Sending the mail failed. Please check the details: "+sfe,true);
	        	
	        }
	    }
	 
	 public static void addAttachment(Multipart multipart, String filename)
	 {
		 	DataSource source = new FileDataSource(filename);
		    BodyPart messageBodyPart = new MimeBodyPart();        
		    try 
		    {
				messageBodyPart.setDataHandler(new DataHandler(source));
			} 
		    catch (MessagingException e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try 
		    {
				messageBodyPart.setFileName(filename);
			}
		    catch (MessagingException e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try
		    {
				multipart.addBodyPart(messageBodyPart);
			} 
		    catch (MessagingException e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	
}
