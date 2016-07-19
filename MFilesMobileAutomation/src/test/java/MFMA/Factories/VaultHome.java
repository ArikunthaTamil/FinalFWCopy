package MFMA.Factories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import MFMA.Screens.VaultHomeScreen;
import genericLibrary.Log;
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
    	//if (!isPageLoaded) {
    	//	Log.fail("VaultHome not loaded");
		//}
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
	
	public Boolean pageLoaded()
	{
		isLoaded();
		return isPageLoaded;
	}

	/**
	 * vaultHome : click CreateNewObject
	 * @param Null
	 * @throws Exception 
	 */
    public void clickCreateNewObject() throws Exception
    {
    	Utils.waitForElement(driver, btnCreateObject);   	 
	    btnCreateObject.click();
    } //End clickCreateNewObject
        
    /**
	 * vaultHome : select ObjectType
	 * @param Null
	 * @throws Exception 
	 */
    public void selectObjectType(String objectTypeName) throws Exception
    {
    	Utils.waitForElement(driver, objectTypeList);
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
    } //End selectObjectType
      
    /**
	 * vaultHome : select ClassName
	 * @param className
	 * @throws Exception 
	 */
    public void selectClassName(String className) throws Exception
    {
    	if(Utils.doesElementExist(driver, txtSelectClassTitle))
    	{
    		if(txtSelectClassTitle.getText().equalsIgnoreCase("Select class"))
    		{
    			Utils.waitForElement(driver, objectTypeList);
    			int count = 0;
    			int listCount = objectListItems.size();
    	    	 
    	    	while(count < listCount){		
    	    		if(objectListItems.get(count).getText().contentEquals(className)){
    	    			objectListItems.get(count).click();
    	    			break;
    	    		} //End If
    	    		count++;
    	    	} //End While
    	    	
    	    	if(count >= listCount)	{
    	    		Utils.swipe(driver, objectListItems.get(listCount-1), objectListItems.get(0), 3000);
    	    		Utils.waitForElement(driver, objectTypeList);
    	    		selectClassName(className);
    	    	}	
    		}
    	}
    } //End selectClassName
    
    /**
	 * vaultHome : select ClassName
	 * @param className
	 * @throws Exception 
	 */
    public void selectTemplate(String template) throws Exception
    {
    	if(Utils.doesElementExist(driver, txtSelectClassTitle))
    	{
    		if(txtSelectClassTitle.getText().equalsIgnoreCase("Select Template"))
    		{
    			Utils.waitForElement(driver, objectTypeList);
    			int count = 0;
    			int listCount = objectListItems.size();
    	    	 
    	    	while(count < listCount){		
    	    		if(objectListItems.get(count).getText().contentEquals(template)){
    	    			objectListItems.get(count).click();
    	    			break;
    	    		} //End If
    	    		count++;
    	    	} //End While
    	    	
    	    	if(count >= listCount)	{
    	    		Utils.swipe(driver, objectListItems.get(listCount-1), objectListItems.get(0), 3000);
    	    		Utils.waitForElement(driver, objectTypeList);
    	    		selectTemplate(template);
    	    	}
    		}	
    	}
    } //End selectClassName
    
    /**
	 * Navigate to Object Creation Screen
	 * @return instance of ObjectCreation Screen
	 * @throws Exception 
	 */
    public ObjectCreation navigateToObjectCreationScreen() throws Exception
    {
    	Log.event("Navigated to Object Creation Screen!");
		return (ObjectCreation) new ObjectCreation(driver).get();
    }
}
