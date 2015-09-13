package xero.test.me;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import xero.commons.contracts.ActionSteps;
import xero.commons.contracts.CommonUtils;
import xero.commons.enums.FACTORY_CRITERIA;
import xero.commons.enums.WAIT_METHOD;
import xero.commons.webdriver.Grid;
import xero.conf.ConfigurationListener;
import xero.conf.factory.XeroFactoryProducer;
import xero.invoice.contracts.InvoiceActions;
import xero.invoice.enums.INVOICE_TABLE_COLS;

public class TestTheTester {
	private ActionSteps actionsSteps;
	private CommonUtils commonUtils;	
	private InvoiceActions invoiceActions;
	
	private Grid grid;
	private int timeToExpire = 30;
	private HashMap<Object, Object> invoiceMap = new HashMap<Object, Object>();
	//test data
	private String email = "rafcasto@gmail.com";
	private String password = "rafael88";
	//test data grid item 
	private String item1 = "TSS - Black: T-Shirt Small Black";
	@BeforeMethod
	public void setUp(){
		this.grid = Grid.initGrid();		
		actionsSteps = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.ActionSteps).getActions(null, this.grid.getDriver());
		commonUtils = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.CommonUtils).getCommonUtils(null, actionsSteps);		
		this.invoiceActions = XeroFactoryProducer.getFactory(FACTORY_CRITERIA.InvoiceActions).getInvoiceActions(null, actionsSteps, commonUtils);
	}
	
	@Test(testName="Test_001")
	public void testConfiguration(){
		//open test invarioment
		grid.getDriver().get(ConfigurationListener.configurationPojo.getUrl());
		//login to the application 
		actionsSteps.sendText(By.id("email"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, email, "filling the email " + email );
		actionsSteps.sendText(By.id("password"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, password, "filling the password " + password );
		actionsSteps.clickOnElemment(By.linkText("Login"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Login");
		
		//creating a new Invoice 
		actionsSteps.clickOnElemment(By.linkText("Accounts"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Accounts");
		actionsSteps.clickOnElemment(By.linkText("Sales"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Sales");
		actionsSteps.clickOnElemment(By.partialLinkText("New"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "create a new invoice ");
		
		//setting invoice values on hashMap
		invoiceMap.put(INVOICE_TABLE_COLS.ITEM, item1);
		invoiceMap.put(INVOICE_TABLE_COLS.DESCRIPTION, "Description test");
		invoiceMap.putIfAbsent(INVOICE_TABLE_COLS.QTY, "2");
		
		//setting values for invoice table row 1
		this.invoiceActions.invoiceTableUpdate(By.id("lineItems"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, 1, invoiceMap, "Setting values for invoice table");			
		//setting values for invoice table row 2
		this.invoiceActions.invoiceTableUpdate(By.id("lineItems"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, 2, invoiceMap, "Setting values for invoice table");
		//setting values for invoice table row 3
		this.invoiceActions.invoiceTableUpdate(By.id("lineItems"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, 3, invoiceMap, "Setting values for invoice table");
		
//		for(WebElement table:tableRow.findElements(By.tagName("td"))){
//			System.out.println("Table " + table.getTagName());
//		}
		grid.closeWebDriver();
		
	} 
}
