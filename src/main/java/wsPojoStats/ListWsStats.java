package wsPojoStats;

import java.util.ArrayList;
import java.util.List;

public class ListWsStats {
	public List <WsObjectStats> listWsObjectStats;
	
	public ListWsStats(){
		listWsObjectStats = new ArrayList<WsObjectStats>();
	}
	
	public List<WsObjectStats> getListWsObjectStats() {
		return listWsObjectStats;
	}

	public void setListWsObjectStats(List<WsObjectStats> listWsObjectStats) {
		this.listWsObjectStats = listWsObjectStats;
	}

	public void addObject(WsObjectStats o){
		listWsObjectStats.add(o);
	}
}
