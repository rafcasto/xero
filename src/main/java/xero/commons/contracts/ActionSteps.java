package xero.commons.contracts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import xero.commons.enums.SELECT_METHOD;
import xero.commons.enums.WAIT_METHOD;

public interface ActionSteps {
	public WebElement findElement(By locator,int timeToExpire,WAIT_METHOD WAIT_METHOD,String logMessage);
	public List<WebElement> findElements(By locator,int timeToExpire,WAIT_METHOD WAIT_METHOD,String logMessage);
	public void waitForElement(By locator,int timeToExpire,WAIT_METHOD WAIT_METHOD,String logMessage);
	public void sendText(By locator,int timeToExpire,WAIT_METHOD waitMethod,String text,String logMessage);
	public void clickOnElemment(By locator,int timeToExpire,WAIT_METHOD waitMethod,String logMessage);
	public void select(By locator,int timeToExpire,WAIT_METHOD waitMethod,String option, SELECT_METHOD SELECT_METHOD, String logMessage);
	public void pressTab(By locator,int timeToExpire,WAIT_METHOD WAIT_METHOD,String logMessage);
	public void openUrl(String url,String logMessage);
	public String getPageContet();
	public void setAttribute(WebElement element,String att,String value);
}
	