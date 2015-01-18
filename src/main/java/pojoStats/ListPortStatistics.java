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
		double receiveBytes;
		double transmitBytes;
		double receiveErrors;
		double receiveDrops;
		double transmitErrors;
		double transmitDrops;
		
		PortSwitch portS;
		List <portStatistic> lps;
		ListWsStats lws = new ListWsStats();
		for (portStatistics pss : portStatistics){
			mac=pss.getNode().getId();
			lps=pss.getPortStatistic();
			lp.removeList();
			for(portStatistic ps : lps){
				portId=ps.getNodeConnector().getId();
				receiveBytes=ps.getReceiveBytes();
				transmitBytes=ps.getTransmitBytes();
				receiveDrops=ps.getReceiveDrops();
				receiveErrors=ps.getReceiveErrors();
				transmitDrops=ps.getTransmitDrops();
				transmitErrors=ps.getTransmitErrors();
				
				
				portS = new PortSwitch(portId, receiveBytes, transmitBytes,receiveDrops,transmitDrops,receiveErrors,transmitErrors);
				lp.addPort(portS);
				
			}

			lws.addObject(new WsObjectStats(mac, lp.getListPorts()));
		}

		return lws;
	}
	
}
