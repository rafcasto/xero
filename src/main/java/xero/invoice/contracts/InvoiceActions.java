package xero.invoice.contracts;

import java.util.HashMap;

import org.openqa.selenium.By;

import xero.commons.enums.WAIT_METHOD;

public interface InvoiceActions {
	public void invoiceTableUpdate(By locator,int timeToExpire,WAIT_METHOD WAIT_METHOD,int rowNum,HashMap<Object, Object> tableMaps,String logMessage);
}
