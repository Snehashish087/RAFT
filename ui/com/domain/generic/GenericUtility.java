package com.domain.generic;


import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;


public class GenericUtility
{
	WebDriver driver;
	
	public GenericUtility(WebDriver driver) 
	{
		this.driver=driver;
	}

	
	
	public void selectDropdownByValue(WebElement element,String value){
		Select select=new Select(element);
		select.selectByValue(value);
	}
	
	public void selectDropdownByIndex(WebElement element,int index){
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	
	public void selectDropdownByVisible(WebElement element,String visible_text){
		Select select=new Select(element);
		select.selectByVisibleText(visible_text);
	}
	
	public void pressTabAndEnter(WebElement element) throws AWTException{
		element.click();
		element.sendKeys(Keys.TAB);
		Robot robot=new Robot();
		robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
	}
	
	public void pressEnter(WebElement element){
		element.sendKeys(Keys.TAB);
	}
	
	
		
		
	
	


		// Method to clear value from a text box
		public void clearValue(WebElement element) {
			//WebElement locator = element;
			element.clear();
		}

		// Method to mouse hover on any element on a web page
		public void mouseHover( WebElement element)
		{
			WebElement locator = element;
			Actions action = new Actions(driver);
			action.moveToElement(locator).build().perform();
		}

		public void offsetClick( WebElement element, int x, int y) {
			WebElement locator = element;
			Actions action = new Actions(driver);
			action.moveToElement(locator, x, y).click().build().perform();
		}

		// Method to select value from drop down based on visible text
		public void selectDropdown(WebElement element, String value) {
			WebElement locator = element;
			Select dropdown = new Select(locator);
			dropdown.selectByVisibleText(value);
		}

		/*
		 * public String getTextFromFirstSelectedOptionInDropdownBox(WebElement
		 * locator){ Select comboBox = new Select(locator);
		 * comboBox.getFirstSelectedOption().getText(); return
		 * getTextFromFirstSelectedOptionInDropdownBox(null); }
		 */

		// Method to get text for any element on a web page
		public String getText(WebElement element) {
			WebElement locator = element;
			return locator.getText();
		}

		public void scrollIntoViewUsingJSExecutor(WebElement element) {
			// waitForElementToBeClickable
			// scrollPagetoSpecificPoint(driver, xvalue, yvalue);
			// locator.getLocation().x
			// ((JavascriptExecutor)MultipleBrowser.driver).executeScript("arguments[0].scrollIntoView(true);",locator);
		}

		// Method to get the Title of the web page
		public String getTitle() {
			return driver.getTitle();
		}

		// Method to get the CurrentURL of the web page
		public String getCurrentURL() {
			return driver.getCurrentUrl();
		}

		public String getAttribute(WebElement element, String value) {
			WebElement locator = element;
			return locator.getAttribute(value);
		}

		public boolean isSelected(WebElement element) throws NoSuchElementException {
			WebElement locator = element;
			return locator.isSelected();
		}

		public WebElement getElementFromString(String xpath_string) throws NoSuchElementException {
			WebElement locator = driver.findElement(By.xpath(xpath_string));
			return locator;
		}
		public boolean isChecked(WebElement element) throws NoSuchElementException {
			boolean flag=element.isSelected();		
			return flag;
		}
		// webdriver wait
		// Tell webdriver to wait
		public void SeleniumWebdriverwait( long timeInMilliseconds) {
			new WebDriverWait(driver, 10);

		}


		// Method to wait for specific amount of time (forceful wait)
		public void waitForMilliSeconds(long timeInMilliseconds) {
			try {
				Thread.sleep(timeInMilliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public boolean isDisplayed(WebElement element) {
			try
			{
				WebElement locator = element;
				return locator.isDisplayed();
			} 
			catch (Exception e) 
			{
				return false;
			}
		}

		public boolean isEnabled(WebElement element) {
			WebElement locator = element;
			return locator.isEnabled();
		}

		public String getColor(WebElement element) {
			WebElement locator = element;
			String color = locator.getCssValue("color");

			String[] numbers = color.replace("rgba(", "").replace(")", "")
					.split(",");
			int r = Integer.parseInt(numbers[0].trim());
			int g = Integer.parseInt(numbers[1].trim());
			int b = Integer.parseInt(numbers[2].trim());
			String color_locator = (r + "," + g + "," + b);
			return color_locator;
		}



		public Boolean isElementPresent(WebElement locator){
			try {
				locator.isDisplayed();
				return true;
			} catch (NoSuchElementException e) {
				return false;
			}
		}


		public boolean isAlertPresent() {
			try {
				driver.switchTo().alert();
				return true;
			} catch (NoAlertPresentException e) {
				return false;
			}
		}

		public String closeAlertAndGetItsText(
				boolean acceptNextAlert) {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				if (acceptNextAlert) {
					alert.accept();
				} else {
					alert.dismiss();
				}
				return alertText;
			} finally {
				acceptNextAlert = true;
			}
		}

		public static void SendKeysXTimes(int keyCode, int XTimes) {
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}

			for (int i = 1; i <= XTimes; i++) {
				robot.keyPress(keyCode);
			}
		}

		public void waitForPageLoaded(int milliseconds) {
			try {
				Thread.sleep(milliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript(
							"return document.readyState").equals("complete");
				}
			};

			Wait<WebDriver> wait = new WebDriverWait(driver, 30);
			try {
				wait.until(expectation);
			} catch (Throwable error) {

			}
		}

		public void waitForElementFluently( WebElement element,int seconds) {
			int retry = 0;
			int retryCount = seconds < 25 ? 2 : 1;
			while (retry < retryCount) {
				try {
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							.withTimeout(seconds, TimeUnit.SECONDS)
							.pollingEvery(200, TimeUnit.MILLISECONDS);
					wait.until(ExpectedConditions.visibilityOf(element));
				} catch (Exception ex) {

				}
				retry++;
			}

			/*
			 * final long startTime = System.currentTimeMillis();
			 * 
			 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			 * .withTimeout(seconds, TimeUnit.SECONDS) .pollingEvery((seconds / 5),
			 * TimeUnit.SECONDS) .ignoring(StaleElementReferenceException.class);
			 * 
			 * int tries = 0; int retryCount = 5; boolean found = false; WebElement
			 * we = null;
			 * 
			 * while (tries < retryCount) { // logger.info( //
			 * "Wait for element "+locator.toString()+" Try number " + (tries++) //
			 * );
			 * 
			 * try { we = wait.until(ExpectedConditions.visibilityOf(locator)); we =
			 * wait.until(ExpectedConditions .elementToBeClickable(locator));
			 * 
			 * found = true; // attempt a find to check that staleException is not
			 * going to // be thrown break; } catch (Exception e) { // logger.info(
			 * "Exception in waitForElementFluently: \n" + // e.getMessage() +
			 * "\n"); } }
			 */
		}

		public void waitForData( WebElement element, int seconds,String text) {
			WebElement locator = element;
			Wait<WebDriver> wait = (new WebDriverWait(driver, seconds));
			wait.until(ExpectedConditions.textToBePresentInElement(locator, text));
		}

		public void waitForElementToBeClickable(WebElement element, int seconds) {
			WebElement locator = element;
			Wait<WebDriver> wait = (new WebDriverWait(driver, seconds));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		public void scrollPageUp() {
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("window.scrollTo(0, 0);");
			Actions clicker = new Actions(driver);
			clicker.sendKeys(Keys.PAGE_UP);

		}

		public void scrollPageDown() {
			Actions clicker = new Actions(driver);
			clicker.sendKeys(Keys.PAGE_DOWN);
		}

		public void scrollPagetoSpecificPoint( String xvalue,
				String yvalue) {
			JavascriptExecutor jsx = (JavascriptExecutor) driver;
			jsx.executeScript("window.scrollBy(" + xvalue + "," + yvalue + ")", "");
		}

		/* public boolean checkDropDownOptions(By element,
	                  List<String> stringlist) {
	           List<WebElement> webelementlist = getElementListWithIdentification(element);
	           List<WebElement> dropdownoptions = webelementlist;
	           boolean result = false;
	           List<String> type = stringlist;
	           int i = 0;
	           for (WebElement options : dropdownoptions) {
	                  if ((options.getText()).equals(type.get(i).toString())) {
	                        result = true;
	                  }
	                  i++;
	           }
	           return result;
	    }*/

		public boolean isEmpty(WebElement element) {
			WebElement locator = element;
			if ((locator.getText()).equals(""))
				return true;
			return false;
		}

		public void DragDrop( WebElement source, WebElement target) {
			Actions builder = new Actions(driver);
			builder.dragAndDrop(source, target).build().perform();
		}

		// Method to Press Enter Key
		public void pressEnterKey() {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
		}

		// Method to double click
		public void doubleclick() {
			Actions action = new Actions(driver);
			action.doubleClick().build().perform();

		}

		public void doubleclick(WebElement we) {
			Actions action = new Actions(driver);
			action.doubleClick(we).build().perform();
		}

		public void pressTab() {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.TAB).build().perform();
		}

		public void pressshiftandtab() {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.chord(Keys.SHIFT), Keys.chord(Keys.TAB)).build()
			.perform();

		}

		public String getDateParameters(String ElementName) {
			Calendar cal = Calendar.getInstance();
			String Month = new SimpleDateFormat("MMM").format(cal.getTime());
			String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
			String Day = new SimpleDateFormat("dd").format(cal.getTime());
			String Hour = new SimpleDateFormat("HH").format(cal.getTime());
			String Min = new SimpleDateFormat("mm").format(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			String Day1 = new SimpleDateFormat("dd").format(cal.getTime());

			if (ElementName.equals("Year"))
				return Year;
			if (ElementName.equals("Month"))
				return Month;
			if (ElementName.equals("Day"))
				return Day;
			if (ElementName.equals("NextDay"))
				return Day1;
			if (ElementName.equals("Hour"))
				return Hour;
			if (ElementName.equals("Min"))
				return Min;

			return null;
		}

		public void inputValueinRichTextbox( WebElement element,String value) {
			WebElement locator = element;
			((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML='"
					+ value + "';", locator);
		}

		/*public void inputValueUsingInnerHTML(WebElement element,String value) {
	           WebElement locator = element;
	           Waitformilliseconds(3000);
	           if (Initialization.browserName.trim().equals("ios-ipad")) {
	                  value = value.replace("\n", "<br/>");
	                  ((JavascriptExecutor) driver).executeScript(
	                               "arguments[0].innerHTML='" + value + "';", locator);

	           } else {
	                  inputValue(element, value);
	           }
	    }
		 */
		public void pressCtrlKey() {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.CONTROL).build().perform();
		}

		public void pressSpaceKey() {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.SPACE).build().perform();
		}

		public void pressBackSpaceKey() {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.BACK_SPACE).build().perform();
		}

