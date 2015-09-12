package xero.commons.enums;

public enum WEB_DRIVER_BROWSER {
	IE("IE"),FX("FX"),CR("CR"),AD("AD");
	
	
	private String browser;
	
	private WEB_DRIVER_BROWSER(String browser){
		this.browser = browser;
	}
	
	public String getBrowser(){
		return this.browser;
	}
}
