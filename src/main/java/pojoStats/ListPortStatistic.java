package pojoStats;

import java.util.ArrayList;
import java.util.List;

public class ListPortStatistic {
	public List <portStatistic> portstatistic;

	public ListPortStatistic() {
		portstatistic = new ArrayList<portStatistic>();
	}
	
	public void addPortStat(portStatistic ps){
		portstatistic.add(ps);
	}
	
	
	
	
}
