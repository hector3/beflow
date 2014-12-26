package wsPojoController;

public class headNodeConnector {
	node nodeHead;
	String idIntHead;
	
	public headNodeConnector(node node, String id) {
		this.nodeHead = node;
		this.idIntHead = id;

	}

	public node getNode() {
		return nodeHead;
	}

	public void setNode(node node) {
		this.nodeHead = node;
	}

	public String getId() {
		return idIntHead;
	}

	public void setId(String id) {
		this.idIntHead = id;
	}
}
