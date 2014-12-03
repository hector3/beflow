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
	
	@Path("/id/{idUser}")
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt2(@PathParam("idUser") int idUser) throws UnknownHostException {
		
		
		String mensaje="Consulta sobre el usuario con id: "+idUser;
		return mensaje;
    	
    	
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
		
		
		/*Company comp=crud.read_company(user.getName_company());
		System.out.println("Compañia: "+comp.getCompany_name());
		System.out.println("Direccion: "+comp.getAddress());
		User userH = new User(user.getLogin(),user.getPassword(),user.getRole(),user.getName(),user.getPhone(),user.getDepartment());
		*/
		
		//comp.addUsuario(userH);
		//userH.setCompany(comp);
	  	//crud.update_company(comp);
	  	//String resp="Usuario añadido con exito: "+user.getName();
	  	
		
		//LIDIA
		Company comp= crud.read_company(user.getName_company());
		crud.wsadd_user(comp,user);
		
		return "h";
		
		
		
		
		

    }
	
	@Path("/addCompany")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertCompany(String json) throws UnknownHostException{
		
		//return "hellooo";
		final CompanyWS company = gson.fromJson(json, CompanyWS.class);
		Company comp = new Company(company.getCompany_name(),company.getAddress(),company.getLeader());
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