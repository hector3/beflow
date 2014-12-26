package pojoController;

public class timeStamp {
	long value;
	String name;
	
	public timeStamp(long value, String name) {

		this.value = value;
		this.name = name;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
