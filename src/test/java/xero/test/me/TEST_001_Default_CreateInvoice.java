package xero.test.me;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.Assert;
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

/***
 * @author Rafael Castillo
 * ## Description ## 
 * This scripts verify the create invoice functionality 
 * UserStory 0001
 * 
 * */
public class TEST_001_Default_CreateInvoice {
	private ActionSteps actionsSteps;
	private CommonUtils commonUtils;	
	private InvoiceActions invoiceActions;
	
	private Grid grid;
	private int timeToExpire = 30;
	private HashMap<Object, Object> invoiceMap = new HashMap<Object, Object>();
	//test data
	
	//test data grid item 
	private String item1 = "BOOK: Fish out of Water: Finding Your Brand";
	@BeforeMethod
	public void setUp(){
		this.grid = Grid.initGrid();		
		actionsSteps = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.ActionSteps).getActions(FACTORY_CRITERIA.DEFAULT, this.grid.getDriver());
		commonUtils = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.CommonUtils).getCommonUtils(FACTORY_CRITERIA.DEFAULT, actionsSteps);		
		this.invoiceActions = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.InvoiceActions).getInvoiceActions(FACTORY_CRITERIA.DEFAULT, actionsSteps, commonUtils);
	}
	
	@Test(testName="Test_001")
	public void testConfiguration(){	
		//creating a new Invoice 
		this.invoiceActions.goToInvoice("Creating a new Invoice Test_001");
		//setting invoice values on hashMap
		invoiceMap.put(INVOICE_CREATE_HEADER.TO, "ABC Furniture");
		invoiceMap.put(INVOICE_CREATE_HEADER.DUE_DATE, "Sep 14, 2015");
		invoiceMap.put(INVOICE_CREATE_HEADER.REFERENCE, "Ref test");
		//sending invoice headers data 
		this.invoiceActions.provideInvoiceHeaders(invoiceMap, "providing header Information...");
				
		//Invoice Table
		invoiceMap.put(INVOICE_TABLE_COLS.ITEM, item1);		
		invoiceMap.putIfAbsent(INVOICE_TABLE_COLS.QTY, "2");
		//setting values for invoice table row 1
		this.invoiceActions.invoiceTableUpdate(By.id("lineItems"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, 1, invoiceMap, "Setting values for invoice table");			
				
		//Approving Invoice 
		this.invoiceActions.approveInvoice("Approving Invoice");
		//review screen assertion methods
		// the fallowin is not a good practice but, because the lack of time 
		//i am doing the validation without any encapsulation hope you understand 
		this.actionsSteps.waitForElement(By.partialLinkText("Email"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "waiting for email button");
		Assert.assertTrue(actionsSteps.getPageContet().contains("Invoice Approved"),"test case failed");
		
		
	}
	
	@AfterMethod
	public void closeDriver(){
		grid.closeWebDriver();
	}
}
