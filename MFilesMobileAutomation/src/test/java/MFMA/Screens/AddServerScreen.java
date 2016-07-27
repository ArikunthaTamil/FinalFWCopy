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
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class AddServerScreen extends LoadableComponent <AddServerScreen>{
	
	@AndroidFindBy(id="login_server")
	@iOSFindBy(xpath="//UIATextField[1]")
	protected WebElement txtServername;
	
	@AndroidFindBy(id="login_log_in")
	@iOSFindBy(xpath="//UIAButton[@name='Connect'][1]")
	protected WebElement btnConnect;
	
	@AndroidFindBy(id="message")
	@iOSFindBy(xpath="//UIAScrollView[1]/UIAStaticText[2]")
	protected WebElement txtEmptyErrorMessage;
	
	@AndroidFindBy(id="button1")
	@iOSFindBy(xpath="//UIACollectionCell[1]/UIAButton[1]")
	protected WebElement btnEmptyErrorOK;
	
	@AndroidFindBy(id="fragment_error_title")
	protected WebElement txtInvalidErrorTitle;
	
	@AndroidFindBy(id="fragment_error_message")
	@iOSFindBy(xpath="//UIAScrollView[1]/UIAStaticText[2]")
	protected WebElement txtInvalidErrorMessage;
	
	@AndroidFindBy(id="fragment_error_ok")
	@iOSFindBy(xpath="//UIACollectionCell[1]/UIAButton[1]")
	protected WebElement btnInvalidErrorOK;
	
	@AndroidFindBy(id="fragment_error_details")
	protected WebElement btnInvalidErrorDetails;
	
	@iOSFindBy(xpath="//UIAButton[@label='OK']")
	protected WebElement btnPopupOk;

	@Override
	protected void isLoaded() throws Error{}

	@Override
	protected void load() {}
}