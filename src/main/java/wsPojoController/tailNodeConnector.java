package wsPojoController;

public class tailNodeConnector {

	
	node node;
	String id;

	
	public tailNodeConnector(node node, String id) {
		this.node = node;
		this.id = id;

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


	
}