		public WebElement getElementWithIdentification1( WebElement element ){
			//(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(driver.findElement(element)));
			return element;
		}

		/* public List<WebElement> getElementListWithIdentification( By element ){
	           //(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(driver.findElement(element)));
	           return driver.findElements(element);
	    }*/


		/* public boolean validateElementsinArray(List<WebElement> bylist){
	           SoftAssert soft = new SoftAssert();
	           boolean flag=false;
	           int count=0;
	           try{
	                  Iterator<WebElement> it=bylist.iterator();
	                  while(it.hasNext()){
	                        WebElement WebElement=it.next();
	                        if(isElementPresent(WebElement)){
	                                count++;
	                        }
	                        soft.assertThat(
	                                      isDisplayed(WebElement))
	                                      .as(WebElement.toString()+" is Column Table header should be Displayed")
	                                      .isTrue();
	                  }
	                  if(bylist.size()==count){
	                        flag=true;
	                  }
	           }catch(Exception e){
	           }
	           return flag;
	    }*/

		public void clickItemBasedOnListElements(By WebElement,String visbleText){
			List<WebElement> list=driver.findElements(WebElement);
			Iterator<WebElement> it=list.iterator();
			while(it.hasNext()){
				WebElement element=it.next();
				String item_text=element.getText();
				if(visbleText.equals(item_text)){
					element.click();
				}
			}
		}

