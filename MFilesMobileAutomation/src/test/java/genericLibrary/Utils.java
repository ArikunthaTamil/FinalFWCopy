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
	
	static XmlTest test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
	static String platform = test.getParameter("platform");
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
	
	/// <summary>
    /// Method to wait for the Web Element
    /// </summary>
    /// <param name="driver">RemoteWebDriver Object</param>
    /// <param name="pElement">element to be exist</param>
    /// <returns>Matching Web element if the element exists, raise exception otherwise</returns>
    public static Boolean waitForElement(RemoteWebDriver driver, WebElement pElement)
    {
    	Boolean isFound = false;
    	try
    	{
            int _TimeToWait = 60;
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
			Log.event("Unable to find a element after ");
    	}
    	return isFound;
    }
    
  /// <summary>
    /// Method to wait for the Web Element
    /// </summary>
    /// <param name="driver">RemoteWebDriver Object</param>
    /// <param name="pElement">element to be exist</param>
    /// <returns>Matching Web element if the element exists, raise exception otherwise</returns>
    public static Boolean doesElementExist(RemoteWebDriver driver, WebElement pElement, int _timeToWait)
    {
    	Boolean isFound = false;
    	try
    	{
            //int _TimeToWait = 60;
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
    
  /// <summary>
    /// Method to wait for the Web Element
    /// </summary>
    /// <param name="driver">RemoteWebDriver Object</param>
    /// <param name="pElement">element to be exist</param>
    /// <returns>Matching Web element if the element exists, raise exception otherwise</returns>
    public static Boolean doesElementNotExist(RemoteWebDriver driver, WebElement pElement, int _timeToWait)
    {
    	Boolean isNotFound = false;
    	try
    	{
            //int _TimeToWait = 60;
            for (int i = 0; i < _timeToWait; i++)
            {
                Thread.sleep(1000); //Wait for 1 second

                try
                {
                	isNotFound = pElement.isDisplayed();
                    if (!isNotFound) break;
                    System.out.println("Searching the Element..." + pElement);
                }
                catch (Exception ex)
                {
                	isNotFound=true;
                	System.out.println(ex.getMessage());
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
    
    
	/// <summary>
    /// To swipe across elements
    /// </summary>
    /// <param name="driver">RemoteWebDriver Object</param>
    /// <param name="pFromElement">Starting Element</param>
    /// <param name="pToElement">Ending Element</param>
    /// <param name="duration">amount of time in milliseconds for the entire swipe action to take</param>
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
