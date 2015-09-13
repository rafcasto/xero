package xero.commons.webdriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import xero.commons.enums.WEB_DRIVER_BROWSER;
import xero.commons.enums.WEB_DRIVER_PLATFORM;
import xero.commons.impl.WebActions;
import xero.conf.ConfigurationListener;

public class Grid {
	final static Logger logger = Logger.getLogger(Grid.class);
	private static Grid grid = new Grid();
	private   WebDriver driver;
	private Grid(){
		
	}
	
	/**
	 * Initializing the grid
	 * */
	public static Grid initGrid(){
		grid.initPlatform();
		logger.info("init Selenium grid class...");
		return grid;
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public void closeWebDriver(){
		this.driver.close();
	}
	
	/**
	 * Initializing  platform
	 * * */
	private  void initPlatform(){
		if(ConfigurationListener.configurationPojo.getPlatform().equals(WEB_DRIVER_PLATFORM.WEB.getPlatform())){
			logger.info("init web platform...");
			initWebDriver();
			logger.info("finish web platform...");
		}else if(ConfigurationListener.configurationPojo.getPlatform().equals(WEB_DRIVER_PLATFORM.MOBILE.getPlatform())){
			//when implementing mobile method call can be in this part 
			//if time i will implement this part
		}else{
			//no option available
		}
	}
	
	/**
	 * Initializing  web driver
	 * * */
	private  void initWebDriver(){
		if(ConfigurationListener.configurationPojo.isLocalRc()){
			//Configuration to run in local computer
			logger.info("init local web driver...");
			initLocalWebDriver(ConfigurationListener.configurationPojo.getBrowser());
			logger.info("local web driver loaded successfully...");
		}else{
			//call configuration to load webdriver for remote computer
			initRemoteWebDriver(ConfigurationListener.configurationPojo.getBrowser());
		}
	}
	/**
	 * Initializing local web driver for web platform
	 * * */
	private  void initLocalWebDriver(String browser){
		if(WEB_DRIVER_BROWSER.IE.getBrowser().equalsIgnoreCase(browser)){
			driver = new InternetExplorerDriver();
		}else if(WEB_DRIVER_BROWSER.FX.getBrowser().equalsIgnoreCase(browser)){
			driver = new FirefoxDriver();
		}else if(WEB_DRIVER_BROWSER.CR.getBrowser().equalsIgnoreCase(browser)){
			driver = new ChromeDriver();
		}else if(WEB_DRIVER_BROWSER.AD.getBrowser().equalsIgnoreCase(browser)){
			//if possible loading android driver this might be remove since android driver might need some other configuration
		}
		
	}
	/**
	 * Initializing remote web driver
	 * * */
	private  void initRemoteWebDriver(String browser){
		//code for remote webDriver 
		//if time i will implement this part
		 DesiredCapabilities capability = null;
		if(WEB_DRIVER_BROWSER.IE.getBrowser().equalsIgnoreCase(browser)){
			capability = DesiredCapabilities.internetExplorer();
		}else if(WEB_DRIVER_BROWSER.FX.getBrowser().equalsIgnoreCase(browser)){
			capability = DesiredCapabilities.firefox();
		}else if(WEB_DRIVER_BROWSER.CR.getBrowser().equalsIgnoreCase(browser)){
			capability = DesiredCapabilities.chrome();
		}else if(WEB_DRIVER_BROWSER.AD.getBrowser().equalsIgnoreCase(browser)){
			//if possible loading android driver this might be remove since android driver might need some other configuration
		}
		try {									
			driver =  new RemoteWebDriver(new URL(ConfigurationListener.configurationPojo.getHub()),capability);						
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error("Remote driver not loaded: " + e.toString());
			e.printStackTrace();
		}
	}
}
