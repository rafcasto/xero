package xero.commons.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import xero.commons.contracts.ActionSteps;
import xero.commons.enums.SELECT_METHOD;
import xero.commons.enums.WAIT_METHOD;

public class WebActions implements ActionSteps{
	final static Logger logger = Logger.getLogger(WebActions.class);
	private WebDriver driver;
	
	public WebActions(WebDriver driver){
		this.driver = driver;
		logger.info("Loading WebActions...");
	}

	public void waitForElement(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, String logMessage) {
		// TODO Auto-generated method stub
		this.waitForElementPrivate(locator, timeToExpire, WAIT_METHOD, logMessage);
		logger.info("Waiting for WebElement: "  + logMessage + " " + locator.toString());
	}

	public void sendText(By locator, int timeToExpire, WAIT_METHOD waitMethod,
			String text, String logMessage) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(timeToExpire, TimeUnit.SECONDS);
		this.findElement(locator, timeToExpire, waitMethod, logMessage).sendKeys(text);		
		logger.info("Sending Text: "  + text + " to element: " + locator.toString() + " " + logMessage);
	}
	
	public void clickOnElemment(By locator, int timeToExpire,
			WAIT_METHOD waitMethod, String logMessage) {
		// TODO Auto-generated method stub
		try{
			this.clickOnElementPrivate(locator, timeToExpire, waitMethod, logMessage);
		}catch(TimeoutException ex){
			this.clickOnElementPrivate(locator, timeToExpire, waitMethod, logMessage);
		}
		logger.info("Clicking on Element : " + locator.toString() + " logMessage: " + logMessage);
	}
	
	private void clickOnElementPrivate(By locator, int timeToExpire,
			WAIT_METHOD waitMethod, String logMessage){
		driver.manage().timeouts().implicitlyWait(timeToExpire, TimeUnit.SECONDS);
		this.findElement(locator, timeToExpire, waitMethod, logMessage).click();

	}

	public void select(By locator, int timeToExpire, WAIT_METHOD waitMethod,
			String option, SELECT_METHOD SELECT_METHOD, String logMessage) {
		// TODO Auto-generated method stub
		this.selectPrivate(locator, timeToExpire, waitMethod, option, SELECT_METHOD, logMessage);
		logger.info("Select from dropdown : " + locator.toString() +  " logMessage " + logMessage );
	}
	
	private void selectPrivate(By locator, int timeToExpire, WAIT_METHOD waitMethod,
			String option, SELECT_METHOD SELECT_METHOD, String logMessage){
				
		switch(SELECT_METHOD){
		case SELECT_BY_INDEX :{
			this.getSelectElement(locator, timeToExpire, waitMethod, option, SELECT_METHOD, logMessage).selectByIndex(Integer.valueOf(option));
		}break;
		case SELECT_BY_JSCRIPT :{
			WebElement sel = this.findElement(locator, timeToExpire, waitMethod, logMessage);
			sel.click();
			WebElement value = sel.findElement(By.xpath("//*[.='"+option+"']"));
			value.click();
		}break;
		case SELECT_BY_TEXT :{
			this.getSelectElement(locator, timeToExpire, waitMethod, option, SELECT_METHOD, logMessage).selectByVisibleText(option);
		}break;
		case SELECT_BY_VALUE : {
			this.getSelectElement(locator, timeToExpire, waitMethod, option, SELECT_METHOD, logMessage).selectByValue(option);
		}break;
		default : {
			
		}
		} 
	}
	
	/**
	 * private return selet element
	 * */
	private Select getSelectElement(By locator, int timeToExpire, WAIT_METHOD waitMethod,
			String option, SELECT_METHOD SELECT_METHOD, String logMessage){
			WebElement dropdown = this.findElement(locator, timeToExpire, waitMethod, logMessage);
			return new Select(dropdown);
		
	}
	
	/**
	 * wait for element private method
	 * */
	private void waitForElementPrivate(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, String logMessage){
		WebDriverWait wait = new WebDriverWait(driver, timeToExpire);	
		switch(WAIT_METHOD){
		case FOR_ELEMENT_TO_BE_CLICKABLE : {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}break;
		case  FOR_ELEMENT_TO_BE_VISIBLE : {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}break;
		default : {
			
			}
		}
	}

	public WebElement findElement(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, String logMessage) {
		// TODO Auto-generated method stub
		WebElement element = null;
		try{
			
			element = this.findElementPrivate(locator, timeToExpire, WAIT_METHOD, logMessage);
			logger.info("Find Element in the try:" + locator.toString() + " Message" + logMessage);
		}catch(StaleElementReferenceException ex){
			element = this.findElementPrivate(locator, timeToExpire, WAIT_METHOD, logMessage);
			logger.info("Find Element after a  StaleElementReferenceException:" + locator.toString() + " Message" + logMessage);
			
		}				
		return element;
				
	}
	
	/**
	 * private find element method
	 * */
	private WebElement findElementPrivate(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, String logMessage){
		this.waitForElement(locator, timeToExpire, WAIT_METHOD, logMessage);
		return driver.findElement(locator);
	}

	
	
	public List<WebElement> findElements(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, String logMessage) {
		// TODO Auto-generated method stub
		List<WebElement> element = new ArrayList<WebElement>();
		try{
			
			element = this.findElemenstPrivate(locator, timeToExpire, WAIT_METHOD, logMessage);
			logger.info("Find Elements in the try:" + locator.toString() + " Message" + logMessage);
		}catch(StaleElementReferenceException ex){
			element = this.findElemenstPrivate(locator, timeToExpire, WAIT_METHOD, logMessage);
			logger.info("Find Elements after a  StaleElementReferenceException:" + locator.toString() + " Message" + logMessage);
			
		}
		return element;
	}
	
	/**
	 * private find elements method
	 * */
	private List<WebElement> findElemenstPrivate(By locator, int timeToExpire,
			WAIT_METHOD WAIT_METHOD, String logMessage){
		this.waitForElement(locator, timeToExpire, WAIT_METHOD, logMessage);
		return driver.findElements(locator);
	}

	public void pressTab(By locator, int timeToExpire, WAIT_METHOD WAIT_METHOD,
			String logMessage) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(timeToExpire, TimeUnit.SECONDS);
		this.findElement(locator, timeToExpire, WAIT_METHOD, logMessage).sendKeys(Keys.TAB);
	}

}
