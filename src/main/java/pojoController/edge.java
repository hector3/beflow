package pojoController;

public class edge {

	tailNodeConnector tailNodeConnector;
	headNodeConnector headNodeConnector;
	
	
	public edge(tailNodeConnector tnc, headNodeConnector hnc) {
		
		this.headNodeConnector = hnc;
		this.tailNodeConnector = tnc;
	}

	public headNodeConnector getHnc() {
		return headNodeConnector;
	}

	public void setHnc(headNodeConnector hnc) {
		this.headNodeConnector = hnc;
	}

	public tailNodeConnector getTnc() {
		return tailNodeConnector;
	}

	public void setTnc(tailNodeConnector tnc) {
		this.tailNodeConnector = tnc;
	}
	
	
}
