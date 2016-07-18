package MFMA.Factories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import MFMA.Screens.VaultHomeScreen;
import genericLibrary.Utils;

public class VaultHome extends VaultHomeScreen
{
	RemoteWebDriver driver;
	private boolean isPageLoaded = false;
	
	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : RemoteWebdriver
	 */
	public VaultHome(RemoteWebDriver driver){

    	this.driver = driver;
		//ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 2);
		PageFactory.initElements(driver, this);
    }
	
	final public void isLoaded(){
    	if (!isPageLoaded) {
			Assert.fail();
		}
		try {
			isPageLoaded = Utils.waitForElement(driver, vaultTitle);
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
	 * vaultHome : click CreateNewObject
	 * @param Null
	 * @throws Exception 
	 */
    public void clickCreateNewObject() {
    	Utils.waitForElement(driver, btnCreateObject);
    	try {    	 
	    	
    		btnCreateObject.click();
    	}
    	catch (Exception e){
    		throw e;
    	}

    } //End clickCreateNewObject
        
    /**
	 * vaultHome : select ObjectType
	 * @param Null
	 * @throws Exception 
	 */
    public void selectObjectType(String objectTypeName) {
    	Utils.waitForElement(driver, objectTypeList);
    	try {    	 
	    	
    		int count = 0;
    		int listCount = objectListItems.size();
	    	 
	    	while(count < listCount){		
	    		if(objectListItems.get(count).getText().contentEquals(objectTypeName)){
	    			objectListItems.get(count).click();
	    			break;
	    		} //End If
	    		count++;
	    	} //End While
	    	
	    	if(count >= listCount)	{
	    		Utils.swipe(driver, objectListItems.get(listCount-1), objectListItems.get(0), 3000);
	    		Utils.waitForElement(driver, objectTypeList);
	    		selectObjectType(objectTypeName);
	    	}
	    	
    	}
    	catch (Exception e){
    		throw e;
    	}

    } //End selectObjectType
    
    /**
	 * vaultHome : select ClassName
	 * @param Null
	 * @throws Exception 
	 */
    public void selectClassName(String ClassName) {
    	Utils.waitForElement(driver, objectTypeList);
    	try {    	 
	    	
    		int count = 0;
    		int listCount = objectListItems.size();
	    	 
	    	while(count < listCount){		
	    		if(objectListItems.get(count).getText().contentEquals(ClassName)){
	    			objectListItems.get(count).click();
	    			break;
	    		} //End If
	    		count++;
	    	} //End While
	    	
	    	if(count >= listCount)	{
	    		Utils.swipe(driver, objectListItems.get(listCount-1), objectListItems.get(0), 3000);
	    		Utils.waitForElement(driver, objectTypeList);
	    		selectObjectType(ClassName);
	    	}
	    	
    	}
    	catch (Exception e){
    		throw e;
    	}

    } //End selectClassName
}
