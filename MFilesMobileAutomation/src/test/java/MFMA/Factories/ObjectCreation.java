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
import genericLibrary.StopWatch;
import genericLibrary.Utils;

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
		//ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 2);
		PageFactory.initElements(driver, this);
    }
	
	final public void isLoaded(){
    	if (!isPageLoaded) {
    		Log.fail("Object Creation screen not loaded");
		}
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
	
	@SuppressWarnings("rawtypes")
	public void populatingValues(LinkedHashMap <String, String> objectProperties) throws Exception
	{
		final long startTime = StopWatch.startTime();
		
		Set objectPropertiesSet = objectProperties.entrySet();
		Iterator objectPropertiesIterator = objectPropertiesSet.iterator();
		
		while (objectPropertiesIterator.hasNext()) {

			Map.Entry mapEntry = (Map.Entry) objectPropertiesIterator.next();
			String[] PropertyKeyWithValueType = mapEntry.getKey().toString().split("_");
			String value = mapEntry.getValue().toString();
			
			switch (PropertyKeyWithValueType[0].toLowerCase())
			{
				case "text":
					this.setTextValue(PropertyKeyWithValueType[1], value);
					break;
					
				case "list":
					this.setListValue(PropertyKeyWithValueType[1], value);
					break;
					
				case "date":
					this.setDateValue(PropertyKeyWithValueType[1], value);
					break;
				
				default:
					Log.trace("Invalid data" , StopWatch.elapsedTime(startTime));
					break;
			}
		}
	}
	
	public void setTextValue(String propertyName, String propertyValue)
	{
		Utils.waitForElement(driver, listProperties);
		int count = 0;
		int listCount = txtPropertyKeys.size();
		//String beforeSwipe = null;
		//String afterSwipe = null;
    	while(count < listCount){		
    		if(txtPropertyKeys.get(count).getText().contentEquals(propertyName)){
    			txtPropertyValues.get(count).sendKeys(propertyValue);
    			driver.navigate().back();
    			break;
    		} //End If
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
        		setTextValue(propertyName,propertyValue);
        	}
    	//} while(!beforeSwipe.equalsIgnoreCase(afterSwipe));
    	
	}
	
	public void setListValue(String propertyName, String propertyValue) throws Exception
	{
		Utils.waitForElement(driver, listProperties);
		int count = 0;
		int listCount = txtPropertyKeys.size();
    	 
    	while(count < listCount){		
    		if(txtPropertyKeys.get(count).getText().contentEquals(propertyName)){
    			txtPropertyValues.get(count).click();
    			this.selectListPropertyValue(propertyValue);
    			break;
    		} //End If
    		count++;
    	} //End While
    	
    	if(count >= listCount)	{
    		Utils.swipe(driver, txtPropertyKeys.get(listCount-1), txtPropertyKeys.get(0), 3000);
    		Utils.waitForElement(driver, listProperties);
    		setListValue(propertyName,propertyValue);
    	}
	}
	
	public void selectListPropertyValue(String propertyValue) throws Exception
    {
    	if(txtAddValueAlertTitle.isDisplayed())
    	{
	    	Utils.waitForElement(driver, listAddValueAlertValues);
			int count = 0;
			int listCount = txtAddValueAlertValues.size();
	    	 
	    	while(count < listCount){		
	    		if(txtAddValueAlertValues.get(count).getText().contentEquals(propertyValue)){
	    			txtAddValueAlertValues.get(count).click();
	    			break;
	    		} //End If
	    		count++;
	    	} //End While
	    	
	    	if(count >= listCount)	{
	    		Utils.swipe(driver, txtAddValueAlertValues.get(listCount-1), txtAddValueAlertValues.get(0), 3000);
	    		Utils.waitForElement(driver, listAddValueAlertValues);
	    		selectListPropertyValue(propertyValue);
	    	}
    	}
    } //End selectClassName
	
	public void setDateValue(String propertyName, String propertyValue) throws Exception
	{
		Utils.waitForElement(driver, listProperties);
		int count = 0;
		int listCount = txtPropertyKeys.size();
    	 
    	while(count < listCount){		
    		if(txtPropertyKeys.get(count).getText().contentEquals(propertyName)){
    			txtPropertyValues.get(count).click();
    			this.selectDatePropertyValue(propertyValue);
    			break;
    		} //End If
    		count++;
    	} //End While
    	
    	if(count >= listCount)	{
    		Utils.swipe(driver, txtPropertyKeys.get(listCount-1), txtPropertyKeys.get(0), 3000);
    		Utils.waitForElement(driver, listProperties);
    		setDateValue(propertyName,propertyValue);
    	}
	}
	
	public void selectDatePropertyValue(String dateValue) throws Exception
	{
		String[] dateValues = dateValue.split("_");
		
		if(!dateNumberSelectedText.get(0).getText().equalsIgnoreCase(dateValues[0]))
		{
			this.setMonth(dateValues[0]);
		}	
		if(!dateNumberSelectedText.get(1).getText().equalsIgnoreCase(dateValues[1]))
		{
			this.setDate(dateValues[1]);
		}
		if(!dateNumberSelectedText.get(2).getText().equalsIgnoreCase(dateValues[2]))
		{
			this.setYear(dateValues[2]);
		}
		btnSetDate.click();
	}
	
	public void setMonth(String month)
	{
		do
		{
			Utils.swipe(driver, dateNumberSelectedText.get(0), dateNumberButtons.get(0), 2000);
			
		}while(!dateNumberSelectedText.get(0).getText().equalsIgnoreCase(month));
	}
	
	public void setDate(String date)
	{
		do
		{
			Utils.swipe(driver, dateNumberSelectedText.get(1), dateNumberButtons.get(2), 2000);
			
		}while(!dateNumberSelectedText.get(1).getText().equalsIgnoreCase(date));
	}
	
	public void setYear(String year)
	{
		do
		{
			Utils.swipe(driver, dateNumberSelectedText.get(2), dateNumberButtons.get(4), 2000);
			
		}while(!dateNumberSelectedText.get(2).getText().equalsIgnoreCase(year));
	}
	
	public void tapOnSaveButton() throws Exception
	{
		btnSave.click();
	}
	
}
