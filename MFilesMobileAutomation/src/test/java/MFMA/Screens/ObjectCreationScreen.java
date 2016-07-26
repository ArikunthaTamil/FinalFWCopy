package MFMA.Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ObjectCreationScreen extends LoadableComponent<ObjectCreationScreen>
{
	@AndroidFindBy(id="actionbar_title")
	@iOSFindBy(xpath="//UIATableCell[2]/UIAStaticText[2]")
    protected WebElement objectCreationTitle;
    
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Navigate up']")
    protected WebElement btnMenu;
    
	@AndroidFindBy(id="menu_item_edit_save")
	@iOSFindBy(xpath="//UIAButton[@label='Create']")
    protected WebElement btnSave;
    
	@AndroidFindBy(id="menu_item_edit_add")
    protected WebElement btnCreateObject;
    
	@AndroidFindBy(id="menu_item_edit_cancel")
    protected WebElement btnCancelObject;
    
	@AndroidFindBy(id="component_object_title_text")
    protected WebElement txtObjectTitle;
    
	@AndroidFindBy(id="fragment_properties_list")
	@iOSFindBy(xpath="//UIATableView")
    protected WebElement listProperties;
    
	@AndroidFindBy(id="component_object_title_class")
    protected WebElement txtObjectClassTitle;
    
	@AndroidFindBy(id="component_file_controls_add_file")
	@iOSFindBy(xpath="//UIATableCell[2]/UIAButton")
    protected WebElement btnAddFile;
    
	@AndroidFindBy(id="component_property_item_key")
    protected List<WebElement> txtPropertyKeys;
	
	@iOSFindBy(xpath="//UIATableCell")
    protected List<WebElement> txtProperties;
	
	@iOSFindBy(xpath="//UIAButton[@label='Properties']")
    protected WebElement btnProperty;
	
	@iOSFindBy(xpath="//UIATextView[1]")
    protected WebElement txtProperty;
	
	@iOSFindBy(xpath="//UIAPickerWheel[1]")
    protected WebElement txtDatePropertyMonth;
	
	@iOSFindBy(xpath="//UIAPickerWheel[2]")
    protected WebElement txtDatePropertyDay;
	
	@iOSFindBy(xpath="//UIAPickerWheel[3]")
    protected WebElement txtDatePropertyYear;
	
	@AndroidFindBy(id="component_property_item_value_edit")
    protected List<WebElement> txtPropertyValues;
    
	@AndroidFindBy(id="property_lookup_value_add_button")
    protected WebElement btnAddPropertyValue;
    
	@AndroidFindBy(id="component_property_item_button_left")
    protected WebElement btnPropertyRemove;
    
	@AndroidFindBy(id="property_lookup_value_remove")
    protected WebElement btnPropertyValueRemove;
    
	@AndroidFindBy(id="property_lookup_value_row")
    protected WebElement btnListAddPropertyValue;
    
	@AndroidFindBy(id="alertTitle")
    protected WebElement txtAddPropertyAlertTitle;
    
	@AndroidFindBy(id="select_dialog_listview")
    protected WebElement listAddPropertyAlertValues;
    
	@AndroidFindBy(id="text1")
    protected List<WebElement> checkAddPropertyAlertValues;
    
	@AndroidFindBy(id="button2")
    protected WebElement btnAddPropertyAlertCancel;
    
	@AndroidFindBy(id="button1")
    protected WebElement btnAddPropertyAlertOk;
    
	@AndroidFindBy(id="title")
    protected WebElement txtAddValueAlertTitle;
    
	@AndroidFindBy(id="component_progressbar_list_filter")
    protected WebElement txtAddValueAlertFilter;
    
	@AndroidFindBy(id="list")
	@iOSFindBy(xpath="//UIATableView")
    protected WebElement listAddValueAlertValues;
    
	@AndroidFindBy(id="component_string_list_text")
	@iOSFindBy(xpath="//UIATableCell")
    protected List<WebElement> txtAddValueAlertValues;
    
	@AndroidFindBy(id="datePicker")
    protected WebElement datePicker;
    
	@AndroidFindBy(className="android.widget.NumberPicker")
    protected List<WebElement> dateNumberPickers;
    
	@AndroidFindBy(className="android.widget.Button")
    protected List<WebElement> dateNumberButtons;
    
	@AndroidFindBy(id="numberpicker_input")
    protected List<WebElement> dateNumberSelectedText;
    
	@AndroidFindBy(id="button1")
	@iOSFindBy(xpath="//UIAButton[@label='mfiles icon done']")
    protected WebElement btnSetDate;
    
	@AndroidFindBy(id="alertTitle")
    protected WebElement txtSelectedDate;
    
    @Override
	protected void isLoaded() throws Error {}

	@Override
	protected void load() {}

}
