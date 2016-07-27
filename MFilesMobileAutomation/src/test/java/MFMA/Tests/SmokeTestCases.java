package MFMA.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;

import MFMA.Factories.*;
import MFMA.Screens.AddServerScreen;
import MFMA.Screens.LoginScreen;
import genericLibrary.BaseTest;
import genericLibrary.DataUtils;
import genericLibrary.EmailReport;
import genericLibrary.EnvironmentPropertiesReader;
import genericLibrary.Log;
import genericLibrary.MobileDriverUtils;
import genericLibrary.Utils;

@Listeners(EmailReport.class)
public class SmokeTestCases extends BaseTest {
	
	public static String xlTestDataWorkBook = null;
	public static String userName = null;
	public static String password = null;
	public static String testVault = null;
	public static String serverName = null;
	public static String objectType = null;
	public static String className = null;
	public static String templateName = null;
	public static Boolean testResult = null;
	public RemoteWebDriver driver = null;
	public ITestResult result;
	public static String deviceName = null;
	
	/**
	 * init : Before Class method to perform initial operations.
	 */
	@BeforeClass (alwaysRun=true)
	public void init() throws Exception {
		
		try {
			MobileDriverUtils.startHub();
			MobileDriverUtils.startNode();
			Thread.sleep(10000);
			driver = MobileDriverUtils.getDriver();
			Utils.popupHandler(driver);
			deviceName = MobileDriverUtils.testName;			
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
	 * writeResult : AfterMethod method to get the test result status of last executed test case
	 */
	@AfterMethod
	public void writeResult(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			testResult = true;
		}
		else
		{
			testResult = false;
			driver.quit();
			driver = MobileDriverUtils.getDriver();
			Utils.popupHandler(driver);
		}
	}
	
	/**
	 * TC_001 : Connect to server
	 */
	@Test(description = "Connect to server with empty string")
	public void SmokeCaseTest1_1() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest001", "Connect to server with empty string", "MFMA_SmokeTest", deviceName);
		try {
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", driver, extentedReport);
			
			addServerPage.connectToServer("");
			Log.message("Step-2: Entered nothing in the server textbox", driver, extentedReport);
			
			addServerPage.verifyEmptyError();
			Log.pass("Step-3: Verified Empty Error Message", driver, extentedReport);
			
			addServerPage.tapOkButton();
			Log.message("Step-4: Tapped Empty Error Message Dialog Box", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_1

	/**
	 * TC_002 : Connect to Server
	 */
	@Test(description = "Connect to server with invalid address")
	public void SmokeCaseTest1_2() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest002", "Connect to server with invalid address", "MFMA_SmokeTest", deviceName);
		try {
				
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", driver, extentedReport);
			
			addServerPage.connectToServer("fghgh");
			Log.message("Step-2: Entered invalid text in the server textbox", driver, extentedReport);
			
			addServerPage.verifyInvalidError(extentedReport);
			Log.pass("Step-3: Verified Invalid Error Message", driver, extentedReport);
			
			addServerPage.tapInvalidOkButton();
			Log.message("Step-4: Tapped Invalid Error Message Dialog Box", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_2
	
	/**
	 * TC_003 : Connect to Server
	 */
	@Test(description = "Connect to server with valid address")
	public void SmokeCaseTest1_3() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest003", "Connect to server with valid address", "MFMA_SmokeTest", deviceName);
		
		HashMap <String, String> testData = DataUtils.testDatabyID("MFMATest003", "SmokeTestCases_POC");
		serverName = testData.get("ServerName").toString();
		
		try {

			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", driver, extentedReport);
			
			addServerPage.connectToServer(serverName);
			Log.message("Step-2: Entered nothing in the server testbox", driver, extentedReport);
			
			Login loginPage = addServerPage.navigateToLoginPage();
			if(!loginPage.pageLoaded())
				Log.fail("Login Screen not loaded", driver, extentedReport);
			Log.pass("Step-3: Successfully Navigated to Login Page", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_3
	
	/**
	 * TC_004 : Login to application
	 */
	@Test(description = "Login to application with empty username and password")
	public void SmokeCaseTest1_4() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest004", "Login to application with empty username and password", "MFMA_SmokeTest", deviceName);
		try {
			if(!testResult)
			{
				AddServer addServerPage = new AddServer(driver);
				addServerPage.connectToServer(serverName);
			}
			
			Login loginPage = new Login(driver);
			loginPage.loginToMfiles("", "");
			Log.message("step-1: Logging into MFiles with empty string", driver, extentedReport);
			
			loginPage.verifyEmptyError();
			Log.pass("Step-2: Verified Empty Error Message", driver, extentedReport);
			
			loginPage.tapOkButton();
			Log.message("Step-3: Tapped Empty Error Message Dialog Box", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_4
	
	/**
	 * TC_005 : Login to application
	 */
	@Test(description = "Login to application with invalid username")
	public void SmokeCaseTest1_5() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest005", "Login to application with invalid username", "MFMA_SmokeTest", deviceName);
		try {
			
			if(!testResult)
			{
				AddServer addServerPage = new AddServer(driver);
				addServerPage.connectToServer(serverName);
			}
			
			Login loginPage = new Login(driver);
			loginPage.loginToMfiles("asfb4565", "test");
			Log.message("step-1: Logging into MFiles with invalid username", driver, extentedReport);
			
			loginPage.verifyInvalidError(extentedReport);
			Log.pass("Step-2: Verified Invalid Error Message", driver, extentedReport);
			
			loginPage.tapInvalidOkButton();
			Log.message("Step-3: Tapped Invalid Error Message Dialog Box", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_5
	
	/**
	 * TC_006 : Login to application
	 */
	@Test(description = "Login to application with valid credentials")
	public void SmokeCaseTest1_6() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest006", "Login to application with valid credentials", "MFMA_SmokeTest", deviceName);
		
		HashMap <String, String> testData = DataUtils.testDatabyID("MFMATest006", "SmokeTestCases_POC");
		userName = testData.get("UserName").toString();
		password = testData.get("Password").toString();
		
		try {
			if(!testResult)
			{
				AddServer addServerPage = new AddServer(driver);
				addServerPage.connectToServer(serverName);
			}
			
			Login loginPage = new Login(driver);
			loginPage.loginToMfiles(userName, password);
			Log.message("step-1: Logging into MFiles with valid credentials", driver, extentedReport);
			
			Vault vault = loginPage.navigateToVaultPage();
			if(!vault.pageLoaded())
				Log.fail("Vault Screen not loaded", driver, extentedReport);
			Log.pass("step-2: Navigated to Vault Page", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_6
	
	/**
	 * TC_007 : Vault Selection
	 */
	@Test(description = "Vault Selection")
	public void SmokeCaseTest1_7() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest007", "Login to application with valid credentials", "MFMA_SmokeTest", deviceName);
		
		HashMap <String, String> testData = DataUtils.testDatabyID("MFMATest007", "SmokeTestCases_POC");
		testVault = testData.get("vaultName").toString();
		
		try {
			
			if(!testResult)
			{
				AddServer addServerPage = new AddServer(driver);
				addServerPage.connectToServer(serverName);
				Login loginPage = addServerPage.navigateToLoginPage();
				loginPage.loginToMfiles(userName, password);
			}
		
			Vault vault = new Vault(driver);
			Log.message("Step-1: Started with Vault Screen", driver, extentedReport);
			
			vault.selectVault(testVault);
			Log.message("Step-2: Selecting Vault", driver, extentedReport);
			
			VaultHome vaultHome = vault.navigateToVaultHomeScreen();
			if(!vaultHome.pageLoaded())
				Log.fail("Vault Home not loaded", driver, extentedReport);
			Log.pass("Step-3: Navigated to Vault Home Screen Successfully!!", driver, extentedReport);
				
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_7
	
	/**
	 * TC_008 : Object Creation
	 */
	@Test(description = "Object Creation")
	public void SmokeCaseTest1_8() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest008", "Creating Object", "MFMA_SmokeTest", deviceName);
		
		HashMap <String, String> testData = DataUtils.testDatabyID("MFMATest008", "SmokeTestCases_POC");
		objectType = testData.get("objectType").toString();
		className = testData.get("className").toString();
		templateName = testData.get("templateName").toString();
		
		try {
			if(!testResult)
			{
				AddServer addServerPage = new AddServer(driver);
				addServerPage.connectToServer(serverName);
				Login loginPage = addServerPage.navigateToLoginPage();
				loginPage.loginToMfiles(userName, password);
				Vault vault = loginPage.navigateToVaultPage();
				vault.selectVault(testVault);
			}
		
			VaultHome vaultHome = new VaultHome(driver);
			Log.message("Step-1: Started with Vault Home Screen", driver, extentedReport);
			
			vaultHome.clickCreateNewObject();
			Log.message("Step-2: Clicks on create object button", driver, extentedReport);
			
			vaultHome.selectObjectType(objectType);
			vaultHome.selectClassName(className);
			vaultHome.selectTemplate(templateName);
			Log.message("Step-3: Selecting object Type, class and template if any", driver, extentedReport);
			
			ObjectCreation objectCreation = vaultHome.navigateToObjectCreationScreen();
			if(!objectCreation.pageLoaded())
				Log.fail("Object Creation Screen not loaded", driver, extentedReport);
			Log.pass("Step-4: Navigated to Object Creation Screen Successfully!!", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_8
	
	/**
	 * TC_009 : Populating properties
	 */
	@Test(description = "Populating properties")
	public void SmokeCaseTest1_9() throws Exception {
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest009", "Populating properties", "MFMA_SmokeTest", deviceName);
		try {
				if(!testResult)
				{
					AddServer addServerPage = new AddServer(driver);
					addServerPage.connectToServer(serverName);
					Login loginPage = addServerPage.navigateToLoginPage();
					loginPage.loginToMfiles(userName, password);
					Vault vault = loginPage.navigateToVaultPage();
					vault.selectVault(testVault);
					VaultHome vaultHome = vault.navigateToVaultHomeScreen();
					vaultHome.clickCreateNewObject();
					vaultHome.selectObjectType(objectType);
					vaultHome.selectClassName(className);
					vaultHome.selectTemplate(templateName);
				}
		
			ObjectCreation objectCreation = new ObjectCreation(driver);
			Log.message("Step-1: Started with Object Creation Screen", driver, extentedReport);
			
			LinkedHashMap<String, String> propertyList = new LinkedHashMap<String, String>();
			propertyList.put("Name or title", "sample");
			propertyList.put("Assignment description", "assignment");
			propertyList.put("Assigned to", "ext");
			propertyList.put("Deadline", "September_28_2016");
			
			objectCreation.populatingValuesWithoutType(propertyList);
			Log.message("Step-2: Populated values", driver, extentedReport);
			
			objectCreation.tapOnSaveButton();
			Log.pass("Step-3: Tap on Save Button", driver, extentedReport);
					
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
		} //End finally
		
	} //End SmokeCaseTest1_9
	
	/**
	 * tearDown : After Class method to perform final operations.
	 */
	@AfterClass
	public void tearDown() throws Exception
	{
		driver.quit();
	}
		 
}
