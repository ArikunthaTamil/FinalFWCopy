package MFMA.Screens;
import java.util.List;


import genericLibrary.Log;
import genericLibrary.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class LoginScreen extends LoadableComponent <LoginScreen>{
	
    @AndroidFindBy(id="login_username")
    @iOSFindBy(xpath="//UIATableCell[2]/UIATextField[1]")
    protected WebElement txtUsername;
    
    @AndroidFindBy(id="login_password")
    @iOSFindBy(xpath="//UIATableCell[3]/UIASecureTextField[1]")
    protected WebElement txtPassword;
    
    @AndroidFindBy(id="message")
    @iOSFindBy(xpath="//UIAScrollView[1]/UIAStaticText[2]")
	protected WebElement txtEmptyErrorMessage;
	
    @AndroidFindBy(id="button1")
    @iOSFindBy(xpath="//UIACollectionCell[1]/UIAButton[1]")
	protected WebElement btnEmptyErrorOK;

    @AndroidFindBy(id="barone")
    protected WebElement titleText;

    @AndroidFindBy(id="login_log_in")
    @iOSFindBy(xpath="//UIAButton[@name='Log In']")
    protected WebElement btnLogin;
    
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

    @Override
    public void isLoaded() throws Error{}
	
	protected void load(){}
}
