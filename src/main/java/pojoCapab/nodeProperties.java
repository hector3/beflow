package pojoCapab;


public class nodeProperties {
	properties properties;
	node node;
	
	public nodeProperties(pojoCapab.properties properties, pojoCapab.node node) {
		this.properties = properties;
		this.node = node;
	}
	
	public properties getProperties() {
		return properties;
	}
	public void setProperties(properties properties) {
		this.properties = properties;
	}
	public node getNode() {
		return node;
	}
	public void setNode(node node) {
		this.node = node;
	}
}