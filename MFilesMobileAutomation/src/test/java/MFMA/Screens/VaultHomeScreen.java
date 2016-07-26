package MFMA.Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class VaultHomeScreen extends LoadableComponent<VaultHomeScreen>
{
	@AndroidFindBy(id="home_vault_name")
	@iOSFindBy(xpath="//UIAStaticText[1]")
    protected WebElement vaultTitle;
    
	@AndroidFindBy(id="component_home_item_text")
	@iOSFindBy(xpath="//UIACollectionCell")
    protected List<WebElement> vaultItems;
    
	@AndroidFindBy(id="component_string_list_text")
	@iOSFindBy(xpath="//UIATableCell")
    protected List<WebElement> objectListItems;
    
	@AndroidFindBy(id="menu_create_new_object")
	@iOSFindBy(xpath="//UIAButton[@label='Add']")
    protected WebElement btnCreateObject;

	@iOSFindBy(xpath="//UIAButton[@label='Cancel']")
    protected WebElement btnCancel;
	
	@AndroidFindBy(className="android.widget.ListView")
    protected WebElement objectTypeList;
    
	@AndroidFindBy(id="title")
	@iOSFindBy(xpath="//UIAStaticText[1]")
    protected WebElement txtSelectClassTitle;
    
	@AndroidFindBy(id="component_object_list_section_title")
    protected List<WebElement> sectionListItems;
    
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Navigate up']")
	@iOSFindBy(xpath="//UIAButton[1]")
    protected WebElement btnMenu;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	@iOSFindBy(xpath="//UIANavigationBar/UIAImage[2]")
    protected WebElement btnMoreOptions;
    
	@Override
	protected void isLoaded() throws Error {}

	@Override
	protected void load() {}

}
