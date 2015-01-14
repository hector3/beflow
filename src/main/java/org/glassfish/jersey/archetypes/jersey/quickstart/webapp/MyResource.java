package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

import flowController.Flow;
import httpclient.HttpCliente;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import wsPojoController.WSListObjects;
import wsPojoStats.ListWsStats;
import wsPojoStats.WsObjectStats;
import wsPojoTopology.Topology;
import wsobjects.*;
import controller.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;



import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import pojoController.ListEdgeProperties;
import pojoControllerFlowConfig.ConfigObject;
import pojoNodes.Nodes;
import pojoStats.ListPortStatistics;
import rrdtool.BBDDrrdtool;
import rrdtool.Servlet2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Root resource (exposed at "myresource" path)
 */




@Path("/myresource")
public class MyResource {
	final String origen="http://*";	
	
	CRUD crud=new CRUD();
	Gson gson = new Gson();
	LibSuport ls=new LibSuport();
	Response response=null;
	HttpResponse httpresponse=null;
	HttpCliente httpcliente= new HttpCliente();
	BBDDrrdtool rrdtool =new BBDDrrdtool();
	// change your request and response accordingly

	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws UnknownHostException 
     */
	
	
    
	/*
	 * 
	 * ruta para acceder http://localhost:8000/jersey-quickstart-webapp/beflow/myresource
	 * 
	 */
	
	
	
	
	

