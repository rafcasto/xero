package xero.conf;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import xero.conf.pojo.ConfigurationPojo;

public class ConfigurationListener implements ISuiteListener {

	public static ConfigurationPojo configurationPojo= new ConfigurationPojo();
	//defaults 
	private String url = "localhost";
	private String browser = "fx";
	private String platform = "web";
	private String port = "4040";
	private String endPointUrl = "localhost/service1";
	private String localRc = "false";
	private String password = "rafael88";
	private String user = "rafcasto@gmail.com";
	private String hub = "localhost";
	private String sauceLab = "false";
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite isuit) {
		// TODO Auto-generated method stub
		//reassigning values to default parameters and reassigning to the pojo
		this.assignValues(isuit);
		//assigning values to the configuration pojo
		this.assignVluesConfigurationPojo();
		
	}
	
	/**
	 * reassigning values to default parameters and reassigning to the pojo 
	 * */
	private void assignValues(ISuite isuit){
		url = isuit.getParameter("url") != null ? isuit.getParameter("url") : url;
		browser = isuit.getParameter("browser") != null ? isuit.getParameter("browser") : browser;
		platform = isuit.getParameter("platform") != null ? isuit.getParameter("platform") : platform;
		port = isuit.getParameter("port") != null ? isuit.getParameter("port") : port;
		endPointUrl = isuit.getParameter("endPointUrl") != null ? isuit.getParameter("endPointUrl") : endPointUrl;
		localRc = isuit.getParameter("localRc") != null ? isuit.getParameter("localRc") : localRc;
		user = isuit.getParameter("user") != null ? isuit.getParameter("user") : user;
		password = isuit.getParameter("password") != null ? isuit.getParameter("password") : password;
		hub = isuit.getParameter("hub") != null ? isuit.getParameter("hub") : hub;
		sauceLab = isuit.getParameter("sauceLab") != null ? isuit.getParameter("sauceLab") : sauceLab;
	}
	
	/**
	 * assigning values to the configuration pojo 
	 * */
	private void assignVluesConfigurationPojo(){
		configurationPojo.setUrl(url);
		configurationPojo.setBrowser(browser);
		configurationPojo.setEndPointUrl(endPointUrl);
		configurationPojo.setPlatform(platform);
		configurationPojo.setPort(port);
		configurationPojo.setLocalRc(this.getBoolean(localRc));
		configurationPojo.setUser(user);
		configurationPojo.setPassword(password);
		configurationPojo.setHub(hub);
		configurationPojo.setSauceLab(sauceLab);
		
	}
	
	/**
	 * Converting from string to boolean value
	 * 
	 * */
	private boolean getBoolean(String value){
		if(localRc.equalsIgnoreCase("true")){
			return true;
		}else{
			return false;
		}
	} 
}
