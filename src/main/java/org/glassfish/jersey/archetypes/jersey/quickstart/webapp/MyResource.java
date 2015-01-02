package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

import httpclient.HttpCliente;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import wsPojoController.WSListObjects;
import wsPojoStats.ListWsStats;
import wsPojoStats.WsObjectStats;
import wsobjects.*;
import controller.*;

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

import pojoController.ListEdgeProperties;
import pojoStats.ListPortStatistics;

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
	HttpCliente httpcliente= new HttpCliente();
	
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
		
		
		return "Hello jersey";
    	
    	
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
    public int checkLogin(String json) throws UnknownHostException{
		
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
		
		return codresp;
		

    }	
	
	
	@Path("/addUser")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertUser(String json) throws UnknownHostException{
		
		final UserWS user = gson.fromJson(json, UserWS.class);
		String resultado="";
		long id=0;
		if (crud.user_exists(user)){
			resultado="El usuario ya existe modifícalo si así lo deseas";
		}
		else{
			id=crud.wsadd_user(user);
			resultado="Usuario añadido correctamente a la compañía: "+user.getName_company()+" con id "+ id;
		}
		
		return resultado;
	}

	
	@Path("/updateUser")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateUser(String json) throws UnknownHostException{
		
		final UserWS user = gson.fromJson(json, UserWS.class);
		String resultado="";
		//long id=0;
		//if (crud.user_exists(user)){
			try{
			crud.wsupdate_user(user);			
			resultado="El usuario ya existe y se ha modificado";
			}catch(Exception e){
				System.out.println("problemas.. Excepcion: "+e.getMessage());
				resultado="Ha habido problemas";
			}
		
		//else{
			
		//	resultado="El usuario no existe en la base de datos. Créalo.";
		//}
		
		return resultado;
	}
	@Path("/addCompany")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertCompany(String json) throws UnknownHostException{
		final CompanyWS company = gson.fromJson(json, CompanyWS.class);
		String resultado= "";
		//compruebo si la compañia existe
		if(crud.company_exists(company)==true){
			resultado="La compañía ya existe!!";
			
		}
		else{
			
			long id=crud.create_company(company);
			resultado = "La compañía es nueva y se crea con id "+id;
		}
		System.out.println(resultado);
		return resultado;
    }
	
	@Path("/updateCompany")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateCompany(String json) throws UnknownHostException{
		final CompanyWS company = gson.fromJson(json, CompanyWS.class);
		String resultado= "";
		
		try{
			crud.wsupdate_company(company);
			resultado="La compañía ya existe y se ha modificado";
		}
		catch(Exception e){
			resultado="Problemas al actualizar compañia";
			
		}

		System.out.println(resultado);
		return resultado;
    }
	
	
	@Path("/delUser/{email}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delUser(@PathParam("email") String email) throws UnknownHostException{
		
		String resultado="";
		try{
		crud.wsdelete_user(email);
		resultado="Usuario eliminado OK";
		}catch(Exception e){
			System.out.println("Problemoooooo... Excepcion: "+e.getMessage());
			resultado="nanai";
		}
		
		return resultado;
	
    	
    }
	
	@Path("/delCompany/{name}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delCompany(@PathParam("name") String name) throws UnknownHostException{
		
		String resultado="";
		try{
		crud.wsdelete_company(name);
		resultado="Company eliminada OK";
		}catch(Exception e){
			System.out.println("Problemoooooo... Excepcion: "+e.getMessage());
			resultado="Problemoooooo... Excepcion: "+e.getMessage();
		}
		
		return resultado;
	
    	
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


}