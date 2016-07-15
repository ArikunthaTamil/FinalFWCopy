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

public class AddServerScreen extends LoadableComponent <AddServerScreen>{
	
	@FindBy(id="login_server")
	protected WebElement txtServername;
	
	@FindBy(id="login_log_in")
	protected WebElement btnConnect;
	
	@FindBy(id="message")
	protected WebElement txtEmptyErrorMessage;
	
	@FindBy(id="button1")
	protected WebElement btnEmptyErrorOK;
	
	@FindBy(id="fragment_error_title")
	protected WebElement txtInvalidErrorTitle;
	
	@FindBy(id="fragment_error_message")
	protected WebElement txtInvalidErrorMessage;
	
	@FindBy(id="fragment_error_ok")
	protected WebElement btnInvalidErrorOK;
	
	@FindBy(id="fragment_error_details")
	protected WebElement btnInvalidErrorDetails;

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
}