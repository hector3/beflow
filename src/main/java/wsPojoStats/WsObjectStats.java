package wsPojoStats;

import java.util.List;

public class WsObjectStats {
	
	String mac;
	List<PortSwitch> listPorts;
	public WsObjectStats(String mac, List<PortSwitch> listPorts) {
		
		this.mac = mac;
		this.listPorts = listPorts;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public List<PortSwitch> getListPorts() {
		return listPorts;
	}
	public void setListPorts(List<PortSwitch> listPorts) {
		this.listPorts = listPorts;
	}

	
	
}