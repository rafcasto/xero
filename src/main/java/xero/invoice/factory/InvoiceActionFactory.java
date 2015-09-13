package xero.invoice.factory;

import net.sourceforge.htmlunit.corejs.javascript.ast.EmptyExpression;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import xero.commons.contracts.ActionSteps;
import xero.commons.contracts.CommonUtils;
import xero.commons.contracts.DataMapping;
import xero.commons.contracts.VerificationAsserts;
import xero.commons.enums.FACTORY_CRITERIA;
import xero.commons.factories.ActionFactory;
import xero.conf.factory.XeroAbstractFactory;
import xero.invoice.contracts.InvoiceActions;
import xero.invoice.impl.InvoiceActionsImpl;

public class InvoiceActionFactory extends XeroAbstractFactory{

	final static Logger logger = Logger.getLogger(ActionFactory.class);

	@Override
	public InvoiceActions getInvoiceActions(FACTORY_CRITERIA FACTORY_CRITERIA,
			ActionSteps actions,CommonUtils commonUtils) {
		// TODO Auto-generated method stub
		// We can add an additional criteria in the future
		this.validateInvoiceCreation(actions, commonUtils);
		InvoiceActions invoiceActions = new InvoiceActionsImpl(actions, commonUtils);
		return invoiceActions;
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

	@Override
	public ActionSteps getActions(FACTORY_CRITERIA FACTORY_CRITERIA,
			WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void validateInvoiceCreation(ActionSteps actions,CommonUtils commonUtils){
		if(actions == null){			
			logger.error("action has not been initialized");
			throw new NullPointerException();
		}if(commonUtils == null){
			logger.error("commonUtils has not been initialized");
			throw new NullPointerException();
		}
		
	}


}
