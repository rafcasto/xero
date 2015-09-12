package xero.conf.pojo;


/**
 * store the parameters values that has been set in the testng xml template
 * **/
public class ConfigurationPojo {
	private String url; //url of the test enviroment
	private String browser; // determine the browser type IE(Internet Explorer),FX (Firefox) ,CR (crhome),SF (safari) 
	private String platform; //flag that will predict if we are testing a mobile or a web application it can be integrated later with appium
	private String port; // when testing web or APIs to determine the port on wich the application is running 	
	private String endPointUrl; // when testing web services or APIs this param will be needed 
	private boolean localRc; //Flag that control if the scripts will be run at local computer or in a remote computer
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getEndPointUrl() {
		return endPointUrl;
	}
	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}
	public boolean isLocalRc() {
		return localRc;
	}
	public void setLocalRc(boolean localRc) {
		this.localRc = localRc;
	}
}
