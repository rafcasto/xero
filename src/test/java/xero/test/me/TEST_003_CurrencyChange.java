package xero.test.me;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import xero.commons.contracts.ActionSteps;
import xero.commons.contracts.CommonUtils;
import xero.commons.enums.FACTORY_CRITERIA;
import xero.commons.enums.WAIT_METHOD;
import xero.commons.webdriver.Grid;
import xero.conf.factory.XeroFactoryProducer;
import xero.invoice.contracts.InvoiceActions;
import xero.invoice.enums.INVOICE_CREATE_HEADER;
import xero.invoice.enums.INVOICE_TABLE_COLS;

/**
 * @author Rafael Castillo
 * ### Description: ####
 * This script verify when the user change the currency, 
 * this change is being reflected in the confirmation page.  
 * 
 * #### To be consider. #### 
 * this scripts needs data configuration before to be executed 
 * the  EUR Euro currency must be configured for the user before executing this script
 * UserStory 0001
 * **/
public class TEST_003_CurrencyChange {
	private ActionSteps actionsSteps;
	private CommonUtils commonUtils;	
	private InvoiceActions invoiceActions;
	
	private Grid grid;
	private int timeToExpire = 30;
	private HashMap<Object, Object> invoiceMap = new HashMap<Object, Object>();
	//test data grid item 
	private String item1 = "TSM - Black: T-Shirt Medium Black";
	private String currency = "EUR Euro";
	@BeforeMethod
	public void setUp(){
		this.grid = Grid.initGrid();		
		actionsSteps = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.ActionSteps).getActions(FACTORY_CRITERIA.DEFAULT, this.grid.getDriver());
		commonUtils = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.CommonUtils).getCommonUtils(FACTORY_CRITERIA.DEFAULT, actionsSteps);		
		this.invoiceActions = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.InvoiceActions).getInvoiceActions(FACTORY_CRITERIA.DEFAULT, actionsSteps, commonUtils);

	}
	
	@Test(testName="Test_003")
	public void testConfiguration(){
		//creating a new Invoice 
				this.invoiceActions.goToInvoice("Creating a new Invoice Test_003");
				//setting invoice values on hashMap
				invoiceMap.put(INVOICE_CREATE_HEADER.TO, "ABC Furniture");
				invoiceMap.put(INVOICE_CREATE_HEADER.DUE_DATE, "Sep 14, 2015");
				invoiceMap.put(INVOICE_CREATE_HEADER.REFERENCE, "Ref test");
				invoiceMap.put(INVOICE_CREATE_HEADER.CURRENCY, currency);
				//sending invoice headers data 
				this.invoiceActions.provideInvoiceHeaders(invoiceMap, "providing header Information...");
						
				//Invoice Table
				invoiceMap.put(INVOICE_TABLE_COLS.ITEM, item1);		
				invoiceMap.putIfAbsent(INVOICE_TABLE_COLS.QTY, "2");
				//setting values for invoice table row 1
				this.invoiceActions.invoiceTableUpdate(By.id("lineItems"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, 1, invoiceMap, "Setting values for invoice table");			
				//setting values for invoice table row 2
				invoiceMap.putIfAbsent(INVOICE_TABLE_COLS.QTY, "4");
				this.invoiceActions.invoiceTableUpdate(By.id("lineItems"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, 2, invoiceMap, "Setting values for invoice table");
				//setting values for invoice table row 3
				invoiceMap.putIfAbsent(INVOICE_TABLE_COLS.QTY, "9");
				this.invoiceActions.invoiceTableUpdate(By.id("lineItems"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, 3, invoiceMap, "Setting values for invoice table");		
				//attempting to approve the invoice 
				this.invoiceActions.approveInvoice("Attempting to approve Invoice...");
				//review screen assertion methods
		
	}
	
	@AfterMethod
	public void closeDriver(){
		grid.closeWebDriver();
	} 
}