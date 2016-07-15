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
    
    @FindBy(id="menu_create_new_object")
    protected WebElement btnCreateObject;
    
    @FindBy(className="android.widget.ImageButton")
    protected List<WebElement> imageElements;
    protected WebElement btnMenu = imageElements.get(0);
    protected WebElement extraOptions = imageElements.get(2);
    
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

}