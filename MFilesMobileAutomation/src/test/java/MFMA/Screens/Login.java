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

public class Login extends LoadableComponent <Login>{
	
	private boolean isPageLoaded = false;
	private final RemoteWebDriver driver;
	
    @FindBy(id="login_username")
    WebElement txtUsername;
    
    @FindBy(id="login_password")
    WebElement txtPassword;

    @FindBy(id="barone")
    WebElement titleText;

    @FindBy(id="login_log_in")
    WebElement btnLogin;

    public Login(RemoteWebDriver driver){

    	this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 2);
		PageFactory.initElements(finder, this);
    }
    
    @Override
    final public void isLoaded(){
    	if (!isPageLoaded) {
			Assert.fail();
		}
		try {
			isPageLoaded = Utils.waitForElement(driver, txtPassword);
		}
		catch (TimeoutException e) {
			throw e;
		}
	}
	
	final protected void load(){
		try {
			//Utils.fluentWait(driver);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		isPageLoaded = true;
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
	 * selectVault : selects the vault
	 * @param vaultName Name of the vault
	 * @throws Exception 
	 */
    public void selectVault(String vaultName) {
    	
    	try {
    	
	    	WebElement vaultList = this.driver.findElement(By.id("vault_selection_list"));
	    	List<WebElement> vaults = vaultList.findElements(By.id("component_string_list_text"));
	    	 
	    	int count = 0;
	    	 
	    	while(count < vaults.size()){		
	    		if(vaults.get(count).getText().contentEquals(vaultName)){
	    			vaults.get(count).click();
	    			break;
	    		} //End If
	    		count++;
	    	} //End While
    	}
    	catch (Exception e){
    		throw e;
    	}

    } //End selectVault

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
        driver.navigate().back();
        this.clickLogin(); //Click Login button
        this.selectVault(vaultName);
    }
}
