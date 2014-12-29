package wsobjects;

public class NodeWS {
	
	long id_node;
	String node_name;
	String MAC_address;
	String port_number;
	String name_company;
	
	public NodeWS(long id_node,String node_name, String MAC_address,String port_number, String name_company) {
		this.id_node=id_node;
		this.node_name= node_name;
		this.MAC_address = MAC_address;
		this.port_number = port_number;
		this.name_company = name_company;
	}

	

	public long getId_node() {
		return id_node;
	}


	public void setId_node(long id_node) {
		this.id_node = id_node;
	}

	public String getNode_name() {
		return node_name;
	}


	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}


	public String getMAC_address() {
		return MAC_address;
	}


	public void setMAC_address(String mAC_address) {
		MAC_address = mAC_address;
	}


	public String getPort_number() {
		return port_number;
	}


	public void setPort_number(String port_number) {
		this.port_number = port_number;
	}


	
	public String getName_company() {
		return name_company;
	}


	public void setName_company(String name_company) {
		this.name_company = name_company;
	}
	


	
}
 