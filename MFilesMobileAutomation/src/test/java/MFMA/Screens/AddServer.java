package MFMA.Screens;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.relevantcodes.extentreports.ExtentTest;

import genericLibrary.Log;
import genericLibrary.Utils;

public class AddServer extends LoadableComponent <AddServer>{
	
	RemoteWebDriver driver;
	
	@FindBy(id="login_server")
	WebElement txtServername;
	
	@FindBy(id="login_log_in")
	WebElement btnConnect;
	
	@FindBy(id="message")
	WebElement txtEmptyErrorMessage;
	
	@FindBy(id="button1")
	WebElement btnEmptyErrorOK;
	
	@FindBy(id="fragment_error_title")
	WebElement txtInvalidErrorTitle;
	
	@FindBy(id="fragment_error_message")
	WebElement txtInvalidErrorMessage;
	
	@FindBy(id="fragment_error_ok")
	WebElement btnInvalidErrorOK;
	
	@FindBy(id="fragment_error_details")
	WebElement btnInvalidErrorDetails;

    public AddServer(RemoteWebDriver driver){

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
    	Log.event("Navigated to Manage Contact Page!");
		return new Login(driver).get();
    }
    
  	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
}