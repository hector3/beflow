package wsPojoStats;

public class PortSwitch {
	String portId;
	int receivePackets;
	int transmitPackets;
	
	
	public PortSwitch(String portId, int receivePackets, int transmitPackets) {

		this.portId = portId;
		this.receivePackets = receivePackets;
		this.transmitPackets = transmitPackets;
	}


	public String getPortId() {
		return portId;
	}


	public void setPortId(String portId) {
		this.portId = portId;
	}


	public int getReceivePackets() {
		return receivePackets;
	}


	public void setReceivePackets(int receivePackets) {
		this.receivePackets = receivePackets;
	}


	public int getTransmitPackets() {
		return transmitPackets;
	}


	public void setTransmitPackets(int transmitPackets) {
		this.transmitPackets = transmitPackets;
	}
	
	
}
