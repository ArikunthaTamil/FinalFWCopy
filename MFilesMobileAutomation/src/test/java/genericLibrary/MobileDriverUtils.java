package genericLibrary;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

public class MobileDriverUtils {
	
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();
	public static XmlTest test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
	public static String platform = test.getParameter("platform");
	public static String device = test.getParameter("deviceName");
	public static String appPath = System.getProperty("user.dir") + "\\Application\\";
	public static String driverHost = test.getParameter("deviceHost");
	public static String driverPort = test.getParameter("devicePort");
	public static String appName = test.getParameter("appName");
	public static String testName = test.getName();
	
	/**
	 * getDriver : Launches driver and returns the instance of the driver
	 * @return Instance of the driver
	 * @throws Exception 
	 */
	public static RemoteWebDriver getDriver() throws Exception {

		RemoteWebDriver driver = null;
		URL hubURL = null;

		try {
			hubURL = new URL("http://" + driverHost + ":" + driverPort + "/wd/hub");
			
			switch (platform.toUpperCase()) {
				
				case "ANDROID":{
	    			DesiredCapabilities capabilities = new DesiredCapabilities();
	    	    	capabilities.setCapability("device",platform);
	    	    	capabilities.setCapability("deviceName",device);
	    	    	capabilities.setCapability("platformName", platform);
	    	    	capabilities.setCapability("hideKeyboard", "true"); 
	    	    	capabilities.setCapability("appPackage", "com.mfiles.mobile"); 
	    	    	capabilities.setCapability("app", appPath + "Android\\" + appName);
	    	    	driver =  new AndroidDriver(hubURL, capabilities);
	    	    	break;
	    		}	
	    		case "IOS":{
	    			DesiredCapabilities capabilities = new DesiredCapabilities();
	    	    	capabilities.setCapability("device",platform);
	    	    	capabilities.setCapability("deviceName",device);
	    	    	capabilities.setCapability("platformName", platform);
	    	    	capabilities.setCapability("app", "/Users/builds/APKS/IOS/" + appName);
	    	    	driver =  new IOSDriver(hubURL, capabilities);
	    	    	break;
	    		}	
	    		default:{
	    			DesiredCapabilities capabilities = new DesiredCapabilities();
	    	    	capabilities.setCapability("device",platform);
	    	    	capabilities.setCapability("deviceName",device);
	    	    	capabilities.setCapability("platformName", platform);
	    	    	capabilities.setCapability("appPackage", "com.mfiles.mobile"); 
	    	    	capabilities.setCapability("app", appPath + "Android\\" + appName);
	    	    	driver =  new AndroidDriver(hubURL, capabilities);
	    		}	
    		
			} //End switch
			
		} //End try
		catch (Exception e) {
			throw new Exception("Exception encountered in getDriver Method." + e.getMessage().toString());
		}
		
		return driver;

	} //End getDriver
	
	
	public static void startHub() throws Exception 
	{
		String hubDrive = configProperty.getProperty("hubDrive");
		String cmd = "cmd /c start cmd.exe /K \""+"cd " + hubDrive + "/ && " + "java -jar selenium-server-standalone-2.52.0.jar -role hub";
		Runtime.getRuntime().exec(cmd);
	}
	
	public static void startNode() throws Exception 
	{
		String jsonPath = configProperty.getProperty("jsonPath");
		String cmd = "cmd /c start cmd.exe /K \""+"appium --nodeconfig "+ jsonPath + testName +".json" + " -p " + driverPort + " -U " + device + " --session-override";
		Runtime.getRuntime().exec(cmd);
	}

} //End getDriver