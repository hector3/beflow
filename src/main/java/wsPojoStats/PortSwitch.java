package wsPojoStats;

public class PortSwitch {
	String portId;
	double receivePackets;
	double transmitPackets;
	
	
	public PortSwitch(String portId, double receivePackets, double transmitPackets) {

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


	public double getReceivePackets() {
		return receivePackets;
	}


	public void setReceivePackets(double receivePackets) {
		this.receivePackets = receivePackets;
	}


	public double getTransmitPackets() {
		return transmitPackets;
	}


	public void setTransmitPackets(double transmitPackets) {
		this.transmitPackets = transmitPackets;
	}
	
	
}
