package wsobjects;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.archetypes.jersey.quickstart.webapp.Node;

public class ListNodesFromCompany {
	public List <NodeWS> nodesList;
	
	public ListNodesFromCompany (){
		nodesList = new ArrayList<NodeWS>();
	}
	
	public void addNodeWS (NodeWS nodews){
		nodesList.add(nodews);
	}
	
	public ArrayList<NodeWS> getList (List <Node> lista){
		List <NodeWS> listaws = new ArrayList<NodeWS>();
		
		try{
			for (Node tempNode : lista){
				NodeWS nodews = new NodeWS(0, tempNode.getNode_name(), tempNode.getMAC_address(), tempNode.getPort_number(), tempNode.getCompany().getCompany_name());
				listaws.add(nodews);
				
			}
			
		}catch(Exception e){
			System.out.println("problemas.. Excepcion: "+e.getMessage());
		}
		
		
		return (ArrayList <NodeWS>) listaws;
		
	}
}
