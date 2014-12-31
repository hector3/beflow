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
			System.out.println(lps.size());
			System.out.println(mac);
			lp.removeList();
			for(portStatistic ps : lps){
				System.out.println("helloooo!");
				portId=ps.getNodeConnector().getId();
				receivePackets=ps.getReceivePackets();
				transmitPackets=ps.getTransmitPackets();
				System.out.println(portId);
				System.out.println(receivePackets);
				System.out.println(transmitPackets);
				portS = new PortSwitch(portId, receivePackets, transmitPackets);
				lp.addPort(portS);
			}

			lws.addObject(new WsObjectStats(mac, lp));
		}

		return lws;
	}
	
}
