package MFMA.Factories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import MFMA.Screens.VaultScreen;
import genericLibrary.Log;
import genericLibrary.MobileDriverUtils;
import genericLibrary.Utils;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Vault extends VaultScreen
{
	RemoteWebDriver driver;
	private boolean isPageLoaded = false;
	
	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : RemoteWebdriver
	 */
	public Vault(RemoteWebDriver driver){

    	this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
	
	final public void isLoaded(){
		try {
			isPageLoaded = Utils.waitForElement(driver, vaultList);
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
	
	/**
	 * Verifying whether page loaded properly
	 * @return true if page loaded
	 */
	public Boolean pageLoaded()
	{
		isLoaded();
		return isPageLoaded;
	}
	/**
	 * selectVault : selects the vault
	 * @param vaultName Name of the vault
	 * @throws Exception 
	 */
    public void selectVault(String vaultName) throws Exception
    {
    	Utils.waitForElement(driver, vaultList);   	 
	    int count = 0; 
    	while(count < vaults.size()){
    		
    		if(MobileDriverUtils.platform.equalsIgnoreCase("Android"))
    		{
    			if(vaults.get(count).getText().contentEquals(vaultName)){
        			vaults.get(count).click();
        			break;
    			}
    		}
    		else
    		{
    			if(vaults.get(count).findElement(By.className("UIAStaticText")).getText().contentEquals(vaultName)){
        			vaults.get(count).click();
        			break;
    			}
    		} //End If

    		count++;
    	} //End While
    } //End selectVault
    
    /**
	 * Navigate to Vault Home Screen
	 * @return instance of VaultHome Screen
	 * @throws Exception 
	 */
    public VaultHome navigateToVaultHomeScreen() throws Exception
    {
    	Log.event("Navigated to Vault Home Screen!");
		return (VaultHome) new VaultHome(driver).get();
    }
}
