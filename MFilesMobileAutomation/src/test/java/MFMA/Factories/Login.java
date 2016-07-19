package MFMA.Factories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import MFMA.Screens.LoginScreen;
import genericLibrary.Log;
import genericLibrary.Utils;

public class Login extends LoginScreen 
{
	RemoteWebDriver driver;
	private boolean isPageLoaded = false;
	
	
	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : RemoteWebdriver
	 */
	public Login(RemoteWebDriver driver){

    	this.driver = driver;
		//ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 2);
		PageFactory.initElements(driver, this);
    }
	
	final public void isLoaded(){
		try {
			isPageLoaded = Utils.waitForElement(driver, txtPassword);
		}
		catch (TimeoutException e) {
			throw e;
		}
	}//isLoaded
	
	final protected void load(){
		try {
			//Utils.fluentWait(driver);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		isPageLoaded = true;
	}//load
	
	public Boolean pageLoaded()
	{
		isLoaded();
		return isPageLoaded;
	}
	/**
	 * Tapping Empty Error Dialog Box OK Button
	 */
	public void tapOkButton()
    {
    	btnEmptyErrorOK.click();
    }
	
	/**
	 * Tapping Invalid Error Dialog Box OK Button
	 */
    public void tapInvalidOkButton()
    {
    	btnInvalidErrorOK.click();
    }
    
	/**
	 * setUserName : Sets the user name
	 * @param userName - Name of the user
	 * @throws Exception 
	 */
    public void setUserName(String strUserName) throws Exception
    {
    	txtUsername.sendKeys(strUserName);
    }

    /**
	 * setPassword : Sets the password
	 * @param password - Password
	 * @throws Exception 
	 */
    public void setPassword(String password) throws Exception
    {
    	txtPassword.sendKeys(password);
    }

    /**
	 * clickLogin : Clicks login button
	 * @throws Exception 
	 */
    public void clickLogin() throws Exception
    {
    	btnLogin.click();
    }

    /**
	 * setUserName : Sets the user name
	 * @param userName - Name of the user
	 * @param password - Password of the user
	 * @throws Exception 
	 */
    public void loginToMfiles(String userName,String strPasword) throws Exception
    {
        this.setUserName(userName); //Fill user name
        this.setPassword(strPasword); //Fill password
        driver.navigate().back();
        this.clickLogin(); //Click Login button
        //this.selectVault(vaultName);
    }
    
    /**
	 * Verify Empty Error Message
	 * @throws Exception 
	 */
    public void verifyEmptyError() throws Exception
    {
    	Utils.waitForElement(driver, txtEmptyErrorMessage);
		if (!(txtEmptyErrorMessage.getText()).equalsIgnoreCase("Username is required."))
			throw new Exception("Empty Login Error Message Mismatches!");
    }
    
    /**
	 * Verify Invalid Error Message
	 * @throws Exception 
	 */
    public void verifyInvalidError(ExtentTest extentedReport) throws Exception
    {
    	Utils.waitForElement(driver, txtInvalidErrorMessage);
		if (!(txtInvalidErrorMessage.getText()).equalsIgnoreCase("Please check your username, password and server address, and confirm you have a valid license on the server."))
			Log.failWithExtentScreenshot("Invalid Login Error Message Mismatches!", driver, extentedReport, true);
    }
    
    /**
	 * Navigate to Vault Page
	 * @return instance of Vault Page
	 * @throws Exception 
	 */
    public Vault navigateToVaultPage() throws Exception
    {
    	Log.event("Navigated to Vault Screen!");
		return (Vault) new Vault(driver).get();
    }
}
