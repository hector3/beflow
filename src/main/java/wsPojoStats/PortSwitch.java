package wsPojoStats;

public class PortSwitch {
	String portId;
	double receiveBytes;
	double transmitBytes;
	double receiveDrops;
	double transmitDrops;
	double receiveErrors;
	double transmitErrors;
	
	public PortSwitch(String portId, double receiveBytes, double transmitBytes,double receiveDrops, double transmitDrops, double receiveErrors,double transmitErrors) {

		this.portId = portId;
		this.receiveBytes = receiveBytes;
		this.transmitBytes = transmitBytes;
		this.receiveDrops =receiveDrops;
		this.transmitDrops=transmitDrops;
		this.receiveErrors=receiveErrors;
		this.transmitErrors=transmitErrors;
	}


	public String getPortId() {
		return portId;
	}


	public void setPortId(String portId) {
		this.portId = portId;
	}


	public double getReceiveBytes() {
		return receiveBytes;
	}


	public void setReceiveBytes(double receiveBytes) {
		this.receiveBytes = receiveBytes;
	}


	public double getTransmitBytes() {
		return transmitBytes;
	}


	public void setTransmitBytes(double transmitBytes) {
		this.transmitBytes = transmitBytes;
	}


	public double getReceiveDrops() {
		return receiveDrops;
	}


	public void setReceiveDrops(double receiveDrops) {
		this.receiveDrops = receiveDrops;
	}


	public double getTransmitDrops() {
		return transmitDrops;
	}


	public void setTransmitDrops(double transmitDrops) {
		this.transmitDrops = transmitDrops;
	}


	public double getReceiveErrors() {
		return receiveErrors;
	}


	public void setReceiveErrors(double receiveErrors) {
		this.receiveErrors = receiveErrors;
	}


	public double getTransmitErrors() {
		return transmitErrors;
	}


	public void setTransmitErrors(double transmitErrors) {
		this.transmitErrors = transmitErrors;
	}
	
	
	
	
	
	
	
}
