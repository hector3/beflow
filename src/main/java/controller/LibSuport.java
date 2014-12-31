package controller;

import javax.ws.rs.core.Response;

import pojoController.*;
import pojoStats.ListPortStatistic;
import pojoStats.ListPortStatistics;
import pojoStats.portStatistic;
import pojoStats.portStatistics;



public class LibSuport {

	Response response;
	
	public LibSuport(){
		
	}
	
	public Response genResponse(String json){
		
		response = Response.status(200).
		entity(json).
		header("Access-Control-Allow-Origin", "*").build();
		
		
		return response;
		
	}
	
	public ListEdgeProperties creaObjetoTest(){
		
		config cnf = new config(2);
		name nam = new name("ether3-slave-loc");
		state stat = new state(5);
		timeStamp timest =new timeStamp(14192715, "hola");
		properties properties = new properties(timest, nam, stat, cnf);
		node nodeH = new node("00:01:d4:ca:6d:c4:44:1e", "78");
		node nodeT = new node("00:01:d4:ca:6d:b5:f4:0f", "78");
		headNodeConnector headnodeconnector = new headNodeConnector(nodeH, "1", "tipo");
		tailNodeConnector tailnodeconnector = new tailNodeConnector(nodeT, "3", "sahd");
		edge edg = new edge(tailnodeconnector,headnodeconnector);
		edgeProperties ep = new edgeProperties(properties, edg);
		ListEdgeProperties listEdgeProperties = new ListEdgeProperties();
		listEdgeProperties.addProp(ep);
	
		return listEdgeProperties;
		
	}
	/*
	public ListPortStatistics creaListTest(){
		pojoStats.node node =new pojoStats.node("String1","String2");
		pojoStats.nodeConnector nodeConnector= new pojoStats.nodeConnector(node, "xxx", "xxx");
		pojoStats.portStatistic portStatistic = new pojoStats.portStatistic(nodeConnector, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
		ListPortStatistic lpe = new ListPortStatistic();
		lpe.addPortStat(portStatistic);
		lpe.addPortStat(portStatistic);
		pojoStats.node node2 =new pojoStats.node("String3","String4");
		portStatistics portStatistics = new portStatistics(node2, lpe);
		
		
		return null;
		
	}

	*/
}
