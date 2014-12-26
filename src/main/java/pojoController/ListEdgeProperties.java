package pojoController;

import java.util.ArrayList;
import java.util.List;

import wsPojoController.WSListObjects;
import wsPojoController.edgePropWs;


public class ListEdgeProperties {
	public List <edgeProperties> edgeProperties;
	
	public ListEdgeProperties() {
		edgeProperties = new ArrayList<edgeProperties>();
	}
	
	public void addProp(edgeProperties ep){
		edgeProperties.add(ep);
	}
	
	public void recorreList(){
		for (edgeProperties ep : edgeProperties){
			System.out.println(ep.getEdge().getHnc().getId());
		}
	}
	
	public WSListObjects genListPurged(){
		
		WSListObjects wslo = new WSListObjects();
		for (edgeProperties ep : edgeProperties){
			wsPojoController.node nodeT = new wsPojoController.node(ep.getEdge().getTnc().getNode().getId());
			wsPojoController.tailNodeConnector tnc = new wsPojoController.tailNodeConnector(nodeT, ep.getEdge().getTnc().getId());
			wsPojoController.node nodeH = new wsPojoController.node(ep.getEdge().getHnc().getNode().getId());
			wsPojoController.headNodeConnector hnc = new wsPojoController.headNodeConnector(nodeH, ep.getEdge().getHnc().getId());
			wsPojoController.edge edge = new wsPojoController.edge(tnc, hnc);
			
			
			wsPojoController.name nm = new wsPojoController.name(ep.getProperties().getNm().getValue());
			wsPojoController.properties properties = new wsPojoController.properties(nm);
			edgePropWs epnew = new edgePropWs(properties, edge);
			wslo.addProp(epnew);
			

		}
		return wslo;
	}
}
