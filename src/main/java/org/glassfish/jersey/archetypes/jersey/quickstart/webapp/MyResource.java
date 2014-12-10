package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

import java.net.UnknownHostException;
import java.util.List;

import wsobjects.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {
	
	
	CRUD crud=new CRUD();
	Gson gson = new Gson();
	
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
    public String getIt2(@PathParam("email") String email) throws UnknownHostException {
		
		
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
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			
		}
		
		return(objetoEnJson);

		
    	
    	
    }

	
	@Path("/getAllCompanies")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getallCompanies() throws UnknownHostException {
		
		List<Company> lista = crud.company_list();
		
		try{
			
			for (Company tempComp : lista) {
				
				 System.out.println(tempComp.getCompany_name());
				 System.out.println(tempComp.getLeader());
				 System.out.println(tempComp.getAddress());
				}
		}
		
		
		catch(Exception e){
			System.out.println("problemas.. Excepcion: "+e.getMessage());
		}
		String objetoEnJson;
		try{
			objetoEnJson=gson.toJson(lista);
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			
		}
		
		return("Hola");
		//return(objetoEnJson);	
    }
	
	
	
	@Path("/getCompany/{name}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompany(@PathParam("name") String name) throws UnknownHostException {
		
		
		String mensaje="Consulta sobre la compañia con nombre: "+name;
		CompanyWS comp = null;
		try{
			comp=crud.wsread_company(name);//quan modifiquis el crud s'arregla l'error
			Company com = new Company();
			
		}
		catch(Exception e){
			System.out.println("problemas.. Excepcion: "+e.getMessage());
		}
		String objetoEnJson;
		try{
			objetoEnJson=gson.toJson(comp);
		}catch(Exception e){
			System.out.println("problema con GSON, excepción: "+e.getMessage());
			objetoEnJson="error";
			
		}
		
		return(objetoEnJson);
	   
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
			resultado="nanai";
		}
		
		return resultado;
	
    	
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void modificaItem(String listObjetos) throws UnknownHostException{


    	
    }
}