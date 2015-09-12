package xero.commons.enums;

public enum WEB_DRIVER_PLATFORM {
	WEB("web"),MOBILE("mobile");
	
	
	private String platform;
	
	private WEB_DRIVER_PLATFORM(String platform){
		this.platform = platform;
	}
	
	public String getPlatform(){
		return this.platform;
	}
}
