package pojoController;

import java.util.ArrayList;
import java.util.List;

import wsPojoController.WSListObjects;
import wsPojoController.edgePropWs;
import wsPojoTopology.Topology;
import wsPojoTopology.edges;
import wsPojoTopology.nodes;


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
	public Topology genListPurgedTop(){
		List <nodes> listnodes = new ArrayList <nodes>();
		List <edges> listedges = new ArrayList <edges>();
		for (edgeProperties ep : edgeProperties){
			String idSource=ep.getEdge().getHnc().getId();
			String idTarget=ep.getEdge().getTnc().getId();
			String id=idSource+"-"+idTarget;
			nodes node =new nodes(ep.getEdge().getTnc().getNode().getId(),ep.getEdge().getTnc().getNode().getId(),3);
			edges edge =new edges(id,ep.getEdge().getHnc().getNode().getId(),ep.getEdge().getTnc().getNode().getId());
			boolean repetidoNode=false;
			for(nodes nodesTemp : listnodes){
				if(node.getId().equalsIgnoreCase(nodesTemp.getId())){
					repetidoNode=true;
				}
			}
			
			if(!repetidoNode){
			listnodes.add(node);
			}
			
			boolean repetidoEdge=false;
			for(edges edgesTemp : listedges){
				if(edge.getSource().equalsIgnoreCase(edgesTemp.getTarget())&&(edge.getTarget().equalsIgnoreCase(edgesTemp.getSource()))){
					repetidoEdge=true;
				}
			}			
			if(!repetidoEdge){
			listedges.add(edge);
			}
		}
		Topology top = new Topology(listnodes,listedges);
		return top;
	}
}
