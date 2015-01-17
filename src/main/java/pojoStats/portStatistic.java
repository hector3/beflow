package pojoStats;

public class portStatistic {
	
	nodeConnector nodeConnector;
	long receivePackets;
	long transmitPackets;
	long receiveBytes;
	long transmitBytes;
	long receiveDrops;
	long transmitDrops;
	long receiveErrors;
	long transmitErrors;
	long receiveFrameError;
	long receiveOverRunError;
	long receiveCrcError;
	long collisionCount;
	
	
	public portStatistic(nodeConnector nodeConnector,
			long receivePackets, long transmitPackets, long receiveBytes,
			long transmitBytes, long receiveDrops, long transmitDrops,
			long receiveErrors, long transmitErrors, long receiveFrameError,
			long receiveOverRunError, long receiveCrcError, long collisionCount) {

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


	public long getReceivePackets() {
		return receivePackets;
	}


	public void setReceivePackets(long receivePackets) {
		this.receivePackets = receivePackets;
	}


	public long getTransmitPackets() {
		return transmitPackets;
	}


	public void setTransmitPackets(long transmitPackets) {
		this.transmitPackets = transmitPackets;
	}


	public long getReceiveBytes() {
		return receiveBytes;
	}


	public void setReceiveBytes(long receiveBytes) {
		this.receiveBytes = receiveBytes;
	}


	public long getTransmitBytes() {
		return transmitBytes;
	}


	public void setTransmitBytes(long transmitBytes) {
		this.transmitBytes = transmitBytes;
	}


	public long getReceiveDrops() {
		return receiveDrops;
	}


	public void setReceiveDrops(long receiveDrops) {
		this.receiveDrops = receiveDrops;
	}


	public long getTransmitDrops() {
		return transmitDrops;
	}


	public void setTransmitDrops(long transmitDrops) {
		this.transmitDrops = transmitDrops;
	}


	public long getReceiveErrors() {
		return receiveErrors;
	}


	public void setReceiveErrors(long receiveErrors) {
		this.receiveErrors = receiveErrors;
	}


	public long getTransmitErrors() {
		return transmitErrors;
	}


	public void setTransmitErrors(long transmitErrors) {
		this.transmitErrors = transmitErrors;
	}


	public long getReceiveFrameError() {
		return receiveFrameError;
	}


	public void setReceiveFrameError(long receiveFrameError) {
		this.receiveFrameError = receiveFrameError;
	}


	public long getReceiveOverRunError() {
		return receiveOverRunError;
	}


	public void setReceiveOverRunError(long receiveOverRunError) {
		this.receiveOverRunError = receiveOverRunError;
	}


	public long getReceiveCrcError() {
		return receiveCrcError;
	}


	public void setReceiveCrcError(long receiveCrcError) {
		this.receiveCrcError = receiveCrcError;
	}


	public long getCollisionCount() {
		return collisionCount;
	}


	public void setCollisionCount(long collisionCount) {
		this.collisionCount = collisionCount;
	}
	
	
}

