package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

import java.net.UnknownHostException;

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
	
	@Path("/mail/{email}")
	@GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt2(@PathParam("email") String email) throws UnknownHostException {
		
		
		String mensaje="Consulta sobre el usuario con mail: "+email;
		System.out.println(mensaje);
		User user = null;
		try{
			user=crud.read_user(email);
			System.out.println("Me lega el correo: "+email);
			System.out.println("Del objeto saco departamento: "+user.getDepartment());
			//Userws userws = new UserWS(0, user.getLogin(), user.getPassword(), user.getRole(), user.getName(), user.getPhone(), user.getDepartment(), user.getCompany());
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
//		return gson.toJson(user);
		
    	
    	
    }

	@Path("/login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String checkLogin(String json) throws UnknownHostException{
		
		final CredWS cred1 = gson.fromJson(json, CredWS.class);
		
		
		String resp = "estoy en login";
//		String resp="No entra a ningun if";
//		User user=crud.read_user(cred.getLogin());
//		if((user.getLogin()==cred.getLogin())&&(user.getPassword()==cred.getPass()))
//			{
//			resp="Credenciales correctas. Devuelvo una url";
//			}
//		else if((user.getLogin()==cred.getLogin())&&(user.getPassword()!=cred.getPass()))
//			{
//			resp="Credenciales incorrectas. La contraseña no es correcta pero el login si";
//			}
//		else
//			{
//			resp="Error en la consulta";
//			}
//		
		
		
		return cred1.getLogin();
		

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
		long id=0;
		if (crud.user_exists(user)){
			try{
			crud.wsupdate_user(user);			
			resultado="El usuario ya existe y se ha modificado";
			}catch(Exception e){
				System.out.println("problemas.. Excepcion: "+e.getMessage());
				resultado="Ha habido problemas";
			}
			
		}
		else{
			
			resultado="El usuario no existe en la base de datos. Créalo.";
		}
		
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
	
	
	@Path("/delUser")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delUser(String s) throws UnknownHostException{
			
		User user=crud.read_user(s);
		System.out.println("user eliminado: "+user.getName());
		return ("User eliminado: ");
    	
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void modificaItem(String listObjetos) throws UnknownHostException{


    	
    }
}