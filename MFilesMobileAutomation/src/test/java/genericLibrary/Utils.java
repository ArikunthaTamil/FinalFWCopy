package genericLibrary;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import genericLibrary.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.xml.XmlTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class Utils {
	
	public static int snoozeTime = 0;
	public static int snoozeIdx = 0;
	
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();
	static XmlTest test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
	static String platform = test.getParameter("platform");
	static int minTimeout = Integer.parseInt(configProperty.getProperty("minElementWait"));
	static int maxTimeout = Integer.parseInt(configProperty.getProperty("maxElementWait"));
	static int extraTimeout = Integer.parseInt(configProperty.getProperty("extraElementWait"));
	
	/**
	 * fluentWait: This method is to wait until the current progress gets completed
	 * @param driver
	 * @return None
	 * @throws Exception
	 */
	public static void fluentWait(WebDriver driver) throws Exception {

		final long startTime = StopWatch.startTime();
	
		try {

			

		} // End try
		catch (Exception e) {
			throw new Exception("Exception at Utils.fluentWait : ", e);
		} // End catch

		finally {
			
			long elapsedTime = StopWatch.elapsedTime(startTime);
			Log.event("Utils.fluentWait : Fluent wait operation completed", elapsedTime);
		}

	} // End fluentWait
	
	/**
	 * waitForElement: Method to wait for the Web Element
	 * @param driver: RemoteWebDriver Object
	 * @param pElement: element to be exist
	 * @return Boolean: True if the element exists, false otherwise
	 * @throws Exception
	 */
    public static Boolean waitForElement(RemoteWebDriver driver, WebElement pElement)
    {
    	Boolean isFound = false;
    	try
    	{
            int _TimeToWait = maxTimeout;
            for (int i = 0; i < _TimeToWait; i++)
            {
                Thread.sleep(1000); //Wait for 1 second

                try
                {
                    isFound = pElement.isDisplayed();
                    if (isFound) break;
                    System.out.println("Searching the Element..." + pElement);
                }
                catch (Exception ex)
                {
                	System.out.println(ex.getMessage());
                }
            }
    	}
        catch(Exception e)
    	{
        	isFound = false;
			Log.event("Unable to find a element after waiting for " + maxTimeout);
    	}
    	return isFound;
    }
    
    /**
	 * doesElementExist: Method to verify for the existence of Web Element
	 * @param driver: RemoteWebDriver Object
	 * @param pElement: element to be exist
	 * @return Boolean: True if the element exists, false otherwise
	 * @throws Exception
	 */
    public static Boolean doesElementExist(RemoteWebDriver driver, WebElement pElement)
    {
    	Boolean isFound = false;
    	try
    	{
            int _timeToWait = minTimeout;
            for (int i = 0; i < _timeToWait; i++)
            {
                Thread.sleep(1000); //Wait for 1 second

                try
                {
                    isFound = pElement.isDisplayed();
                    if (isFound) break;
                    System.out.println("Searching the Element..." + pElement);
                }
                catch (Exception ex)
                {
                	System.out.println(ex.getMessage());
                }
            }
    	}
        catch(Exception e)
    	{
        	isFound = false;
			Log.event("Unable to find a element after ");
    	}
    	return isFound;
    }
    
    /**
	 * doesElementExist: Method to verify for the existence of child Web Element
	 * @param driver: RemoteWebDriver Object
	 * @param pElement: parent element
	 * @param by: locator of the child element
	 * @return Boolean: True if the element exists, false otherwise
	 * @throws Exception
	 */
    public static Boolean doesElementExist(RemoteWebDriver driver, WebElement pElement, By by)
    {
    	Boolean isFound = false;
    	try
    	{
            int _timeToWait = minTimeout;
            for (int i = 0; i < _timeToWait; i++)
            {
                Thread.sleep(1000); //Wait for 1 second

                try
                {
                    WebElement element  = pElement.findElement(by);
                    if (element.isDisplayed()) break;
                    Log.event("Searching the Element..." + pElement);
                }
                catch (Exception ex)
                {
                	Log.event(ex.getMessage());
                }
            }
    	}
        catch(Exception e)
    	{
        	isFound = false;
			Log.event("Unable to find a element after ");
    	}
    	return isFound;
    }
    
    /**
	 * doesElementExist: Method to verify for the non existence of the Web Element
	 * @param driver: RemoteWebDriver Object
	 * @param pElement: element not to be existed
	 * @return Boolean: True if the element not exists, false otherwise
	 */
    public static Boolean doesElementNotExist(RemoteWebDriver driver, WebElement pElement)
    {
    	Boolean isNotFound = false;
    	try
    	{
            int _timeToWait = maxTimeout;
            for (int i = 0; i < _timeToWait; i++)
            {
                Thread.sleep(1000); //Wait for 1 second

                try
                {
                	isNotFound = pElement.isDisplayed();
                    if (!isNotFound) break;
                    Log.event("Searching the Element..." + pElement);
                }
                catch (Exception ex)
                {
                	isNotFound=true;
                	Log.event(ex.getMessage());
                }
            }
    	}
        catch(Exception e)
    	{
        	isNotFound = true;
			Log.event("Unable to find a element after ");
    	}
    	return isNotFound;
    }
    
    /**
	 * swipe: To swipe across elements
	 * @param driver: RemoteWebDriver Object
	 * @param pFromElement: Starting Element
	 * @param pToElement: Ending Element
	 * @param duration: amount of time in milliseconds for the entire swipe action
	 */
    @SuppressWarnings("unchecked")
	public static void swipe(RemoteWebDriver driver, WebElement pFromElement, WebElement pToElement, int duration)
    {
        int _startX = pFromElement.getLocation().x + pFromElement.getSize().width / 2;
        int _startY = pFromElement.getLocation().y + pFromElement.getSize().height / 2;
        int _endX = pToElement.getLocation().x + pFromElement.getSize().width / 2;
        int _endY = pToElement.getLocation().y;

        if (platform.equalsIgnoreCase("ANDROID"))
        {
            ((AndroidDriver<WebElement>)driver).swipe(_startX, _startY, _endX, _endY, duration);
        }
        else
        {
        	((IOSDriver<IOSElement>)driver).swipe(_startX, _startY, _endX, _endY, duration);
        }
    }
    
}
