package MFMA.Screens;
import java.util.List;

import genericLibrary.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class Login extends LoadableComponent <Login>{
	
	private boolean pageLoaded = false;
	private final WebDriver driver;
	
    @FindBy(id="login_username")
    WebElement txtUsername;
    
    @FindBy(id="login_password")
    WebElement txtPassword;

    @FindBy(id="barone")
    WebElement titleText;

    @FindBy(id="login_log_in")
    WebElement btnLogin;

    public Login(WebDriver driver){

    	this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 2);
		PageFactory.initElements(finder, this);

    }
    
    final protected void isLoaded(){
		if (!(driver.getCurrentUrl().toLowerCase().contains("/login.aspx") && driver.getTitle().contains("M-Files Web Access"))){
			if (!pageLoaded)
				Assert.fail();
			Log.fail("Expected page was a WebAccess Home page, but current page is not a Login page." + "Current Page is: " + driver.getCurrentUrl(), driver); // Verify whether is Home page
		}
	}
	
	final protected void load(){
		try {
			//Utils.fluentWait(driver);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		pageLoaded = true;
	}
       
	/**
	 * setUserName : Sets the user name
	 * @param userName - Name of the user
	 * @throws Exception 
	 */
    public void setUserName(String strUserName){
    	try {
    		txtUsername.sendKeys(strUserName);
    	}
    	catch (Exception e){
    		throw e;
    	}
    	
    }

    /**
	 * setPassword : Sets the password
	 * @param password - Password
	 * @throws Exception 
	 */
    public void setPassword(String password){
    	
    	try {
    		txtPassword.sendKeys(password);
    	}
    	catch (Exception e){
    		throw e;
    	}
    	
    }

    /**
	 * clickLogin : Clicks login button
	 * @throws Exception 
	 */
    public void clickLogin(){
    	
    	try {
    		btnLogin.click();
    	}
    	catch (Exception e){
    		throw e;
    	}
    }

    /**
	 * setUserName : Sets the user name
	 * @param userName - Name of the user
	 * @param password - Password of the user
	 * @param vaultName - Test vault to select for login
	 * @return Instance of home page
	 * @throws Exception 
	 */
    public void loginToMfiles(String userName,String strPasword,String vaultName){
    	
        this.setUserName(userName); //Fill user name
        this.setPassword(strPasword); //Fill password
        this.clickLogin(); //Click Login button
        this.selectVault(vaultName);
    }
}
