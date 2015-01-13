package wsPojoTopology;

public class nodes {
	String id;
	String label;
	int size;
	public nodes(String id, String label, int size) {
		this.id = id;
		this.label = label;
		this.size = size;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
