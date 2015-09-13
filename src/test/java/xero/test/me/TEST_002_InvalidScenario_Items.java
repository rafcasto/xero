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
 * #### Description. ####
 * this script validate that the user is not able to create an 
 * invoice when items in invoice are greater than items in the inventory
 * #### To be consider. #### 
 * this scripts needs data configuration before to be executed 
 * the TSM - Black: T-Shirt Medium Black item most be in the inventory
 * and the item availability in inventory should not be greater than 4 
 * UserStory 0001
 * **/
public class TEST_002_InvalidScenario_Items {
	private ActionSteps actionsSteps;
	private CommonUtils commonUtils;	
	private InvoiceActions invoiceActions;
	
	
	private Grid grid;
	private int timeToExpire = 30;
	private HashMap<Object, Object> invoiceMap = new HashMap<Object, Object>();
	//test data grid item 
	private String item1 = "TSM - Black: T-Shirt Medium Black";
	@BeforeMethod
	public void setUp(){			
		this.grid = Grid.initGrid();		
		actionsSteps = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.ActionSteps).getActions(FACTORY_CRITERIA.DEFAULT, this.grid.getDriver());
		commonUtils = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.CommonUtils).getCommonUtils(FACTORY_CRITERIA.DEFAULT, actionsSteps);		
		this.invoiceActions = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.InvoiceActions).getInvoiceActions(FACTORY_CRITERIA.DEFAULT, actionsSteps, commonUtils);
	}
	
	@Test(testName="Test_002")
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
