package wsPojoStats;

import java.util.List;

public class WsObjectStats {
	
	String mac;
	ListPorts listPorts;
	public WsObjectStats(String mac, ListPorts listPorts) {

		this.mac = mac;
		this.listPorts = listPorts;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public ListPorts getListPorts() {
		return listPorts;
	}
	public void setListPorts(ListPorts listPorts) {
		this.listPorts = listPorts;
	}
	
	
	
}
