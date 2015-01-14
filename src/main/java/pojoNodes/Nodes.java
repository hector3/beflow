package pojoNodes;

import java.util.ArrayList;
import java.util.List;

import wsPojoNodes.NodesController;

public class Nodes {
	public List <nodeProperties> nodeProperties;
	
	public Nodes(){
		nodeProperties = new ArrayList <nodeProperties>();
	}
	
	public NodesController genListPurged(){
		String id;
		String name;
		List <wsPojoNodes.Node> lista = new ArrayList <wsPojoNodes.Node>();
		for (nodeProperties np : nodeProperties){
			id=np.getNode().getId();
			name=np.getProperties().getDescription().getValue();
			wsPojoNodes.Node node = new wsPojoNodes.Node(id,name);
			lista.add(node);
					
		}
		NodesController nc = new NodesController(lista);
		return nc;
	}
}
