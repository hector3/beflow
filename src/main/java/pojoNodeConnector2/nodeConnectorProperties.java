package pojoNodeConnector2;


public class nodeConnectorProperties {
	properties properties;
	nodeconnector nodeconnector;
	
	public nodeConnectorProperties(pojoNodeConnector2.properties properties,
			pojoNodeConnector2.nodeconnector nodeconnector) {
		this.properties = properties;
		this.nodeconnector = nodeconnector;
	}
	public properties getProperties() {
		return properties;
	}
	public void setProperties(properties properties) {
		this.properties = properties;
	}
	public nodeconnector getNodeconnector() {
		return nodeconnector;
	}
	public void setNodeconnector(nodeconnector nodeconnector) {
		this.nodeconnector = nodeconnector;
	}

	
	
	
	
}