package xero.conf.factory;

import xero.commons.enums.FACTORY_CRITERIA;
import xero.commons.factories.ActionFactory;
import xero.commons.factories.CommonUtilFactory;
import xero.invoice.factory.InvoiceActionFactory;

public class XeroFactoryProducer {
	
	public static XeroAbstractFactory getFactory(FACTORY_CRITERIA FACTORY_CRITERIA){
		XeroAbstractFactory factory = null;
		switch(FACTORY_CRITERIA){
		case ActionSteps :{
			factory = new ActionFactory();
		}break;
		case CommonUtils :{
			factory = new CommonUtilFactory();
		}break; 
		case DataMapping:{
			//TBD
		}break; 
		case InvoiceActions :{
			factory = new InvoiceActionFactory();			
		}break;
		case VerificationAsserts:{
			//TBD
		}break;
		default :{
			
		}
		}
		return factory;		
	}
}
