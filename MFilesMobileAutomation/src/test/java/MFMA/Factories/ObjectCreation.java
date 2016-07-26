package MFMA.Factories;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import MFMA.Screens.ObjectCreationScreen;
import genericLibrary.Log;
import genericLibrary.MobileDriverUtils;
import genericLibrary.StopWatch;
import genericLibrary.Utils;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ObjectCreation extends ObjectCreationScreen 
{
	RemoteWebDriver driver;
	private boolean isPageLoaded = false;
	
	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : RemoteWebdriver
	 */
	public ObjectCreation(RemoteWebDriver driver){

    	this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
	
	final public void isLoaded(){
		try {
			isPageLoaded = Utils.waitForElement(driver, btnAddFile);
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
	 * Populating property values for the current object
	 * @Param objectProperties: HashMap with property Name and values
	 */
	@SuppressWarnings("rawtypes")
	public void populatingValuesWithoutType(LinkedHashMap <String, String> objectProperties) throws Exception
	{
		final long startTime = StopWatch.startTime();
		
		Set objectPropertiesSet = objectProperties.entrySet();
		Iterator objectPropertiesIterator = objectPropertiesSet.iterator();
		
		while (objectPropertiesIterator.hasNext()) {

			Map.Entry mapEntry = (Map.Entry) objectPropertiesIterator.next();
			String propertyKey = mapEntry.getKey().toString();
			String propertyValue = mapEntry.getValue().toString();
			this.setPropertyValue(propertyKey,propertyValue);
			
		}
	}
	
	/**
	 * Sets property value for a property
	 * @Param propertyName: Name of the property
	 * @Param propertyValue: Value of the property
	 */
	public void setPropertyValue(String propertyName, String propertyValue) throws Exception
	{
		WebElement text = null;
		WebElement list = null;
		WebElement date = null;
		int count,listCount;
		Boolean propertyMatch = false;
		Utils.waitForElement(driver, listProperties);
		if(MobileDriverUtils.platform.equalsIgnoreCase("Android"))
		{
			count = 0;
			listCount = txtPropertyKeys.size();
		}
		else
		{
			count = 2;
			listCount = txtProperties.size();
		}
    	while(count < listCount)
    	{
    		if(MobileDriverUtils.platform.equalsIgnoreCase("Android"))
    		{
    			if(txtPropertyKeys.get(count).getText().contains(propertyName))
        		{
        			//Boolean text = Utils.doesElementExist(driver, txtPropertyValues.get(count).findElement(By.className("android.widget.EditText")));
        			try
        			{
        				text = txtPropertyValues.get(count).findElement(By.className("android.widget.EditText"));
        				if(text!=null)
            			{
            				txtPropertyValues.get(count).click();
            				txtPropertyValues.get(count).sendKeys(propertyValue);
            				driver.navigate().back();
            				break;
            			}
        			}
        			catch(Exception e) { System.out.println(e);}	
        			try
        			{
        				list = txtPropertyValues.get(count).findElement(By.id("property_lookup_value_add_button"));
        				if(list!=null)
            			{
            				txtPropertyValues.get(count).findElement(By.id("property_lookup_value_add_button")).click();
            				this.selectListPropertyValue(propertyValue);
            				break;
            			}
        			}
        			catch(Exception e) { System.out.println(e);}	
        			try
        			{
        				date = txtPropertyValues.get(count).findElement(By.id("property_datetime_input"));
        				if(date!=null)
            			{
            				txtPropertyValues.get(count).click();
            				this.selectDatePropertyValue(propertyValue);
                			break;
            			}
        				else
            			{
            				Log.event("Not Matched with any property type");
            				break;
            			}
        			}
        			catch(Exception e) { System.out.println(e);}	
        			//Boolean list = Utils.doesElementExist(driver, txtPropertyValues.get(count).findElements(By.id("property_lookup_value_add_button")));
        		} //End If
    		}
    		else
    		{
    			try
    			{
    				if(txtProperties.get(count).findElement(By.className("UIAStaticText")).getText().contains(propertyName.toUpperCase()))
            		{
            			//Boolean text = Utils.doesElementExist(driver, txtPropertyValues.get(count).findElement(By.className("android.widget.EditText")));
            			try
            			{
            				text = txtProperties.get(count).findElement(By.className("UIATextView"));
            				if(text!=null)
                			{
            					txtProperties.get(count).findElement(By.className("UIATextView")).click();
            					if(Utils.doesElementExist(driver, btnProperty))
            					{
            						txtProperty.clear();
            						txtProperty.sendKeys(propertyValue);
            						btnProperty.click();
            					}
            					else
            					{
            						txtProperties.get(count).findElement(By.className("UIATextView")).clear();
            						txtProperties.get(count).findElement(By.className("UIATextView")).sendKeys(propertyValue + "\n");
            					}
            					
                				break;
                			}
            			}
            			catch(Exception e) { System.out.println(e);}	
            			try
            			{
            				list = txtProperties.get(count).findElement(By.className("UIATableView"));
            				if(list!=null)
                			{
            					txtProperties.get(count).findElement(By.className("UIATableView")).click();
            					//btnProperty.click();
            					//count++;
                				this.selectListPropertyValue(propertyValue);
                				break;
                			}
            			}
            			catch(Exception e) { System.out.println(e);}	
            			try
            			{
            				date = txtProperties.get(count).findElement(By.className("UIAButton"));
            				if(date!=null)
                			{
            					txtProperties.get(count).findElement(By.className("UIAButton")).click();
                				this.selectDatePropertyValue(propertyValue);
                    			break;
                			}
            				else
                			{
                				Log.event("Not Matched with any property type");
                				break;
                			}
            			}
            			catch(Exception e) { System.out.println(e);}		
            		}
    			}
    			catch(Exception e) { System.out.println(e);}
        	} 
    		count++;
    	} //End While
    	//do
    	//{
		if(count >= listCount)	
    	{
    		//beforeSwipe = txtPropertyKeys.get(listCount-1).getText();
    		Utils.swipe(driver, txtPropertyKeys.get(listCount-1), txtPropertyKeys.get(0), 3000);
    		//afterSwipe = txtPropertyKeys.get(listCount-1).getText();
    		Utils.waitForElement(driver, listProperties);
    		setPropertyValue(propertyName,propertyValue);
    	}		
	}
	
	/**
	 * Sets list value for the list property type
	 * @Param propertyValue: Value of the property
	 */
	public void selectListPropertyValue(String propertyValue) throws Exception
    {
    	Utils.waitForElement(driver, listAddValueAlertValues);
		int count = 0;
		int listCount = txtAddValueAlertValues.size();
    	 
    	while(count < listCount){
    		if(MobileDriverUtils.platform.equalsIgnoreCase("Android"))
    		{
    			if(txtAddValueAlertValues.get(count).getText().contentEquals(propertyValue)){
	    			txtAddValueAlertValues.get(count).click();
	    			break;
    			}
    		}
    		else
    		{
    			if(txtAddValueAlertValues.get(count).findElement(By.className("UIAStaticText")).getText().contentEquals(propertyValue)){
	    			txtAddValueAlertValues.get(count).click();
	    			break;
    			}    		
    		} //End If
    		count++;
    	} //End While
    	
    	if(count >= listCount)	{
    		Log.event("Entered Swipe Block");
    		Utils.swipe(driver, txtAddValueAlertValues.get(listCount-1), txtAddValueAlertValues.get(0), 3000);
    		Utils.waitForElement(driver, listAddValueAlertValues);
    		selectListPropertyValue(propertyValue);
    	}
    } //End selectClassName
	
	/**
	 * Sets date value for the date property type
	 * @Param propertyValue: Value of the property
	 */
	public void selectDatePropertyValue(String dateValue) throws Exception
	{
		String[] dateValues = dateValue.split("_");
		
		if(MobileDriverUtils.platform.equalsIgnoreCase("Android"))
		{
			while(!dateNumberSelectedText.get(0).getText().equalsIgnoreCase(dateValues[0].substring(0, 3)))
			{
				Utils.swipe(driver, dateNumberSelectedText.get(0), dateNumberButtons.get(0), 1000);
			}
			
			while(!dateNumberSelectedText.get(1).getText().equalsIgnoreCase(dateValues[1]))
			{
				Utils.swipe(driver, dateNumberSelectedText.get(1), dateNumberButtons.get(2), 1000);
			}
			
			while(!dateNumberSelectedText.get(2).getText().equalsIgnoreCase(dateValues[2]))
			{
				Utils.swipe(driver, dateNumberSelectedText.get(2), dateNumberButtons.get(4), 1000);
			}
		}
		else
		{
			txtDatePropertyMonth.sendKeys(dateValues[0]);
			txtDatePropertyDay.sendKeys(dateValues[1]);
			txtDatePropertyYear.sendKeys(dateValues[2]);
		}
		btnSetDate.click();
	}
	
	public void tapOnSaveButton() throws Exception
	{
		btnSave.click();
	}
	
}
