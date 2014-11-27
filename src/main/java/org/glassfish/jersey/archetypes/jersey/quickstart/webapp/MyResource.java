package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {
	
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws UnknownHostException 
     */
    
	//ruta para acceder http://localhost:8000/jersey-quickstart-webapp/webapi/myresource
	
	@GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(String s) throws UnknownHostException {
    	return "Hello Jersey!!";
    }
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertItem(String s) throws UnknownHostException{

    }
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delItem(String s) throws UnknownHostException{
   	
    	
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void modificaItemMongo(String listObjetos) throws UnknownHostException{


    	
    }
}
