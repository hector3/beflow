package wsPojoCap;

import java.util.List;

public class nodecap {
	String mac;
	List <String> capabilities;
	public nodecap(String mac, List<String> capabilities) {
		this.mac = mac;
		this.capabilities = capabilities;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public List<String> getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(List<String> capabilities) {
		this.capabilities = capabilities;
	}
	
	

	
	
	
}
