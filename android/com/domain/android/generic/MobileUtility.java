package com.domain.android.generic;

import java.util.Set;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class MobileUtility 
{
	static AppiumDriver<MobileElement> driver;
	public MobileUtility(AppiumDriver driver) 
	{
		this.driver=driver;
	}
	
	public static void vertical_swipe() 
	{
		TouchAction ts = new TouchAction(driver);
	
		ts.press(207, 582).moveTo(8, -360).release().perform();
	}
	public static void manual_vertical_swipe(int origin_x,int origin_y,int dest_x,int dest_y)
	{
		TouchAction ts=new TouchAction(driver);
		
		ts.press(origin_x, origin_y).moveTo(dest_x, dest_y).release().perform();
	}
	public static void hide_keyboard()
	{
		driver.navigate().back();
	}
	
	/*
	 *An Example for the vertical swipe method
	 *public static void manual_vertical_swipe()
	 *	{
	 *		TouchAction ts=new TouchAction(driver);
	 *		ts.press(450, 1500).moveTo(450, -500).release().perform();
	 *	}
	 *	
	 */
	
	public static void waitForMilliSeconds(long timeInMilliseconds) {
		try {
			Thread.sleep(timeInMilliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void switchContext(String context)
	{
		System.out.println("Before Switching : "+driver.getContext());
		Set<String> con = driver.getContextHandles();
		for(String c : con)
		{
			System.out.println("Available Context : "+c);
			if(c.contains(context))
			{
				driver.context(c);
				break;
			}
		}
		System.out.println("After Switching : "+driver.getContext());
	}
	
	public static void sleep(int milliseconds)
	{
		try 
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void screenRotationLandScape()
	
	{
		ScreenOrientation currentOrientation = driver.getOrientation();
		Reporter.log("Current Orientation: "+currentOrientation,true);
		driver.rotate(currentOrientation.LANDSCAPE);
	
	}
	public static void screenRotationPotrait()
	
	{
		ScreenOrientation currentOrientation = driver.getOrientation();
		Reporter.log("Current Orientation: "+currentOrientation,true);
		driver.rotate(currentOrientation.PORTRAIT);
	}
}
