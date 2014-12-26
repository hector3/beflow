package wsPojoController;

import java.util.ArrayList;
import java.util.List;



public class edgePropWs {

	properties properties;
	edge edge;
	
	public edgePropWs(properties properties,edge edge) {
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
