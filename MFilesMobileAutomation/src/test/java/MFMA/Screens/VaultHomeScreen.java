package MFMA.Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

public class VaultHomeScreen extends LoadableComponent<VaultHomeScreen>
{
	@FindBy(id="home_vault_name")
    protected WebElement vaultTitle;
    
    @FindBy(id="component_home_item_text")
    protected List<WebElement> vaultItems;
    
    @FindBy(id="component_string_list_text")
    protected List<WebElement> objectListItems;
    
    @FindBy(id="menu_create_new_object")
    protected WebElement btnCreateObject;
    
    @FindBy(className="android.widget.ListView")
    protected WebElement objectTypeList;
    
    @FindBy(id="title")
    protected WebElement txtSelectClassTitle;
    
    @FindBy(id="component_object_list_section_title")
    protected List<WebElement> sectionListItems;
    
    @FindBy(xpath="//android.widget.ImageButton[@content-desc='Navigate up']")
    protected WebElement btnMenu;

    @FindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
    protected WebElement btnMoreOptions;
    
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

}
