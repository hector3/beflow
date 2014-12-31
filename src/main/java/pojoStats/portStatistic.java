package pojoStats;

public class portStatistic {
	
	nodeConnector nodeConnector;
	int receivePackets;
	int transmitPackets;
	int receiveBytes;
	int transmitBytes;
	int receiveDrops;
	int transmitDrops;
	int receiveErrors;
	int transmitErrors;
	int receiveFrameError;
	int receiveOverRunError;
	int receiveCrcError;
	int collisionCount;
	
	
	public portStatistic(nodeConnector nodeConnector,
			int receivePackets, int transmitPackets, int receiveBytes,
			int transmitBytes, int receiveDrops, int transmitDrops,
			int receiveErrors, int transmitErrors, int receiveFrameError,
			int receiveOverRunError, int receiveCrcError, int collisionCount) {

		this.nodeConnector = nodeConnector;
		this.receivePackets = receivePackets;
		this.transmitPackets = transmitPackets;
		this.receiveBytes = receiveBytes;
		this.transmitBytes = transmitBytes;
		this.receiveDrops = receiveDrops;
		this.transmitDrops = transmitDrops;
		this.receiveErrors = receiveErrors;
		this.transmitErrors = transmitErrors;
		this.receiveFrameError = receiveFrameError;
		this.receiveOverRunError = receiveOverRunError;
		this.receiveCrcError = receiveCrcError;
		this.collisionCount = collisionCount;
	}


	public nodeConnector getNodeConnector() {
		return nodeConnector;
	}


	public void setNodeConnector(nodeConnector nodeConnector) {
		this.nodeConnector = nodeConnector;
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


	public int getReceiveBytes() {
		return receiveBytes;
	}


	public void setReceiveBytes(int receiveBytes) {
		this.receiveBytes = receiveBytes;
	}


	public int getTransmitBytes() {
		return transmitBytes;
	}


	public void setTransmitBytes(int transmitBytes) {
		this.transmitBytes = transmitBytes;
	}


	public int getReceiveDrops() {
		return receiveDrops;
	}


	public void setReceiveDrops(int receiveDrops) {
		this.receiveDrops = receiveDrops;
	}


	public int getTransmitDrops() {
		return transmitDrops;
	}


	public void setTransmitDrops(int transmitDrops) {
		this.transmitDrops = transmitDrops;
	}


	public int getReceiveErrors() {
		return receiveErrors;
	}


	public void setReceiveErrors(int receiveErrors) {
		this.receiveErrors = receiveErrors;
	}


	public int getTransmitErrors() {
		return transmitErrors;
	}


	public void setTransmitErrors(int transmitErrors) {
		this.transmitErrors = transmitErrors;
	}


	public int getReceiveFrameError() {
		return receiveFrameError;
	}


	public void setReceiveFrameError(int receiveFrameError) {
		this.receiveFrameError = receiveFrameError;
	}


	public int getReceiveOverRunError() {
		return receiveOverRunError;
	}


	public void setReceiveOverRunError(int receiveOverRunError) {
		this.receiveOverRunError = receiveOverRunError;
	}


	public int getReceiveCrcError() {
		return receiveCrcError;
	}


	public void setReceiveCrcError(int receiveCrcError) {
		this.receiveCrcError = receiveCrcError;
	}


	public int getCollisionCount() {
		return collisionCount;
	}


	public void setCollisionCount(int collisionCount) {
		this.collisionCount = collisionCount;
	}
	
	
}

