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
import genericLibrary.Utils;

public class Vault extends VaultScreen
{
	RemoteWebDriver driver;
	private boolean isPageLoaded = false;
	
	public Vault(RemoteWebDriver driver){

    	this.driver = driver;
		//ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 2);
		PageFactory.initElements(driver, this);
    }
	
	final public void isLoaded(){
    	if (!isPageLoaded) {
			Assert.fail();
		}
		try {
			isPageLoaded = Utils.waitForElement(driver, vaultList);
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
	 * selectVault : selects the vault
	 * @param vaultName Name of the vault
	 * @throws Exception 
	 */
    public void selectVault(String vaultName) {
    	Utils.waitForElement(driver, vaultList);
    	try {    	 
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
    
    public VaultHome navigateToVaultHomeScreen() throws Exception
    {
    	Log.event("Navigated to Vault Home Screen!");
		return (VaultHome) new VaultHome(driver).get();
    }
}
