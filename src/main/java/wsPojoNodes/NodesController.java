package wsPojoNodes;

import java.util.List;

public class NodesController {
	
	public List <Node> nodes;

	public NodesController(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	} 
	
	
}
