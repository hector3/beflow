package pojoController;

public class properties {
	
	timeStamp timeStamp;
	name name;
	state state;
	config config;
	
	public properties(timeStamp ts, name nm, state st, config cf) {
		
		this.timeStamp = ts;
		this.name = nm;
		this.state = st;
		this.config = cf;
	}

	public timeStamp getTs() {
		return timeStamp;
	}

	public void setTs(timeStamp ts) {
		this.timeStamp = ts;
	}

	public name getNm() {
		return name;
	}

	public void setNm(name nm) {
		this.name = nm;
	}

	public state getSt() {
		return state;
	}

	public void setSt(state st) {
		this.state = st;
	}

	public config getCf() {
		return config;
	}

	public void setCf(config cf) {
		this.config = cf;
	}
	
	

}
