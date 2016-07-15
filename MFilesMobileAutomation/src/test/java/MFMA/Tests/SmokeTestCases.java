package MFMA.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

import MFMA.Screens.AddServer;
import MFMA.Screens.Login;
import genericLibrary.BaseTest;
import genericLibrary.EmailReport;
import genericLibrary.Log;
import genericLibrary.MobileDriverUtils;

@Listeners(EmailReport.class)
public class SmokeTestCases extends BaseTest {
	
	public static String xlTestDataWorkBook = null;
	public static String userName = null;
	public static String password = null;
	public static String testVault = null;
	public static String serverName = null;

	/**
	 * init : Before Class method to perform initial operations.
	 */
	@BeforeClass (alwaysRun=true)
	public void init() throws Exception {
		
		try {
			
			xlTestDataWorkBook = "SmokeTestCases.xls";
			XmlTest xmlParameters = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
			userName = xmlParameters.getParameter("UserName");
			password = xmlParameters.getParameter("Password");
			testVault = xmlParameters.getParameter("VaultName");
			serverName = xmlParameters.getParameter("server");
			
		} //End try
		
		catch(Exception e) {
			if (e.getClass().toString().contains("NullPointerException")) 
				throw new Exception ("Test data sheet does not exists.");
			else {
				System.out.println(e);
				throw e;
			}
		} //End catch
		
	} //End init
	
	/**
	 * TC_001 : Connect to server
	 */
	@Test(description = "Connect to server with empty string")
	public void SmokeCaseTest1_1() throws Exception {
		
		RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest001", "Connect to server with empty string", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", extentedReport);
			
			addServerPage.connectToServer("");
			Log.message("Step-2: Entered nothing in the server testbox", extentedReport);
			
			addServerPage.verifyEmptyError();
			Log.pass("Step-3: Verified Empty Error Message", driver, extentedReport, true);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_1
	
	/**
	 * TC_002 : Connect to Server
	 */
	@Test(description = "Connect to server with invalid address")
	public void SmokeCaseTest1_2() throws Exception {
		
		RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest002", "Connect to server with invalid address", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", extentedReport);
			
			addServerPage.connectToServer("@#$%^");
			Log.message("Step-2: Entered nothing in the server testbox", extentedReport);
			
			addServerPage.verifyInvalidError(extentedReport);
			Log.pass("Step-3: Verified Invalid Error Message", driver, extentedReport, true);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_2
	
	/**
	 * TC_003 : Connect to Server
	 */
	@Test(description = "Connect to server with valid address")
	public void SmokeCaseTest1_3() throws Exception {
		
		RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest003", "Connect to server with valid address", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", extentedReport);
			
			addServerPage.connectToServer("http://172.24.166.141");
			Log.message("Step-2: Entered nothing in the server testbox", extentedReport);
			
			Login loginPage = addServerPage.navigateToLoginPage();
			loginPage.isLoaded();
			Log.pass("Step-3: Successfully Navigated to Login Page", driver, extentedReport, true);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_3
	
	/**
	 * TC_004 : Connect to Server
	 */
	@Test(description = "Login to application with empty string")
	public void SmokeCaseTest1_4() throws Exception {
		
		RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest004", "Login to application with empty string", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", extentedReport);
			
			addServerPage.connectToServer("http://172.24.166.141");
			Log.message("Step-2: Entered nothing in the server testbox", extentedReport);
			
			Login loginPage = addServerPage.navigateToLoginPage();
			loginPage.isLoaded();
			Log.pass("Step-3: Successfully Navigated to Login Page", driver, extentedReport, true);
			
			loginPage.loginToMfiles("alexk", "test", "Sample Vault");
			Log.message("step-4: Logging into MFiles");
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_4
	/*
	@Test(description = "Connect to invalid server")
	public void AdvancedUseCaseTest1_2() throws Exception {
		
		RemoteWebDriver driver = MobileDriverUtils.getDriver();
		
		try {
			AddServerPage addServerPage = new AddServerPage(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!");
			
			addServerPage.connectToServer("");
			Log.message("Step-2: Entered nothing in the server testbox");
			
			addServerPage.verifyEmptyError();
			Log.pass("Step-3: Verified Empty Error Message", driver, true);
			
			Log.testCaseResult();
		}
		catch (Exception e) {
			Log.exception(e, driver);
		} //End catch
		
		finally {
			Log.endTestCase();
			driver.quit();
		} //End finally
		
	} //End AdvancedUseCaseTest1_1_10A
	 /**
	 * TC_001 : Login to application
	 */
	/*
	@Test(description = "Login to application")
	public void AdvancedUseCaseTest1_1_10A() throws Exception {
		
		RemoteWebDriver driver = MobileDriverUtils.getDriver();
		
		try {
			
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToMfiles("AlexK", "Password", "sample vault");
							
		}
		catch (Exception e) {
			Log.exception(e, driver);
		} //End catch
		
		finally {
		} //End finally
		
	} //End AdvancedUseCaseTest1_1_10A
	
	/**
	 * TC_001 : Login to application
	 *//*
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = "excelDataReader", groups = {""}, 
			description = "Login to application")
	public void AdvancedUseCaseTest1_1_10A(HashMap<String,String> dataValues, String driverType) throws Exception {
		
		WebDriver driver = MobileDriverUtils.getDriver();
		
		try {
			
			ConcurrentHashMap <String, String> dataPool = new ConcurrentHashMap <String, String>(dataValues);
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToMfiles("AlexK", "Password", "sample vault");
							
		}
		catch (Exception e) {
			Log.exception(e, driver);
		} //End catch
		
		finally {
		} //End finally
		
	} //End AdvancedUseCaseTest1_1_10A
*/	
		 
}
