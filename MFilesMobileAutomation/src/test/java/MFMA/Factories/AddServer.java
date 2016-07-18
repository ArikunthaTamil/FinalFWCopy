package MFMA.Factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import MFMA.Screens.AddServerScreen;
import genericLibrary.Log;
import genericLibrary.Utils;

public class AddServer extends AddServerScreen
{
	RemoteWebDriver driver;
	
	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : RemoteWebdriver
	 */
	public AddServer(RemoteWebDriver driver)
	{
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	/**
	 * setServerName : Sets the server
	 * @param setServerName - Name/Ipaddress of the server
	 * @throws Exception 
	 */
    public void setServerName(String strServerName) throws Exception
    {
    	txtServername.sendKeys(strServerName);
    }
    
    /**
	 * clickConnect : clicks the connect button
	 * @throws Exception 
	 */
    public void clickConnect() throws Exception
    {
    	btnConnect.click();
    }
    
    /**
	 * connectToServer : Connecting to the server
	 * @param setServerName - Name/Ipaddress of the server
	 * @throws Exception 
	 */
    public void connectToServer(String strServerName) throws Exception
    {
        this.setServerName(strServerName);   
        this.clickConnect();
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
	 * Verify Empty Error Message
	 * @throws Exception 
	 */
    public void verifyEmptyError() throws Exception
    {
    	Utils.waitForElement(driver, txtEmptyErrorMessage);
		if (!(txtEmptyErrorMessage.getText()).equalsIgnoreCase("Server is required."))
			throw new Exception("Add Server Empty Error Message Mismatches!");
    }
    
    /**
	 * Verify Invalid Error Message
	 * @throws Exception 
	 */
    public void verifyInvalidError(ExtentTest extentedReport) throws Exception
    {
    	Utils.waitForElement(driver, txtInvalidErrorMessage);
		if (!(txtInvalidErrorMessage.getText()).equalsIgnoreCase("Please check your username, password and server address, and confirm you have a valid license on the server."))
			Log.failWithExtentScreenshot("Add Server Invalid Error Message Mismatches!", driver, extentedReport, true);
    }
    
    /**
	 * Navigate to Login Screen
	 * @return instance of Login Screen
	 * @throws Exception 
	 */
    public Login navigateToLoginPage() throws Exception
    {
    	Log.event("Navigated to Login Page!");
		return (Login) new Login(driver).get();
    }
}
