package controller;

import javax.ws.rs.core.Response;

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
	
}
