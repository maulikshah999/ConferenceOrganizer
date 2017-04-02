package com.csueb.ds.webservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csueb.ds.database.MongoDBConnection;
import com.csueb.ds.models.Conference;

@Path("/conference")
public class WSConference {
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createConferenceJSON(Conference conference){
	
		String result = "Conference successfully added." + conference;
		return Response.status(201).entity(result).build();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConferenceJSON() throws Exception{
		
		List<Conference> confList = MongoDBConnection.getInstance().findAllConference();		
		return Response.status(201).entity(confList).build();
		
	}
	
	@GET
	@Path("/res")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConferenceResJSON(){
		
		/*MongoDBConnection mongodb = MongoDBConnection.getInstance();
		try {			
			mongodb.checkConnectionDB();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		return Response.status(201).entity("hello rane").build();
	}
}
