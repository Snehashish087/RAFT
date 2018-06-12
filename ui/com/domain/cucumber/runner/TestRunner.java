package com.domain.cucumber.runner;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.PropertyConfigurator;
import org.junit.*;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.cucumber.listener.Reporter;
import com.domain.cucumber.generic.AutoConstVariables;
import com.domain.generic.BaseTest;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;



//Uncomment the @RunWith if you are using Junit test 
@RunWith(Cucumber.class)
@CucumberOptions(features="/Users/snehashish/shopkick_automation/CIService/RAFT/cucumber-features-repository/contactcreate.feature",
				 glue="com.domain.cucumber.stepdefinations",
				 plugin = {"html:cucumber-report/cucumber-html-report","pretty","junit:cucumber-report/junit/cucumber.xml",
						    "com.cucumber.listener.ExtentCucumberFormatter:/Users/snehashish/shopkick_automation/CIService/RAFT/cucumber-report/cucumber-extent-report/cucumberExtentReport.html"},
				 dryRun=false,//dry run = maps the feature file with the implementation file
				 monochrome = true,
				 strict = false,
				 tags = {"@SmokeTesting"}
				 
				 )

public class TestRunner 
{
	@BeforeClass
	public static void precondition()
	{
		Logger logger = Logger.getLogger("Pre-Condition");
		PropertyConfigurator.configure("/Users/snehashish/shopkick_automation/CIService/RAFT/log-config/CucumberLogger");
	}
	
	@AfterClass
	public static void reportSetup()
	{
		Reporter.loadXMLConfig(new File("/Users/snehashish/shopkick_automation/CIService/RAFT/extent-config/cucumber-extent-config.xml"));
		Reporter.setSystemInfo("Username",System.getProperty("user.name"));
		Reporter.setSystemInfo("Host Name","Domain");
		Reporter.setSystemInfo("Environment","UI Automation Testing using Cucumber");
		Reporter.setSystemInfo("User Name","Snehashish Chakraborty");
		/*Reporter.setTestRunnerOutput("Cucumber JUnit Test Runner");
		Reporter.setTestRunnerOutput("log 1");
		Reporter.setTestRunnerOutput("<pre>Log 2</pre>");
		Reporter.setTestRunnerOutput("<h2>heading 2 </h2>");
		Reporter.addStepLog("afafsa");
		*/
		
		try 
		{
			TestRunner.sendMail();
		} 
		catch (MessagingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	 public static void sendMail() throws MessagingException 
	 {
		 	Logger logger = Logger.getLogger("sendMail");
	        String host = "smtp.gmail.com";
	        String Password = "1ep10cs087";
	        String from = "snehashish.chakraborty@goodworklabs.com";
	     
	        
	  
	  
	        String cucumberExtentReport = "/Users/snehashish/shopkick_automation/CIService/RAFT/cucumber-report/cucumber-extent-report/cucumberExtentReport.html";
	        String cucumberHtmlReport = "/Users/snehashish/shopkick_automation/CIService/RAFT/cucumber-report/cucumber-html-report/index.html";
	        String cucumberLoggerReport = "/Users/snehashish/shopkick_automation/CIService/RAFT/log/Cucumber/application.html";
	        
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

	       BaseTest.addAttachment(multipart, cucumberExtentReport);
	       BaseTest.addAttachment(multipart, cucumberLoggerReport);
	       BaseTest.addAttachment(multipart, cucumberHtmlReport);
	       
	        
	        
	        
	        
	        
	        
	        

	        message.setContent(multipart);

	        try {
	            Transport tr = session.getTransport("smtps");
	            tr.connect(host, from, Password);
	            tr.sendMessage(message, message.getAllRecipients());
	            logger.info("Mail sent successfully");
	           
	            
	            tr.close();

	        } catch (SendFailedException sfe) 
	        {
	        	logger.info("Mail could not be dispatched. Please check the mail trace or the recipients mail ID");
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