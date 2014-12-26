package controller;

import javax.ws.rs.core.Response;

import pojoController.*;



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

	
}
