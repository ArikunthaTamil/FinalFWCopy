package MFMA.Screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

public class VaultScreen extends LoadableComponent<VaultScreen> {

	@FindBy(id="vault_selection_list")
    protected WebElement vaultList;
    
    @FindBy(id="component_string_list_text")
    protected List<WebElement> vaults;
    
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

}
