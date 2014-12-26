package wsPojoController;

public class headNodeConnector {
	node node;
	String idInt;
	
	public headNodeConnector(node node, String id) {
		this.node = node;
		this.idInt = id;

	}

	public node getNode() {
		return node;
	}

	public void setNode(node node) {
		this.node = node;
	}

	public String getId() {
		return idInt;
	}

	public void setId(String id) {
		this.idInt = id;
	}
}
