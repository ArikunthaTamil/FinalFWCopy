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
import genericLibrary.MobileDriverUtils;
import genericLibrary.Utils;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

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
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
	
	final public void isLoaded(){
		try {
			isPageLoaded = Utils.waitForElement(driver, btnCreateObject);
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
    	Utils.waitForElement(driver, objectListItems.get(1));
		int count = 0;
		int listCount = objectListItems.size();
    	 
    	while(count < listCount){
    		
    		if(MobileDriverUtils.platform.equalsIgnoreCase("Android"))
    		{
    			if(objectListItems.get(count).getText().contentEquals(objectTypeName)){
    				objectListItems.get(count).click();
    				break;
    			} //End If
    		}
    		else
    		{	
    			if(objectListItems.get(count).findElement(By.className("UIAStaticText")).getText().contentEquals(objectTypeName)){
				objectListItems.get(count).click();
				break;
				} //End If		
    		}
    		count++;
    	} //End While
    	
    	if(count >= listCount)	{
    		Utils.swipe(driver, objectListItems.get(listCount-1), objectListItems.get(0), 3000);
    		Utils.waitForElement(driver, objectListItems.get(1));
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
    			Utils.waitForElement(driver, objectListItems.get(1));
    			int count = 0;
    			int listCount = objectListItems.size();
    	    	 
    	    	while(count < listCount){		
    	    		if(MobileDriverUtils.platform.equalsIgnoreCase("Android"))
    	    		{
    	    			if(objectListItems.get(count).getText().contentEquals(className)){
    	    				objectListItems.get(count).click();
    	    				break;
    	    			} //End If
    	    		}
    	    		else
    	    		{	
    	    			if(objectListItems.get(count).findElement(By.className("UIAStaticText")).getText().contentEquals(className)){
    					objectListItems.get(count).click();
    					break;
    					} //End If		
    	    		}
    	    		count++;
    	    	} //End While
    	    	
    	    	if(count >= listCount)	{
    	    		Utils.swipe(driver, objectListItems.get(listCount-1), objectListItems.get(0), 3000);
    	    		Utils.waitForElement(driver, objectListItems.get(1));
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
    			Utils.waitForElement(driver, objectListItems.get(1));
    			int count = 0;
    			int listCount = objectListItems.size();
    	    	 
    	    	while(count < listCount){		
    	    		if(MobileDriverUtils.platform.equalsIgnoreCase("Android"))
    	    		{
    	    			if(objectListItems.get(count).getText().contentEquals(template)){
    	    				objectListItems.get(count).click();
    	    				break;
    	    			} //End If
    	    		}
    	    		else
    	    		{	
    	    			if(objectListItems.get(count).findElement(By.className("UIAStaticText")).getText().contentEquals(template)){
    					objectListItems.get(count).click();
    					break;
    					} //End If		
    	    		}
    	    		count++;
    	    	} //End While
    	    	
    	    	if(count >= listCount)	{
    	    		Utils.swipe(driver, objectListItems.get(listCount-1), objectListItems.get(0), 3000);
    	    		Utils.waitForElement(driver, objectListItems.get(1));
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
