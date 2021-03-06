package xero.commons.factories;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import xero.commons.contracts.ActionSteps;
import xero.commons.contracts.CommonUtils;
import xero.commons.contracts.DataMapping;
import xero.commons.contracts.VerificationAsserts;
import xero.commons.enums.FACTORY_CRITERIA;
import xero.commons.enums.WEB_DRIVER_BROWSER;
import xero.commons.impl.CommonUtilsImpl;
import xero.commons.impl.WebActions;
import xero.conf.ConfigurationListener;
import xero.conf.factory.XeroAbstractFactory;
import xero.invoice.contracts.InvoiceActions;
import xero.invoice.impl.InvoiceActionsImpl;

public class ActionFactory extends XeroAbstractFactory{
	final static Logger logger = Logger.getLogger(ActionFactory.class);
	@Override
	public ActionSteps getActions(FACTORY_CRITERIA FACTORY_CRITERIA,WebDriver driver) {
		// TODO Auto-generated method stub
		if(driver == null){
			logger.error("Driver has not been initialized");
			throw new NullPointerException();
		}
		
		ActionSteps actions = null;
		actions = new WebActions(driver);
		return actions;
	}

	@Override
	public InvoiceActions getInvoiceActions(FACTORY_CRITERIA FACTORY_CRITERIA,
			ActionSteps actions, CommonUtils commonUtils) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonUtils getCommonUtils(FACTORY_CRITERIA FACTORY_CRITERIA,
			ActionSteps actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataMapping getDataMapping(FACTORY_CRITERIA FACTORY_CRITERIA,
			ActionSteps actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerificationAsserts getVerificationAssert(
			FACTORY_CRITERIA FACTORY_CRITERIA, ActionSteps actions) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