		public String screenshotCapture(WebDriver driver,String ScreenshotName) {

			try {
				TakesScreenshot screenshot=(TakesScreenshot)driver;
				File source=screenshot.getScreenshotAs(OutputType.FILE);
				String dest="./Screenshot/"+ScreenshotName+".png";
				File destination=new File(dest);
				FileUtils.copyFile(source, destination);
				System.out.println("Screenshot taken");
				return dest;
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot"+e.getMessage());
				return e.getMessage();
			} 

		}
	
		public String getParentWindow(){
			String ParentWindow;
			ParentWindow = driver.getWindowHandle();
			return ParentWindow;
		}

		public void SwitchToNewWindow(String ParentWindow) {


			Set<String> windows = driver.getWindowHandles();
			System.out.println("Total windows ::: " + windows.size());
			System.out.println(windows);
			if (windows.size() >= 1) {
				for (String winHandle : windows) {
					System.out.println(winHandle);
					if (!ParentWindow.equals(winHandle)) {
						System.out.println("Child Window handle ::: " + winHandle);
						driver.switchTo().window(winHandle).manage().window().maximize();
						System.out.println(driver.getCurrentUrl());
						break;
					}
				}
			}
		}

		public boolean Verify_Element_ReduseTime(WebElement element) throws Exception {
			boolean flag = false;
			WebDriverWait wait;
			try {

				wait = new WebDriverWait(driver, 10);
				driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

				if (element.isDisplayed()) {

					flag = true;

				}
				wait = new WebDriverWait(driver, 60);
				driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			} catch (Exception e) {
				// System.out.println(e.getMessage());
				wait = new WebDriverWait(driver, 60);
				driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			}
			return flag;
		}

