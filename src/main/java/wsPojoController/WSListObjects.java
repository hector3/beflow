package wsPojoController;

import java.util.ArrayList;
import java.util.List;

public class WSListObjects {
	public List <edgePropWs> edgeProperties;
	
	public WSListObjects() {
		edgeProperties = new ArrayList<edgePropWs>();
	}
	
	public void addProp(edgePropWs ep){
		edgeProperties.add(ep);
	}
	

}
