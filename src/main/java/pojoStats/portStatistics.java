package pojoStats;

import java.util.List;

public class portStatistics {
	node node;
	List <portStatistic> portStatistic;

	
	public portStatistics(node node, List<portStatistic> portStatistic) {

		this.node = node;
		this.portStatistic = portStatistic;
	}


	public node getNode() {
		return node;
	}


	public void setNode(node node) {
		this.node = node;
	}


	public List<portStatistic> getPortStatistic() {
		return portStatistic;
	}


	public void setPortStatistic(List<portStatistic> portStatistic) {
		this.portStatistic = portStatistic;
	}

	
	
	
}
