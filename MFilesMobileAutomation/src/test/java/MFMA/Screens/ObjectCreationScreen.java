package MFMA.Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

public class ObjectCreationScreen extends LoadableComponent<ObjectCreationScreen>
{
	@FindBy(id="actionbar_title")
    protected WebElement objectCreationTitle;
    
    @FindBy(xpath="//android.widget.ImageButton[@content-desc='Navigate up']")
    protected WebElement btnMenu;
    
    @FindBy(id="menu_item_edit_save")
    protected WebElement btnSave;
    
    @FindBy(id="menu_item_edit_add")
    protected WebElement btnCreateObject;
    
    @FindBy(id="menu_item_edit_cancel")
    protected WebElement btnCancelObject;
    
    @FindBy(id="component_object_title_text")
    protected WebElement txtObjectTitle;
    
    @FindBy(id="fragment_properties_list")
    protected WebElement listProperties;
    
    @FindBy(id="component_object_title_class")
    protected WebElement txtObjectClassTitle;
    
    @FindBy(id="component_file_controls_add_file")
    protected WebElement btnAddFile;
    
    @FindBy(id="component_property_item_key")
    protected List<WebElement> txtPropertyKeys;
    
    @FindBy(id="component_property_item_value_edit")
    protected List<WebElement> txtPropertyValues;
    
    @FindBy(id="property_lookup_value_add_button")
    protected WebElement btnAddPropertyValue;
    
    @FindBy(id="component_property_item_button_left")
    protected WebElement btnPropertyRemove;
    
    @FindBy(id="property_lookup_value_remove")
    protected WebElement btnPropertyValueRemove;
    
    @FindBy(id="property_lookup_value_row")
    protected WebElement btnListAddPropertyValue;
    
    @FindBy(id="alertTitle")
    protected WebElement txtAddPropertyAlertTitle;
    
    @FindBy(id="select_dialog_listview")
    protected WebElement listAddPropertyAlertValues;
    
    @FindBy(id="text1")
    protected List<WebElement> checkAddPropertyAlertValues;
    
    @FindBy(id="button2")
    protected WebElement btnAddPropertyAlertCancel;
    
    @FindBy(id="button1")
    protected WebElement btnAddPropertyAlertOk;
    
    @FindBy(id="title")
    protected WebElement txtAddValueAlertTitle;
    
    @FindBy(id="component_progressbar_list_filter")
    protected WebElement txtAddValueAlertFilter;
    
    @FindBy(id="list")
    protected WebElement listAddValueAlertValues;
    
    @FindBy(id="component_string_list_text")
    protected List<WebElement> txtAddValueAlertValues;
    
    @FindBy(id="datePicker")
    protected WebElement datePicker;
    
    @FindBy(className="android.widget.NumberPicker")
    protected List<WebElement> dateNumberPickers;
    
    @FindBy(className="android.widget.Button")
    protected List<WebElement> dateNumberButtons;
    
    @FindBy(id="numberpicker_input")
    protected List<WebElement> dateNumberSelectedText;
    
    @FindBy(id="button1")
    protected WebElement btnSetDate;
    
    @FindBy(id="alertTitle")
    protected WebElement txtSelectedDate;
    
    @Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

}
