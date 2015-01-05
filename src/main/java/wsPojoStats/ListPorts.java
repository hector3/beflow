package wsPojoStats;

import java.util.ArrayList;
import java.util.List;

public class ListPorts {

	public List <PortSwitch> listports;
	
	public ListPorts(){
		listports = new ArrayList<PortSwitch>();
	}
	
	public List<PortSwitch> getListPorts() {
		return listports;
	}

	public void setListports(List<PortSwitch> listports) {
		this.listports = listports;
	}

	public void addPort(PortSwitch ps){
		listports.add(ps);
	}
	
	public void removeList(){
		listports.removeAll(listports);
	}
}
