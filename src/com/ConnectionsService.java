package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Connections;
@Path("/Connections")


public class ConnectionsService {
	
	
	Connections connectionsObj = new Connections();

	 
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return connectionsObj.readConnections();
	 }
	
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertConnections(
		
  @FormParam("conName") String conName,
 @FormParam("conType") String conType,
 @FormParam("conDesc") String conDesc,		
 @FormParam("conAdminId") String conAdminId)
{
 String output = connectionsObj.insertConnections( conName,  conType, conDesc,conAdminId);
return output;
}
	

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateConnections(String sData)
{
//Convert the input string to a JSON object
 JsonObject connectionsObject = new JsonParser().parse(sData).getAsJsonObject();
//Read the values from the JSON object
 String conID = connectionsObject.get("conID").getAsString();
 String conName = connectionsObject.get("conName").getAsString();
 String conType = connectionsObject.get("conType").getAsString();
 String conDesc = connectionsObject.get("conDesc").getAsString();
 String conAdminId = connectionsObject.get("conAdminId").getAsString();
 String output = connectionsObj.updateConnections( conID, conName,  conType,  conDesc, conAdminId);
return output;
}



@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String sData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(sData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String conID = doc.select("conID").text();
 String output = connectionsObj.deleteConnections(conID);
return output;
}
}
