package MFMA.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
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
import genericLibrary.Log;
import genericLibrary.MobileDriverUtils;

@Listeners(EmailReport.class)
public class SmokeTestCases extends BaseTest {
	
	public static String xlTestDataWorkBook = null;
	public static String userName = null;
	public static String password = null;
	public static String testVault = null;
	public static String serverName = null;
	RemoteWebDriver driver = null;
	ITestResult result;
	/**
	 * init : Before Class method to perform initial operations.
	 */
	@BeforeClass (alwaysRun=true)
	public void init() throws Exception {
		
		try {
			driver = MobileDriverUtils.getDriver();
			//xlTestDataWorkBook = "SmokeTestCases.xls";
			//XmlTest xmlParameters = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
			//userName = xmlParameters.getParameter("UserName");
			//password = xmlParameters.getParameter("Password");
			//testVault = xmlParameters.getParameter("VaultName");
			//serverName = xmlParameters.getParameter("server");
					
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
		
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest001", "Connect to server with empty string", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", driver, extentedReport);
			
			addServerPage.connectToServer("");
			Log.message("Step-2: Entered nothing in the server testbox", driver, extentedReport);
			
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
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_1
	
	/**
	 * TC_002 : Connect to Server
	 */
	@Test(description = "Connect to server with invalid address")
	public void SmokeCaseTest1_2() throws Exception {
		
		//RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest002", "Connect to server with invalid address", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			//int status = result.getStatus();
			//System.out.println(status);
			
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", driver, extentedReport);
			
			addServerPage.connectToServer("@#$%^");
			Log.message("Step-2: Entered nothing in the server testbox", driver, extentedReport);
			
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
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_2
	
	/**
	 * TC_003 : Connect to Server
	 */
	@Test(description = "Connect to server with valid address")
	public void SmokeCaseTest1_3() throws Exception {
		
		//RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest003", "Connect to server with valid address", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			AddServer addServerPage = new AddServer(driver);
			Log.message("Step-1: MFiles Mobile Application launched successfully!!", driver, extentedReport);
			
			addServerPage.connectToServer("http://172.24.166.141");
			//addServerPage.connectToServer("https://dmztr02.m-files.com:4443/mf11.2");
			Log.message("Step-2: Entered nothing in the server testbox", driver, extentedReport);
			
			Login loginPage = addServerPage.navigateToLoginPage();
			loginPage.isLoaded();
			Log.pass("Step-3: Successfully Navigated to Login Page", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_3
	
	/**
	 * TC_004 : Login to application
	 */
	@Test(description = "Login to application with empty username and password")
	public void SmokeCaseTest1_4() throws Exception {
		
		//RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest004", "Login to application with empty username and password", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			Login loginPage = new Login(driver);
			loginPage.loginToMfiles("", "");
			//loginPage.loginToMfiles("alexk", "password", "ENG 2015.2");
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
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_4
	
	/**
	 * TC_005 : Login to application
	 */
	@Test(description = "Login to application with invalid username")
	public void SmokeCaseTest1_5() throws Exception {
		
		//RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest005", "Login to application with invalid username", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			Login loginPage = new Login(driver);
			loginPage.loginToMfiles("asfb4565", "test");
			//loginPage.loginToMfiles("alexk", "password", "ENG 2015.2");
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
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_5
	
	/**
	 * TC_006 : Login to application
	 */
	@Test(description = "Login to application with valid credentials")
	public void SmokeCaseTest1_6() throws Exception {
		
		//RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest006", "Login to application with valid credentials", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
			Login loginPage = new Login(driver);
			loginPage.loginToMfiles("alexk", "test");
			Log.message("step-1: Logging into MFiles with valid credentials", driver, extentedReport);
			
			Vault vault = loginPage.navigateToVaultPage();
			vault.isLoaded();
			Log.pass("step-2: Navigated to Vault Page", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_6
	
	/**
	 * TC_007 : Vault Selection
	 */
	@Test(description = "Vault Selection")
	public void SmokeCaseTest1_7() throws Exception {
		
		//RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest007", "Login to application with valid credentials", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
		
			Vault vault = new Vault(driver);
			Log.message("Step-1: Started with Vault Screen", driver, extentedReport);
			
			vault.selectVault("Sample Vault");
			Log.message("Step-2: Selecting Vault", driver, extentedReport);
			
			VaultHome vaultHome = vault.navigateToVaultHomeScreen();
			vaultHome.isLoaded();
			Log.pass("Step-3: Navigated to Vault Home Screen Successfully!!", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_7
	
	/**
	 * TC_008 : Object Creation
	 */
	@Test(description = "Object Creation")
	public void SmokeCaseTest1_8() throws Exception {
		
		//RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest008", "Creating Object", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
		
			VaultHome vaultHome = new VaultHome(driver);
			Log.message("Step-1: Started with Vault Home Screen", driver, extentedReport);
			
			vaultHome.clickCreateNewObject();
			Log.message("Step-2: Clicks on create object button", driver, extentedReport);
			
			vaultHome.selectObjectType("Assignment");
			vaultHome.selectClassName("Customer");
			vaultHome.selectTemplate("Empty");
			Log.message("Step-3: Selecting object Type, class and template if any", driver, extentedReport);
			
			ObjectCreation objectCreation = vaultHome.navigateToObjectCreationScreen();
			objectCreation.isLoaded();
			Log.pass("Step-4: Navigated to Object Creation Screen Successfully!!", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_8
	
	/**
	 * TC_009 : Populating properties
	 */
	@Test(description = "Populating properties")
	public void SmokeCaseTest1_9() throws Exception {
		
		//RemoteWebDriver driver = MobileDriverUtils.getDriver();
		ExtentTest extentedReport = Log.testCaseInfo("MFMATest009", "Populating properties", "MFMA_SmokeTest", "MFMA - AspireQA");
		try {
		
			ObjectCreation objectCreation = new ObjectCreation(driver);
			Log.message("Step-1: Started with Object Creation Screen", driver, extentedReport);
			
			LinkedHashMap<String, String> propertyList = new LinkedHashMap<String, String>();
			propertyList.put("text_Name or title *", "sample");
			propertyList.put("text_Assignment description", "assignment");
			propertyList.put("list_Assigned to *", "ext");
			propertyList.put("date_Deadline", "Sep_28_2016");
			
			objectCreation.populatingValues(propertyList);
			Log.message("Step-2: Populated values", driver, extentedReport);
			
			objectCreation.tapOnSaveButton();
			Log.pass("Step-3: Tap on Save Button", driver, extentedReport);
			
			//ObjectCreation objectCreation = vaultHome.navigateToObjectCreationScreen();
			//objectCreation.isLoaded();
			//Log.pass("Step-4: Navigated to Object Creation Screen Successfully!!", driver, extentedReport);
			
			Log.testCaseResult(extentedReport);
		}
		catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} //End catch
		
		finally {
			Log.endTestCase(extentedReport);
			//driver.quit();
		} //End finally
		
	} //End SmokeCaseTest1_9
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
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
		 
}
