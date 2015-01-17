package pojoNodeConnector2;

import java.util.ArrayList;
import java.util.List;
//putu git
public class NodeConnector {
	List <nodeConnectorProperties> nodeConnectorProperties;
	
	
	public NodeConnector(){
		nodeConnectorProperties = new ArrayList <nodeConnectorProperties>();
	}


	public List<nodeConnectorProperties> getNodeConnectorProperties() {
		return nodeConnectorProperties;
	}


	public void setNodeConnectorProperties(List<nodeConnectorProperties> nodeConnectorProperties) {
		this.nodeConnectorProperties = nodeConnectorProperties;
	}
	
	public wsPojoNodeConnector2.ListPorts genListPurged(){
		String namePort;
		String idPort;
		List <wsPojoNodeConnector2.port> listPorts = new ArrayList <wsPojoNodeConnector2.port>();
		for(nodeConnectorProperties ncp:nodeConnectorProperties){
			namePort=ncp.getProperties().getName().getValue();
			idPort=ncp.getNodeconnector().getId();
			listPorts.add(new wsPojoNodeConnector2.port(namePort,idPort));
			
		}

		return new wsPojoNodeConnector2.ListPorts(listPorts);
		
	}
	
}
