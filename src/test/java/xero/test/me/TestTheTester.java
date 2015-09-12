package xero.test.me;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import xero.commons.contracts.ActionSteps;
import xero.commons.enums.WAIT_METHOD;
import xero.commons.factories.ActionFactory;
import xero.commons.webdriver.Grid;
import xero.conf.ConfigurationListener;
import xero.invoice.contracts.InvoiceActions;
import xero.invoice.enums.INVOICE_TABLE_COLS;

public class TestTheTester {
	private ActionSteps actions;
	private ActionFactory actionFactory;
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
		this.actionFactory = new ActionFactory(grid.getDriver());
		actions = actionFactory.getActions();
		this.invoiceActions = actionFactory.getInvoiceActions();
	}
	
	@Test(testName="Test_001")
	public void testConfiguration(){
		//open test invarioment
		grid.getDriver().get(ConfigurationListener.configurationPojo.getUrl());
		//login to the application 
		actions.sendText(By.id("email"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, email, "filling the email " + email );
		actions.sendText(By.id("password"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, password, "filling the password " + password );
		actions.clickOnElemment(By.linkText("Login"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Login");
		
		//creating a new Invoice 
		actions.clickOnElemment(By.linkText("Accounts"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Accounts");
		actions.clickOnElemment(By.linkText("Sales"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Sales");
		actions.clickOnElemment(By.partialLinkText("New"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "create a new invoice ");
		
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
