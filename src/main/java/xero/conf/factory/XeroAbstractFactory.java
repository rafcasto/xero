package xero.conf.factory;

import org.openqa.selenium.WebDriver;

import xero.commons.contracts.ActionSteps;
import xero.commons.contracts.CommonUtils;
import xero.commons.contracts.DataMapping;
import xero.commons.contracts.VerificationAsserts;
import xero.commons.enums.FACTORY_CRITERIA;
import xero.invoice.contracts.InvoiceActions;

public abstract class XeroAbstractFactory {
	public abstract ActionSteps getActions(FACTORY_CRITERIA FACTORY_CRITERIA,WebDriver driver);
	public abstract InvoiceActions getInvoiceActions(FACTORY_CRITERIA FACTORY_CRITERIA,ActionSteps actions,CommonUtils commonUtils);
	public abstract CommonUtils getCommonUtils(FACTORY_CRITERIA FACTORY_CRITERIA,ActionSteps actions);
	public abstract DataMapping getDataMapping(FACTORY_CRITERIA FACTORY_CRITERIA,ActionSteps actions);
	public abstract VerificationAsserts getVerificationAssert(FACTORY_CRITERIA FACTORY_CRITERIA,ActionSteps actions);
}
