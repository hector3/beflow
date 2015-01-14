package pojoControllerFlowConfig;

import java.util.ArrayList;
import java.util.List;

import wsPojoControllerFlowConfig.WsConfigObject;

public class ConfigObject {

	public List <flowConfig> flowConfig;

	public ConfigObject() {
		flowConfig = new ArrayList <flowConfig>();
	}
	
	public void addflowConfig(flowConfig fc){
		flowConfig.add(fc);
	}
	
	public WsConfigObject genListPurged(){
		List <String> actionsList;
		List <wsPojoControllerFlowConfig.flowConfig> lfc  = new ArrayList<wsPojoControllerFlowConfig.flowConfig>();
		String name;
		String idnode;
		String ingressPort;
		String priority;
		String vlanId;
		String vlanPriority;
		String nwSrc;
		String nwDst;
		String protocol;
		String idleTimeout;
		String hardTimeout;
		System.out.println("Numero de flows: "+flowConfig.size());
		for(flowConfig fc: flowConfig){
			name = fc.getName();
			System.out.println(name);
			idnode = fc.getNode().getId();
			System.out.println(idnode);
			ingressPort=fc.getIngressPort();
			priority=fc.getPriority();
			vlanId=fc.getVlanId();
			vlanPriority=fc.getPriority();
			nwSrc=fc.getNwSrc();
			nwDst=fc.getNwDst();
			protocol=fc.getProtocol();
			idleTimeout=fc.getIdleTimeout();
			hardTimeout=fc.getHardTimeout();
			actionsList = new ArrayList <String>();
			for(String st:fc.getActions()){
				System.out.println(st);
				actionsList.add(st);
			}
			wsPojoControllerFlowConfig.flowConfig wsfc = new wsPojoControllerFlowConfig.flowConfig(name,idnode,ingressPort,priority,vlanId,vlanPriority,nwSrc,nwDst,protocol,idleTimeout,hardTimeout,actionsList);
			lfc.add(wsfc);
			
		}
		WsConfigObject wsco = new WsConfigObject(lfc);
		return wsco;
		
	}
	
	public void recorreList(){
		for(flowConfig fc : flowConfig){
			System.out.println(fc.getName());
		}
	}	
	
}
