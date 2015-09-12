package xero.invoice.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import xero.commons.contracts.ActionSteps;
import xero.commons.enums.WAIT_METHOD;
import xero.commons.impl.WebActions;
import xero.invoice.contracts.InvoiceActions;
import xero.invoice.enums.INVOICE_TABLE_COLS;
import xero.invoice.pojo.InvoicePojo;

public class InvoiceActionsImpl implements InvoiceActions {
	private ActionSteps actions;
	private InvoicePojo invoicePojo;
	final static Logger logger = Logger.getLogger(InvoiceActionsImpl.class);
	public InvoiceActionsImpl(ActionSteps actions){
		this.actions = actions;
	}
	
	
	public void invoiceTableUpdate(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, int rowNum,
			HashMap<Object, Object> tableMaps, String logMessage) {
		
		logger.info("start update invoiceTableUpdate...");
		// TODO Auto-generated method stub
		this.initInvoicePojo(tableMaps);
		
		if(!this.invoicePojo.getItem().equals("")){
			this.setItemValue(locator, timeToExpire, WAIT_METHOD, rowNum, tableMaps, this.invoicePojo.getItem(), logMessage);
		} if(!this.invoicePojo.getDescription().equals("")){
			System.out.println("description: " + invoicePojo.getDescription());
			this.setDescription(locator, timeToExpire, WAIT_METHOD, rowNum, tableMaps, this.invoicePojo.getDescription(), logMessage);
		} if(!this.invoicePojo.getQty().equals("")){
			this.setQTY(locator, timeToExpire, WAIT_METHOD, rowNum, tableMaps, invoicePojo.getQty(), logMessage);
		} if(!this.invoicePojo.getDisc().equals("")){
			
		} if(!this.invoicePojo.getAccount().equals("")){
			
		} if(!this.invoicePojo.getRegion().equals("")){
			
		} if(!this.invoicePojo.getAmmount().equals("")){
			
		} if(!this.invoicePojo.getUnite_price().equals("")){
			
		} if(!this.invoicePojo.getTaxt_rate().equals("")){
			
		}
		
		
		logger.info("finish update invoiceTableUpdate...");
	}
	
	/**
	 * initializing invoice pojo
	 * */ 
	private void initInvoicePojo(HashMap<Object, Object> tableMaps){
		this.invoicePojo = new InvoicePojo();
		invoicePojo.setItem( (tableMaps.get(INVOICE_TABLE_COLS.ITEM) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.ITEM) :  "" );   
		invoicePojo.setDescription((tableMaps.get(INVOICE_TABLE_COLS.DESCRIPTION) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.DESCRIPTION) :  "" );
		invoicePojo.setQty((tableMaps.get(INVOICE_TABLE_COLS.QTY) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.QTY) :  "" );
		invoicePojo.setUnite_price((tableMaps.get(INVOICE_TABLE_COLS.UNIT_PRICE) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.UNIT_PRICE) :  "" );
		invoicePojo.setDisc((tableMaps.get(INVOICE_TABLE_COLS.DISC) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.DISC) :  "" );
		invoicePojo.setAccount((tableMaps.get(INVOICE_TABLE_COLS.ACCOUNT) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.ACCOUNT) :  "" );
		invoicePojo.setTaxt_rate((tableMaps.get(INVOICE_TABLE_COLS.TAXT_RATE) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.TAXT_RATE) :  "" );
		invoicePojo.setRegion((tableMaps.get(INVOICE_TABLE_COLS.REGION) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.REGION) :  "" );
		invoicePojo.setAmmount((tableMaps.get(INVOICE_TABLE_COLS.AMMOUNT_USD) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.AMMOUNT_USD) :  "");  
		invoicePojo.setX((tableMaps.get(INVOICE_TABLE_COLS.X) != null) ? (String) tableMaps.get(INVOICE_TABLE_COLS.X) :  "" );
		
	}
	
	/**
	 * getting invoice table row 
	 * */
	private WebElement getInvoiceTableRow(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, int rowNum,
			HashMap<Object, Object> tableMaps, String logMessage){
		WebElement tableRow = null; 
		WebElement invoiceTable = actions.findElement(locator, timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Finding invoice table");
		tableRow = invoiceTable.findElements(By.tagName("table")).get(rowNum);
		return  tableRow;
	}
	
	/**
	 * setting item value
	 * */
	private void setItemValue(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, int rowNum,
			HashMap<Object, Object> tableMaps,String item, String logMessage){
		this.getInvoiceTableRow(locator, timeToExpire, WAIT_METHOD, rowNum, tableMaps, logMessage)
		.findElement(By.className("x-grid3-col-colPriceList")).click();
		actions.sendText(By.xpath(".//input[@id='ext-comp-1001']"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, item, "filling the item 1 " + item );
		actions.pressTab(By.xpath(".//input[@id='ext-comp-1001']"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Press tab");
		logger.info("set item value: " + logMessage);
	}


	/**
	 * setting description 
	 * */
	private void setDescription(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, int rowNum,
			HashMap<Object, Object> tableMaps,String item, String logMessage){
		WebElement tablerow = 
		this.getInvoiceTableRow(locator, timeToExpire, WAIT_METHOD, rowNum, tableMaps, logMessage)
		.findElement(By.className("x-grid3-col-colDescription"));
		tablerow.click();		
			actions.sendText(By.xpath(".//textarea[@id='ext-comp-1002']"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, item, "filling description  " + item );
			actions.pressTab(By.xpath(".//textarea[@id='ext-comp-1002']"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Press tab");
			logger.info("set description value: " + logMessage);
	}
	
	/**
	 * setting description 
	 * */
	private void setQTY(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, int rowNum,
			HashMap<Object, Object> tableMaps,String item, String logMessage){
		WebElement tablerow = 
				this.getInvoiceTableRow(locator, timeToExpire, WAIT_METHOD, rowNum, tableMaps, logMessage)
				.findElement(By.className("x-grid3-col-colQuantity"));
				tablerow.click();		
					actions.sendText(By.xpath(".//input[@id='ext-comp-1004']"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, item, "filling description  " + item );
					actions.pressTab(By.xpath(".//input[@id='ext-comp-1004']"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Press tab");
					logger.info("set qty value: " + logMessage);
	}
	
}
