package wsPojoStats;

import java.util.ArrayList;
import java.util.List;

public class ListPorts {

	public List <PortSwitch> listports;
	
	public ListPorts(){
		listports = new ArrayList<PortSwitch>();
	}
	
	public void addPort(PortSwitch ps){
		listports.add(ps);
	}
	
	public void removeList(){
		listports.removeAll(listports);
	}
}
