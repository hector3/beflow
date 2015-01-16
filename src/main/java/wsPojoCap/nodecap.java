package wsPojoCap;

public class nodecap {
	String mac;
	String capabilities;
	public nodecap(String mac, String capabilities) {

		this.mac = mac;
		this.capabilities = capabilities;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}
	
	
	
	
}
