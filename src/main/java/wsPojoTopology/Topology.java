package wsPojoTopology;

import java.util.List;

import pojoController.edgeProperties;


public class Topology {
	public List <nodes> nodes;
	public List <edges> edges;
	
	public Topology(List<wsPojoTopology.nodes> nodes,List<wsPojoTopology.edges> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}
	public List<nodes> getNodes() {
		return nodes;
	}
	public void setNodes(List<nodes> nodes) {
		this.nodes = nodes;
	}
	public List<edges> getEdges() {
		return edges;
	}
	public void setEdges(List<edges> edges) {
		this.edges = edges;
	}
	
}
