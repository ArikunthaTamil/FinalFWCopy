package MFMA.Screens;
import java.util.List;


import genericLibrary.Log;
import genericLibrary.Utils;

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
	
    @FindBy(id="login_username")
    protected WebElement txtUsername;
    
    @FindBy(id="login_password")
    protected WebElement txtPassword;
    
    @FindBy(id="message")
	protected WebElement txtEmptyErrorMessage;
	
	@FindBy(id="button1")
	protected WebElement btnEmptyErrorOK;

    @FindBy(id="barone")
    protected WebElement titleText;

    @FindBy(id="login_log_in")
    protected WebElement btnLogin;
    
    @FindBy(id="fragment_error_title")
	protected WebElement txtInvalidErrorTitle;
	
	@FindBy(id="fragment_error_message")
	protected WebElement txtInvalidErrorMessage;
	
	@FindBy(id="fragment_error_ok")
	protected WebElement btnInvalidErrorOK;
	
	@FindBy(id="fragment_error_details")
	protected WebElement btnInvalidErrorDetails;

    @Override
    public void isLoaded(){
    }
	
	protected void load(){
	
	}
}
