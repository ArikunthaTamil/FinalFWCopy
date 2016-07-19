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
	    	    	//capabilities.setCapability("appPackage", "com.mfiles.mobile"); 
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
		//Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd E:/ && java -jar selenium-server-standalone-2.52.0.jar -role hub\"");
	}
	
	public static void startNode() throws Exception 
	{
		String jsonPath = configProperty.getProperty("jsonPath");
		String cmd = "cmd /c start cmd.exe /K \""+"appium --nodeconfig "+ jsonPath + testName +".json" + " -p " + driverPort + " -U " + device + " --session-override";
		Runtime.getRuntime().exec(cmd);
		//Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"appium --nodeconfig D:\\TTBooking\\SamsungGalaxyAndroid.json -p 4766 -U 4d005018f4f24005 --session-override\"");
	}
	
	/*
	/**
	 * Get the test session Node IP address,port when executing through Grid
	 * 
	 * @param driver
	 *            : Webdriver
	 * @return: Session ID
	 * @throws Exception
	 *             : Selenium Exception
	 */
	/*
	public static final String getTestSessionNodeIP(final WebDriver driver) throws Exception {

		XmlTest test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
		driverHost = System.getProperty("hubHost") != null ? System.getProperty("hubHost") : test.getParameter("deviceHost");
		driverPort = test.getParameter("devicePort");
		HttpHost host = new HttpHost(driverHost, Integer.parseInt(driverPort));
		HttpClient client = HttpClientBuilder.create().build();
		URL testSessionApi = new URL("http://" + driverHost + ":" + driverPort + "/grid/api/testsession?session=" + ((RemoteWebDriver) driver).getSessionId());
		BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", testSessionApi.toExternalForm());
		HttpResponse response = client.execute(host, r);
		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		String nodeIP = object.getString("proxyId").toLowerCase();
		nodeIP = nodeIP.replace("http://", "");
		nodeIP = nodeIP.replaceAll(":[0-9]{1,5}", "").trim();
		return nodeIP;
	}
	
	/**
	 * Get the test session Hub IP address,port when executing through Grid
	 * 
	 * @param driver
	 *            : Webdriver
	 * @return: Session ID
	 * @throws Exception
	 *             : Selenium Exception
	 
	public static final String getHubSession(final WebDriver driver) throws Exception {

		XmlTest test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
		driverHost = System.getProperty("hubHost") != null ? System.getProperty("hubHost") : test.getParameter("deviceHost");
		driverPort = test.getParameter("devicePort");
		HttpHost host = new HttpHost(driverHost, Integer.parseInt(driverPort));
		HttpClient client = HttpClientBuilder.create().build();
		URL testSessionApi = new URL("http://" + driverHost + ":" + driverPort + "/grid/api/testsession?session=" + ((RemoteWebDriver) driver).getSessionId());
		BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", testSessionApi.toExternalForm());
		HttpResponse response = client.execute(host, r);
		JSONObject object = new JSONObject(EntityUtils.toString(response.getEntity()));
		String nodeIP = object.getString("proxyId").toLowerCase();
		nodeIP = nodeIP.replace("http://", "");
		nodeIP = nodeIP.replaceAll(":[0-9]{1,5}", "").trim();
		return nodeIP;
	}
	*/
} //End getDriver