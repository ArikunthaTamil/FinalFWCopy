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
	
	public AddServer(RemoteWebDriver driver)
	{
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	//Set server name in textbox
    public void setServerName(String strServerName){
    	
    	txtServername.sendKeys(strServerName);
    }
    
    //Click on connect button
    public void clickConnect(){
    	btnConnect.click();
    }
    
    public void connectToServer(String strServerName){

        //Fill server name
        this.setServerName(strServerName);
        
        //Click Connect button
        this.clickConnect();

    }
    
    public void tapOkButton()
    {
    	btnEmptyErrorOK.click();
    }
    
    public void tapInvalidOkButton()
    {
    	btnInvalidErrorOK.click();
    }
    
    public void verifyEmptyError() throws Exception
    {
    	Utils.waitForElement(driver, txtEmptyErrorMessage);
		if (!(txtEmptyErrorMessage.getText()).equalsIgnoreCase("Server is required."))
			throw new Exception("Add Server Empty Error Message Mismatches!");
    }
    
    public void verifyInvalidError(ExtentTest extentedReport) throws Exception
    {
    	Utils.waitForElement(driver, txtInvalidErrorMessage);
		if (!(txtInvalidErrorMessage.getText()).equalsIgnoreCase("Please check your username, password and server address, and confirm you have a valid license on the server."))
			Log.failWithExtentScreenshot("Add Server Invalid Error Message Mismatches!", driver, extentedReport, true);
    }
    
    public Login navigateToLoginPage() throws Exception
    {
    	Log.event("Navigated to Login Page!");
		return (Login) new Login(driver).get();
    }
}
