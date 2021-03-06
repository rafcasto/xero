package xero.invoice.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xalan.templates.ElemMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import xero.commons.contracts.ActionSteps;
import xero.commons.contracts.CommonUtils;
import xero.commons.enums.WAIT_METHOD;
import xero.commons.impl.WebActions;
import xero.conf.ConfigurationListener;
import xero.invoice.contracts.InvoiceActions;
import xero.invoice.enums.INVOICE_CREATE_HEADER;
import xero.invoice.enums.INVOICE_TABLE_COLS;
import xero.invoice.pojo.InvoiceCreateHeadersPojo;
import xero.invoice.pojo.InvoicePojo;

public class InvoiceActionsImpl  implements InvoiceActions {
	private ActionSteps actions;
	private InvoicePojo invoicePojo;
	private CommonUtils commonUtils;
	private int timeToExpire = 30; 
	final static Logger logger = Logger.getLogger(InvoiceActionsImpl.class);
	public InvoiceActionsImpl(ActionSteps actions){
		this.actions = actions;
	}
	
	public InvoiceActionsImpl(ActionSteps actions,CommonUtils commonUtils){
		this.commonUtils = commonUtils;
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

	/**
	 * Creating an invoice 
	 * @param logMessage 
	 * @param hashmap values needed to create a new Invoice
	 * */
	public void goToInvoice(String logMessage) {
		// TODO Auto-generated method stub
		this.commonUtils.login(ConfigurationListener.configurationPojo.getUser(),ConfigurationListener.configurationPojo.getPassword(), logMessage);
		actions.clickOnElemment(By.linkText("Accounts"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Accounts");
		actions.clickOnElemment(By.linkText("Sales"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Sales");
		actions.clickOnElemment(By.partialLinkText("New"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "create a new invoice ");

	}
	
	
	/**
	 * mapping hashmap to pojo header
	 * */
	private InvoiceCreateHeadersPojo setHeaders(HashMap<Object, Object> headersMap){
		InvoiceCreateHeadersPojo headers = new InvoiceCreateHeadersPojo();
		headers.setTo( (headersMap.get(INVOICE_CREATE_HEADER.TO) != null) ? (String) headersMap.get(INVOICE_CREATE_HEADER.TO) :  "" );
		headers.setDate( (headersMap.get(INVOICE_CREATE_HEADER.DATE) != null) ? (String) headersMap.get(INVOICE_CREATE_HEADER.DATE) :  "" );
		headers.setDue_date( (headersMap.get(INVOICE_CREATE_HEADER.DUE_DATE) != null) ? (String) headersMap.get(INVOICE_CREATE_HEADER.DUE_DATE) :  "" );
		headers.setInvoice_number((headersMap.get(INVOICE_CREATE_HEADER.INVOICE_NUMBER) != null) ? (String) headersMap.get(INVOICE_CREATE_HEADER.INVOICE_NUMBER) :  "" );
		headers.setReference((headersMap.get(INVOICE_CREATE_HEADER.REFERENCE) != null) ? (String) headersMap.get(INVOICE_CREATE_HEADER.REFERENCE) :  "" );
		headers.setBranding((headersMap.get(INVOICE_CREATE_HEADER.BRANDING) != null) ? (String) headersMap.get(INVOICE_CREATE_HEADER.BRANDING) :  "" );
		headers.setCurrency((headersMap.get(INVOICE_CREATE_HEADER.CURRENCY) != null) ? (String) headersMap.get(INVOICE_CREATE_HEADER.CURRENCY) :  "" );
		headers.setAmmount_are((headersMap.get(INVOICE_CREATE_HEADER.AMMOUNT_ARE) != null) ? (String) headersMap.get(INVOICE_CREATE_HEADER.AMMOUNT_ARE) :  "" );
		return headers;
	}

	public void provideInvoiceHeaders(HashMap<Object, Object> headers,
			String logMessage) {
		// TODO Auto-generated method stub
		InvoiceCreateHeadersPojo headersPojo = this.setHeaders(headers);
		if(!headersPojo.getTo().equals("")){
			actions.sendText(By.xpath("//input[starts-with(@id, 'PaidToName')]"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, headersPojo.getTo(), logMessage);
			actions.pressTab(By.xpath("//input[starts-with(@id, 'PaidToName')]"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, logMessage);
		}if(!headersPojo.getDate().equals("")){
			actions.sendText(By.xpath("//input[starts-with(@id, 'InvoiceDate')]"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, headersPojo.getDate(), logMessage);
		}if(!headersPojo.getDue_date().equals("")){
			actions.sendText(By.xpath("//input[starts-with(@id, 'DueDate')]"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, headersPojo.getDue_date(), logMessage);			
		}if(!headersPojo.getInvoice_number().equals("")){
			actions.sendText(By.xpath("//input[starts-with(@id, 'InvoiceNumber')]"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, headersPojo.getInvoice_number(), logMessage);			
		}if(!headersPojo.getReference().equals("")){
			actions.sendText(By.xpath("//input[starts-with(@id, 'Reference')]"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, headersPojo.getReference(), logMessage);
		}if(!headersPojo.getBranding().equals("")){
			actions.sendText(By.id("TemplateDropDown_value"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, headersPojo.getBranding(), logMessage);			
		}if(!headersPojo.getCurrency().equals("")){
			this.setCurrency(headersPojo.getCurrency(), logMessage);
			//actions.sendText(By.xpath("//input[starts-with(@id, 'Currency')]"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, headersPojo.getCurrency(), logMessage);
		}if(!headersPojo.getAmmount_are().equals("")){
			//i will comeback to this later 
		}
	}

	public void approveInvoice(String logMesage) {
		// TODO Auto-generated method stub
		this.actions.clickOnElemment(By.linkText("Approve"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, logMesage);
	}
	
	
	private void setCurrency(String currency,String logMessage){
		WebElement element = actions.findElement(By.id("currencyField"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, logMessage);
		System.out.println("total of input: " + element.findElements(By.tagName("input")).size());
		element.click();
		int i = 0;
		for(WebElement input : element.findElements(By.tagName("input"))){
			System.out.println("value " + input.getText() + " value " + input.getAttribute("value"));
			if(i ==1){
				input.clear();
				input.sendKeys(currency);
//				actions.setAttribute(element, "value", currency);			
				input.sendKeys(Keys.TAB);
			}
			i++;
			
		}
		//actions.sendText(By.xpath("//input[starts-with(@id, 'Currency')]"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, currency, logMessage);
	}
	
}
