package pojoNodeConnector;

import java.util.ArrayList;
import java.util.List;

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
	
	public wsPojoNodeConnector.ListPorts genListPurged(){
		String namePort;
		List <String> listPorts = new ArrayList <String>();
		wsPojoNodeConnector.ListPorts lp;
		for(nodeConnectorProperties ncp:nodeConnectorProperties){
			namePort=ncp.getProperties().getName().getValue();
			listPorts.add(namePort);
			
		}
		lp = new wsPojoNodeConnector.ListPorts(listPorts);
		return lp;
		
	}
}