	@GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getItest() throws UnknownHostException {
		
		
		BBDDrrdtool h = new BBDDrrdtool();
		try {
			h.genGraph("00:01:d4:ca:6d:b5:f4:0f_3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "hello jersey";	
    }
	
		
	
	@Path("/getUser/{email}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt2(@PathParam("email") String email) throws UnknownHostException {
		

		String mensaje="Consulta sobre el usuario con mail: "+email;
		System.out.println(mensaje);
		UserWS user = null;
		try{
			user=crud.read_user(email);

		}
		catch(Exception e){
			System.out.println("problemas.. Excepcion: "+e.getMessage());
		}
		String objetoEnJson;
		try{
			objetoEnJson=gson.toJson(user);
			response = ls.genResponse(objetoEnJson);
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			
		}
		
		return(response);    	
    }

	
	@Path("/getAllCompanies")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getallCompanies() throws UnknownHostException {

		List<Company> lista = crud.company_list();
		ListCompaniesWS listaws = new ListCompaniesWS();
		
		String objetoEnJson;
		try{
			objetoEnJson=gson.toJson(listaws.getList(lista));
			response = ls.genResponse(objetoEnJson);
			//response.setHeader("Content-Type", "application/json");
//			response = Response.status(200).
//	        entity(objetoEnJson).
//	        header("Access-Control-Allow-Origin", "*").build();

			
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";		
		}
		System.out.println(response.toString());
		return(response);	
    }
	
	@Path("/getUsersFromCompany/{company}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersFromCompany(@PathParam("company") String comp) throws UnknownHostException {
		
		List<User> lista = crud.wsuser_list(comp);
		
		ListUsersFromCompany listaws = new ListUsersFromCompany();
			
		
		String objetoEnJson;
		try{
			objetoEnJson=gson.toJson(listaws.getList(lista));
			response = ls.genResponse(objetoEnJson);
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			
		}
		
		//return("Hola");
		return(response);	
    }
	
	
	@Path("/getCompany/{name}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompany(@PathParam("name") String name) throws UnknownHostException {
		
		
		String mensaje="Consulta sobre la compañia con nombre: "+name;
		CompanyWS comp = null;
		try{
			comp=crud.wsread_company(name);//quan modifiquis el crud s'arregla l'error
			
			
		}
		catch(Exception e){
			System.out.println("problemas.. Excepcion: "+e.getMessage());
		}
		String objetoEnJson;
		try{
			objetoEnJson=gson.toJson(comp);
			response = ls.genResponse(objetoEnJson);
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			
		}
		
		return(response);
	   
	}
		
    	

	
	
	
	
	    
	   
	    
	
	@Path("/login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkLogin(String json) throws UnknownHostException{
		
		final CredWS cred = gson.fromJson(json, CredWS.class);
		int codresp=500;
		UserWS user = null;
		try{
			user=crud.read_user(cred.getLogin());
		}
		catch(Exception e){
			System.out.println("problemas.. O el login no existe o la base de datos está ko. Excepcion: "+e.getMessage());
		}
		
		if(user!=null)
		{
		
		if((user.getLogin().equals(cred.getLogin()))&&(user.getPassword().equals(cred.getPass())))
			{
			codresp=200;
			}
		else if((user.getLogin().equals(cred.getLogin()))&&(!user.getPassword().equals(cred.getPass())))
			{
			codresp=400;
			}
		else
			{
			codresp=600;
			}
		
		}
		
		else{
			codresp=600;
		}
		response=ls.genResponseInt(codresp);
		
		
		return response;
		

    }	
	
	
	@Path("/addUser")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertUser(String json) throws UnknownHostException{
		
		final UserWS user = gson.fromJson(json, UserWS.class);
		String resultado="";
		long id=0;
		if (crud.user_exists(user)){
			resultado="El usuario ya existe modifícalo si así lo deseas";
			response=ls.genResponse(resultado);
		}
		else{
			id=crud.wsadd_user(user);
			resultado="Usuario añadido correctamente a la compañía: "+user.getName_company()+" con id "+ id;
			response=ls.genResponse(resultado);
		}
		
		return response;
	}

	
	@Path("/updateUser")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(String json) throws UnknownHostException{
		
		final UserWS user = gson.fromJson(json, UserWS.class);
		String resultado="";
		//long id=0;
		//if (crud.user_exists(user)){
			try{
			crud.wsupdate_user(user);			
			resultado="El usuario ya existe y se ha modificado";
			response=ls.genResponse(resultado);
			}catch(Exception e){
				System.out.println("problemas.. Excepcion: "+e.getMessage());
				resultado="Ha habido problemas";
				response=ls.genResponse(resultado);
			}
		
		//else{
			
		//	resultado="El usuario no existe en la base de datos. Créalo.";
		//}
		
		return response;
	}
	@Path("/addCompany")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCompany(String json) throws UnknownHostException{
		System.out.println("Post con path addCompany recibido");
		final CompanyWS company = gson.fromJson(json, CompanyWS.class);
		String resultado= "";
		//compruebo si la compañia existe
		if(crud.company_exists(company)==true){
			resultado="La compañía ya existe!!";
			response=ls.genResponse(resultado);
			
		}
		else{
			
			long id=crud.create_company(company);
			resultado = "La compañía es nueva y se crea con id "+id;
			response=ls.genResponse(resultado);
		}
		System.out.println(resultado);
		return response;
    }
	
	@Path("/updateCompany")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCompany(String json) throws UnknownHostException{
		final CompanyWS company = gson.fromJson(json, CompanyWS.class);
		String resultado= "";
		
		try{
			crud.wsupdate_company(company);
			resultado="La compañía ya existe y se ha modificado";
			response=ls.genResponse(resultado);
		}
		catch(Exception e){
			resultado="Problemas al actualizar compañia";
			response=ls.genResponse(resultado);
			
		}

		System.out.println(resultado);
		return response;
    }
	
	
	@Path("/delUser/{email}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delUser(@PathParam("email") String email) throws UnknownHostException{
		
		String resultado="";
		try{
		crud.wsdelete_user(email);
		resultado="Usuario eliminado OK";
		response=ls.genResponse(resultado);
		
		}catch(Exception e){
			System.out.println("Problemoooooo... Excepcion: "+e.getMessage());
			resultado = ("Problemas al eliminar usuario. Excepción: "+e.getMessage());
			response=ls.genResponse(resultado);
		}
		
		return response;
	
    	
    }
	
	@Path("/delCompany/{name}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delCompany(@PathParam("name") String name) throws UnknownHostException{
		
		String resultado="";
		try{
		crud.wsdelete_company(name);
		resultado="Company eliminada OK";
		response=ls.genResponse(resultado);
		}catch(Exception e){
			System.out.println("Problemoooooo... Excepcion: "+e.getMessage());
			resultado = ("Problemas al eliminar company. Excepción: "+e.getMessage());
			response=ls.genResponse(resultado);
		}
		
		return response;
	
    	
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void modificaItem(String listObjetos) throws UnknownHostException{


    	
    }
    
    /**************************CONTROLLER***************************************/
    
	@Path("/getInfoController")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoController() throws UnknownHostException {
		//crea objeto test
		//ListEdgeProperties lep = ls.creaObjetoTest();
		String objetoEnJson=httpcliente.getNodes();
		final ListEdgeProperties lep = gson.fromJson(objetoEnJson, ListEdgeProperties.class);
		//objetoEnJson = gson.toJson(lep);
		WSListObjects wslo = lep.genListPurged();
		try{
			objetoEnJson = gson.toJson(wslo);
			System.out.println(objetoEnJson);
			response = ls.genResponse(objetoEnJson);
			
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			response = ls.genResponse(objetoEnJson);
		}
		System.out.println(response.toString());
		return(response);	
    }

	@Path("/getTopologyController")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopologyController() throws UnknownHostException {
		//crea objeto test
		//ListEdgeProperties lep = ls.creaObjetoTest();
		String objetoEnJson=httpcliente.getNodes();
		final ListEdgeProperties lep = gson.fromJson(objetoEnJson, ListEdgeProperties.class);
		//objetoEnJson = gson.toJson(lep);
		Topology top = lep.genListPurgedTop();
		try{
			objetoEnJson = gson.toJson(top);
			System.out.println(objetoEnJson);
			response = ls.genResponse(objetoEnJson);
			
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			response = ls.genResponse(objetoEnJson);
		}
		System.out.println(response.toString());
		return(response);	
    }
	
	
	
	@Path("/getStatsController")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatiscticsController() throws UnknownHostException {
		//crea objeto test
		//ListEdgeProperties lep = ls.creaObjetoTest();
		String objetoEnJson=httpcliente.getStatistics();
		
		//
		////WSListObjects wslo = lep.genListPurged();
		try{
			
			final ListPortStatistics lps = gson.fromJson(objetoEnJson, ListPortStatistics.class);
			objetoEnJson = gson.toJson(lps.genListPurged());
			response = ls.genResponse(objetoEnJson);
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			response = ls.genResponse(objetoEnJson);
		}
		System.out.println(response.toString());
		return(response);	
    }

	
	
	@Path("/addFlow")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFlow(String json) throws UnknownHostException {
		//crea objeto test
		//ListEdgeProperties lep = ls.creaObjetoTest();
		String respuesta;
		
		respuesta=null;
		try{
			System.out.println(json);
			final Flow flow = gson.fromJson(json, Flow.class);
			respuesta=httpcliente.addflowController(flow.getNode().getId(),flow.getName(),flow);
			response = ls.genResponse(respuesta);
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			respuesta="error";
			response = ls.genResponse(respuesta);
		}
		//System.out.println(response.toString());
		return(response);	
    }
	
	@Path("/getFlows")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlows() throws UnknownHostException {
		//crea objeto test
		//ListEdgeProperties lep = ls.creaObjetoTest();
		String objetoEnJson;

		try{
			
			objetoEnJson=httpcliente.getFlows();
			System.out.println(objetoEnJson.toString());
			final ConfigObject co = gson.fromJson(objetoEnJson, ConfigObject.class);
			objetoEnJson = gson.toJson(co.genListPurged());
			
			response = ls.genResponse(objetoEnJson);
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			response = ls.genResponse(objetoEnJson);
		}
		System.out.println(response.toString());
		return(response);	
    }
	
	@Path("/getNodesController")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNodesController() throws UnknownHostException {

		String objetoEnJson;

		try{
			
			objetoEnJson=httpcliente.getNodesController();
			final Nodes nodes = gson.fromJson(objetoEnJson, Nodes.class);

			objetoEnJson = gson.toJson(nodes.genListPurged());
			
			response = ls.genResponse(objetoEnJson);
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			response = ls.genResponse(objetoEnJson);
		}
		System.out.println(response.toString());
		return(response);	
    }
	
	@Path("/delFlow/{mac}/{name}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
    public Response delFlow(@PathParam("mac") String mac,@PathParam("name") String name) throws UnknownHostException {
		//crea objeto test
		//ListEdgeProperties lep = ls.creaObjetoTest();
		System.out.println("recibidos: "+mac+ " y "+name);
		StatusLine sl=null;
		String respuesta;
		try{
			sl=httpcliente.delFlow(mac,name);
			System.out.println("Respuesta HTTP del controller: "+sl.getStatusCode());
			//objetoEnJson+="Eliminado OK";
			//final ListPortStatistics lps = gson.fromJson(objetoEnJson, ListPortStatistics.class);
			//objetoEnJson = gson.toJson(lps.genListPurged());
			response = ls.genResponseInt(sl.getStatusCode());
			
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			response = ls.genResponseInt(-1);
		}
		
		return(response);	
    }
/*********************************NODES******************************************/
	
	@Path("/addNode")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertNode(String json) throws UnknownHostException{
		System.out.println(json);
		String resultado= "";
		try{
			final NodeWS node = gson.fromJson(json, NodeWS.class);
			
			//compruebo si la compañia existe
			if(crud.node_exists(node)==true){
				resultado="El Nodo ya existe!!";
				response = ls.genResponse(resultado);
				
			}
			else{
				
				long id=crud.wsadd_node(node);
				resultado = "Nodo creado con éxito con id: "+id;
				response = ls.genResponse(resultado);
			}
		}catch(Exception e){
			
			System.out.println("Oh Oh .. problema.. Excepción: "+e.getMessage());
			//response = ls.genResponse(e.getMessage());
			
		}
		
		System.out.println(resultado);
		return response;
		
	}

	@Path("/updateNode")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNode(String json) throws UnknownHostException{
		final NodeWS node = gson.fromJson(json, NodeWS.class);
		String resultado= "";
		
		try{
			crud.wsupdate_node(node);
			resultado="El nodo existe y se ha modificado";
			response=ls.genResponse(resultado);
		}
		catch(Exception e){
			resultado="Problemas al actualizar nodo";
			response=ls.genResponse(resultado);
			
		}

		System.out.println(resultado);
		return response;
    }

	@Path("/delNode/{name}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delNode(@PathParam("name") String name) throws UnknownHostException{
		
		String resultado="";
		try{
			crud.wsdelete_node(name);
			resultado="Nodo eliminado OK";
			response=ls.genResponse(resultado);
		}catch(Exception e){
			System.out.println("Problemoooooo... Excepcion: "+e.getMessage());
			resultado="Problema al eliminar el nodo. Excepción: "+e.getMessage();
			response=ls.genResponse(resultado);
		}
		
		return response;
	
    	
    }

	@Path("/getNodeByName/{name}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNodeByName(@PathParam("name") String name) throws UnknownHostException {
		String objetoEnJson;
		
		try{
			NodeWS node = null;
			node=crud.wsread_node(name);
			objetoEnJson=gson.toJson(node);
			response = ls.genResponse(objetoEnJson);			
			
		}
		catch(Exception e){
			System.out.println("problema en crud.wsread_node.. Excepcion: "+e.getMessage());
			objetoEnJson="problema con GSON, excepción: "+e.getMessage();
			response = ls.genResponse(objetoEnJson);
		}
		
		
		return response;
	   
	}
	
	@Path("/getNodesFromCompany/{company}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNodesFromCompany(@PathParam("company") String comp) throws UnknownHostException {
		
		
		
		ListNodesFromCompany listaws = new ListNodesFromCompany();
			
		
		String objetoEnJson;
		try{
			List<Node> lista = crud.wsnode_list(comp);
			objetoEnJson=gson.toJson(listaws.getList(lista));
			response = ls.genResponse(objetoEnJson);
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			response=ls.genResponse(objetoEnJson);
			
		}
		
		return(response);	
    }

	
	@Path("/getGraph/{node_name}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getgraph(@PathParam("node_name") String node_name) throws UnknownHostException {
		

		String mensaje ="Consulta sobre grafica: "+node_name;
		System.out.println(mensaje);
		String result="";
		
		try{ //genero la grafica
			
			result = rrdtool.genGraph(node_name);
			response=ls.genResponse(result);

		}
		catch(Exception e){
			System.out.println("problemas.. Excepcion: "+e.getMessage());
		}
		
		
		return(response);    	
    }

}
