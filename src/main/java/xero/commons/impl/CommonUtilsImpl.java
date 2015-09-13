package xero.commons.impl;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import xero.commons.contracts.ActionSteps;
import xero.commons.contracts.CommonUtils;
import xero.commons.enums.WAIT_METHOD;
import xero.conf.ConfigurationListener;

public class CommonUtilsImpl implements CommonUtils{
	final static Logger logger = Logger.getLogger(WebActions.class);
	private ActionSteps actions;
	private int timeToExpire = 30;
	public CommonUtilsImpl(ActionSteps actions){
		this.actions = actions;
	}
	public void login(String user, String password, String logMessage) {
		// TODO Auto-generated method stub
		//login to the application 
		try{
			actions.openUrl(ConfigurationListener.configurationPojo.getUrl(), logMessage);
			logger.info("Loggin User: " + user +  " password: " + password);
			actions.sendText(By.id("email"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, user, "filling the email " + user );
			actions.sendText(By.id("password"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, password, "filling the password " + password );
			actions.clickOnElemment(By.linkText("Login"), timeToExpire, WAIT_METHOD.FOR_ELEMENT_TO_BE_CLICKABLE, "Click on Login");
			logger.info("Loggin success full: " + logMessage);
		}catch(Exception ex){
			logger.error("Error Execution the login: " + ex.toString());
		}
		
	}

}
