package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

import java.net.UnknownHostException;

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
	

	@Path("/id/{idUser}")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt2(@PathParam("idUser") int idUser) throws UnknownHostException {
		
		
		String mensaje="Consulta sobre el usuario con id: "+idUser;
		return mensaje;
    	
    	
    }

	@Path("/addUser")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertUser(User user) throws UnknownHostException{
		
		Company comp=user.getCompany();
		
		
		//Company comp=user.getCompany();
		//System.out.println("La compañia es: "+comp.getCompany_name());
		//comp.addUsuario(user);
		//user.setCompany(comp);
		//crud.create_user(user);	
		
		
		return comp.getAddress();//gson.toJson(user);
		

    }
	
	@Path("/addCompany")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertCompany(Company comp) throws UnknownHostException{
		
		//return "hellooo";
		crud.create_company(comp);
		
		
		return "Empresa añadida: "+comp.getCompany_name();
		

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
