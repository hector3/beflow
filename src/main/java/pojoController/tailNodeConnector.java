package pojoController;

public class tailNodeConnector {

	
	node node;
	String id;
	String type;
	
	public tailNodeConnector(node node, String id, String type) {
		this.node = node;
		this.id = id;
		this.type = type;
	}

	public node getNode() {
		return node;
	}

	public void setNode(node node) {
		this.node = node;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