		public void windowHandle(String windoHandle) {
			try {
				Thread.sleep(3000);
				Set<String> windows = driver.getWindowHandles();
				System.out.println("Total windows ::: " + windows.size());
				if (windows.size() >= 1) {
					for (String winHandle : windows) {
						if (!windoHandle.equals(winHandle)) {
							System.out.println(winHandle);
							String newwindow = winHandle;
							// GOC.winSSOParentHndle=winHandle;
							System.out.println("Child Window handle ::: " + newwindow);
							// Thread.sleep(8000);
							driver.switchTo().window(newwindow).manage().window().maximize();
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Exception in windowHandle(), in  Login.java :: " + e.getMessage());
			}

		}

		public boolean waitUntilVisible(WebElement element) {

			WebDriverWait wait = new WebDriverWait(driver, 120);

			wait.ignoring(ElementNotVisibleException.class);
			WebElement obj = wait.until(ExpectedConditions.visibilityOf(element));
			if (obj == null)
				return false;
			else
				return true;

		}



		public void TakeScreenShot(ITestResult result)
		{

			String name=result.getMethod().getMethodName();
			EventFiringWebDriver efw=new EventFiringWebDriver(BaseTest.driver);
			File src=efw.getScreenshotAs(OutputType.FILE);
			try 
			{
				FileUtils.copyFile(src, new File("/Users/snehashish/shopkick_automation/CIService/RAFT/screenshots/"+name+".png"));
			} 
			catch (IOException e) 
			{
				
				e.printStackTrace();
			}
		}

		public static String getProperty(String property) {
			Properties pro = new Properties();

			try {
				pro.load(new FileInputStream("Propertyfile.properties"));
			} catch (Exception e) {
				// TODO: handle exception
			}

			return pro.getProperty(property);
		}

		public void scroolPageToExpectedid(WebElement element) {

			try {

				// WebElement element = driver.findElement(WebElement);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				Thread.sleep(500);

			} catch (Exception e) {
				System.out
				.println("Exception in scroolPageToExpectedid(), in  AppLevelFunctions.java :: " + e.getMessage());

			}

		}

		public static String GetDateNow(String dformat) {
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat(dformat);
			String dateNow = formatter.format(currentDate.getTime());
			return dateNow;
		}
		
		
		public static String GetDate_NoofDays(String dformat,int i) {
			Calendar currentDate = Calendar.getInstance();
			currentDate.add(Calendar.DAY_OF_WEEK, i);
			SimpleDateFormat formatter = new SimpleDateFormat(dformat);
			String dateNow = formatter.format(currentDate.getTime());
			return dateNow;
		}

		public String convertDate(String Conv_date,String dformat){

			Date date = null;
			// *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
			SimpleDateFormat dt = new SimpleDateFormat(dformat);
			try {
				date = dt.parse(Conv_date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dt.format(date).toString();
		}

		public String convertDate(String Conv_date,String fromForMat,String toFormat){

			Date date = null;
			// *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
			SimpleDateFormat dt = new SimpleDateFormat(fromForMat);
			SimpleDateFormat toF = new SimpleDateFormat(toFormat);
			try {
				date = dt.parse(Conv_date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return toF.format(date).toString();
		}

		public static String getSysUserName() {
			return System.getProperty("user.name");
		}

		public static String getSysName() {
			return System.getenv("COMPUTERNAME");
		}
	
}
