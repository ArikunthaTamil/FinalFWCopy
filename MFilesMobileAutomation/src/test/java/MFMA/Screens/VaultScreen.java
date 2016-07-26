package MFMA.Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class VaultScreen extends LoadableComponent<VaultScreen> {

	@AndroidFindBy(id="vault_selection_list")
	@iOSFindBy(className="UIATableView")
    protected WebElement vaultList;
    
    @AndroidFindBy(id="component_string_list_text")
    @iOSFindBy(className="UIATableCell")
    protected List<WebElement> vaults;
    
    @iOSFindBy(xpath="//UIAButton[1]")
    protected WebElement btnBack;
    
	@Override
	protected void isLoaded() throws Error{}

	@Override
	protected void load(){}

}
