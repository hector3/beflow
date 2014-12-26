package wsPojoController;

public class tailNodeConnector {

	
	node nodeTail;
	String idIntTail;

	
	public tailNodeConnector(node node, String id) {
		this.nodeTail = node;
		this.idIntTail = id;

	}

	public node getNode() {
		return nodeTail;
	}

	public void setNode(node node) {
		this.nodeTail = node;
	}

	public String getId() {
		return idIntTail;
	}

	public void setId(String id) {
		this.idIntTail = id;
	}


	
}
