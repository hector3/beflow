package wspolicies;

public class Consum {

	double transmitBytes;
	double receiveBytes;
	
	public Consum (double rx, double tx){
		
		this.transmitBytes =tx;
		this.receiveBytes = rx;
	}

	public double getTransmitBytes() {
		return transmitBytes;
	}

	public void setTransmitBytes(double transmitBytes) {
		this.transmitBytes = transmitBytes;
	}

	public double getReceiveBytes() {
		return receiveBytes;
	}

	public void setReceiveBytes(double receiveBytes) {
		this.receiveBytes = receiveBytes;
	}
	
	
}
