package xero.commons.factories;

import org.openqa.selenium.WebDriver;

import xero.commons.contracts.ActionSteps;
import xero.commons.enums.WEB_DRIVER_BROWSER;
import xero.commons.impl.WebActions;
import xero.conf.ConfigurationListener;
import xero.invoice.contracts.InvoiceActions;
import xero.invoice.impl.InvoiceActionsImpl;

public class ActionFactory {
	private ActionSteps actions;
	private InvoiceActions inoiceActions;
	public ActionFactory(WebDriver driver){
		this.initActionBrowser(driver);
	}
	
	public ActionSteps getActions(){
		return this.actions;
	}
	
	public InvoiceActions getInvoiceActions(){
		return this.inoiceActions;
	}
	//init based on platform
	
	//init based on browser
	private void initActionBrowser(WebDriver driver){
		if(WEB_DRIVER_BROWSER.IE.getBrowser().equalsIgnoreCase(ConfigurationListener.configurationPojo.getBrowser())){
			actions = new WebActions(driver); // this is a default in the future if we need to change implamentation because browser compativilities we can just change the implementation	
		}else if(WEB_DRIVER_BROWSER.FX.getBrowser().equalsIgnoreCase(ConfigurationListener.configurationPojo.getBrowser())){
			actions = new WebActions(driver);// this is a default in the future if we need to change implamentation because browser compativilities we can just change the implementation
		}else if(WEB_DRIVER_BROWSER.CR.getBrowser().equalsIgnoreCase(ConfigurationListener.configurationPojo.getBrowser())){
			//Mobile android To be defined					
		}else if(WEB_DRIVER_BROWSER.AD.getBrowser().equalsIgnoreCase(ConfigurationListener.configurationPojo.getBrowser())){
			actions = new WebActions(driver);// this is a default in the future if we need to change implamentation because browser compativilities we can just change the implementation
		}else{
			//no valid option
		}
		inoiceActions = new InvoiceActionsImpl(actions);//not a good practice ><
	}
}
