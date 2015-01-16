package wsPojoCap;

import java.util.List;

public class Capabilities {
	List <nodecap> nodecap;

	public Capabilities(List<wsPojoCap.nodecap> nodecap) {
		this.nodecap = nodecap;
	}

	public List<nodecap> getNodecap() {
		return nodecap;
	}

	public void setNodecap(List<nodecap> nodecap) {
		this.nodecap = nodecap;
	}
	
	
}
