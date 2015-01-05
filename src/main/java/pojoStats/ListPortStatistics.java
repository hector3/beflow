package pojoStats;

import java.util.ArrayList;
import java.util.List;

import wsPojoStats.ListPorts;
import wsPojoStats.ListWsStats;
import wsPojoStats.PortSwitch;
import wsPojoStats.WsObjectStats;

public class ListPortStatistics {
	
	public List <portStatistics> portStatistics;
	
	public ListPortStatistics(){
		portStatistics = new ArrayList<portStatistics>();
	}
	
	public void addPortStatistics(portStatistics ps){
		portStatistics.add(ps);
	}

	public ListWsStats genListPurged() {
		String mac;
		ListPorts lp = new ListPorts();
		String portId;
		int receivePackets;
		int transmitPackets;
		PortSwitch portS;
		List <portStatistic> lps;
		ListWsStats lws = new ListWsStats();
		for (portStatistics pss : portStatistics){
			mac=pss.getNode().getId();
			lps=pss.getPortStatistic();
			lp.removeList();
			for(portStatistic ps : lps){
				portId=ps.getNodeConnector().getId();
				receivePackets=ps.getReceivePackets();
				transmitPackets=ps.getTransmitPackets();
				portS = new PortSwitch(portId, receivePackets, transmitPackets);
				lp.addPort(portS);
				
			}

			lws.addObject(new WsObjectStats(mac, lp.getListPorts()));
		}

		return lws;
	}
	
}
