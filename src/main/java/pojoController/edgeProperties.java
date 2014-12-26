package pojoController;

import java.util.ArrayList;
import java.util.List;



public class edgeProperties {

	properties properties;
	edge edge;
	public edgeProperties(properties properties,edge edge) {
		this.properties = properties;
		this.edge = edge;
	}
	public properties getProperties() {
		return properties;
	}
	public void setProperties(properties properties) {
		this.properties = properties;
	}
	public edge getEdge() {
		return edge;
	}
	public void setEdge(edge edge) {
		this.edge = edge;
	}
	
	
}
